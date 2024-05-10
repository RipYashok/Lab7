import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import model.Route;
import sereverChannel.ServerMain;
import sereverChannel.utils.DBScan;
import sereverChannel.utils.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws JSchException {
        ServerMain server = new ServerMain();
        try{
        String sshHost = "helios.cs.ifmo.ru";
            int sshPort = 2222;
            String dbHost = "localhost";
            int dbPort = 5432;
            String dbName = "studs";
            String User = "s368497";
            String sshPassword = "hebt,8252";
            String dbPassword = "r4xVnr0VSlwYWkSV";

            JSch jsch = new JSch();
            Session session = jsch.getSession(User, sshHost, sshPort);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            int assignedPort = session.setPortForwardingL(0, dbHost, dbPort);
            String dbUrl = "jdbc:postgresql://localhost:" + assignedPort + "/" + dbName;

            Connection connection = DriverManager.getConnection(dbUrl, User, dbPassword);

            List<Route> collection = DBScan.scan(connection);
            Log.logger("Инициализированная коллекция.");
            server.Server(collection, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

