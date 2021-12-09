package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;

public class SearchAgent extends MainAgent{
	JLabel detailsGenre;
	JLabel detailsISBN;
	JLabel detailsTitle;
	JLabel detailsAuthor;
	JLabel detailsDescription;
	JLabel detailsPublication;
	JLabel detailsQuantity;
	JLabel detailsPrice;
	JLabel detailsDateAdded;
	private JTable table_1;
	 public SearchAgent() {
		 System.out.println("in Search Agent");
	 }  
     
	 protected void setup() 
     {
    	 System.out.println("Search-agent " + getAID().getName() + "is ready.");
    	 new Search(this);

         addBehaviour(new TickerBehaviour(this, Long.valueOf(10000)) {
             protected void onTick() {
                 System.out.println("UserManagers-agent " + getAID().getName() + "is cycling.");
             }
         });
     }   //  --- setup ---
	
	 public TableModel getbooks()  {
		DBConnect db = new DBConnect();
    	ResultSet booksset;
		try {
			booksset = db.getbooks();
			return DbUtils.resultSetToTableModel(booksset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return DbUtils.resultSetToTableModel(null);
		}
    	
	}
	
	 public TableModel searchBooks(String Keyword)  {
		DBConnect db = new DBConnect();
    	ResultSet booksset;
		try {
			booksset = db.searchBooks(Keyword);
			return DbUtils.resultSetToTableModel(booksset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return DbUtils.resultSetToTableModel(null);
		}
    	
	}
	
	 public Book getBookFromISBN(String ISBN)  {
		DBConnect db = new DBConnect();
    	Book book;
		try {
			book = db.getBookFromISBN(ISBN);
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Book();
		}
    	
	}
	 
	 public TableModel getBookRowFromISBN(String ISBN)  {
		 DBConnect db = new DBConnect();
	    	ResultSet book;
			try {
				book = db.getBookRowFromISBN(ISBN);
				return DbUtils.resultSetToTableModel(book);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return DbUtils.resultSetToTableModel(null);
			}
	    	
		}
	
	 public void populateDetailsPane(String ISBN, SearchAgent searchAgent) {
			Book book=searchAgent.getBookFromISBN(ISBN);
			System.out.print(book);
			detailsAuthor.setText(book.getAuthor());
			detailsGenre.setText(book.getGenre());
			detailsISBN.setText(book.getISBN());
			detailsTitle.setText(book.getTitle());
			detailsDescription.setText(book.getDescription());
			detailsPublication.setText(book.getPublication());
			detailsQuantity.setText(book.getQuantity());
			detailsPrice.setText(book.getPrice());
			detailsDateAdded.setText(book.getDateAdded());
			
		}
	
	
}