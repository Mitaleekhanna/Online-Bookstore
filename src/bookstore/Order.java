package bookstore;

import java.util.ArrayList;

public class Order {
	String orderId;
	String userId;
	String paymentId;
	String status;
	int total=0;
	ArrayList<OrderItem> orderitems=new ArrayList<OrderItem>();
	public Order(String orderId, String userId, String paymentId, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.paymentId = paymentId;
		this.status = status;
		this.total = total;
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public void addItem(String name,int qty,int price){
		orderitems.add(new OrderItem(name,qty,price));
		this.total+=(price*qty);
	}
}
