package grasspow.extrabotany.api;

import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.minecraft.util.Unit;

public class ExtraBotanyFabricCapabilities {
    public static final ItemApiLookup<NatureOrb, Unit> NATURE_ORB = ItemApiLookup.get(NatureOrb.ID, NatureOrb.class, Unit.class);

    private ExtraBotanyFabricCapabilities(){}
}
