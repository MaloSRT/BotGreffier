package fr.msrt.botgreffier.bdd;

import fr.msrt.botgreffier.BotGreffier;
import fr.msrt.botgreffier.info.Info;
import org.apache.commons.text.StringEscapeUtils;

import java.sql.*;

public class MySQL {

    private Info info = new Info();

    private Connection connection = null;
    private static final String dbURL = BotGreffier.CONFIG.getString("dbURL", "jdbc:mysql://database.url");

    public MySQL() {

        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    public boolean GetSQLstatus() {

        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            System.err.println("Problème de connection à la BDD");
            return false;
        }
        return true;

    }

    public boolean GetAvailability() {

        try {

            ResultSet rsMinVerStamp;
            Statement stmtMinVerStamp = connection.createStatement();
            rsMinVerStamp = stmtMinVerStamp.executeQuery("SELECT minVerStamp FROM verStampCheck WHERE id = 1");

            if (rsMinVerStamp.next()) {
                int minVerStamp = rsMinVerStamp.getInt("minVerStamp");
                if (minVerStamp <= info.getVerStamp()) {
                    return true;
                } else {
                    System.err.println("Cette version du bot est obsolète");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return false;

    }

    public int Difference() {

        try {

            ResultSet rsRunTime,
                    rsUnixTime;
            Statement stmtR = connection.createStatement(),
                    stmtU = connection.createStatement();
            rsRunTime = stmtR.executeQuery("SELECT time FROM runCheck WHERE id = 1");
            rsUnixTime = stmtU.executeQuery("SELECT UNIX_TIMESTAMP()");

            if (rsRunTime.next() && rsUnixTime.next()) {
                int runTime = rsRunTime.getInt("time"),
                        unixTime = rsUnixTime.getInt("UNIX_TIMESTAMP()");
                return unixTime - runTime;
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return 100;

    }

    public void UpdateRunTime() {

        try {

            ResultSet rsUnixTime;
            Statement stmtU = connection.createStatement();
            rsUnixTime = stmtU.executeQuery("SELECT UNIX_TIMESTAMP()");

            if (rsUnixTime.next()) {
                int unixTime = rsUnixTime.getInt("UNIX_TIMESTAMP()");
                String del = "DELETE FROM runCheck WHERE id = 1",
                        ins = "INSERT INTO runCheck(id, time) VALUES (1," + unixTime + ")";
                PreparedStatement preparedStatementDel = connection.prepareStatement(del),
                        preparedStatementIns = connection.prepareStatement(ins);
                preparedStatementDel.execute();
                preparedStatementIns.execute();
                System.out.println("Run log updated");
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    public void InsertAVYE(String usr, String msg) {

        usr = StringEscapeUtils.escapeHtml4(usr).replaceAll("'", "&apos;");
        msg = StringEscapeUtils.escapeHtml4(msg).replaceAll("'", "&apos;");

        try {

            String del = "DELETE FROM avye WHERE a = 0",
                    ins = "INSERT INTO avye(user, message) VALUES('" + usr + "', '" + msg + "')";
            PreparedStatement preparedStatementDel = connection.prepareStatement(del),
                    preparedStatementIns = connection.prepareStatement(ins);
            preparedStatementDel.execute();
            preparedStatementIns.execute();
            System.out.println("Allez vas-y enregistre");

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

}
