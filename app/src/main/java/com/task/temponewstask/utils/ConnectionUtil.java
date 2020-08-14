package com.task.temponewstask.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.task.temponewstask.AppClass;


public class ConnectionUtil {
    public static NetworkInfo getNetworkInfo() {
        ConnectivityManager connMgr = (ConnectivityManager) AppClass.Companion.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connMgr.getActiveNetworkInfo();
    }

    public static boolean isOnline() {
        NetworkInfo networkInfo = ConnectionUtil.getNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isGpsWorking() {

        final LocationManager manager = (LocationManager) AppClass.Companion.getInstance().getSystemService(Context.LOCATION_SERVICE);

        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
