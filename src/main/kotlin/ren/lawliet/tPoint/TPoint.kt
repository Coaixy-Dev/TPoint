package ren.lawliet.tPoint

import org.bukkit.plugin.java.JavaPlugin
import ren.lawliet.tPoint.Commands.MainCommand
import java.io.File

class TPoint : JavaPlugin() {

    override fun onEnable() {
        logger.info("TPoint Loading...")
        getCommand("tpoint")?.setExecutor(MainCommand())
        reload()
    }
    private fun reload(){
        val defaultConfigFile = File(dataFolder, "config.yml")
        if (!defaultConfigFile.exists()) {
            saveDefaultConfig()
        }
        Config.setDefaultConfigFile(defaultConfigFile)
        Config.setTPointFolder(dataFolder.path + "/TPoint")
        reloadConfig()
    }
}
