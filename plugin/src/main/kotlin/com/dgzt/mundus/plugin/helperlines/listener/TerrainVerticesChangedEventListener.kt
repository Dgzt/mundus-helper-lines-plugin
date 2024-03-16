package com.dgzt.mundus.plugin.helperlines.listener

import com.dgzt.mundus.plugin.helperlines.HelperLinesManager
import main.com.mbrlabs.mundus.editorcommons.events.TerrainVerticesChangedEvent

class TerrainVerticesChangedEventListener : TerrainVerticesChangedEvent.TerrainVerticesChangedEventListener {

    override fun onTerrainVerticesChanged(event: TerrainVerticesChangedEvent) {
        HelperLinesManager.updateVertices(event.terrainComponent)
    }
}
