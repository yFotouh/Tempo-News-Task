package com.task.temponewstask.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object FileSystemHelper {
    fun readFileFromAssests(
        context: Context,
        fileName: String?
    ): String {
        val returnString = StringBuilder()
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(
                InputStreamReader(context.assets.open(fileName!!), "UTF-8")
            )

            // do reading, usually loop until end of file reading
            var mLine: String?
            while (reader.readLine().also { mLine = it } != null) {
                returnString.append(mLine)
                //process line
            }
        } catch (e: IOException) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    //log the exception
                }
            }
        }
        return returnString.toString()
    }
}