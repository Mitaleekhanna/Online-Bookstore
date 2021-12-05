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
	public TableModel getbooks() throws SQLException {
		DBConnect db = new DBConnect();
    	ResultSet booksset = db.getbooks();
    	return DbUtils.resultSetToTableModel(booksset);
	}
	public TableModel getbooksbykeyword(String keyword) throws SQLException {
		DBConnect db = new DBConnect();
    	ResultSet booksset = db.searchbookbykeyword(keyword);
//    	System.out.print(DbUtils.resultSetToTableModel(booksset));
    	return DbUtils.resultSetToTableModel(booksset);
	}
	public TableModel getbooksbyattribute(String name, String value) throws SQLException {
		DBConnect db = new DBConnect();
    	ResultSet booksset = db.getbooksbyattribute(name,value);
//    	System.out.print(DbUtils.resultSetToTableModel(booksset));
    	return DbUtils.resultSetToTableModel(booksset);
	}
	
	
	
}