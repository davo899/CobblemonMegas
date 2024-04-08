package com.selfdot.cobblemonmegas.forge

import com.selfdot.cobblemonmegas.common.CobblemonMegas
import net.minecraftforge.fml.DistExecutor

class SetPermissionValidatorRunnable : DistExecutor.SafeRunnable {
    override fun run() {
        CobblemonMegas.getInstance().setPermissionValidator(ForgePermissionValidator())
    }

}