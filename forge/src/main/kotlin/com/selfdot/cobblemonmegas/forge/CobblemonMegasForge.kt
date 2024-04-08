package com.selfdot.cobblemonmegas.forge

import com.selfdot.cobblemonmegas.common.CobblemonMegas
import com.selfdot.cobblemonmegas.common.DataKeys
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Void
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.DistExecutor
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
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER) { SetPermissionValidatorRunnable() }
    }

}
