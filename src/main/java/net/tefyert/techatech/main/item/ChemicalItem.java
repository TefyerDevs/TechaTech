package net.tefyert.techatech.main.item;

import net.minecraft.world.item.Item;
import net.tefyert.techatech.api.chemical.Chemical;

public class ChemicalItem extends Item {
    Chemical chemical;
    public ChemicalItem(Properties properties, Chemical chemical) {
        super(properties);
        this.chemical = chemical;
    }

    public Chemical getChemical() {
        return chemical;
    }
}
