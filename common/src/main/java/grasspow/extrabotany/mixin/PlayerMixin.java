package grasspow.extrabotany.mixin;

import grasspow.extrabotany.common.item.equipment.BuddhistRelicsItem;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin  {

    @Inject(method = "tick", at = @At ("HEAD"))
    private void onTick(CallbackInfo info) {
        Player player = (Player) (Object) this;
        BuddhistRelicsItem.onItemUpdate(player,player.level());
    }
}
