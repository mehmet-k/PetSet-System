package backend.models;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_purchases")
public class userPurchase {
	
	@Id
	@Column(name = "itemid")
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int itemID;
	
	@Column(name = "userid")
	private int userID;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "purchasedate")
	private Date purchaseDate;

	public userPurchase() {
		
	}
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
}
