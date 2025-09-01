package dev.enderman.unboat

import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Unboat : JavaPlugin() {

    private val id = 27128

    override fun onEnable() {
        server.pluginManager.registerEvents(BoatListener(), this)
        server.pluginManager.registerEvents(AggravateListener(), this)

        Metrics(this, id)
    }
}
