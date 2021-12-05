package bookstore;
import java.sql.*;

public class DBConnect {
	private String USERNAME="root";
	private String PASSWORD="";
	private String DBNAME="bookstore";	
	static Connection con;
	DBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBNAME, USERNAME, PASSWORD);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select 0+1;"
					+ "");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  ");  
			System.out.println("Connection establised succesfully.");

		} catch (Exception e) {
			System.out.println("Error establishing connection.");
			System.out.println(e);
		}
	}
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int adduserdata(String userName, String password, String userrole, String email, String phone, String address) {
		DBConnect db=new DBConnect();
		Statement stmt = null;
		int rs = 0;
		ResultSet rscheck = null;
		try {
			stmt = db.con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String stcheck = "SELECT * FROM users where name='"+userName+"' AND email='"+email+"'";
		try {
			rscheck = stmt.executeQuery(stcheck);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(rscheck.next()!=false || rscheck != null) {
				String st = ("INSERT INTO users (name,password,user_role,email,phone_number,address,feedback) values ('"+userName+"','"+password+"','"+userrole+"','"+email+"','"+phone+"','"+address+"', '')");
				try {
					rs=stmt.executeUpdate(st);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		if(rs == 0) {
			return 0;
		}else {
			return 1;
		}
		 
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBConnect db=new DBConnect();
		Statement stmt=db.con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select 10+1;"
				+ "");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  ");  
		
	}
	public String loginuser(String email,String password) throws SQLException {
		DBConnect db=new DBConnect();
		Statement stmt = null;
		ResultSet rscheck = null;
		stmt = db.con.createStatement();
		String stcheck = "SELECT name FROM users where email='"+email+"' AND password='"+password+"'";
		System.out.print(stcheck);
		rscheck = stmt.executeQuery(stcheck);
		System.out.print(rscheck);
		db.closeConnection();
		if(rscheck.next()!=false || rscheck != null) {
//			System.out.print("in if");
			return rscheck.getString("name");
			
		}else {
//			System.out.print("in else");
			return "false";
		}
		
	}
	public ResultSet getbooks() throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql="select * from books"; //select all books
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;

	}
	public ResultSet getbooksbyattribute(String name, String value) throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql="select * from books where "+name+"='"+value+"'"; //select all books
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;

	}
	public ResultSet searchbookbykeyword(String keyword) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT * FROM books WHERE keywords like '%"+keyword+"%'"; //select all books
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;
		
	}
	public String addorder(int uid,int bid,int quantity) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
        String sql="INSERT INTO orders (isbn,user_id,payment_id,status,quantity) VALUES ("+bid+","+uid+",-1,'pending',"+quantity+")"; //select all books
        Statement stmt = db.con.createStatement();
        int rs=stmt.executeUpdate(sql);
        String sql1 = "SELECT name FROM books where isbn="+bid;
        ResultSet rs1 = stmt.executeQuery(sql1);
        System.out.print(rs1);
        rs1.next();
        return rs1.getString("name");
        
        
	}
	public int getlastorderid() throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT MAX(order_id) as order_id FROM orders";
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        rs.next();
        return rs.getInt("order_id");
	}
	public int getamountbyisbn(int isbn) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT price FROM books where isbn="+isbn;
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        rs.next();
        return rs.getInt("price");
	}
	public int addpayment(int order_id,int amount,String payment_type) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
        String sql="INSERT INTO payments (order_id,amount,payment_type) VALUES ("+order_id+","+amount+",'"+payment_type+"')"; //select all books
        Statement stmt = db.con.createStatement();
        int rs=stmt.executeUpdate(sql);
        String sql1 = "SELECT MAX(payment_id) as payment_id FROM payments";
        ResultSet rs1 = stmt.executeQuery(sql1);
        rs1.next();
        return rs1.getInt("payment_id");
	}
}
