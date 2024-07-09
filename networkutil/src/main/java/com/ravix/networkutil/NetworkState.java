package com.ravix.networkutil;

import androidx.annotation.NonNull;

/**
 * Created by Ravix
 * Date: 09-07-2024
 * Time: 10:21
 */


public class NetworkState {
    private final int downSpeed;
    private final int upSpeed;
    private final boolean hasInternet;
    private final SpeedCategory speedCategory;

    public NetworkState(int downSpeed, int upSpeed, boolean hasInternet, SpeedCategory speedCategory) {
        this.downSpeed = downSpeed;
        this.upSpeed = upSpeed;
        this.hasInternet = hasInternet;
        this.speedCategory = speedCategory;
    }

    public int getDownSpeed() {
        return downSpeed;
    }

    public int getUpSpeed() {
        return upSpeed;
    }

    public boolean hasInternet() {
        return hasInternet;
    }

    public SpeedCategory getSpeedCategory() {
        return speedCategory;
    }

    @NonNull
    @Override
    public String toString() {
        return "Download Speed: " + downSpeed + " kbps\n" +
                "Upload Speed: " + upSpeed + " kbps\n" +
                "Has Internet: " + (hasInternet ? "Yes" : "No") + "\n" +
                "Speed Category: " + speedCategory + "\n";
    }
}

