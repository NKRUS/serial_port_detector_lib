package ru.kit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This API lets you to get device <b>serial(com)-port number</b> or <b>device name</b> as Windows Device Manager displays.
 * There are just two methods - {@link #getFriendlyName(String registryKey)} and {@link #getComNumber(String registryKey)} which getting <b>registryKey</b> as a parameter.
 *
 * <p><tt><b>registryKey</b></tt> - is a path from Windows registry. This path points to the device-record. The record allows us to get information about device,
 * including serial-port(com-port) number.
 *
 * <p><tt><b>registryKey</b></tt> example:<p>
 *
 * As system path - SYSTEM\CurrentControlSet\Enum\USB\VID_10C4&PID_EA60\0001
 * <p>As method parameter - <tt>"SYSTEM\\CurrentControlSet\\Enum\\USB\\VID_10C4&PID_EA60\\0001"</tt>
 * <p>or<p>
 * <tt>"SYSTEM\\CurrentControlSet\\Enum\\USB\\VID_10C4&PID_EA60\\5&607b864&0&10"</tt>
 * @author Nikita Solovyov
 * @version 1.0.0
 *
 */
public class ComDetector {

    /**
     * Returns name of device, like Windows Device Manager shows it.
     * For example:
     * <p><b>registryKey =</b> <tt>"SYSTEM\\CurrentControlSet\\Enum\\USB\\VID_10C4&PID_EA60\\0001"</tt></p>
     * <p><b>returns = </b> Silicon Labs CP210x USB to UART Bridge (COM8)</p>
     * @param registryKey path to the device-record in the Windows Registry
     * @return - Friendly Name of device.
     */
    public static String getFriendlyName(String registryKey) {
        if (registryKey == null || registryKey.isEmpty()) {
            throw new IllegalArgumentException("'registryKey' null or empty");
        }
        try {
            int hkey = WinRegistry.HKEY_LOCAL_MACHINE;
            return WinRegistry.readString(hkey, registryKey, "FriendlyName");
        } catch (Exception ex) { // catch-all:
            // readString() throws IllegalArg, IllegalAccess, InvocationTarget
            System.err.println(ex.getMessage());
            return null;
        }
    }


    /**
     * Returns serial(com)-port number of device. It using {@link #getFriendlyName(String registryKey)}.
     * <p>Given a registry key, attempts to parse out the integer after substring "COM" in the 'FriendlyName' value; returns -1 on failure.
     * @param registryKey - path to the device-record in the Windows Registry
     * @return - serial(com)port number of device; returns -1 on failure.
     */
    public static int getComNumber(String registryKey) {
        String friendlyName = getFriendlyName(registryKey);

        if (friendlyName != null && friendlyName.indexOf("COM") >= 0) {
            String substr = friendlyName.substring(friendlyName.indexOf("COM"));
            Matcher matchInt = Pattern.compile("\\d+").matcher(substr);
            if (matchInt.find()) {
                return Integer.parseInt(matchInt.group());
            }
        }
        return -1;
    }

}
