package fr.msrt.botgreffier.utils;

public class SysoutCmd {

    public static void sysoutCmd(String msgContent) {

        if (msgContent.length() <= 35) {
            System.out.println("Command : " + msgContent);
        } else {
            System.out.println("Command : " + msgContent.substring(0, 35) + " [...]");
        }

    }

}
