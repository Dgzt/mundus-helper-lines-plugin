/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.dgzt.mundus.plugin.helperlines

import com.mbrlabs.mundus.pluginapi.Example
import org.pf4j.Extension
import org.pf4j.Plugin

class HelperLinesPlugin : Plugin() {

    @Extension
    class A : Example {
        override fun getName(): String {
            return "Helper lines"
        }
    }
}
