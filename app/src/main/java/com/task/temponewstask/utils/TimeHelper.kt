package com.task.temponewstask.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeHelper {
    val todayTimeString: String
        get() {
            val c = Calendar.getInstance()
            val df = SimpleDateFormat("yyyy-MM-dd")
            return df.format(c.time)
        }

    @JvmStatic
    fun reformatBackEndDate(backendDate: String?): String {
//        2020-08-14T10:36:39Z
        var format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        var newDate: Date? = null
        try {
            newDate = format.parse(backendDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        format = SimpleDateFormat("hh:mm MMM dd,yyyy")
        return format.format(newDate)
    }
}