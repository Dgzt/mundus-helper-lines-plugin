package com.dgzt.mundus.plugin.helperlines

import com.badlogic.gdx.math.Vector3

data class HelperLineCenterObject(
        var x: Int = 0,
        var y: Int = 0,
        var position: Vector3 = Vector3(),
        var full: Boolean = true
) {

    fun initialize(x: Int, y: Int, position: Vector3, full: Boolean): HelperLineCenterObject{
        this.x = x
        this.y = y
        this.position = position
        this.full = full

        return this
    }
}
