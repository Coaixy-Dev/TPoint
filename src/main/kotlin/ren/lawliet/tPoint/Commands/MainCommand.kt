package ren.lawliet.tPoint.Commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ren.lawliet.tPoint.Config
import ren.lawliet.tPoint.Logger

/**
 *@author        Coaixy
 *@createTime    2024-07-21
 *@packageName   ren.lawliet.tPoint.Commands
 */

class MainCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("tpoint.use")) {
                Logger.noPermission("")
                return true
            }
            if (args.isEmpty()) {
                Logger.helpText()
            }
            if (args.size == 1) {
                when (args[0]) {
                    "help" -> {
                        Logger.helpText()
                    }

                    "list" -> {
                        sender.sendMessage("§7[§6TPoint§7] §fTPoint List")
                        val playerTPoints = Config.getPlayerTPoints(sender.uniqueId)
                        playerTPoints.getKeys(false).forEach {
                            sender.sendMessage("§7[§6TPoint§7] §f$it")
                        }
                    }
                    else -> {
                        val playerTPoints = Config.getPlayerTPoints(sender.uniqueId)
                        if (playerTPoints.contains(args[0])) {
                            val location = playerTPoints.getLocation(args[0])

                            if (location != null) {
                                sender.teleport(location)
                            }
                            location?.let { Logger.teleportSuccess(it) }
                        } else {
                            Logger.noTPoint(args[0])
                        }
                    }
                }
            } else if (args.size == 2) {
                when (args[0]) {
                    "set" -> {
                        val playerTPoints = Config.getPlayerTPoints(sender.uniqueId)
                        playerTPoints.set(args[1], sender.location)
                        playerTPoints.save(Config.getPlayerTPointFile(sender.uniqueId))
                        Logger.setSuccess(args[1])
                    }

                    "del" -> {
                        val playerTPoints = Config.getPlayerTPoints(sender.uniqueId)
                        playerTPoints.set(args[1], null)
                        playerTPoints.save(Config.getPlayerTPointFile(sender.uniqueId))
                        Logger.delSuccess(args[1])
                    }
                }
            }
        } else {
            return false
        }
        return true
    }
}