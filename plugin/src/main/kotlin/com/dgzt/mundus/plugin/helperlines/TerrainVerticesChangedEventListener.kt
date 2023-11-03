package com.dgzt.mundus.plugin.helperlines

import com.badlogic.gdx.Gdx
import main.com.mbrlabs.mundus.editorcommons.events.TerrainVerticesChangedEvent

class TerrainVerticesChangedEventListener : TerrainVerticesChangedEvent.TerrainVerticesChangedEventListener {

    var changedNum = 0

    override fun onTerrainVerticesChanged(event: TerrainVerticesChangedEvent) {
        ++changedNum
        Gdx.app.log("", "onTerrainVerticesChanged $changedNum")
    }
}
