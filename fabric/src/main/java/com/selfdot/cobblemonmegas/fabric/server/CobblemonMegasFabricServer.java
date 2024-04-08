package com.selfdot.cobblemonmegas.fabric.server;

import com.selfdot.cobblemonmegas.common.CobblemonMegas;
import com.selfdot.cobblemonmegas.fabric.FabricPermissionValidator;
import net.fabricmc.api.DedicatedServerModInitializer;

public class CobblemonMegasFabricServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        CobblemonMegas.getInstance().setPermissionValidator(new FabricPermissionValidator());
    }

}
