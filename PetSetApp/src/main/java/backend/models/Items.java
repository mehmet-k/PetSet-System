package backend.models;

import frontend.index;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Table(name="ITEMS")
public class Items {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "itemtpyeid")
	private int itemTypeId;
	
	@Column(name = "itemtype")
	private String itemType;
	
	@Column(name = "itemname")
	private String itemName;
	
	@Column(name = "status")
	private int status=1;

	@Column(name = "price")
	private int price;
	
	public Items() {
		
	}
	
	public Items(int itemTypeId, String itemType, String itemName,int price) {
		super();
		this.itemTypeId = itemTypeId;
		this.itemType = itemType;
		this.itemName = itemName;
		this.price = price;
	}

	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
}
