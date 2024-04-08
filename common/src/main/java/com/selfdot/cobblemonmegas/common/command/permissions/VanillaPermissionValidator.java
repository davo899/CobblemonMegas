package com.selfdot.cobblemonmegas.common.command.permissions;

import net.minecraft.command.CommandSource;

public class VanillaPermissionValidator implements PermissionValidator {

    @Override
    public boolean hasPermission(CommandSource source, Permission permission) {
        return source.hasPermissionLevel(permission.level().ordinal());
    }

}
