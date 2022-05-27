
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class AccountRepository implements IRepository<Account>, IAccountRepository {
    
    @Override
    public Account authenticate(String username, String password) {
        Account account = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());
            String sql = "select * from account where username = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                String inputPasswordHash = StringUtils.applySha256(password, resultSet.getString("passwordSalt"));
                if (inputPasswordHash.equals(resultSet.getString("passwordHash"))) {
                    account = new Account(resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("passwordHash"),
                            resultSet.getString("passwordSalt"),
                            resultSet.getString("genesisHash"),
                            resultSet.getString("fullname"),
                            resultSet.getString("gender"),
                            resultSet.getInt("age"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("role"),
                            resultSet.getInt("status"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return account;
    }

    @Override
    public boolean isExist(Object username) {
        String Username = username.toString();
        boolean check = false;
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());
            String sql = "select * from account where username = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, Username);
            ResultSet resultSet = statement.executeQuery();
            
            check = resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

    @Override
    public boolean changePassword(String username, String password, String newPassword) {
        Connection connection = null;
        PreparedStatement statement = null;
        Account account = authenticate(username,password);

        if (account == null) {
            return false;
        }

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());
            String sql = "update account set passwordHash=? WHERE username = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, StringUtils.applySha256(newPassword, account.getPasswordSalt()));
            statement.setString(2, username);
            
            statement.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }

    @Override
    public List<Account> findALL() {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from account";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public Account findByID(int id) {
        Account account = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());
            String sql = "select * from student where id = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return account;
    }

    @Override
    public List<Account> findByName(String name) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from student where fullname like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public List<Account> findByGender(String gender) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from student where gender = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, gender);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public List<Account> findByAge(int age) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from student where age = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, age);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public List<Account> findByEmail(String email) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from student where email like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + email + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public List<Account> findByPhone(String phone) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from student where phone = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public List<Account> findByStatus(int status) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "select * from student where status = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, status);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Account account = new Account(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash"),
                        resultSet.getString("passwordSalt"),
                        resultSet.getString("genesisHash"),
                        resultSet.getString("fullname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("role"),
                        resultSet.getInt("status"));
                accountList.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return accountList;
    }

    @Override
    public void insert(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "insert into account(username, passwordHash, passwordSalt, fullname, gender, age, email, phone) values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPasswordHash());
            statement.setString(3, account.getPasswordSalt());
            statement.setString(4, account.getFullname());
            statement.setString(5, account.getGender());
            statement.setInt(6, account.getAge());
            statement.setString(7, account.getEmail());
            statement.setString(8, account.getPhone());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "update account set username=?,passwordHash=?,passwordSalt=?,fullname=?,gender=?,age=?,email=?,phone=?,role=?,status=? where id=?";
            statement = connection.prepareCall(sql);


            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPasswordHash());
            statement.setString(3, account.getPasswordSalt());
            statement.setString(4, account.getFullname());
            statement.setString(5, account.getGender());
            statement.setInt(6, account.getAge());
            statement.setString(7, account.getEmail());
            statement.setString(8, account.getPhone());
            statement.setString(9, account.getRole());
            statement.setInt(10, account.getStatus());
            statement.setInt(11, account.getId());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(AppConfig.getDbURL(), AppConfig.getDbUser(), AppConfig.getDbPassword());

            String sql = "delete from account where id=?";
            statement = connection.prepareCall(sql);

            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
