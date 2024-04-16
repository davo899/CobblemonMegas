package com.selfdot.cobblemonmegas.common.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.battles.ShowdownActionRequest;
import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.selfdot.cobblemonmegas.common.util.MegaUtils.reasonCannotMegaEvolve;

@Mixin(ShowdownActionRequest.class)
public abstract class ShowdownActionRequestMixin {

    @Shadow
    public abstract List<ShowdownMoveset> getActive();

    @Inject(method = "sanitize", at = @At("HEAD"), remap = false, cancellable = true)
    private void injectSanitize(PokemonBattle battle, BattleActor battleActor, CallbackInfo ci) {
        if (getActive() == null) return;
        ServerPlayerEntity player = null;
        for (ServerPlayerEntity serverPlayerEntity : battle.getPlayers()) {
            if (serverPlayerEntity.getUuid().equals(battleActor.getUuid())) {
                player = serverPlayerEntity;
                break;
            }
        }
        if (player == null) return;
        for (int i = 0; i < getActive().size(); i++) {
            ShowdownMoveset showdownMoveset = getActive().get(i);
            showdownMoveset.getGimmicks().forEach(showdownMoveset::blockGimmick);
            BattlePokemon battlePokemon = battleActor.getActivePokemon().get(i).getBattlePokemon();
            if (battlePokemon == null) battlePokemon = battleActor.getPokemonList().get(0);
            showdownMoveset.setCanMegaEvo(reasonCannotMegaEvolve(player, battlePokemon.getOriginalPokemon()) == null);
        }
        ci.cancel();
    }

}
