package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatosLocator {
    private static String cadenaCon="jdbc:mysql://localhost:3306/bancabd?serverTimezone=UTC";
    private static String user="root";
    private static String pwd="root";
    private static String driver="com.mysql.cj.jdbc.Driver";
    //carga del driver
    static {
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(cadenaCon, user, pwd);
    } 
}