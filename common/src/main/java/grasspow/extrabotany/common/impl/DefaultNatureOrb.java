package grasspow.extrabotany.common.impl;

import grasspow.extrabotany.api.NatureOrb;
import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import vazkii.botania.common.helper.DataComponentHelper;

public record DefaultNatureOrb(ItemStack stack) implements NatureOrb {

    @Override
    public int getNature() {
        return stack.getOrDefault(ExtraBotanyDataComponents.NATURE, 0);
    }

    @Override
    public int getMaxNature() {
        return stack.getOrDefault(ExtraBotanyDataComponents.MAX_NATURE, 0);
    }

    @Override
    public boolean addNature(int nature) {
        if ((getNature() + nature)<0) return false;
        DataComponentHelper.setIntNonZero(stack, ExtraBotanyDataComponents.NATURE, Math.min(getNature() + nature, getMaxNature()));
        return true;
    }

    @Override
    public boolean canReceiveNatureFromNatureAdder(BlockEntity adder) {
        return true;
    }

    @Override
    public boolean isNoExport() {
        return false;
    }
}
