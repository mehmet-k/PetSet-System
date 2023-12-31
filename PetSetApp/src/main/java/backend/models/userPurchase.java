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
}
