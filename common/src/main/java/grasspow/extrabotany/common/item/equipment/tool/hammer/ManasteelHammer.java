package grasspow.extrabotany.common.item.equipment.tool.hammer;

import grasspow.extrabotany.api.item.IHammer;
import net.minecraft.world.item.Tier;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.item.equipment.tool.manasteel.ManasteelPickaxeItem;

import java.util.regex.Pattern;

public class ManasteelHammer extends ManasteelPickaxeItem implements IHammer {
    private static final Pattern TORCH_PATTERN = Pattern.compile("(?:(?:(?:[A-Z-_.:]|^)torch)|(?:(?:[a-z-_.:]|^)Torch))(?:[A-Z-_.:]|$)");
    private static final int MANA_PER_DAMAGE = 40;
    private static final int TIME = 5;

    public ManasteelHammer(Tier mat, Properties props) {
        this(BotaniaAPI.instance().getManasteelItemTier(), props, -3.0F);
    }

    public ManasteelHammer(Tier mat, Properties props, float attackSpeed) {
        super(mat, props.attributes(ManasteelHammer.createAttributes(mat, 1, attackSpeed)), attackSpeed);
    }


    @Override
    public boolean isHammer() {
        return true;
    }

    public int getManaPerDamage() {
        return MANA_PER_DAMAGE;
    }

    //    @SubscribeEvent
//    public void onMining(PlayerEvent.BreakSpeed event){
//        if(event.getEntity().getMainHandItem().getItem() instanceof IHammer){
//            event.setNewSpeed(event.getOriginalSpeed() * 1.25F);
//        }
//    }
}
