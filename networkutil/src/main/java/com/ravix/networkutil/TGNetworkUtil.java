package com.ravix.networkutil;

/**
 * Created by Ravix
 * Date: 08-07-2024
 * Time: 10:40
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TGNetworkUtil {

    private static TGNetworkUtil instance;
    private final ConnectivityManager connectivityManager;
    private final MutableLiveData<NetworkState> networkStateLiveData = new MutableLiveData<>();
    private final NetworkCallback networkCallback = new NetworkCallback();
    private final boolean keepLogging;
    private SpeedCategoryProvider speedCategoryProvider;

    private TGNetworkUtil(Context context, boolean keepLogging, SpeedCategoryProvider speedCategoryProvider) {
        this.keepLogging = keepLogging;
        this.speedCategoryProvider = speedCategoryProvider;
        connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
        // Immediate check
        updateNetworkState();
    }

    public static synchronized TGNetworkUtil getInstance(Context context, boolean keepLogging, SpeedCategoryProvider speedCategoryProvider) {
        if (instance == null) {
            instance = new TGNetworkUtil(context, keepLogging, speedCategoryProvider);
        }
        return instance;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkStateLiveData;
    }

    public boolean isInternetAvailable() {
        updateNetworkState(); // Ensure state is up-to-date
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }

    public void unregisterNetworkCallback() {
        if (connectivityManager != null && networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    public void updateNetworkState() {
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        NetworkState networkState;
        if (networkCapabilities != null) {
            int downSpeed = networkCapabilities.getLinkDownstreamBandwidthKbps();
            int upSpeed = networkCapabilities.getLinkUpstreamBandwidthKbps();
            boolean hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            SpeedCategory speedCategory = speedCategoryProvider.getSpeedCategory(downSpeed);
            networkState = new NetworkState(downSpeed, upSpeed, hasInternet, speedCategory);

            if (keepLogging) {
                NetworkLogger.log(networkState);
                if (hasInternet) {
                    NetworkLogger.logInternetConnected();
                } else {
                    NetworkLogger.logInternetDisconnected();
                }
            }

        } else {
            networkState = new NetworkState(0, 0, false, SpeedCategory.NO_INTERNET);

            if (keepLogging) {
                NetworkLogger.log(networkState);
                NetworkLogger.logInternetDisconnected();
            }
        }
        networkStateLiveData.postValue(networkState);
    }

    private class NetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(@NonNull Network network) {
            updateNetworkState();
        }

        @Override
        public void onLost(@NonNull Network network) {
            updateNetworkState();
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            updateNetworkState();
        }
    }

    public interface SpeedCategoryProvider {
        SpeedCategory getSpeedCategory(int downSpeedKbps);
    }


}
