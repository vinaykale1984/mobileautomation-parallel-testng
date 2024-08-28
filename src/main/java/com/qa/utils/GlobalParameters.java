package com.qa.utils;

public class GlobalParameters {

    private static ThreadLocal<String> platformName = new ThreadLocal<>();
    private static ThreadLocal<String> udid = new ThreadLocal<>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<Integer> systemPort = new ThreadLocal<>();
    private static ThreadLocal<Integer> chromeDriverPort = new ThreadLocal<>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<>();
    private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<>();

    public void setPlatformName(String platformName1) {
        platformName.set(platformName1);
    }

    public String getPlatformName() {
        return platformName.get();
    }

    public void setUdid(String udid1) {
        udid.set(udid1);
    }

    public String getUdid() {
        return udid.get();
    }

    public void setDeviceName(String deviceName1) {
        deviceName.set(deviceName1);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setSystemPort(int systemPort1) {
        systemPort.set(systemPort1);
    }

    public int getSystemPort() {
        return systemPort.get();
    }

    public void setChromeDriverPort(int chromeDriverPort1) {
        chromeDriverPort.set(chromeDriverPort1);
    }

    public int getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort1) {
        wdaLocalPort.set(wdaLocalPort1);
    }

    public String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort1) {
        webkitDebugProxyPort.set(webkitDebugProxyPort1);
    }

    public String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public void initializeGlobalParameters() {
        GlobalParameters parameters = new GlobalParameters();
        parameters.setPlatformName(System.getProperty("platformName", "Android"));
        parameters.setUdid(System.getProperty("udid", "fdd1f933"));
        parameters.setDeviceName(System.getProperty("deviceName", "OnePlus 6"));
        System.out.println(parameters.getPlatformName());

        switch (parameters.getPlatformName()) {
            case "Android":
                parameters.setSystemPort(Integer.parseInt(System.getProperty("systemport", String.valueOf(parameters.getSystemPort()))));
                parameters.setChromeDriverPort(Integer.parseInt(System.getProperty("chromeDriverPort", String.valueOf(parameters.getChromeDriverPort()))));
                break;
            case "iOS":
                parameters.setWdaLocalPort(System.getProperty("wdaLocalPort", "100009"));
                parameters.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11009"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name");


        }
    }

}
