package com.selfdot.cobblemonmegas.common;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider;
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeatures;
import com.cobblemon.mod.common.command.argument.PartySlotArgumentType;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.selfdot.cobblemonmegas.common.command.MegaEvolveInBattleCommand;
import com.selfdot.cobblemonmegas.common.command.MegaEvolveSlotCommand;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import kotlin.Unit;
import net.minecraft.server.command.ServerCommandSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CobblemonMegas {

    private static final CobblemonMegas INSTANCE = new CobblemonMegas();

    private CobblemonMegas() { }

    public static CobblemonMegas getInstance() {
        return INSTANCE;
    }

    private final Set<UUID> BATTLE_MEGA_EVOLVE = new HashSet<>();

    public void onInitialize() {
        CommandRegistrationEvent.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>
                    literal("megaevolve")
                .requires(ServerCommandSource::isExecutedByPlayer)
                .executes(new MegaEvolveInBattleCommand())
                .then(RequiredArgumentBuilder.<ServerCommandSource, Integer>
                        argument("pokemon", PartySlotArgumentType.Companion.partySlot())
                    .executes(new MegaEvolveSlotCommand())
                )
            )
        );

        LifecycleEvent.SERVER_STARTING.register((server) -> {
            SpeciesFeatures.INSTANCE.register(
                DataKeys.MEGA_SPECIES_FEATURE,
                new FlagSpeciesFeatureProvider(List.of(DataKeys.MEGA_SPECIES_FEATURE), false)
            );
            for (Species species : PokemonSpecies.INSTANCE.getSpecies()) {
                if (species.getForms().stream().anyMatch(
                    form -> form.getName().equalsIgnoreCase(DataKeys.MEGA_SPECIES_FEATURE)
                )) {
                    species.getFeatures().add(DataKeys.MEGA_SPECIES_FEATURE);
                }
            }
        });

        CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.NORMAL, event -> {
            event.getBattle().getActors().forEach(
                actor -> actor.getPokemonList().forEach(
                    battlePokemon -> new FlagSpeciesFeature(DataKeys.MEGA_SPECIES_FEATURE, false)
                        .apply(battlePokemon.getOriginalPokemon())
                )
            );
            return Unit.INSTANCE;
        });

        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, event -> {
            event.getBattle().getActors().forEach(
                actor -> actor.getPokemonList().forEach(
                    battlePokemon -> new FlagSpeciesFeature(DataKeys.MEGA_SPECIES_FEATURE, false)
                        .apply(battlePokemon.getOriginalPokemon())
                )
            );
            return Unit.INSTANCE;
        });
    }

    public Set<UUID> getBattleMegaEvolve() {
        return BATTLE_MEGA_EVOLVE;
    }

}
