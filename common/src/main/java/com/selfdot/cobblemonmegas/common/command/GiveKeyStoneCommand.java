package com.selfdot.cobblemonmegas.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.selfdot.cobblemonmegas.common.DataKeys;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class GiveKeyStoneCommand implements Command<ServerCommandSource> {

    private static final int KEY_STONE_CUSTOM_MODEL_DATA = 99;

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "players");
        if (players == null) return 0;
        players.forEach(player -> {
            ItemStack keyStone = new ItemStack(Items.EMERALD);
            NbtCompound nbt = keyStone.getNbt();
            if (nbt == null) nbt = new NbtCompound();
            nbt.putInt("CustomModelData", KEY_STONE_CUSTOM_MODEL_DATA);
            nbt.putBoolean(DataKeys.NBT_KEY_KEY_STONE, true);
            keyStone.setNbt(nbt);
            keyStone.setCustomName(Text.literal("Key Stone"));
            player.giveItemStack(keyStone);
        });
        return SINGLE_SUCCESS;
    }

}
