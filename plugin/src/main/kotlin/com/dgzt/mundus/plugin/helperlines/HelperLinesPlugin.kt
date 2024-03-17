/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.dgzt.mundus.plugin.helperlines

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g3d.RenderableProvider
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.dgzt.mundus.plugin.helperlines.listener.GameObjectModifiedListener
import com.dgzt.mundus.plugin.helperlines.listener.TerrainAddedEventListener
import com.dgzt.mundus.plugin.helperlines.listener.TerrainRemovedEventListener
import com.dgzt.mundus.plugin.helperlines.listener.TerrainVerticesChangedEventListener
import com.mbrlabs.mundus.commons.scene3d.components.TerrainComponent
import com.mbrlabs.mundus.pluginapi.DisposeExtension
import com.mbrlabs.mundus.pluginapi.EventExtension
import com.mbrlabs.mundus.pluginapi.MenuExtension
import com.mbrlabs.mundus.pluginapi.PluginEventManager
import com.mbrlabs.mundus.pluginapi.RenderExtension
import com.mbrlabs.mundus.pluginapi.SceneExtension
import com.mbrlabs.mundus.pluginapi.StatusBarExtension
import com.mbrlabs.mundus.pluginapi.TerrainHoverExtension
import com.mbrlabs.mundus.pluginapi.ui.LabelWidget
import com.mbrlabs.mundus.pluginapi.ui.RootWidget
import com.mbrlabs.mundus.pluginapi.ui.Widget
import org.pf4j.Extension
import org.pf4j.Plugin

class HelperLinesPlugin : Plugin() {

    companion object {
        const val RECTANGLE_RADIO_BUTTON_TEXT = "Rectangle"
        const val HEXAGON_RADIO_BUTTON_TEXT = "Hexagon"
    }

    @Extension
    class HelperLinesMenuExtension : MenuExtension {
        override fun getMenuName(): String = "Helper lines"
        override fun setupDialogRootWidget(root: RootWidget) {
            root.addCheckbox("Enabled", PropertyManager.enabled) {
                PropertyManager.enabled = it
                if (PropertyManager.enabled) {
                    HelperLinesManager.createHelperLines()
                } else {
                    HelperLinesManager.clearHelperLines()
                }
            }.setAlign(Widget.WidgetAlign.LEFT)
            root.addRow()
            root.addLabel("")
            root.addRow()
            root.addSpinner("Line width", 0.1f, 30f, PropertyManager.lineWidth, 0.1f) { PropertyManager.lineWidth = it }.setAlign(Widget.WidgetAlign.LEFT)
            root.addRow()
            root.addRadioButtons(RECTANGLE_RADIO_BUTTON_TEXT, HEXAGON_RADIO_BUTTON_TEXT, HelperLinesType.RECTANGLE == PropertyManager.type) {
                when (it) {
                    RECTANGLE_RADIO_BUTTON_TEXT -> PropertyManager.type = HelperLinesType.RECTANGLE
                    HEXAGON_RADIO_BUTTON_TEXT -> PropertyManager.type = HelperLinesType.HEXAGON
                }

                if (PropertyManager.enabled) {
                    HelperLinesManager.clearHelperLines()
                    HelperLinesManager.createHelperLines()
                }
            }.setAlign(Widget.WidgetAlign.LEFT)
            root.addRow()
            root.addSpinner("Column", 2, 100, PropertyManager.column) {
                PropertyManager.column = it

                if (PropertyManager.enabled) {
                    HelperLinesManager.clearHelperLines()
                    HelperLinesManager.createHelperLines()
                }
            }.setAlign(Widget.WidgetAlign.LEFT)
            root.addRow()
            root.addSpinner("Counter offset X", Int.MIN_VALUE, Int.MAX_VALUE, PropertyManager.counterOffsetX) {
                PropertyManager.counterOffsetX = it

                if (PropertyManager.enabled) {
                    HelperLinesManager.clearHelperLines()
                    HelperLinesManager.createHelperLines()
                }
            }.setAlign(Widget.WidgetAlign.LEFT)
            root.addRow()
            root.addSpinner("Counter offset Y", Int.MIN_VALUE, Int.MAX_VALUE, PropertyManager.counterOffsetY) {
                PropertyManager.counterOffsetY = it

                if (PropertyManager.enabled) {
                    HelperLinesManager.clearHelperLines()
                    HelperLinesManager.createHelperLines()
                }
            }.setAlign(Widget.WidgetAlign.LEFT)
            root.addRow()
            root.addLabel("")
            root.addRow()
            root.addLabel("Version: 0.0.1").setAlign(Widget.WidgetAlign.RIGHT)
        }

    }

    @Extension
    class HelperLinesEventExtension : EventExtension {

        override fun manageEvents(pluginEventManager: PluginEventManager) {
            pluginEventManager.registerEventListener(TerrainVerticesChangedEventListener())
            pluginEventManager.registerEventListener(TerrainAddedEventListener())
            pluginEventManager.registerEventListener(TerrainRemovedEventListener())
            pluginEventManager.registerEventListener(GameObjectModifiedListener())
        }

    }

    @Extension
    class HelperLinesSceneExtension : SceneExtension {
        override fun sceneLoaded(terrains: Array<TerrainComponent>) {
            PropertyManager.terrains.addAll(terrains)
        }

    }

    @Extension
    class HelperLinesRenderExtension : RenderExtension {

        private val renderableProvider: RenderableProvider

        init {
            renderableProvider = RenderableProvider { renderables, pool ->
                run {
                    Gdx.gl.glLineWidth(PropertyManager.lineWidth)
                    HelperLinesManager.helperLineShapes.forEach { it.modelInstance.getRenderables(renderables, pool) }
                }
            }
        }

        override fun getRenderableProvider(): RenderableProvider = renderableProvider
    }

    @Extension
    class HelperLineStatusBarExtension : StatusBarExtension {
        override fun createStatusBarLabel(label: LabelWidget) {
            PropertyManager.statusBarLabel = label
        }
    }

    @Extension
    class HelperLinesTerrainHooverExtension : TerrainHoverExtension {
        override fun hover(terrainComponent: TerrainComponent?, intersection: Vector3?) {
            if (PropertyManager.enabled) {
                if (terrainComponent != null && intersection != null) {
                    val helperLineCenterObject = HelperLinesManager.findHelperLineCenterObject(terrainComponent, intersection)

                    if (helperLineCenterObject != null) {
                        PropertyManager.statusBarLabel?.setText("${helperLineCenterObject.x} x ${helperLineCenterObject.y}")
                    } else {
                        PropertyManager.statusBarLabel?.setText("")
                    }
                } else {
                    PropertyManager.statusBarLabel?.setText("")
                }
            }
        }

    }

    @Extension
    class HelperLineDisposeExtension : DisposeExtension {
        override fun dispose() {
            HelperLinesManager.clearHelperLines()
        }
    }
}
