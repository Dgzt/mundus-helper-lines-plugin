package com.dgzt.mundus.plugin.helperlines

import com.badlogic.gdx.Gdx
import main.com.mbrlabs.mundus.editorcommons.events.TerrainVerticesChangedEvent

class TerrainVerticesChangedEventListener : TerrainVerticesChangedEvent.TerrainVerticesChangedEventListener {

    override fun onTerrainVerticesChanged(event: TerrainVerticesChangedEvent) {
        HelperLinesManager.updateVertices(event.terrainComponent)
    }
}
