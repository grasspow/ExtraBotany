package grasspow.extrabotany.common.item.brew;

import grasspow.extrabotany.common.item.ExtraBotanyItems;

public class CocktailItem extends BaseBrewItemEX {
    public CocktailItem(Properties builder) {
        super(builder,  20, 1.3f, 0, ()-> ExtraBotanyItems.emptyBottle);
    }
}
