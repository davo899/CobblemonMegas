package com.selfdot.cobblemonmegas.common.command.permissions;

import net.minecraft.util.Identifier;

public record Permission(String literal, PermissionLevel level) {

    public Identifier getIdentifier() {
        return new Identifier("selfdot", literal);
    }

}
