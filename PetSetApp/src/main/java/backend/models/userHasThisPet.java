package backend.models;

import java.sql.Date;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="USER_HAS_THIS_PET")
public class userHasThisPet {
	
	@Id
	@Column(name = "petid")
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int petID;
	
	@Column(name = "userid")
	private int userID;
	
	@Column(name = "status")
	private int status;
	

	public userHasThisPet() {
		super();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPetID() {
		return petID;
	}

	public void setPetID(int petID) {
		this.petID = petID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
