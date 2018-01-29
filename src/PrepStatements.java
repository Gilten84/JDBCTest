import java.sql.*;
public class PrepStatements {
	
	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}

	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost:3306/products?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "toporpales#?2345";
		Connection myConn = null;
		PreparedStatement myStat=null;
		ResultSet myResult = null;
		try
		{
			//0.Load a driver
			Class.forName("org.gjt.mm.mysql.Driver");
			
			//1. Get a connection
			myConn = DriverManager.getConnection(url, user, password);
			
			//2. Make PreparedStatement
			myStat = myConn.prepareStatement("select * from employees where salary > ? and department = ?");
			
			//3. Set parameters for prepared statement
			myStat.setDouble(1,350.00);
			myStat.setString(2,"PR");
			
			//4.Execute SQL-query
			myResult = myStat.executeQuery();
			
			display(myResult);
		}catch (Exception ex) 
		{ex.printStackTrace();}
		// TODO Auto-generated method stub

	}

}
