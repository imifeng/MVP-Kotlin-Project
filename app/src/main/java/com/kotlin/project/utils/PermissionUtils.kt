package com.kotlin.project.utils

import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.kotlin.project.MApplication
import permissions.dispatcher.PermissionUtils

object PermissionUtils {

    private val PERMISSION_LOCATION: Array<String> =
        arrayOf(
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION"
        )


    /**
     * Check Bluetooth Permission
     */
    fun isCheckBluetoothPermission(context: Context): Boolean {
        val manager: BluetoothManager =
            context.getSystemService(AppCompatActivity.BLUETOOTH_SERVICE) as BluetoothManager
        return manager.adapter.isEnabled
    }

    /**
     * Check Location Permission
     */
    fun isCheckLocationPermission(context: Context): Boolean {
        if (PermissionUtils.hasSelfPermissions(context, *PERMISSION_LOCATION)) {
            return true
        }
        return false
    }

    /**
     * Check Notify Setting
     */
    fun isCheckNotifySetting(context: Context): Boolean {
        val manager = NotificationManagerCompat.from(context)
        return manager.areNotificationsEnabled()
    }

    /**
     * goto Settings
     */
    fun gotoSettings(context: Context){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:${MApplication.applicationContext.packageName}")
        context.startActivity(intent)
    }
}