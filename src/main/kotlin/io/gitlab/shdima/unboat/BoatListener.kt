package io.gitlab.shdima.unboat

import org.bukkit.entity.Mob
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.vehicle.VehicleEnterEvent

class BoatListener : Listener {
    @EventHandler
    @Suppress("unused")
    private fun onEnterVehicle(event: VehicleEnterEvent) {
        val entity = event.entered

        if (entity !is Mob) return
        if (entity.target == null) return

        event.isCancelled = true
    }
}
