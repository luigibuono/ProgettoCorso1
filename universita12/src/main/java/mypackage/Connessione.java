package mypackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
	
	private static Connection con;
	
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/universita","Luigi","Buono");
			System.out.println("Connesso");
		}catch(ClassNotFoundException | SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getCon() 
	{
		return con;
	}
	
}
