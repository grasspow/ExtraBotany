package grasspow.extrabotany.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

/**
 * An item that has this capability can contain nature.
 */
public interface NatureOrb {
    ResourceLocation ID = exbotRL("nature_orb");
    int getNature();

    int getMaxNature();

    boolean addNature(int x);

    boolean canReceiveNatureFromNatureAdder(BlockEntity adder);

    boolean isNoExport();
}
