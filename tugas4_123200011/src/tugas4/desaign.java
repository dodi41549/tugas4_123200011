/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas4;

/**
 *
 * @author ASUS
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class desaign extends JFrame{
    JLabel username = new JLabel("Username: "); 
    JTextField user = new JTextField (15);
    JLabel password = new JLabel("Password: ");
    JPasswordField pw = new JPasswordField(15);
    JButton login = new JButton("Login");
    JButton cancel = new JButton("Cancel");
    JLabel regist_name = new JLabel ("Username: ");
    JTextField regist_username = new JTextField (15);
    JLabel registpw = new JLabel("Password: ");
    JPasswordField registpassw = new JPasswordField(15);
    JButton register = new JButton("Register");
    
    public desaign() {
       setTitle("Login");
       setSize(500, 200);
       
       setLayout(null);
       
       add(username);
       add(user);
       add(password);
       add(pw);
       add(login);

       add(regist_name);
       add(regist_username);
       add(registpw);
       add(registpassw);
       add(register);
       
       username.setBounds(15, 15, 95, 30);
       user.setBounds(105, 15, 105, 30);
       password.setBounds(15, 50, 95, 30);
       pw.setBounds(105, 50, 105, 30);
       login.setBounds(150, 100, 90, 30);
       regist_name.setBounds(245, 15, 95, 30);
       regist_username.setBounds(330, 15, 105, 30);
       registpw.setBounds(245, 50, 95, 30);
       registpassw.setBounds(330, 50, 105, 30);
       register.setBounds(300, 100, 100, 30);
       
       login.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               koneksi konn = new koneksi();
               String username = user.getText();
               System.out.println(username);
               if(konn.chekUsername(username) && username != "" && konn.chekLogin(username, String.valueOf(pw.getPassword()))){
                   JOptionPane.showMessageDialog(null, "Login Success");
               }else if(username.isEmpty() || String.valueOf(pw.getPassword()).isEmpty()){
                   JOptionPane.showMessageDialog(null, "Tidak Boleh Kosong");
               }
               else if(!konn.chekUsername(username)){
                   JOptionPane.showMessageDialog(null, "Username Salah");
               }
               else{
                   JOptionPane.showMessageDialog(null, "Password Salah");
               }
           }
       });
       
       register.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               koneksi kon = new koneksi();
               String regist_name = regist_username.getText();
               String registpw = String.valueOf(registpassw.getPassword());
               if(!regist_name.isEmpty() && !registpw.isEmpty()){
                   kon.insertData(regist_name, registpw);
               }
               else if(regist_name.isEmpty() || registpw.isEmpty()){
                   JOptionPane.showMessageDialog(null, "Tidak Boleh Kosong");
               }
           }
           
       });
       
       setVisible(true);
    }
}
