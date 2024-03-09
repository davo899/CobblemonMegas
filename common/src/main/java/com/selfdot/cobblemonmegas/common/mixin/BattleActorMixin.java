package com.selfdot.cobblemonmegas.common.mixin;

import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.MoveActionResponse;
import com.cobblemon.mod.common.battles.ShowdownActionResponse;
import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.selfdot.cobblemonmegas.common.CobblemonMegas;
import com.selfdot.cobblemonmegas.common.DataKeys;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mixin(BattleActor.class)
public abstract class BattleActorMixin {

    @Shadow public abstract UUID getUuid();

    @Shadow private List<ShowdownActionResponse> responses;

    @Shadow @Final private List<ActiveBattlePokemon> activePokemon;

    @Inject(method = "writeShowdownResponse", at = @At("HEAD"), remap = false)
    private void injectWriteShowdownResponse(CallbackInfo ci) {
        Set<UUID> BATTLE_MEGA_EVOLVE = CobblemonMegas.getInstance().getBattleMegaEvolve();
        UUID uuid = getUuid();
        if (BATTLE_MEGA_EVOLVE.contains(uuid)) {
            BATTLE_MEGA_EVOLVE.remove(uuid);
            if (responses.size() != 1) return;
            if (!(responses.get(0) instanceof MoveActionResponse moveActionResponse)) return;
            moveActionResponse.setGimmickID(ShowdownMoveset.Gimmick.MEGA_EVOLUTION.getId());

            if (activePokemon.size() != 1) return;
            BattlePokemon battlePokemon = activePokemon.get(0).getBattlePokemon();
            if (battlePokemon == null) return;
            new FlagSpeciesFeature(DataKeys.MEGA_SPECIES_FEATURE, true)
                .apply(battlePokemon.getOriginalPokemon());
        }
    }

}
