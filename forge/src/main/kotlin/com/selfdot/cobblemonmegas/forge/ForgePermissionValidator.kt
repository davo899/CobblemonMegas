package com.selfdot.cobblemonmegas.forge

import com.selfdot.cobblemonmegas.common.command.permissions.MegasPermissions
import com.selfdot.cobblemonmegas.common.command.permissions.Permission
import com.selfdot.cobblemonmegas.common.command.permissions.PermissionValidator
import net.minecraft.command.CommandSource
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.Identifier
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.server.permission.PermissionAPI
import net.minecraftforge.server.permission.events.PermissionGatherEvent
import net.minecraftforge.server.permission.nodes.PermissionNode
import net.minecraftforge.server.permission.nodes.PermissionTypes

class ForgePermissionValidator : PermissionValidator {

    private val nodes = hashMapOf<Identifier, PermissionNode<Boolean>>()

    init {
        MinecraftForge.EVENT_BUS.addListener<PermissionGatherEvent.Nodes> {
            event -> event.addNodes(MegasPermissions.all().map { permission ->
                val node = PermissionNode(
                    permission.identifier,
                    PermissionTypes.BOOLEAN,
                    { player, _, _ -> player?.hasPermissionLevel(permission.level.ordinal) == true }
                )
                this.nodes[permission.identifier] = node
                node
            })
        }
    }

    private fun findNode(permission: Permission) = this.nodes[permission.identifier]

    private fun extractPlayerFromSource(source: CommandSource) =
        if (source is ServerCommandSource) source.player else null

    override fun hasPermission(source: CommandSource, permission: Permission): Boolean {
        val player = this.extractPlayerFromSource(source) ?:
            return source.hasPermissionLevel(permission.level.ordinal)
        val node = this.findNode(permission) ?: return source.hasPermissionLevel(permission.level.ordinal)
        return PermissionAPI.getPermission(player, node)
    }

}
