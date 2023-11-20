package com.dgzt.mundus.plugin.helperlines

import com.badlogic.gdx.utils.Array
import com.mbrlabs.mundus.commons.scene3d.components.TerrainComponent

object PropertyManager {
    const val DEFAULT_COLUMN = 2
    const val DEFAULT_COUNTER_OFFSET_X = 0
    const val DEFAULT_COUNTER_OFFSET_Y = 0

    var enabled = false
    var type = HelperLinesType.RECTANGLE
    var column = DEFAULT_COLUMN
    var counterOffsetX = DEFAULT_COUNTER_OFFSET_X
    var counterOffsetY = DEFAULT_COUNTER_OFFSET_Y
    var terrains = Array<TerrainComponent>()
}
