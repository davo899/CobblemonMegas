package com.selfdot.cobblemonmegas.fabric;

import com.selfdot.cobblemonmegas.common.CobblemonMegas;
import net.fabricmc.api.ModInitializer;

public class CobblemonMegasFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CobblemonMegas.getInstance().onInitialize();
    }

}
