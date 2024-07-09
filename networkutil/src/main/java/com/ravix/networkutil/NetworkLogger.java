package com.ravix.networkutil;

import android.util.Log;

/**
 * Created by Ravix
 * Date: 09-07-2024
 * Time: 10:26
 */


public class NetworkLogger {

    private static final String TAG = "NetworkLogger";

    public static void log(NetworkState networkState) {
        String logMessage = buildNetworkStateLog(networkState);
        Log.d(TAG, logMessage);
    }

    public static void logInternetConnected() {
        String logMessage = buildEventLog("Internet Connected");
        Log.i(TAG, logMessage);
    }

    public static void logInternetDisconnected() {
        String logMessage = buildEventLog("Internet Disconnected");
        Log.w(TAG, logMessage);
    }

    private static String buildNetworkStateLog(NetworkState networkState) {
        return "=== Network State ===\n" +
                "Download Speed: " + networkState.getDownSpeed() + " kbps\n" +
                "Upload Speed: " + networkState.getUpSpeed() + " kbps\n" +
                "Has Internet: " + (networkState.hasInternet() ? "Yes" : "No") + "\n" +
                "Speed Category: " + networkState.getSpeedCategory() + "\n" +
                "====================";
    }

    private static String buildEventLog(String event) {
        return "=== Network Event ===\n" +
                "-- " + event + " --" + "\n" +
                "====================";
    }
}