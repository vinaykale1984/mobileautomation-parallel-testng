package com.qa.utils;

public class GlobalParameters {

    private static ThreadLocal<String> platformName = new ThreadLocal<>();
    private static ThreadLocal<String> udid = new ThreadLocal<>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<>();
    private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<>();

    public void setPlatformName(String platformName1) {
        platformName.set(platformName1);
    }

    public String getPlatformName(){
        return platformName.get();
    }

    public void setUdid(String udid1) {
        udid.set(udid1);
    }

    public String getUdid(){
        return udid.get();
    }

    public void setDeviceName(String deviceName1) {
        deviceName.set(deviceName1);
    }

    public String getDeviceName(){
        return deviceName.get();
    }

    public void setSystemPort(String systemPort1) {
        systemPort.set(systemPort1);
    }

    public String getSystemPort(){
        return systemPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort1) {
        chromeDriverPort.set(chromeDriverPort1);
    }

    public String getChromeDriverPort(){
        return chromeDriverPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort1) {
        wdaLocalPort.set(wdaLocalPort1);
    }

    public String getWdaLocalPort(){
        return wdaLocalPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort1){
        webkitDebugProxyPort.set(webkitDebugProxyPort1);
    }

    public String getWebkitDebugProxyPort(){
        return webkitDebugProxyPort.get();
    }

    public void initializeGlobalParameters(){
        GlobalParameters parameters = new GlobalParameters();
        parameters.setPlatformName(System.getProperty("platformName", "Android"));
        parameters.setUdid(System.getProperty("udid", "fdd1f933"));
        parameters.setDeviceName(System.getProperty("deviceName", "OnePlus 6"));

//        parameters.setPlatformName(System.setProperty("platformName", "PlatformName"));
//        parameters.setUdid(System.setProperty("udid", "UDID"));
//        parameters.setDeviceName(System.setProperty("deviceName", "DeviceName"));

        System.out.println(parameters.getPlatformName());

        switch(parameters.getPlatformName()) {
            case "Android":
                parameters.setSystemPort(System.getProperty("systemport", "10000"));
                parameters.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
                break;
            case "iOS":
                parameters.setWdaLocalPort(System.getProperty("wdaLocalPort", "100002"));
                parameters.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name");


        }
    }

}
