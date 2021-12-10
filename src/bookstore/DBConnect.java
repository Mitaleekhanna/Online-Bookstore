package bookstore;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DBConnect {
	private String USERNAME="root";
	private String PASSWORD="";
	private String DBNAME="new_bookstore";	
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
			if(rscheck.next()==false && rscheck != null) {
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
//	public static void main(String[] args) throws SQLException {
//		// TODO Auto-generated method stub
//		DBConnect db=new DBConnect();
//		Statement stmt=db.con.createStatement();
//		
//		ResultSet rs=stmt.executeQuery("select 10+1;"
//				+ "");  
//		while(rs.next())  
//		System.out.println(rs.getInt(1)+"  ");  
//		
//	}
	public Map<String, String> loginuser(String email,String password) throws SQLException {
		DBConnect db=new DBConnect();
		Statement stmt = null;
		ResultSet rscheck = null;
		Map<String, String> user_data = new HashMap<String, String>();
		
		stmt = db.con.createStatement();
		String stcheck = "SELECT * FROM users where email='"+email+"' AND password='"+password+"'";
		System.out.print(stcheck);
		rscheck = stmt.executeQuery(stcheck);
		System.out.print(rscheck);
		if(rscheck.next()!=false && rscheck != null) {
//			System.out.print("in if");
			user_data.put("name", rscheck.getString("name"));
			user_data.put("user_role", rscheck.getString("user_role"));
			user_data.put("user_id", rscheck.getString("user_id"));
		}
		else {
			user_data = null;
		}
		return user_data;
	}
	public String getUserRole(String email,String password) throws SQLException {
		DBConnect db=new DBConnect();
		Statement stmt = null;
		ResultSet rscheck = null;
		stmt = db.con.createStatement();
		String stcheck = "SELECT user_role FROM users where email='"+email+"' AND password='"+password+"'";
		System.out.print(stcheck);
		rscheck = stmt.executeQuery(stcheck);
		System.out.print(rscheck);
		if(rscheck.next()!=false && rscheck != null) {
			System.out.print("in if");
			return rscheck.getString("user_role");
			
		}else {
			System.out.print("in else");
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
	public ResultSet searchBooks(String keyword) throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT *  FROM `books` WHERE `isbn` LIKE '%"+keyword+"%' OR `name` LIKE '%"+keyword+"%' OR `author` LIKE '%"+keyword+"%' OR `description` LIKE '%"+keyword+"%' OR `genre` LIKE '%"+keyword+"%' OR `publication` LIKE '%"+keyword+"%'"; //select all books
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;

	}
	public ResultSet getbookswithattribute(String genre) throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql="select * from books where genre='"+genre+"'"; //select all books
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;

	}
	public int saveBook(Book book) throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql=String.format("INSERT INTO `books`(`isbn`, `name`, `author`, `description`, `genre`, `publication`, `quantity`, `price`) "
        		+ "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",book.ISBN,book.title,book.author,book.description,book.genre,book.publication,book.quantity,book.price);
        		Statement stmt = db.con.createStatement();
        int rs=stmt.executeUpdate(sql);
        return rs;

	}
	public Book getBookFromISBN(String ISBN) throws SQLException{
		DBConnect db=new DBConnect(); 
		String sql="SELECT *  FROM `books` WHERE `isbn`= "+Integer.parseInt(ISBN);
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()!=false && rs != null) {
	        Book resultBook=new Book();
	        resultBook.setISBN(rs.getString("isbn"));
	        resultBook.setTitle(rs.getString("name"));
	        resultBook.setAuthor(rs.getString("author"));
	        resultBook.setDescription(rs.getString("description"));
	        resultBook.setGenre(rs.getString("genre"));
	        resultBook.setPublication(rs.getString("publication"));
	        resultBook.setQuantity(rs.getString("quantity"));
	        resultBook.setPrice(rs.getString("price"));
	        resultBook.setDateAdded(rs.getString("date_added"));
	        
			return resultBook;
			
		}else {
			return new Book();
		}

	}
	
	public ResultSet getBookRowFromISBN(String ISBN) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
        String sql="select * from books"; //select all books
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;
	}
	public boolean addBookToCart(String userId,String ISBN) throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT `quantity` FROM `books` WHERE `isbn`="+ISBN; //validate quantity
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()!=false) {
        	if(Integer.parseInt(rs.getString("quantity"))<=0) {
        		return false;
        	}
        }
        sql=String.format("INSERT INTO `cart`(`cart_id`, `user_id`, `isbn`, `quantity`) "
        		+ "VALUES (1,'%s','%s',1)",userId,ISBN);
        stmt = db.con.createStatement();
        int stat=stmt.executeUpdate(sql);
        sql=String.format("UPDATE `books` SET `quantity`=`quantity`-1 WHERE `isbn`= %s",ISBN);
        stmt = db.con.createStatement();
        stat=stmt.executeUpdate(sql);
        return true;
	}
	public ResultSet getCartItems(String userId) throws SQLException{
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT cart.isbn as ISBN,books.name as Title,SUM(cart.quantity) as Quantity ,books.price as Price,(books.price*SUM(cart.quantity)) as `Total Item Price` FROM `cart`  INNER join books on books.isbn=cart.isbn  where cart.user_id="+userId+" GROUP by cart.`isbn`"; //validate quantity
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return rs;
	}
	public float getCartTotal(String userId) throws SQLException{
		ResultSet rs=getCartItems(userId);
		float total=0;
		while(rs.next()) {
			total+=Float.parseFloat(rs.getString("Total Item Price"));
		}
		return total;		
	}
	
	public int payAmount(String userId,String Type) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
		int paymentId=new Random().nextInt(1000000);
		int orderId=new Random().nextInt(1000000);

        String sql=String.format("INSERT INTO `payments`(`payment_id`, `order_id`, `amount`, `payment_type`) "
        		+ "VALUES (%s,%s,%s,'%s')",paymentId,orderId,getCartTotal(userId),Type);
        Statement stmt = db.con.createStatement();
        int  rs=stmt.executeUpdate(sql);
        placeOrder(userId, paymentId, orderId);
        clearCart(userId);
        return paymentId;
	}
	
	public int placeOrder(String userId,int paymentId,int orderId) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
		ResultSet rs=getCartItems(userId);
		while(rs.next()) {
	        String sql=String.format("INSERT INTO `orders`(`order_id`, `isbn`, `user_id`, `payment_id`, `status`, `quantity`) "
	        		+ "VALUES (%s,%s,%s,%s,'%s',%s)",orderId,rs.getString("ISBN"),userId,paymentId,"sucess",rs.getString("Quantity"));
	        Statement stmt = db.con.createStatement();
	        stmt.executeUpdate(sql);
	        sql=String.format("UPDATE `books` SET `quantity`=`quantity`-"+rs.getString("Quantity")+"  WHERE `isbn`="+rs.getString("ISBN"));
	        stmt = db.con.createStatement();
	        stmt.executeUpdate(sql);
		}
		return orderId;
	}
	public void clearCart(String userId) throws SQLException {
		DBConnect db=new DBConnect(); //connect to database
		ResultSet rs=getCartItems(userId);

        String sql="DELETE FROM `cart` WHERE `user_id`="+userId;
        Statement stmt = db.con.createStatement();
        stmt.executeUpdate(sql);
	}
	
	public ArrayList<Order> getOrders(String userId) throws SQLException {
//		SELECT DISTINCT order_id FROM `orders` WHERE 1
		DBConnect db=new DBConnect(); //connect to database
        String sql="SELECT distinct order_id FROM `orders` where user_id="+userId; //validate quantity
        Statement stmt = db.con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        ArrayList<Order> orders=new ArrayList<Order>();
		while(rs.next()) {
	        String subSql="SELECT *,orders.quantity as orderedQty FROM `orders`inner join books on books.isbn=orders.isbn where order_id="+rs.getString("order_id"); //validate quantity
	        Statement subStmt = db.con.createStatement();
	        ResultSet subres=subStmt.executeQuery(subSql);			
			Order order=new Order();
			while(subres.next()) {
				order.orderId=subres.getString("order_id");
				order.status=subres.getString("status");
				order.paymentId=subres.getString("payment_id");
				order.userId=userId;
				order.orderitems.add(new OrderItem(subres.getString("name"),subres.getInt("orderedQty"),subres.getInt("price")));
				
			}
			orders.add(order);
		}
        return orders;
	}
}
