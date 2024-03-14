package com.selfdot.cobblemonmegas.forge

import com.selfdot.cobblemonmegas.common.CobblemonMegas
import com.selfdot.cobblemonmegas.common.DataKeys
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.KotlinModLoadingContext


@Mod(DataKeys.MOD_NAMESPACE)
class CobblemonMegasForge {
    init {
        val modEventBus = KotlinModLoadingContext.get().getKEventBus()
        modEventBus.addListener { event: FMLCommonSetupEvent -> commonSetup(event) }
        MinecraftForge.EVENT_BUS.register(this)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        CobblemonMegas.getInstance().onInitialize()
    }

}
