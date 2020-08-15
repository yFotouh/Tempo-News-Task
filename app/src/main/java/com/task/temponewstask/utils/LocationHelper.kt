package com.task.temponewstask.utils

import android.content.Context
import android.location.Location
import io.nlopez.smartlocation.SmartLocation

object LocationHelper {
    fun getSingleLocation(context: Context?): SingleLiveEvent<Location> {
        val liveEvent =
            SingleLiveEvent<Location>()
        SmartLocation.with(context).location()
            .oneFix()
            .start { location -> liveEvent.value = location }
        return liveEvent
    }
}