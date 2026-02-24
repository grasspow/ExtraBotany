package grasspow.extrabotany.common.item.equipment;

import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.brew.BotaniaBrews;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.brew.BaseBrewItem;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.common.item.relic.RelicItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.ArrayList;
import java.util.List;

public class BuddhistRelicsItem extends RelicItem implements IAdvancementRequirement {
    public static final int MANA_PER_DAMAGE = 4;

    public BuddhistRelicsItem(Properties props) {
        super(props.rarity(Rarity.EPIC));
    }

    public static void relicInit(ItemStack stack) {
        if (!stack.has(ExtraBotanyDataComponents.RELIC_DATA)) {
            ItemStack wine = new ItemStack(ExtraBotanyItems.infiniteWine);
            BaseBrewItem.setBrew(wine, BotaniaBrews.fallbackBrew);
            NonNullList<ItemStack> itemStacks = NonNullList.withSize(5, ItemStack.EMPTY);
            itemStacks.set(0, new ItemStack(ExtraBotanyItems.excaliber));
            itemStacks.set(1, wine);
            itemStacks.set(2, new ItemStack(ExtraBotanyItems.fallnaught));
            itemStacks.set(3, new ItemStack(BotaniaItems.infiniteFruit));
            itemStacks.set(4, new ItemStack(BotaniaItems.kingKey));
            stack.set(ExtraBotanyDataComponents.RELIC_DATA, itemStacks);
        }
    }

    public static void onItemUpdate(Player player, Level level) {
        if (!level.isClientSide()) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                final ItemStack stack = player.getInventory().getItem(i);
                if (!(stack.getItem().equals(ExtraBotanyItems.buddhistRelics)))
                    if (stack.has(ExtraBotanyDataComponents.RELIC_DATA)) {
                        if (ManaItemHandler.instance().requestManaExact(stack, player, MANA_PER_DAMAGE, false)) {
                            ManaItemHandler.instance().requestManaExact(stack, player, MANA_PER_DAMAGE, true);
                        } else {
                            ItemStack budd = expired(stack);
                            if (!budd.isEmpty()) {
                                player.getInventory().setItem(i, budd);
                            }
                        }
                    }

            }
        }
    }

    private static ItemStack expired(ItemStack morphStack) {
        List<ItemStack> itemStacks = morphStack.get(ExtraBotanyDataComponents.RELIC_DATA);
        if (itemStacks == null) return ItemStack.EMPTY;
        int id = -1;
        for (int i = 0; i < 5; i++) {
            ItemStack stack = itemStacks.get(i).copy();
            if (morphStack.getItem() == stack.getItem()) {
                id = i;
                break;
            }
        }
        if (id == -1) return ItemStack.EMPTY;
        ItemStack budd = new ItemStack(ExtraBotanyItems.buddhistRelics);
        ItemStack copy = morphStack.copy();
        Relic relic = XplatAbstractions.INSTANCE.findRelic(copy);
        Relic relicNew = XplatAbstractions.INSTANCE.findRelic(budd);
        relicNew.bindToUUID(relic.getSoulbindUUID());
        copy.remove(ExtraBotanyDataComponents.RELIC_DATA);
        itemStacks.set(id, copy);
        budd.set(ExtraBotanyDataComponents.RELIC_DATA, itemStacks);
        return budd.copy();
    }

    public static ItemStack relicShift(ItemStack heldstack) {
        relicInit(heldstack);

        List<ItemStack> currentList = heldstack.get(ExtraBotanyDataComponents.RELIC_DATA);
        if (currentList == null) return ItemStack.EMPTY;
        List<ItemStack> itemStacks = new ArrayList<>(currentList);

        if (heldstack.is(ExtraBotanyItems.buddhistRelics)) {
            ItemStack firstStack = itemStacks.getFirst().copy();
            firstStack.set(ExtraBotanyDataComponents.RELIC_DATA, itemStacks);
            return firstStack;
        }

        int id = -1;
        for (int i = 0; i < 5; i++) {
            if (heldstack.is(itemStacks.get(i).getItem())) {
                id = i;
                break;
            }
        }

        if (id == -1) return ItemStack.EMPTY;

        ItemStack cleanCurrent = heldstack.copy();
        cleanCurrent.remove(ExtraBotanyDataComponents.RELIC_DATA);
        itemStacks.set(id, cleanCurrent);

        if (id == 4) {
            ItemStack mainRelic = new ItemStack(ExtraBotanyItems.buddhistRelics);
            mainRelic.set(ExtraBotanyDataComponents.RELIC_DATA, itemStacks);
            return mainRelic;
        } else {
            ItemStack nextStack = itemStacks.get(id + 1).copy();
            nextStack.set(ExtraBotanyDataComponents.RELIC_DATA, itemStacks);
            return nextStack;
        }
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
    }

    @Override
    public String getAdvancementName() {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
