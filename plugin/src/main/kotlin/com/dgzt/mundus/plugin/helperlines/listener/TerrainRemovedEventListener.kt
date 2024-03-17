package com.dgzt.mundus.plugin.helperlines.listener

import com.dgzt.mundus.plugin.helperlines.HelperLinesManager
import com.dgzt.mundus.plugin.helperlines.PropertyManager
import com.mbrlabs.mundus.editorcommons.events.TerrainRemovedEvent

class TerrainRemovedEventListener : TerrainRemovedEvent.TerrainRemovedEventListener {
    override fun onTerrainRemoved(event: TerrainRemovedEvent) {
        val terrainComponent = event.terrainComponent
        PropertyManager.terrains.removeValue(terrainComponent, true)

        if (PropertyManager.enabled) {
            HelperLinesManager.removeHelperLineShape(terrainComponent)
        }
    }
}
