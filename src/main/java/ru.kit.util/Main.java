package ru.kit.util;

import java.util.Arrays;

/**
 *
 */
class Main {
    static String keyPath = "SYSTEM\\CurrentControlSet\\Enum\\USB\\VID_10C4&PID_EA60\\";
    static String device1 = "0001";
    public static void main(String[] args) {
        /*System.out.println(keyPath+device1);
        System.out.println("First COM device: " + ComDetector.getComNumber(keyPath + device1));
        System.out.println("First COM device: " + ComDetector.getFriendlyName(keyPath + device1));*/
        System.out.println(Arrays.toString(ComDetector.getSerialPortsByDeviceName("Sili")));
    }
}
