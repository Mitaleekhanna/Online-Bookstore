package bookstore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
	public String ISBN;
	public String title;
	public String author;
	public String description;
	public String genre;
	public String publication;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String quantity;
	public String price;
	public String dateAdded;
	
//	Book(String ISBN,String title,String description,String author, String genre, String publication, String quantity, String price){
//		this.author=author;
//		this.description=description;
//		this.ISBN=ISBN;
//		this.title=title;
//		this.genre=genre;
//		this.publication=publication;
//		this.quantity=quantity;
//		this.price=price;
//	}
	boolean contains(String Keyword) {
		if(this.title.contains(Keyword)
				||this.ISBN.contains(Keyword)
				||this.description.contains(Keyword)
				||this.author.contains(Keyword)) {
			return true;
		}else {
			return false;
		}
	}
	boolean save() {
		DBConnect db = new DBConnect();
    	try {
			int booksset = db.saveBook(this);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
