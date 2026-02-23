package grasspow.extrabotany.client.render.entity;

import grasspow.extrabotany.common.entity.projectile.BaseProjectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class DummyRender extends EntityRenderer<BaseProjectile> {

    public DummyRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(BaseProjectile pEntity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
