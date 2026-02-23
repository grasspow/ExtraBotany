package grasspow.extrabotany.common.item.equipment;

import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import vazkii.botania.api.item.Relic;
import vazkii.botania.common.helper.DataComponentHelper;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.common.item.relic.RelicItem;

import java.util.List;

public class BuddhistRelicsItem extends RelicItem implements IAdvancementRequirement {
    public static final int MANA_PER_DAMAGE = 4;

    public BuddhistRelicsItem(Properties props) {
        super(props.rarity(Rarity.EPIC));
//        MinecraftForge.EVENT_BUS.addListener(this::onItemUpdate);
    }

    public static void relicInit(ItemStack stack) {
        if (!stack.has(ExtraBotanyDataComponents.RELIC_DATA)) {
            NonNullList<ItemStack> itemStacks = NonNullList.withSize(5, ItemStack.EMPTY);
            itemStacks.set(0, new ItemStack(ExtraBotanyItems.excaliber));
            itemStacks.set(1, new ItemStack(ExtraBotanyItems.infiniteWine));
            itemStacks.set(2, new ItemStack(ExtraBotanyItems.fallnaught));
            itemStacks.set(3, new ItemStack(BotaniaItems.infiniteFruit));
            itemStacks.set(4, new ItemStack(BotaniaItems.kingKey));
            stack.set(ExtraBotanyDataComponents.RELIC_DATA,itemStacks);
        }
    }

    public static ItemStack expired(ItemStack morphstack) {
        if (!morphstack.has(ExtraBotanyDataComponents.MORPHING)) {
            List<ItemStack> itemStacks = morphstack.get(ExtraBotanyDataComponents.RELIC_DATA);
            int id = 0;
            for (int i = 0; i < 5; i++) {
                ItemStack stack = itemStacks.get(i).copy();
                if (morphstack.getItem() == stack.getItem()) {
                    id = i;
                    break;
                }
            }

            ItemStack budd = new ItemStack(ExtraBotanyItems.buddhistRelics);
            ItemStack copy = morphstack.copy();
            copy.remove(ExtraBotanyDataComponents.RELIC_DATA);
            itemStacks.set(id, copy);
            budd.set(ExtraBotanyDataComponents.RELIC_DATA,itemStacks);
            return budd.copy();
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack relicShift(ItemStack heldstack) {
        List<ItemStack> itemStacks = heldstack.get(ExtraBotanyDataComponents.RELIC_DATA);
        if (heldstack.getItem() == ExtraBotanyItems.buddhistRelics) {
            relicInit(heldstack);
            ItemStack stack = itemStacks.get(0).copy();
            DataComponentHelper.setFlag(stack,ExtraBotanyDataComponents.MORPHING,true);
            stack.set(ExtraBotanyDataComponents.RELIC_DATA,itemStacks);
            return stack.copy();
        } else if (heldstack.has(ExtraBotanyDataComponents.MORPHING)) {
            int id = 0;
            for (int i = 0; i < 5; i++) {
                ItemStack stack = itemStacks.get(i).copy();
                if (heldstack.getItem() == stack.getItem()) {
                    id = i;
                    break;
                }
            }
            if (id == 4) {
                ItemStack budd = new ItemStack(ExtraBotanyItems.buddhistRelics);
                ItemStack copy = heldstack.copy();
                copy.remove(ExtraBotanyDataComponents.RELIC_DATA);
                itemStacks.set(4, copy);
                budd.set(ExtraBotanyDataComponents.RELIC_DATA,itemStacks);
                return budd.copy();
            }
            ItemStack morph = itemStacks.get(id + 1);
            ItemStack copy = heldstack.copy();
            copy.remove(ExtraBotanyDataComponents.RELIC_DATA);
            itemStacks.set(id, copy);
            DataComponentHelper.setFlag(morph,ExtraBotanyDataComponents.MORPHING,true);
            morph.set(ExtraBotanyDataComponents.RELIC_DATA,itemStacks);
            return morph.copy();
        }
        return ItemStack.EMPTY;
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
    }

    @Override
    public String getAdvancementName() {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
