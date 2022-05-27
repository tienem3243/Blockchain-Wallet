
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 * @param <T>
 */
public interface IRepository<T> {

    List<T> findALL();
    T findByID(int id);
    List<T> findByName(String name);
    List<T> findByGender(String gender);
    List<T> findByAge(int age);
    List<T> findByEmail(String email);
    List<T> findByPhone(String phone);
    List<T> findByStatus(int status);
    
    boolean isExist(Object obj);
    
    void insert(T obj);
    void update(T obj);
    void delete(int id);
}
