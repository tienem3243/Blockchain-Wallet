
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Account {
    private int id;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private String genesisHash;
    private String fullname;
    private String gender;
    private int age;
    private String email;
    private String phone;
    private String role;
    private int status;

    public Account(String username, String passwordHash, String passwordSalt) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    public Account(int id, String username, String passwordHash, String passwordSalt, String genesisHash, String fullname, String gender, int age, String email, String phone, String role, int status) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.genesisHash = genesisHash;
        this.fullname = fullname;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getGenesisHash() {
        return genesisHash;
    }

    public String getFullname() {
        return fullname;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public int getStatus() {
        return status;
    }
}
