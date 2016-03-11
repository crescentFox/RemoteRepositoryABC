package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProductBean {
	private int id;
	private String name;
	private double price;
	private java.util.Date make;
	private int expire;
	public ProductBean() {
		
	}
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	public ProductBean(String[] temp) throws IllegalArgumentException {
		if(temp!=null) {
			if(temp.length==5) {
				id = Integer.parseInt(temp[0]);
				name = temp[1];
				price = Double.parseDouble(temp[2]);
				try {
					make = sFormat.parse(temp[3].trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				expire = Integer.parseInt(temp[4]);
			} else {
				throw new IllegalArgumentException("argument length error "+ temp.length);
			}
		}
	}
	@Override
	public String toString() {
		return "ProductBean [" + id + "," + name + "," + price + "," + sFormat.format(make) + "," + expire+ "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public java.util.Date getMake() {
		return make;
	}
	public void setMake(java.util.Date make) {
		this.make = make;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
}
