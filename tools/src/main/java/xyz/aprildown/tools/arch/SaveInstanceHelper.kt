@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface SaveInstanceHelper {
    fun save(key: String, value: Any?)
    fun <T> getAndRemove(key: String): T?

    companion object {
        fun withHelper(fragment: Fragment, f: SaveInstanceHelper.() -> Unit) {
            f.invoke(ViewModelProvider(fragment)[SaveInstanceViewModel::class.java])
        }

        fun withHelper(activity: FragmentActivity, f: SaveInstanceHelper.() -> Unit) {
            f.invoke(ViewModelProvider(activity)[SaveInstanceViewModel::class.java])
        }
    }
}

internal class SaveInstanceViewModel : ViewModel(), SaveInstanceHelper {
    private lateinit var cachedData: ArrayMap<String, Any>

    override fun save(key: String, value: Any?) {
        if (!::cachedData.isInitialized) {
            cachedData = arrayMapOf()
        }
        cachedData[key] = value
    }

    override fun <T> getAndRemove(key: String): T? {
        return if (::cachedData.isInitialized) {
            val value = cachedData[key]
            cachedData.remove(key)
            @Suppress("UNCHECKED_CAST")
            value as? T
        } else null
    }

    override fun onCleared() {
        if (::cachedData.isInitialized) {
            cachedData.clear()
        }
    }
}
