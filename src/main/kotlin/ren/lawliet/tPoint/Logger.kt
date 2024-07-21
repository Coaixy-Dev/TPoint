package ren.lawliet.tPoint

import org.bukkit.Bukkit
import org.bukkit.Location

/**
 *@author        Coaixy
 *@createTime    2024-07-21
 *@packageName   ren.lawliet.tPoint
 */

object Logger {
    fun log(message: String) {
        Bukkit.getServer().consoleSender.sendMessage("§7[§6TPoint§7] §f$message")
    }
    fun helpText() {
        log("""
            §7[§6TPoint§7] §fTPoint v1.0.0
            §7[§6TPoint§7] §f/tpoint help
            §7[§6TPoint§7] §f/tpoint set <name>
            §7[§6TPoint§7] §f/tpoint del <name>
            §7[§6TPoint§7] §f/tpoint list
            §7[§6TPoint§7] §f/tpoint <name>
        """)
    }
    fun setSuccess(name: String) {
        Config.defaultConfig.get("set-success")?.let {
            log(it.toString().replace("%name%", name))
        }
    }
    fun delSuccess(name: String) {
        Config.defaultConfig.get("del-success")?.let {
            log(it.toString().replace("%name%", name))
        }
    }
    fun teleportSuccess(location: Location) {
        Config.defaultConfig.get("tp-success")?.let {
            log(it.toString().replace("%x", location.x.toString()).replace("%y%", location.y.toString()).replace("%z%", location.z.toString()))
        }
    }
    fun noPermission(name: String) {
        Config.defaultConfig.get("no-permission")?.let {
            log(it.toString().replace("%name%", name))
        }
    }
    fun delayMsg(name: String) {
        Config.defaultConfig.get("delay-msg")?.let {
            log(it.toString().replace("%name%", name))
        }
    }
    fun noTPoint(name: String) {
        Config.defaultConfig.get("no-point")?.let {
            log(it.toString().replace("%name%", name))
        }
    }
}