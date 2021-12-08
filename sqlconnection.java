import java.sql.*;
import javax.swing.*;



public class sqlconnection {
	Connection conn = null;
	

	public static Connection dbconnector() {
		// TODO Auto-generated method stub
		

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=KalajayM@99");
			return conn;
					
			
		} catch( Exception e)
		
		{
			JOptionPane.showMessageDialog(null, e);
			
			
		}
		return null;
	}

}
