package ren.lawliet.tPoint

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.UUID

/**
 *@author        Coaixy
 *@createTime    2024-07-21
 *@packageName   ren.lawliet.tPoint
 */

object Config {
    private lateinit var defaultConfigFile: File
    private lateinit var TPointFolder: File
    lateinit var defaultConfig: YamlConfiguration
    fun setDefaultConfigFile(file: File) {
        defaultConfigFile = file
        defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigFile)
    }

    fun setTPointFolder(filePath: String) {
        TPointFolder = File(filePath)
    }

    fun getPlayerTPoints(uuid: UUID): YamlConfiguration {
        return YamlConfiguration.loadConfiguration(getPlayerTPointFile(uuid))
    }

    fun getPlayerTPointFile(uuid: UUID): File {
        val playerTPointFile = File(TPointFolder, "$uuid.yml")
        if (!playerTPointFile.exists()) {
            playerTPointFile.createNewFile()
        }
        return playerTPointFile
    }
}