package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;

public class SearchAgent extends Agent{
	
	
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
	
	
	
	
}