@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

@Deprecated("Use MediatorLiveData to get more flexibility")
class CombinedLiveData2<First, Second>(
    liveData1: LiveData<First>,
    liveData2: LiveData<Second>
) : MediatorLiveData<Pair<First, Second>>() {
    init {
        fun fireEvent() {
            liveData1.value?.let { first ->
                liveData2.value?.let { second ->
                    value = first to second
                }
            }
        }

        addSource(liveData1) {
            fireEvent()
        }
        addSource(liveData2) {
            fireEvent()
        }
    }
}
