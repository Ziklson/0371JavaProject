package server;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static DatabaseManager dm;
    private Connection conn;

    private DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String userHome = System.getProperty("user.home");
        String configPath = userHome + "/ProjectX/database.xml";
        File file = new File(configPath);
        DatabaseConfiguration dbConf;
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("DatabaseConfiguration", DatabaseConfiguration.class);
        if (file.exists()) {
            dbConf = (DatabaseConfiguration) xStream.fromXML(file);
        } else {
            file.getParentFile().mkdirs();

            dbConf = new DatabaseConfiguration();
            try {
                xStream.toXML(dbConf, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String host= dbConf.getHost();
        int port=dbConf.getPort();
        String base= dbConf.getBase();
        String login= dbConf.getLogin();
        String password= dbConf.getPassword();
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + base, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static synchronized DatabaseManager getInstance() {
        if (dm == null) {
            dm = new DatabaseManager();
        }
        return dm;
    }
    public Connection getConnection(){
        return conn;
    }

    public static void main(String[] args) {
        new DatabaseManager();
    }
}
