package dev.enderman.unboat

import org.bukkit.entity.Mob
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityTargetEvent

class AggravateListener : Listener {
    @EventHandler
    @Suppress("unused")
    private fun onAggravate(event: EntityTargetEvent) {
        val entity = event.entity

        if (entity !is Mob) return
        if (entity.target == null) return

        entity.leaveVehicle()
    }
}
