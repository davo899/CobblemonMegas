package com.selfdot.cobblemonmegas.common.command.permissions;

import net.minecraft.command.CommandSource;

public interface PermissionValidator {

    boolean hasPermission(CommandSource source, Permission permission);

}
