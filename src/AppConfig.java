/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class AppConfig {  
    private static String Version;
    
    private static String RoleADMIN;
    private static String RoleMEMBER;
    
    private static String DbURL;
    private static String DbUser;
    private static String DbPassword;
    
    private static String UsrUsername;
    private static String UsrPassword;
    private static String UsrRole;
    
    private static boolean RememberMe;
    
    public AppConfig() {
        load();
    }

    public static void setUsrUsername(String UsrUsername) {
        AppConfig.UsrUsername = UsrUsername;
    }

    public static void setUsrRole(String UsrRole) {
        AppConfig.UsrRole = UsrRole;
    }
    
    public static void setUsrPassword(String UsrPassword) {
        AppConfig.UsrPassword = UsrPassword;
    }

    public static void setRememberMe(boolean RememberMe) {
        AppConfig.RememberMe = RememberMe;
    }
    
    public static String getVersion() {
        return Version;
    }

    public static String getRoleADMIN() {
        return RoleADMIN;
    }

    public static String getRoleMEMBER() {
        return RoleMEMBER;
    }

    public static String getDbURL() {
        return DbURL;
    }

    public static String getDbUser() {
        return DbUser;
    }

    public static String getDbPassword() {
        return DbPassword;
    }

    public static String getUsrUsername() {
        return UsrUsername;
    }

    public static String getUsrPassword() {
        return UsrPassword;
    }

    public static String getUsrRole() {
        return UsrRole;
    }
    
    public static boolean isRememberMe() {
        return RememberMe;
    }
    
    public static void save() {
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("version", Version);
            
            prop.setProperty("role.admin", RoleADMIN);
            prop.setProperty("role.member", RoleMEMBER);
            
            prop.setProperty("db.url", DbURL);
            prop.setProperty("db.user", DbUser);
            prop.setProperty("db.password", DbPassword);
            
            prop.setProperty("usr.username", UsrUsername);
            prop.setProperty("usr.password", UsrPassword);
            prop.setProperty("usr.role", UsrRole);
            
            prop.setProperty("rememberMe", String.valueOf(RememberMe));
            
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException e) {
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @SuppressWarnings("null")
    public static boolean load() {
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
            
            if (input != null) {
                Properties prop = new Properties();
                // load a properties file
                prop.load(input);

                // get the property value and print it out  
                AppConfig.Version = prop.getProperty("version");

                AppConfig.RoleADMIN = prop.getProperty("role.admin");
                AppConfig.RoleMEMBER = prop.getProperty("role.member");

                AppConfig.DbURL = prop.getProperty("db.url");
                AppConfig.DbUser = prop.getProperty("db.user");
                AppConfig.DbPassword = prop.getProperty("db.password");

                AppConfig.UsrUsername = prop.getProperty("usr.username");
                AppConfig.UsrPassword = prop.getProperty("usr.password");
                AppConfig.UsrRole = prop.getProperty("usr.role");
                
                AppConfig.RememberMe = Boolean.parseBoolean(prop.getProperty("rememberMe"));
                
                return true;
            }

        } catch (IOException e) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }
}
