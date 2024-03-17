package com.dgzt.mundus.plugin.helperlines.listener

import com.dgzt.mundus.plugin.helperlines.HelperLinesManager
import com.dgzt.mundus.plugin.helperlines.PropertyManager
import com.mbrlabs.mundus.editorcommons.events.TerrainAddedEvent

class TerrainAddedEventListener : TerrainAddedEvent.TerrainAddedEventListener {
    override fun onTerrainAdded(event: TerrainAddedEvent) {
        val terrainComponent = event.terrainComponent
        PropertyManager.terrains.add(terrainComponent)

        if (PropertyManager.enabled) {
            HelperLinesManager.addNewHelperLineShape(terrainComponent)
        }
    }
}
