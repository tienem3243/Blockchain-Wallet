/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class User {
    public static int id;
    public static String username;
    public static String genesisHash;
    public static String role;

    public static void setUser(Account account) {
        User.id = account.getId();
        User.username = account.getUsername();
        User.genesisHash = account.getGenesisHash();
        User.role = account.getRole();
    }

}
