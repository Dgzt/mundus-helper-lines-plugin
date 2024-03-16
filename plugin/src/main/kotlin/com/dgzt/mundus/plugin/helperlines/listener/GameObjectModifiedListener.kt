package com.dgzt.mundus.plugin.helperlines.listener

import com.dgzt.mundus.plugin.helperlines.HelperLinesManager
import com.dgzt.mundus.plugin.helperlines.PropertyManager
import com.mbrlabs.mundus.commons.scene3d.components.Component
import com.mbrlabs.mundus.commons.scene3d.components.TerrainComponent
import main.com.mbrlabs.mundus.editorcommons.events.GameObjectModifiedEvent

class GameObjectModifiedListener : GameObjectModifiedEvent.GameObjectModifiedListener {
    override fun onGameObjectModified(event: GameObjectModifiedEvent) {
        if (!PropertyManager.enabled) return

        val gameObject = event.gameObject ?: return
        val terrainComponent: TerrainComponent = (gameObject.findComponentByType(Component.Type.TERRAIN)?: return)

        if (gameObject.active && !HelperLinesManager.contains(terrainComponent)) {
            HelperLinesManager.addNewHelperLineShape(terrainComponent)
        } else if (!gameObject.active && HelperLinesManager.contains(terrainComponent)) {
            HelperLinesManager.removeHelperLineShape(terrainComponent)
        }
    }
}
