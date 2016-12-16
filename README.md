# serial_port_detector_lib
**Update: 16.12.2016**
<p>Added new method: `public static String[] getSerialPortsByDeviceName(String deviceName){...}`
<p>*Returns String array of serial ports which matching deviceName.*

**Initial: 15.12.2016**
<p>~~This API let you get device serial(com)-port number or device name as Windows Device Manager displays.~~
<p>~~You just need to identify your device in Windows Registry at once. To identify your device, just open Windows Registry.~~
<p>~~Path: `HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Enum\USB\`~~
<p>~~Now look for your serial-port device, there should be something like this: `VID_10C4&PID_EA60\0001` or `VID_0A89&PID_0008\5&607b864&0&3`.~~
<p>~~In my example, i have catalog named 0001, that's what i need. I can make sure it is my device, by looking at the properties of catalog. There should be FriendlyName property and in the end of a string-value should be serial-port of device.
The API takes this number from the end of a string.~~ 
<p><i>No magic - just parsing.


