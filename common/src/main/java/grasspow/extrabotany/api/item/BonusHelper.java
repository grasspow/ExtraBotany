package grasspow.extrabotany.api.item;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Random;

public class BonusHelper {

    public static int sum(List<WeightCategory> categorys) {
        int weightSum = 0;
        for (WeightCategory wc : categorys) {
            weightSum += wc.getWeight();
        }
        return weightSum;
    }

    public static void addItem(ItemStack stack, int weight, List<WeightCategory> categorys) {
        WeightCategory w = new WeightCategory(stack, weight);
        categorys.add(w);
    }

    public static ItemStack rollItem(Player player, List<WeightCategory> weightcategory) {
        int weightSum = sum(weightcategory);
        Random random = new Random();
        int n = random.nextInt(weightSum);
        int m = 0;
        for (WeightCategory wc : weightcategory) {
            if (m <= n && n < m + wc.getWeight()) {
                if (wc.getWeight() / weightSum <= 0.01F) {
                    return wc.getCategory();
                }
                return wc.getCategory();
            }
            m += wc.getWeight();
        }
        return ItemStack.EMPTY;
    }

}
