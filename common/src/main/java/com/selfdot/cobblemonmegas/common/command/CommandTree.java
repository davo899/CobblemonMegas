package com.selfdot.cobblemonmegas.common.command;

import com.cobblemon.mod.common.command.argument.PartySlotArgumentType;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.string;

public class CommandTree {

    public static void register(
        CommandDispatcher<ServerCommandSource> dispatcher,
        CommandRegistryAccess commandRegistryAccess,
        CommandManager.RegistrationEnvironment registrationEnvironment
    ) {
        dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>
            literal("megaevolve")
            .requires(ServerCommandSource::isExecutedByPlayer)
            .executes(new MegaEvolveInBattleCommand())
            .then(RequiredArgumentBuilder.<ServerCommandSource, Integer>
                argument("pokemon", PartySlotArgumentType.Companion.partySlot())
                .executes(new MegaEvolveSlotCommand())
            )
        );
        dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>
            literal("getmegastone")
            .requires(ServerCommandSource::isExecutedByPlayer)
            .then(RequiredArgumentBuilder.<ServerCommandSource, String>
                argument("megaStone", string())
                .executes(new GetMegaStoneCommand())
            )
        );
    }

}
