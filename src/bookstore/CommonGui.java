package bookstore;
import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.event.*;
import java.sql.SQLException;

public class CommonGui extends JFrame{
	public CommonGui() {
		JFrame f = new JFrame("Books Available");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable book_list= new JTable(); //view data in table format
		SearchAgent SearchAgent=new SearchAgent();
		JButton view_books=new JButton("View Books");//creating instance of JButton to view books
	    view_books.setBounds(20,20,120,25);//x axis, y axis, width, height 
	    view_books.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	             
	            
	            TableModel tb = null;
				System.out.print("in try");
				tb = SearchAgent.getbooks();
				book_list.setModel(tb); 
				f.add(book_list);
				JScrollPane scrollPane = new JScrollPane(book_list); 
				f.add(scrollPane); //add scrollpane
				f.setSize(800, 400); //set size for frame
				f.setVisible(true);
	            
	                   
	       }
	    });
	    
        f.add(view_books);
        f.setSize(800, 400); //set size for frame
        f.setVisible(true);
        f.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		System.out.println("hello from common class");
		new CommonGui();
	}

}