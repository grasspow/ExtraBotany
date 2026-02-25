package grasspow.extrabotany.api.item;


import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface IItemWithLeftClick {
    InteractionResult onLeftClick(Player living, Entity target);
}
