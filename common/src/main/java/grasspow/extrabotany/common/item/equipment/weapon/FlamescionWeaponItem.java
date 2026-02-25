package grasspow.extrabotany.common.item.equipment.weapon;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class FlamescionWeaponItem extends SwordItem {

    public FlamescionWeaponItem(Tier tier, Properties properties) {
        super(tier, properties);
//        MinecraftForge.EVENT_BUS.addListener(this::leftClick);
//        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
//        MinecraftForge.EVENT_BUS.addListener(this::attackEntity);
    }

//    @SubscribeEvent
//    public void attackEntity(AttackEntityEvent evt) {
//        if (!evt.getEntity().level().isClientSide()) {
//            tryStrengthenAttack(evt.getEntity());
//        }
//    }
//
//    @SubscribeEvent
//    public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
//        if (!evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this) {
//            ClientExbotXplatAbs.INSTANCE.sendToServer(new FlamescionStrengthenPack());
//        }
//    }
//
//    @SubscribeEvent
//    public void leftClickBlock(PlayerInteractEvent.LeftClickBlock evt) {
//        if (evt.getEntity().level().isClientSide() && !evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this) {
//            ClientExbotXplatAbs.INSTANCE.sendToServer(new FlamescionStrengthenPack());
//        }
//    }

    //todo unfinished weapon in 1.16.5
    public void tryStrengthenAttack(Player player) {
//        if (!player.level().isClientSide() && !player.getMainHandItem().isEmpty() && player.getMainHandItem().getItem() == this
//                && player.getAttackStrengthScale(0) == 1) {
//            if(player.hasEffect(ModPotions.flamescion)) {
//                for (int i = 0; i < 3; i++) {
//                    EntityStrengthenSlash slash = new EntityStrengthenSlash(player.world, player);
//                    Vec3 targetPos = player.position().add(player.getLookAngle().yRot((float) Math.toRadians(-15F + 15F * i)).scale(5D));
//                    Vec3 vec = targetPos.subtract(player.getLookAngle()).normalize();
//                    slash.setMotion(vec);
//                    slash.setPosition(player.getPosX(), player.getPosY() + 0.5F, player.getPosZ());
//                    slash.faceEntity(new BlockPos(targetPos.x, targetPos.y, targetPos.z));
//                    player.world.addEntity(slash);
//                }
//                player.removeActivePotionEffect(ModPotions.flamescion);
//            }else if(FlamescionHandler.isFlamescionMode(player)){
//                EntityFlamescionSword sword = new EntityFlamescionSword(player.world, player);
//                Vector3d targetPos = player.getPositionVec().add(player.getLookVec().scale(5D));
//                Vector3d vec = targetPos.subtract(player.getPositionVec()).normalize();
//                sword.setMotion(vec);
//                sword.setPosition(player.getPosX(), player.getPosY() + 0.5F, player.getPosZ());
//                player.world.addEntity(sword);
//            }
//        }
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        if(!FlamescionHandler.isOverloaded(playerIn)) {
//            if (playerIn.isSneaking() && !FlamescionHandler.isFlamescionMode(playerIn)) {
//                if (playerIn.isOnGround()) {
//                    List<LivingEntity> entities = EntityMotor.getEntitiesAround(new BlockPos(playerIn.getPositionVec()), 3F, worldIn);
//                    for (LivingEntity entity : entities) {
//                        entity.setMotion(entity.getMotion().add(0, 1D, 0));
//                        if (entity != playerIn)
//                            entity.addPotionEffect(new EffectInstance(ModPotions.timelock, 60));
//                    }
//                    if (worldIn.isRemote)
//                        for (int i = 0; i < 360; i += 30) {
//                            double r = 3D;
//                            double x = playerIn.getPosX() + r * Math.cos(Math.toRadians(i));
//                            double y = playerIn.getPosY() + 0.5D;
//                            double z = playerIn.getPosZ() + r * Math.sin(Math.toRadians(i));
//                            for (int j = 0; j < 6; j++)
//                                worldIn.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0.12F * j, 0);
//                        }
//                } else {
//                    if (worldIn.isRemote)
//                        for (int i = 0; i < 360; i += 15) {
//                            double r = 0.5D;
//                            double x = playerIn.getPosX() + r * Math.cos(Math.toRadians(i));
//                            double y = playerIn.getPosY() + 0.5D;
//                            double z = playerIn.getPosZ() + r * Math.sin(Math.toRadians(i));
//                            Vector3d vec = new Vector3d(x - playerIn.getPosX(), 0, z - playerIn.getPosZ()).normalize();
//                            for (int j = 0; j < 3; j++)
//                                worldIn.addParticle(ParticleTypes.FLAME, x, y, z, vec.scale(0.25D + 0.01D * j).x, 0, vec.scale(0.25D + 0.01D * j).z);
//                        }
//                }
//                playerIn.addPotionEffect(new EffectInstance(ModPotions.incandescence, 60));
//            }else if(FlamescionHandler.isFlamescionMode(playerIn)){
//                Vector3d targetPos = playerIn.getPositionVec().add(playerIn.getLookVec().scale(5D));
//                EntityFlamescionVoid fvoid = new EntityFlamescionVoid(worldIn, playerIn);
//                fvoid.setPosition(targetPos.x, targetPos.y, targetPos.z);
//                if(!worldIn.isRemote)
//                    worldIn.addEntity(fvoid);
//                playerIn.addPotionEffect(new EffectInstance(ModPotions.incandescence, 80));
//                playerIn.getCooldownTracker().setCooldown(this, 40);
//            }
//        }
//
//        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
//    }

}
