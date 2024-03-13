package com.selfdot.cobblemonmegas.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.selfdot.cobblemonmegas.common.CobblemonMegas;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReloadCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        CobblemonMegas.getInstance().getConfig().reload();
        context.getSource().sendMessage(Text.literal("Reloaded CobblemonPolymerMegas config."));
        return SINGLE_SUCCESS;
    }

}
