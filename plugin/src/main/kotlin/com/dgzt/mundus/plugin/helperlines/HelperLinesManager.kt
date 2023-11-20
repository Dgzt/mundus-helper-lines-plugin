package com.dgzt.mundus.plugin.helperlines

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import com.mbrlabs.mundus.commons.scene3d.components.TerrainComponent

object HelperLinesManager {

    private val helperLineShapes = Array<HelperLineShape>()

    fun createHelperLines() {
        Gdx.app.log("", "Create helper lines")
        PropertyManager.terrains.forEach { addNewHelperLineShape(it) }
    }

    fun clearHelperLines() {
        Gdx.app.log("", "Clear helper lines")
        helperLineShapes.forEach { it.dispose() }
        helperLineShapes.clear()
    }

    private fun addNewHelperLineShape(terrainComponent: TerrainComponent) {
        val width = PropertyManager.column
        val counterOffsetX = PropertyManager.counterOffsetX
        val counterOffsetY = PropertyManager.counterOffsetY

        val helperLineShape : HelperLineShape
        if (PropertyManager.type == HelperLinesType.RECTANGLE) {
            helperLineShape = RectangleHelperLineShape(width, counterOffsetX, counterOffsetY, terrainComponent)
        } else {
            helperLineShape = HexagonHelperLineShape(width, counterOffsetX, counterOffsetY, terrainComponent)
        }
        helperLineShapes.add(helperLineShape)
    }
}
