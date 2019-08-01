package fr.msrt.botgreffier.info;

public final class Info {

    // Infos à éditer en fonction des versions du bot
    private static final String ver = "v4.2.0";
    private static final String maj = "29/07/2019 22:30";
    private static final String instanceName = "Linux main";
    private static final int verStamp = 12;


    public static String getVer() {
        return ver;
    }

    public static String getMaj() {
        return maj;
    }

    public static String getInstanceName() {
        return instanceName;
    }

    public static int getVerStamp() {
        return verStamp;
    }

}
