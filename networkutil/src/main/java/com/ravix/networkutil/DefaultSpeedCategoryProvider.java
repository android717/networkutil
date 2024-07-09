package com.ravix.networkutil;

/**
 * Created by Ravix
 * Date: 09-07-2024
 * Time: 10:50
 */

public class DefaultSpeedCategoryProvider implements TGNetworkUtil.SpeedCategoryProvider {

    @Override
    public SpeedCategory getSpeedCategory(int downSpeedKbps) {
        if (downSpeedKbps >= InternetConstants.Kbps_100000) { // 100 Mbps
            return SpeedCategory.VERY_HIGH;
        } else if (downSpeedKbps >= InternetConstants.Kbps_25000) { // 25 Mbps
            return SpeedCategory.HIGH;
        } else if (downSpeedKbps >= InternetConstants.Kbps_10000) { // 10 Mbps
            return SpeedCategory.MODERATE;
        } else if (downSpeedKbps >= InternetConstants.Kbps_3000) { // 3 Mbps
            return SpeedCategory.LOW;
        } else if (downSpeedKbps >= InternetConstants.Kbps_1000) { // 1 Mbps
            return SpeedCategory.VERY_LOW;
        } else {
            return SpeedCategory.NO_INTERNET;
        }
    }
}
