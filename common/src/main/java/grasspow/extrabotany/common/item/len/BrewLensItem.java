package grasspow.extrabotany.common.item.len;

import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.item.lens.Lens;
import vazkii.botania.common.item.lens.LensItem;

public class BrewLensItem extends LensItem implements BrewItem {
    private static final String TAG_BREW_KEY = "brewKey";
    private boolean brew = false;

    public BrewLensItem(Properties builder, Lens lens, int props, boolean isBrew) {
        super(builder, lens, props);
        this.brew = isBrew;
    }

    @Override
    public Brew getBrew(ItemStack brew) {
        return null;
    }

//    @Override
//    public void appendHoverText(ItemStack stack, Level world, List<Component> stacks, TooltipFlag flags) {
//        if (brew) {
//            addPotionTooltip(getBrew(stack).getPotionEffects(stack), stacks, 1);
//        }
//    }

//    public static void addPotionTooltip(List<MobEffectInstance> list, List<Component> lores, float durationFactor) {
//        List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();
//        if (list.isEmpty()) {
//            lores.add((Component.translatable("effect.none")).withStyle(ChatFormatting.GRAY));
//        } else {
//            for (MobEffectInstance effectinstance : list) {
//                MutableComponent iformattabletextcomponent = Component.translatable(effectinstance.getDescriptionId());
//                Holder<MobEffect> effect = effectinstance.getEffect();
//                Map<Attribute, AttributeModifier> map = effect.getAttributeModifiers();
//                if (!map.isEmpty()) {
//                    for (Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
//                        AttributeModifier attributemodifier = entry.getValue();
//                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierValue(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
//                        list1.add(new Pair<>(entry.getKey(), attributemodifier1));
//                    }
//                }
//                if (effectinstance.getAmplifier() > 0) {
//                    iformattabletextcomponent = Component.translatable("potion.withAmplifier", iformattabletextcomponent, Component.translatable("potion.potency." + effectinstance.getAmplifier()));
//                }
//                lores.add(iformattabletextcomponent.withStyle(effect.getCategory().getTooltipFormatting()));
//            }
//        }
//
//        if (!list1.isEmpty()) {
//            lores.add(Component.empty());
//            lores.add((Component.translatable("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));
//
//            for (Pair<Attribute, AttributeModifier> pair : list1) {
//                AttributeModifier attributemodifier2 = pair.getSecond();
//                double d0 = attributemodifier2.getAmount();
//                double d1;
//                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
//                    d1 = attributemodifier2.getAmount();
//                } else {
//                    d1 = attributemodifier2.getAmount() * 100.0D;
//                }
//
//                if (d0 > 0.0D) {
//                    lores.add((Component.translatable("attribute.modifier.plus." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.BLUE));
//                } else if (d0 < 0.0D) {
//                    d1 = d1 * -1.0D;
//                    lores.add((Component.translatable("attribute.modifier.take." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.RED));
//                }
//            }
//        }
//    }
}
