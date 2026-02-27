package grasspow.extrabotany.common.entity.ego;

import com.google.common.collect.ImmutableList;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.equipment.weapon.*;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.entity.GaiaGuardianEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.helper.MathHelper;
import vazkii.botania.common.helper.VecHelper;
import vazkii.botania.common.lib.BotaniaTags;
import vazkii.botania.common.proxy.Proxy;

import java.util.*;

import static vazkii.botania.common.helper.PlayerHelper.isTruePlayer;

public class EGO extends GaiaGuardianEntity {
    public static final float ARENA_RANGE = 12F;
    public static final int ARENA_HEIGHT = 5;
    public static final float MAX_HP = 600F;
    private static final int SPAWN_TICKS = 160;

    private static final String TAG_SOURCE_X = "sourceX";
    private static final String TAG_SOURCE_Y = "sourceY";
    private static final String TAG_SOURCE_Z = "sourcesZ";

    private static final String TAG_PLAYER_COUNT = "playerCount";
    private static final String TAG_STAGE = "stage";
    private static final String TAG_WEAPON_TYPE = "weapon_type";
    private static final String TAG_TP_DELAY = "tpDelay";
    private static final String TAG_ATTACK_DELAY = "attackDelay";
    private static final String TAG_INVUL_TIME = "invulTime";
    private static final int DAMAGE_CAP = 25;
    private static final TagKey<Block> BLACKLIST = BotaniaTags.Blocks.GAIA_GUARDIAN_IMMUNE;
    private static final EntityDataAccessor<Integer> INVUL_TIME = SynchedEntityData.defineId(EGO.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STAGE = SynchedEntityData.defineId(EGO.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> WEAPON_TYPE = SynchedEntityData.defineId(EGO.class, EntityDataSerializers.INT);

    private static final List<BlockPos> PYLON_LOCATIONS = ImmutableList.of(
            new BlockPos(4, 1, 4),
            new BlockPos(4, 1, -4),
            new BlockPos(-4, 1, 4),
            new BlockPos(-4, 1, -4)
    );

//    private static final List<ResourceLocation> CHEATY_BLOCKS = Arrays.asList(
//            new ResourceLocation("openblocks", "beartrap"),
//            new ResourceLocation("thaumictinkerer", "magnet")
//    );

    private float damageTaken = 0;
    private int tpDelay = 0;
    private int changeWeaponDelay = 0;
    private int attackDelay = 0;
    private int playerCount = 0;
    private BlockPos source = BlockPos.ZERO;
    private final List<UUID> playersWhoAttacked = new ArrayList<>();
//    private final ServerBossEvent bossInfo = (ServerBossEvent) new ServerBossEvent(ExtraBotanyEntities.EGO.get().getDescription(), BossEvent.BossBarColor.PINK, BossEvent.BossBarOverlay.PROGRESS).setCreateWorldFog(true);
//    private UUID bossInfoUUID = bossInfo.getId();

    public Player trueKiller = null;

    private final int MAX_WAVE = 6;
    private int wave = 0;
    private int tpTimes = 0;
    private final Integer[] waves = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};

    public EGO(EntityType<EGO> type, Level level) {
        super(type, level);
        xpReward = 825;
        if (level.isClientSide) {
            Proxy.INSTANCE.addBoss(this);
        }
    }

//    public static boolean spawn(Player player, ItemStack stack, Level level, BlockPos pos) {
//        //initial checks
//        if (!(level.getBlockEntity(pos) instanceof BeaconBlockEntity) ||
//                !isTruePlayer(player) ||
//                countEGOAround(level, pos) > 0)
//            return false;
//
//        if (!checkInventory(player)) {
//            if (!level.isClientSide)
//                player.sendSystemMessage(Component.translatable("extrabotanymisc.inventoryUnfeasible").withStyle(ChatFormatting.RED));
//            return false;
//        }
//
//        //check difficulty
//        if (level.getDifficulty() == Difficulty.PEACEFUL) {
//            if (!level.isClientSide) {
//                player.sendSystemMessage(Component.translatable("botaniamisc.peacefulNoob").withStyle(ChatFormatting.RED));
//            }
//            return false;
//        }
//
//        //check pylons
//        List<BlockPos> invalidPylonBlocks = checkPylons(level, pos);
//        if (!invalidPylonBlocks.isEmpty()) {
//            if (level.isClientSide) {
//                warnInvalidBlocks(level, invalidPylonBlocks);
//            } else {
//                player.sendSystemMessage(Component.translatable("botaniamisc.needsCatalysts").withStyle(ChatFormatting.RED));
//            }
//
//            return false;
//        }
//
//        //check arena shape
//        List<BlockPos> invalidArenaBlocks = checkArena(level, pos);
//        if (!invalidArenaBlocks.isEmpty()) {
//            if (level.isClientSide) {
//                warnInvalidBlocks(level, invalidArenaBlocks);
//            } else {
//                XplatAbstractions.INSTANCE.sendToPlayer(player, new BotaniaEffectPacket(EffectType.ARENA_INDICATOR, pos.getX(), pos.getY(), pos.getZ()));
//
//                player.sendSystemMessage(Component.translatable("botaniamisc.badArena").withStyle(ChatFormatting.RED));
//            }
//
//            return false;
//        }
//        var orb = ClientXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
//        if (orb != null) {
//            if (orb.getNature() < 200000 || !orb.canExportManaToPool(level.getBlockEntity(pos)))
//                return false;
//        }
//
//        //all checks ok, spawn the boss
//        if (!level.isClientSide) {
//            if (orb != null) {
//                orb.addNature(-200000);
//            } else {
//                stack.shrink(1);
//            }
//
//            EGO e = ExtraBotanyEntities.EGO.get().create(level);
//            e.tpDelay = SPAWN_TICKS;
//            e.setPos(pos.getX() + 0.5, pos.getY() + 3, pos.getZ() + 0.5);
//            e.setWeaponType(0);
//            e.setCustomName(player.getDisplayName());
//            e.setHealth(e.getMaxHealth());
//            e.source = pos;
//
//            int playerCount = e.getPlayersAround().size();
//            e.playerCount = playerCount;
//            e.setInvulTime(0);
//            e.setAttackDelay(100);
//            e.setTpDelay(160);
//            float healthMultiplier = 0.4F + playerCount * 0.6F;
//            e.getAttribute(Attributes.MAX_HEALTH).setBaseValue(MAX_HP * healthMultiplier);
//            e.setHealth(MAX_HP * healthMultiplier);
//
//            e.getAttribute(Attributes.ARMOR).setBaseValue(20);
//            e.playSound(BotaniaSounds.gaiaSummon, 1F, 1F);
//            e.finalizeSpawn((ServerLevelAccessor) level, level.getCurrentDifficultyAt(e.blockPosition()), MobSpawnType.EVENT, null, null);
//
//            level.addFreshEntity(e);
//        }
//
//        return true;
//    }

    private static List<BlockPos> checkPylons(Level level, BlockPos beaconPos) {
        List<BlockPos> invalidPylonBlocks = new ArrayList<>();

        for (BlockPos coords : PYLON_LOCATIONS) {
            BlockPos pos_ = beaconPos.offset(coords);

            BlockState state = level.getBlockState(pos_);
            if (!state.is(BotaniaBlocks.gaiaPylon)) {
                invalidPylonBlocks.add(pos_);
            }
        }
        return invalidPylonBlocks;
    }

    private static List<BlockPos> checkArena(Level level, BlockPos beaconPos) {
        List<BlockPos> trippedPositions = new ArrayList<>();
        int range = (int) Math.ceil(ARENA_RANGE);
        BlockPos pos;

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                if (Math.abs(x) == 4 && Math.abs(z) == 4 || MathHelper.pointDistancePlane(x, z, 0, 0) > ARENA_RANGE) {
                    continue; // Ignore pylons and out of circle
                }

                boolean hasFloor = false;

                for (int y = -2; y <= ARENA_HEIGHT; y++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue; //the beacon
                    }

                    pos = beaconPos.offset(x, y, z);

                    BlockState state = level.getBlockState(pos);

                    boolean allowBlockHere = y < 0;
                    boolean isBlockHere = !state.getCollisionShape(level, pos).isEmpty();

                    if (allowBlockHere && isBlockHere) //floor is here! good
                    {
                        hasFloor = true;
                    }

                    if (y == 0 && !hasFloor) //column is entirely missing floor
                    {
                        trippedPositions.add(pos.below());
                    }

                    if (!allowBlockHere && isBlockHere && !state.is(BLACKLIST)) //ceiling is obstructed in this column
                    {
                        trippedPositions.add(pos);
                    }
                }
            }
        }

        return trippedPositions;
    }

    private static void warnInvalidBlocks(Level world, Iterable<BlockPos> invalidPositions) {
        WispParticleData data = WispParticleData.wisp(0.5F, 1, 0.2F, 0.2F, 8, false);
        for (BlockPos pos_ : invalidPositions) {
            world.addParticle(data, pos_.getX() + 0.5, pos_.getY() + 0.5, pos_.getZ() + 0.5, 0, 0, 0);
        }
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(2, new FloatGoal(this));
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, ARENA_RANGE * 1.5F));
    }

//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        entityData.define(INVUL_TIME, 0);
//        entityData.define(STAGE, 0);
//        entityData.define(WEAPON_TYPE, 0);
//    }

    @Override
    public void addAdditionalSaveData(CompoundTag cmp) {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_TP_DELAY, tpDelay);
        cmp.putInt(TAG_ATTACK_DELAY, attackDelay);
        cmp.putInt(TAG_SOURCE_X, source.getX());
        cmp.putInt(TAG_SOURCE_Y, source.getY());
        cmp.putInt(TAG_SOURCE_Z, source.getZ());
        cmp.putInt(TAG_PLAYER_COUNT, playerCount);

        cmp.putInt(TAG_INVUL_TIME, getInvulTime());
        cmp.putInt(TAG_STAGE, getStage());
        cmp.putInt(TAG_WEAPON_TYPE, getWeaponType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag cmp) {
        super.readAdditionalSaveData(cmp);
        int x = cmp.getInt(TAG_SOURCE_X);
        int y = cmp.getInt(TAG_SOURCE_Y);
        int z = cmp.getInt(TAG_SOURCE_Z);
        source = new BlockPos(x, y, z);
        tpDelay = cmp.getInt(TAG_TP_DELAY);
        attackDelay = cmp.getInt(TAG_ATTACK_DELAY);
        playerCount = cmp.contains(TAG_PLAYER_COUNT) ? cmp.getInt(TAG_PLAYER_COUNT) : 1;
        if (this.hasCustomName()) {
//            this.bossInfo.setName(this.getDisplayName());
        }
        setInvulTime(cmp.getInt(TAG_INVUL_TIME));
        setStage(cmp.getInt(TAG_STAGE));
        setWeaponType(cmp.getInt(TAG_WEAPON_TYPE));
    }

    @Override
    public void setCustomName(Component name) {
        super.setCustomName(name);
//        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity e = source.getEntity();
        if (e instanceof Player player && isTruePlayer(e) && getInvulTime() == 0) {
            if (!playersWhoAttacked.contains(player.getUUID())) {
                playersWhoAttacked.add(player.getUUID());
            }
            float dmg = Math.min(DAMAGE_CAP, amount);
            damageTaken += dmg;
            if (damageTaken >= 50) {
                damageTaken = 0;
                teleportRandomly();
                if (attackDelay >= 20) {
                    attackDelay /= 2;
                } else {
                    attackDelay -= 10;
                }
            }
            return super.hurt(source, dmg);
        }
        return false;
    }

    @Override
    public void die(DamageSource source) {
        setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        super.die(source);
        LivingEntity lastAttacker = getKillCredit();

        if (!level().isClientSide) {
            for (UUID u : playersWhoAttacked) {
                Player player = level().getPlayerByUUID(u);
                if (!isTruePlayer(player)) {
                    continue;
                }
                DamageSource currSource = player == lastAttacker ? source : player.damageSources().generic();
                if (player != lastAttacker) {
                    // Vanilla handles this in attack code, but only for the killer
                    CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger((ServerPlayer) player, this, currSource);
                }
            }

            // Clear wither from nearby players
            for (Player player : getPlayersAround()) {
                if (player.getEffect(MobEffects.WITHER) != null) {
                    player.removeEffect(MobEffects.WITHER);
                }
            }
            for (EGOLandmine landmine : level().getEntitiesOfClass(EGOLandmine.class, getArenaBB(getSource()))) {
                landmine.discard();
            }

            for (EGOMinion minion : level().getEntitiesOfClass(EGOMinion.class, getArenaBB(getSource()))) {
                minion.spawnAnim();
                minion.discard();
            }

        }
//        playSound(SoundEvents.GENERIC_EXPLODE, 5F, (1F + (level().random.nextFloat() - level().random.nextFloat()) * 0.2F) * 0.7F);
        level().addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ(), 1D, 0D, 0D);

        //playSound(ModSounds.gaiaDeath, 1F, (1F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
//        return resId("entities/ego");
        return super.getDefaultLootTable();
    }

    @Override
    protected void dropFromLootTable(DamageSource source, boolean wasRecentlyHit) {
        // Save true killer, they get extra loot
        if (wasRecentlyHit && isTruePlayer(source.getEntity())) {
            trueKiller = (Player) source.getEntity();
        }
        // Generate loot table for every single attacking player
        for (UUID u : playersWhoAttacked) {
            Player player = level().getPlayerByUUID(u);
            if (!isTruePlayer(player)) {
                continue;
            }
            Player saveLastAttacker = lastHurtByPlayer;
            Vec3 savePos = position();
            lastHurtByPlayer = player; // Fake attacking player as the killer
            // Spoof pos so drops spawn at the player
            setPos(player.getX(), player.getY(), player.getZ());
            super.dropFromLootTable(player.damageSources().generic(), wasRecentlyHit);
            setPos(savePos.x(), savePos.y(), savePos.z());
            lastHurtByPlayer = saveLastAttacker;
        }

        trueKiller = null;
    }

    public List<Player> getPlayersAround() {
        return level().getEntitiesOfClass(Player.class, getArenaBB(source), player -> isTruePlayer(player) && !player.isSpectator());
    }

    private static int countEGOAround(Level level, BlockPos source) {
        List<EGO> l = level.getEntitiesOfClass(EGO.class, getArenaBB(source));
        return l.size();
    }

    private static AABB getArenaBB(BlockPos source) {
        double range = ARENA_RANGE + 3D;
        return new AABB(source.getX() + 0.5 - range, source.getY() + 0.5 - range, source.getZ() + 0.5 - range, source.getX() + 0.5 + range, source.getY() + 0.5 + range, source.getZ() + 0.5 + range);
    }

    private static int countEGOMinionAround(Level level, BlockPos source) {
        List<EGOMinion> l = level.getEntitiesOfClass(EGOMinion.class, getArenaBB(source));
        return l.size();
    }

    private void particles() {
        for (int i = 0; i < 360; i += 8) {
            float r = 0.6F;
            float g = 0F;
            float b = 0.2F;
            float m = 0.15F;
            float mv = 0.35F;

            float rad = i * (float) Math.PI / 180F;
            double x = source.getX() + 0.5 - Math.cos(rad) * ARENA_RANGE;
            double y = source.getY() + 0.5;
            double z = source.getZ() + 0.5 - Math.sin(rad) * ARENA_RANGE;

            WispParticleData data = WispParticleData.wisp(0.5F, r, g, b);
            level().addParticle(data, x, y, z, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * mv, (float) (Math.random() - 0.5F) * m);
        }

        if (getInvulTime() > 10) {
            Vec3 pos = VecHelper.fromEntityCenter(this).subtract(0, 0.2, 0);
            for (BlockPos arr : PYLON_LOCATIONS) {
                Vec3 pylonPos = new Vec3(source.getX() + arr.getX(), source.getY() + arr.getY(), source.getZ() + arr.getZ());
                double worldTime = tickCount;
                worldTime /= 5;

                float rad = 0.75F + (float) Math.random() * 0.05F;
                double xp = pylonPos.x + 0.5 + Math.cos(worldTime) * rad;
                double zp = pylonPos.z + 0.5 + Math.sin(worldTime) * rad;

                Vec3 partPos = new Vec3(xp, pylonPos.y, zp);
                Vec3 mot = pos.subtract(partPos).scale(0.04);

                float r = 0.7F + (float) Math.random() * 0.3F;
                float g = (float) Math.random() * 0.3F;
                float b = 0.7F + (float) Math.random() * 0.3F;

                WispParticleData data = WispParticleData.wisp(0.25F + (float) Math.random() * 0.1F, r, g, b, 1);
                level().addParticle(data, partPos.x, partPos.y, partPos.z, 0, -(-0.075F - (float) Math.random() * 0.015F), 0);
                WispParticleData data1 = WispParticleData.wisp(0.4F, r, g, b);
                level().addParticle(data1, partPos.x, partPos.y, partPos.z, (float) mot.x, (float) mot.y, (float) mot.z);
            }
        }
    }

    private void smashBlocksAround(int centerX, int centerY, int centerZ, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius + 1; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    int x = centerX + dx;
                    int y = centerY + dy;
                    int z = centerZ + dz;
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = level().getBlockState(pos);
                    Block block = state.getBlock();
                    if (state.getDestroySpeed(level(), pos) == -1)
                        continue;
//                    if (CHEATY_BLOCKS.contains(ForgeRegistries.BLOCKS.getKey(block))) {
//                        level().destroyBlock(pos, true);
//                    } else {
                        //don't break blacklisted blocks
                        if (state.is(BLACKLIST))
                            continue;
                        //don't break the floor
                        if (y < source.getY())
                            continue;
                        //don't break blocks in pylon columns
                        if (Math.abs(source.getX() - x) == 4 && Math.abs(source.getZ() - z) == 4)
                            continue;
                        level().destroyBlock(pos, true);
//                    }
                }
            }
        }
    }

    private void clearPotions(Player player) {
//        List<MobEffect> potionsToRemove = player.getActiveEffects().stream()
//                .filter(effect -> effect.getDuration() < 160 && effect.isAmbient() && effect.getEffect().getCategory() != MobEffectCategory.HARMFUL)
//                .map(MobEffectInstance::getEffect)
//                .distinct().toList();
//
//        potionsToRemove.forEach(potion ->
//        {
//            player.removeEffect(potion);
//            ((ServerLevel) level()).getChunkSource().broadcastAndSend(player,
//                    new ClientboundRemoveMobEffectPacket(player.getId(), potion));
//        });
    }

    private void keepInsideArena(Player player) {
        if (MathHelper.pointDistanceSpace(player.getX(), player.getY(), player.getZ(), source.getX() + 0.5, source.getY() + 0.5, source.getZ() + 0.5) >= ARENA_RANGE) {
            Vec3 sourceVector = new Vec3(source.getX() + 0.5, source.getY() + 0.5, source.getZ() + 0.5);
            Vec3 playerVector = VecHelper.fromEntityCenter(player);
            Vec3 motion = sourceVector.subtract(playerVector).normalize();

            player.setDeltaMovement(motion.x, 0.2, motion.z);
            player.hurtMarked = true;
        }
    }

    public static boolean checkFeasibility(ItemStack stack) {
        if (stack.isEmpty())
            return true;

//        String modid = stack.getItem().getCreatorModId(stack);
//        return modid.contains("extrabotany") || modid.contains("botania") || modid.contains("minecraft");
        return true;
    }

//    public static boolean checkInventory(Player player) {
//        if (!ConfigHandler.COMMON.disableDisarm.get()) {
//            for (int i = 0; i < player.getInventory().size(); i++) {
//                final ItemStack stack = player.getInventory().getItem(i);
//                if (!checkFeasibility(stack))
//                    return false;
//            }
//        }
//        return true;
//    }

    /**
     * drop item
     */
    public static void disarm(Player player) {
//        if (!ConfigHandler.COMMON.disableDisarm.get() && !player.isCreative()) {
//            for (int i = 0; i < player.getInventory().size(); i++) {
//                final ItemStack stack = player.getInventory().getItem(i);
//                if (!checkFeasibility(stack)) {
//                    player.drop(stack, false);
//                    player.getInventory().setItem(i, ItemStack.EMPTY);
//                }
//            }
//            for (int i = 0; i < EquipmentHandler.getAllWorn(player).size(); i++) {
//                final ItemStack stack = EquipmentHandler.getAllWorn(player).getItem(i);
//                if (!checkFeasibility(stack)) {
//                    player.drop(stack, false);
//                    EquipmentHandler.getAllWorn(player).setItem(i, ItemStack.EMPTY);
//                }
//            }
//        }
    }

    /**
     * disappear when player count increase
     */
    public void illegalPlayerCount() {
        if (getPlayersAround().size() > playerCount) {
            for (Player player : getPlayersAround())
                if (!level().isClientSide)
                    player.sendSystemMessage(Component.translatable("extrabotany.misc.unlegalPlayercount").withStyle(ChatFormatting.RED));
            discard();
        }
    }

    /**
     * attack
     */
    public boolean tryAttack() {
        if (getPlayersAround().isEmpty())
            return false;

        Entity target = getPlayersAround().get(0);
        this.swing(InteractionHand.MAIN_HAND, true);
        if (!level().isClientSide) {
            switch (getWeaponType()) {
                case 0: {
                    ((TrueShadowKatanaItem) ExtraBotanyItems.trueShadowKatana).attack(this, target);
                    break;
                }
                case 1: {
                    ((TrueTerraBladeItem) ExtraBotanyItems.trueTerraBlade).attack(this, target);
                    break;
                }
                case 2: {
                    ((InfluxWaverItem) ExtraBotanyItems.influxWaver).attack(this, target);
                    break;
                }
                case 3: {
                    ((StarWrathItem) ExtraBotanyItems.starWrath).attack(this, target);
                    break;
                }
                case 4: {
                    ((FirstFractalItem) ExtraBotanyItems.firstFractal).attack(this, target);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        int invul = getInvulTime();

        List<Integer> WAVES = Arrays.asList(waves);

        List<Player> players = getPlayersAround();

        if (players.isEmpty() && !level().players().isEmpty()) {
            discard();
        } else {
            for (Player player : players) {
                //also see SleepingHandler
                if (player.isSleeping()) {
                    player.stopSleeping();
                }

                if (!level().isClientSide) {
                    clearPotions(player);
                    keepInsideArena(player);
                    player.getAbilities().flying &= player.isCreative();
                }
            }
        }

        if (!isAlive() || players.isEmpty()) {
            return;
        }

        if (level().isClientSide) {
            particles();
            Player player = Proxy.INSTANCE.getClientPlayer();
            if (getPlayersAround().contains(player)) {
                player.getAbilities().flying &= player.isCreative();//disable flight
            }
            return;
        }

//        bossInfo.setProgress(getHealth() / getMaxHealth());

        if (isPassenger()) {
            stopRiding();
        }

        if (level().getDifficulty() == Difficulty.PEACEFUL) {
            discard();
        }

        smashBlocksAround(Mth.floor(getX()), Mth.floor(getY()), Mth.floor(getZ()), 1);

        if (!level().isClientSide)
            for (Player player : getPlayersAround())
                disarm(player);

        illegalPlayerCount();

        if (invul > 0) {
            setInvulTime(invul - 1);
            if (getStage() == 1) {
                if (invul >= 20) {
                    setDeltaMovement(getDeltaMovement().x, 0, getDeltaMovement().z);
                    if (invul % 60 == 0)
                        if (wave < MAX_WAVE) {
//                            EGOLandmine.spawnLandmine(wave, level(), source.below(), this);
                            wave++;
                        }
                    return;
                }

            }

            if (getStage() == 2) {
                if (invul >= 20) {
                    setDeltaMovement(getDeltaMovement().x, 0, getDeltaMovement().z);
                    setHealth(getHealth() + 0.25F);
                    if (countEGOMinionAround(level(), source) == 0)
                        setInvulTime(0);
                    return;
                }
            }
        }

        if (attackDelay > 0) {
            attackDelay--;
        } else {
            if (tryAttack()) {
                int delay = (int) (80 - getStage() * 15 + 15 * Math.random());
                attackDelay = delay;
            }
        }

        smashBlocksAround(Mth.floor(getX()), Mth.floor(getY()), Mth.floor(getZ()), 1);

        if (changeWeaponDelay > 0) {
            changeWeaponDelay--;
        } else {
            changeWeaponDelay = 100;
            int weaponType = getStage() == 0 ? level().random.nextInt(2) : getStage() == 1 ? level().random.nextInt(4) : 4;
            setWeaponType(weaponType);
            ItemStack stack;
            switch (weaponType) {
                case 0: {
                    stack = new ItemStack(ExtraBotanyItems.trueShadowKatana);
                    break;
                }
                case 1: {
                    stack = new ItemStack(ExtraBotanyItems.trueTerraBlade);
                    break;
                }
                case 2: {
                    stack = new ItemStack(ExtraBotanyItems.influxWaver);
                    break;
                }
                case 3: {
                    stack = new ItemStack(ExtraBotanyItems.starWrath);
                    break;
                }
                case 4: {
                    stack = new ItemStack(ExtraBotanyItems.firstFractal);
                    break;
                }
                default: { // impossible
                    stack = new ItemStack(Items.WOODEN_SWORD);
                }
            }
            this.setItemInHand(InteractionHand.MAIN_HAND, stack);
        }

        if (tpDelay > 0) {
            tpDelay--;
        } else {
            if (tryAttack()) {
                teleportRandomly();
                tpTimes++;
                tpDelay = 100 - getStage() * 10;
            }
        }
        //--change phase--
        if (getStage() >= 1 && tpTimes % 7 == 0) {
//            EGOLandmine.spawnLandmine(level().random.nextInt(8), level(), source.below(), this);
            tpTimes++;
        }

        if (getStage() == 0 && getHealth() < 0.75F * getMaxHealth()) {
            setStage(1);
            setInvulTime(460);
            Collections.shuffle(WAVES);
            this.setPos(source.getX() + 0.5, source.getY() + 3, source.getZ() + 0.5);
        }
        if (getStage() == 1 && getHealth() < 0.25F * getMaxHealth()) {
            setStage(2);
            setInvulTime(600);
            setWeaponType(4);
            EGOMinion.spawn(this, level(), getSource(), 60F * playerCount);
            this.setPos(source.getX() + 0.5, source.getY() + 3, source.getZ() + 0.5);
        }
    }

    @Override
    public void setHealth(float f) {
        super.setHealth(f);
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
//        bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
//        bossInfo.removePlayer(player);
    }

    @Override
    public boolean isPushable() {
        return super.isPushable() && getInvulTime() == 0;
    }


    @Override
    protected void pushEntities() {
        if (getInvulTime() == 0)
            super.pushEntities();
    }

    private void teleportRandomly() {
        //choose a location to teleport to
        double oldX = getX(), oldY = getY(), oldZ = getZ();
        double newX, newY = source.getY(), newZ;
        int tries = 0;

        do {
            newX = source.getX() + (random.nextDouble() - .5) * ARENA_RANGE;
            newZ = source.getZ() + (random.nextDouble() - .5) * ARENA_RANGE;
            tries++;
            //ensure it's inside the arena ring, and not just its bounding square
        } while (tries < 50 && MathHelper.pointDistanceSpace(newX, newY, newZ, source.getX(), source.getY(), source.getZ()) > 12);

        if (tries == 50) {
            //failsafe: teleport to the beacon
            newX = source.getX() + .5;
            newY = source.getY() + 1.6;
            newZ = source.getZ() + .5;
        }

        //for low-floor arenas, ensure landing on the ground
        BlockPos tentativeFloorPos = BlockPos.containing(newX, newY - 1, newZ);
        if (level().getBlockState(tentativeFloorPos).getCollisionShape(level(), tentativeFloorPos).isEmpty()) {
            newY--;
        }

        //teleport there
        teleportTo(newX, newY, newZ);

        //play sound
        level().playSound(null, oldX, oldY, oldZ, BotaniaSounds.gaiaTeleport, this.getSoundSource(), 1F, 1F);
        this.playSound(BotaniaSounds.gaiaTeleport, 1F, 1F);

        var random = getRandom();

        //spawn particles along the path
        int particleCount = 128;
        for (int i = 0; i < particleCount; ++i) {
            double progress = i / (double) (particleCount - 1);
            float vx = (random.nextFloat() - 0.5F) * 0.2F;
            float vy = (random.nextFloat() - 0.5F) * 0.2F;
            float vz = (random.nextFloat() - 0.5F) * 0.2F;
            double px = oldX + (newX - oldX) * progress + (random.nextDouble() - 0.5D) * getBbWidth() * 2.0D;
            double py = oldY + (newY - oldY) * progress + random.nextDouble() * getBbHeight();
            double pz = oldZ + (newZ - oldZ) * progress + (random.nextDouble() - 0.5D) * getBbWidth() * 2.0D;
            level().addParticle(ParticleTypes.PORTAL, px, py, pz, vx, vy, vz);
        }

        Vec3 oldPosVec = new Vec3(oldX, oldY + getBbHeight() / 2, oldZ);
        Vec3 newPosVec = new Vec3(newX, newY + getBbHeight() / 2, newZ);

        if (oldPosVec.distanceToSqr(newPosVec) > 1) {
            //damage players in the path of the teleport
            for (Player player : getPlayersAround()) {
                boolean hit = player.getBoundingBox().inflate(0.25).clip(oldPosVec, newPosVec)
                        .isPresent();
                if (hit) {
                    player.hurt(damageSources().mobAttack(this), 6);
                }
            }

            //break blocks in the path of the teleport
            int breakSteps = (int) oldPosVec.distanceTo(newPosVec);
            if (breakSteps >= 2) {
                for (int i = 0; i < breakSteps; i++) {
                    float progress = i / (float) (breakSteps - 1);
                    int breakX = Mth.floor(oldX + (newX - oldX) * progress);
                    int breakY = Mth.floor(oldY + (newY - oldY) * progress);
                    int breakZ = Mth.floor(oldZ + (newZ - oldZ) * progress);

                    smashBlocksAround(breakX, breakY, breakZ, 1);
                }
            }
        }
    }

//    @Nonnull
//    @Override
//    public Packet<ClientGamePacketListener> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }

//    @Override
//    public boolean canBeLeashed(Player player) {
//        return false;
//    }

//    @Override
//    public void writeSpawnData(FriendlyByteBuf buffer) {
//        buffer.writeInt(playerCount);
//        buffer.writeLong(source.asLong());
//        buffer.writeLong(bossInfoUUID.getMostSignificantBits());
//        buffer.writeLong(bossInfoUUID.getLeastSignificantBits());
//    }

//    @Override
//    public void readSpawnData(FriendlyByteBuf additionalData) {
//        playerCount = additionalData.readInt();
//        source = BlockPos.of(additionalData.readLong());
//        long msb = additionalData.readLong();
//        long lsb = additionalData.readLong();
//        bossInfoUUID = new UUID(msb, lsb);

//        Proxy.INSTANCE.runOnClient(() -> () -> EgoMusic.play(this));
        //Minecraft.getInstance().getSoundManager().play(new EGO.EgoMusic(this));
//    }

    /**
     * class for music
     **/
//    @OnlyIn(Dist.CLIENT)
//    private static class EgoMusic extends AbstractTickableSoundInstance {
//        private final EGO guardian;
//
//        public EgoMusic(EGO guardian) {
//            super(ExtraBotanySounds.SWORDLAND.get(), SoundSource.RECORDS, SoundInstance.createUnseededRandom());
//            this.guardian = guardian;
//            this.x = guardian.getSource().getX();
//            this.y = guardian.getSource().getY();
//            this.z = guardian.getSource().getZ();
//            // this.repeat = true;
//            // TODO restore once LWJGL3/vanilla bug fixed? AND CHANGE MUSIC
//        }
//
//        public static void play(EGO guardian) {
//            Minecraft.getInstance().getSoundManager().play(new EgoMusic(guardian));
//        }
//
//        @Override
//        public void tick() {
//            if (!guardian.isAlive()) {
//                stop();
//            }
//        }
//    }

    /**
     * getter and setter
     */
    //---------------------------------------------------↓--------------------------
    public int getInvulTime() {
        return entityData.get(INVUL_TIME);
    }

    public void setInvulTime(int time) {
        entityData.set(INVUL_TIME, time);
    }

    public BlockPos getSource() {
        return source;
    }

    public int getStage() {
        return entityData.get(STAGE);
    }

    public void setStage(int stage) {
        entityData.set(STAGE, stage);
    }

    public int getWeaponType() {
        return entityData.get(WEAPON_TYPE);
    }

    public void setWeaponType(int weaponType) {
        entityData.set(WEAPON_TYPE, weaponType);
    }

    public int getTpDelay() {
        return tpDelay;
    }

    public void setTpDelay(int tpDelay) {
        this.tpDelay = tpDelay;
    }

    public int getAttackDelay() {
        return attackDelay;
    }

    public void setAttackDelay(int attackDelay) {
        this.attackDelay = attackDelay;
    }
}
