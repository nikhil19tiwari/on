package in.bank.vishal.model;

public class AddCart {
 
	private String name;
	private String desc;
	private Double price;
	private Integer quantity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart [name=" + name + ", desc=" + desc + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
