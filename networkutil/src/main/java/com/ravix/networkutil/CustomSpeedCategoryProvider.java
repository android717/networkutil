package com.ravix.networkutil;

/**
 * Created by Ravix
 * Date: 09-07-2024
 * Time: 10:51
 */


public class CustomSpeedCategoryProvider implements TGNetworkUtil.SpeedCategoryProvider {
    private final int veryHighThreshold;
    private final int highThreshold;
    private final int moderateThreshold;
    private final int lowThreshold;
    private final int veryLowThreshold;

    public CustomSpeedCategoryProvider(int veryHighThreshold, int highThreshold, int moderateThreshold, int lowThreshold, int veryLowThreshold) {
        this.veryHighThreshold = veryHighThreshold;
        this.highThreshold = highThreshold;
        this.moderateThreshold = moderateThreshold;
        this.lowThreshold = lowThreshold;
        this.veryLowThreshold = veryLowThreshold;
    }

    @Override
    public SpeedCategory getSpeedCategory(int downSpeedKbps) {
        if (downSpeedKbps >= veryHighThreshold) {
            return SpeedCategory.VERY_HIGH;
        } else if (downSpeedKbps >= highThreshold) {
            return SpeedCategory.HIGH;
        } else if (downSpeedKbps >= moderateThreshold) {
            return SpeedCategory.MODERATE;
        } else if (downSpeedKbps >= lowThreshold) {
            return SpeedCategory.LOW;
        } else if (downSpeedKbps >= veryLowThreshold) {
            return SpeedCategory.VERY_LOW;
        } else {
            return SpeedCategory.NO_INTERNET;
        }
    }
}
