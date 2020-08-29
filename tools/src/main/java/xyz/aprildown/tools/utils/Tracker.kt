@file:Suppress("unused")

package xyz.aprildown.tools.utils

interface Tracker {
    fun track(eventName: String, eventContent: Map<String, String> = emptyMap())

    fun track(eventName: String, property: String, value: String) {
        track(eventName, mapOf(property to value))
    }

    companion object {
        const val TAG_CRASH_ATTACHMENT = "crash_attachment"
    }
}
