package com.ravix.networkutil;

/**
 * Created by Ravix
 * Date: 08-07-2024
 * Time: 11:24
 */
public class InternetConstants {

    // Constants for speeds in kbps
    public static final int Kbps_1 = 1;
    public static final int Kbps_100 = 100;
    public static final int Kbps_200 = 200;
    public static final int Kbps_300 = 300;
    public static final int Kbps_1000 = 1000; // 1 Mbps
    public static final int Kbps_3000 = 3000; // 3 Mbps
    public static final int Kbps_10000 = 10000; // 10 Mbps
    public static final int Kbps_20000 = 20000; // 20 Mbps
    public static final int Kbps_25000 = 25000; // 25 Mbps
    public static final int Kbps_30000 = 30000; // 25 Mbps
    public static final int Kbps_50000 = 50000; // 50 Mbps
    public static final int Kbps_100000 = 100000; // 100 Mbps

    // Constants for speeds in Mbps
    public static final int Mbps_1 = 1;
    public static final int Mbps_3 = 3;
    public static final int Mbps_5 = 5;
    public static final int Mbps_10 = 10;
    public static final int Mbps_20 = 20;
    public static final int Mbps_25 = 25;
    public static final int Mbps_30 = 30;
    public static final int Mbps_50 = 50;
    public static final int Mbps_100 = 100;


    // Utility methods for conversion
    public static int toKbps(int mbps) {
        return mbps * 1000;
    }

    public static int toMbps(int kbps) {
        return kbps / 1000;
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println("1 Mbps in kbps: " + toKbps(Mbps_1));
        System.out.println("10000 kbps in Mbps: " + toMbps(Kbps_10000));
    }
}