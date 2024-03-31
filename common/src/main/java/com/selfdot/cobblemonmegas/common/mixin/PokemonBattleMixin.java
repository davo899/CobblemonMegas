package com.selfdot.cobblemonmegas.common.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.selfdot.cobblemonmegas.common.CobblemonMegas;
import com.selfdot.cobblemonmegas.common.util.MegaUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PokemonBattle.class)
public abstract class PokemonBattleMixin {

    @Inject(method = "end", at = @At("TAIL"), remap = false)
    private void injectEnd(CallbackInfo ci) {
        PokemonBattle thisBattle = (PokemonBattle)(Object)this;
        MegaUtils.deMegaEvolveAll(thisBattle);
        thisBattle.getActors().forEach(
            actor -> {
                CobblemonMegas.getInstance().getHasMegaEvolvedThisBattle().remove(actor.getUuid());
                actor.getPlayerUUIDs().forEach(playerId -> {
                    ServerPlayerEntity player = CobblemonMegas.getInstance()
                        .getServer().getPlayerManager().getPlayer(playerId);
                    if (player == null) return;
                    MegaUtils.updateKeyStoneGlow(player);
                });
            }
        );
    }

}
