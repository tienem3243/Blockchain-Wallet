
import java.awt.Color;
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class ValidationForm {
    private static final Color invalidColor = new Color(255, 82, 82);
    private static final int invalidSize = 2;
    private static final Border invalidBorder = BorderFactory.createLineBorder(invalidColor, invalidSize);
    private static final Border validBorder = new JTextField().getBorder();

    public static void invalidField(JTextField textField, String message) {
        textField.setBorder(invalidBorder);
        JOptionPane.showMessageDialog(new Frame(), message);
    }

    public static void validField(JTextField textField) {
        textField.setBorder(validBorder);
    }
    
    public static boolean isNameValid(String name) {
        return false;
    }
    
    public static boolean isAgeValid(int age) {
        return false;
    }
    
    public static boolean isEmailValid(String email) {
        return false;
    }
    
    public static boolean isPhoneValid(String phone) {
        return false;
    }
    
    public static boolean isPasswordValid(String password) {
        return false;
    }
}
