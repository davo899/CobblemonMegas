package com.selfdot.cobblemonmegas.common.mixin;

import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownService;
import com.google.gson.Gson;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GraalShowdownService.class)
public abstract class GraalShowdownServiceMixin {

    @Shadow @Final private Gson gson;

    @Inject(method = "registerSpecies", at = @At("HEAD"), remap = false)
    private void injectRegisterSpecies(CallbackInfo ci) {
        /*
        PokemonSpecies.INSTANCE.getSpecies().forEach(species -> {
            if (species.getName().equalsIgnoreCase("rayquaza")) {
                System.out.println(gson.toJson(new PokemonSpecies.ShowdownSpecies(species, null)));
                species.getForms().forEach(formData -> {
                    System.out.println(gson.toJson(new PokemonSpecies.ShowdownSpecies(species, formData)));
                });
            }
        });*/
    }

}
