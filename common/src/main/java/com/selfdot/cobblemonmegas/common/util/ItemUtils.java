package com.selfdot.cobblemonmegas.common.util;

import net.minecraft.item.ItemStack;

public class ItemUtils {

    public static void setNameNoItalics(ItemStack itemStack, String customName) {
        itemStack.getOrCreateSubNbt("display")
            .putString("Name", "{\"text\":\"" + customName + "\",\"italic\":false}");
    }

}
