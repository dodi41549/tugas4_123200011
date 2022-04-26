/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas4;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class koneksi {
     String DBurl = "jdbc:mysql://localhost:3306/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";
    //
    String data[] = new String[2];
    Connection conn;
    Statement statement;
    static String[] username;
    
    public koneksi() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Connection Success");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Connection Failed " + ex.getMessage());
        }
    }
    
    void insertData(String username, String password){
        try {
            if(!chekUsername(username)){
                String query = "INSERT INTO `login`(`username`,`password`) "
                    + "VALUES('" + username + "','" + password + "')";

                statement = conn.createStatement();
                statement.executeUpdate(query);

                System.out.println("Input Success");
                JOptionPane.showMessageDialog(null, "Register Success");
            }else{
                JOptionPane.showMessageDialog(null, "Username Sudah Tersedia");
            }    
        } catch (HeadlessException | SQLException ex) {
            System.out.println("Input Failed");
        }
    }
    
     String[] readData(){
        try {
            String query = "SELECT * FROM `login`";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[0] = resultSet.getString("username"); 
//                data[1] = resultSet.getString("password"); 
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        } finally {
            return data;
        }
    }
     
     boolean chekUsername(String username){
         try {
             String query = "SELECT * FROM `login` WHERE username='"+username+"'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query); 
            while(resultSet.next()){ //konversi tabel ke string
                data[0] = resultSet.getString("username"); 
            }
            statement.close();
             String name = data[0];
            return true;
         } catch (SQLException e) {
             System.out.println("Tidak Ada");
            return false;
         }                 
     }
     
     boolean chekLogin(String username, String password){
         try {
             String query = "SELECT * FROM `login` WHERE username='"+username+"'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query); 
            while(resultSet.next()){ //konversi tabel ke string
                data[0] = resultSet.getString("username"); 
                data[1] = resultSet.getString("password");
            }
            statement.close();
             System.out.println(data[1]);
             System.out.println(password);
             return data[1].equals(password);
         } catch (SQLException e) {
             System.out.println("Tidak Ada");
            return false;
         }  
     }
}
