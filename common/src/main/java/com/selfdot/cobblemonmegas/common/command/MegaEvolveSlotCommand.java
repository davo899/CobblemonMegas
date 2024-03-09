package com.selfdot.cobblemonmegas.common.command;

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.command.argument.PartySlotArgumentType;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.selfdot.cobblemonmegas.common.DataKeys;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class MegaEvolveSlotCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        Pokemon pokemon = PartySlotArgumentType.Companion.getPokemon(context, "pokemon");
        if (!pokemon.getSpecies().getFeatures().contains(DataKeys.MEGA_SPECIES_FEATURE)) {
            context.getSource().sendError(Text.literal("This Pokémon has no Mega form"));
            return -1;
        }
        new FlagSpeciesFeature(DataKeys.MEGA_SPECIES_FEATURE, true).apply(pokemon);
        return SINGLE_SUCCESS;
    }

}
