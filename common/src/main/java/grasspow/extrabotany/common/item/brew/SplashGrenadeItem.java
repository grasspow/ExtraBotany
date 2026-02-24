package grasspow.extrabotany.common.item.brew;

import grasspow.extrabotany.common.entity.item.brew.SplashGrenadeEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.brew.BotaniaBrews;
import vazkii.botania.common.component.BotaniaDataComponents;
import vazkii.botania.common.helper.DataComponentHelper;
import vazkii.botania.common.item.CustomCreativeTabContents;

import java.util.List;
import java.util.Optional;

import static vazkii.botania.api.BotaniaAPI.botaniaRL;

/**
 * copy and edit from <a href="https://github.com/GoldChick/ExtraBotany">...</a>
 */
public class SplashGrenadeItem extends Item implements BrewItem, CustomCreativeTabContents {
    private static final String TAG_BREW_KEY = "brewKey";
    private static final float multiplier = 0.6F;


    public SplashGrenadeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            SplashGrenadeEntity sg = new SplashGrenadeEntity(level, player);
            sg.setItem(stack);
            sg.setPos(player.getX(), player.getY() + 1.1D, player.getZ());
            sg.shootFromRotation(player, player.getXRot(), player.getYRot(), -5.0F, 0.8F, 1.0F);
            level.addFreshEntity(sg);
        }
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.8F);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void addToCreativeTab(Item me, CreativeModeTab.Output output) {
        for (Brew brew : BotaniaAPI.instance().getBrewRegistry()) {
            if (brew == BotaniaBrews.fallbackBrew) {
                continue;
            }
            ItemStack stack = new ItemStack(this);
            setBrew(stack, brew);
            output.accept(stack);
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(getDescriptionId(), Component.translatable(getBrew(stack).getTranslationKey(stack)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        PotionContents.addPotionTooltip(getBrew(stack).getPotionEffects(stack), tooltipComponents::add, 1, context.tickRate());
    }

    @Override
    public Brew getBrew(ItemStack stack) {
        ResourceLocation id = stack.get(BotaniaDataComponents.BREW);
        return BotaniaAPI.instance().getBrewRegistry().get(id);
    }

    public static void setBrew(ItemStack stack, @Nullable Brew brew) {
        ResourceLocation id;
        if (brew != null) {
            id = BotaniaAPI.instance().getBrewRegistry().getKey(brew);
        } else {
            id = botaniaRL("fallback");
        }
        setBrew(stack, id);
    }

    public static void setBrew(ItemStack stack, @Nullable ResourceLocation brew) {
        DataComponentHelper.setOptional(stack, BotaniaDataComponents.BREW, brew);
    }

    @NotNull
    public static String getSubtype(ItemStack stack) {
        return Optional.ofNullable(stack.get(BotaniaDataComponents.BREW)).map(ResourceLocation::toString).orElse("none");
    }
}
