package com.selfdot.cobblemonmegas.forge;

import com.selfdot.cobblemonmegas.common.CobblemonMegas;
import com.selfdot.cobblemonmegas.common.DataKeys;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DataKeys.MOD_ID)
public class CobblemonMegasForge {

    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        CobblemonMegas.getInstance().onInitialize();
    }

}
