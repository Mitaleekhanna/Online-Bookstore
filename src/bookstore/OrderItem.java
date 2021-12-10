package bookstore;

public class OrderItem {
	public OrderItem(String name, int qty,int price) {
		super();
		this.name = name;
		this.qty = qty;
		this.price=price;
	}
	String name;
	int qty;
	int price;
}
