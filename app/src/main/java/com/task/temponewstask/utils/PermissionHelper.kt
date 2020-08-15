package com.task.temponewstask.utils

import android.content.Context
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

object PermissionHelper {
    fun getPermission(
        context: Context?,
        listener: MultiplePermissionsListener?,
        vararg permission: String?
    ) {
        Dexter.withContext(context)
            .withPermissions(*permission)
            .withListener(listener)
            .check()
    }
}