package backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ADOPTION_REQUESTS")
public class adoptionRequest {
	
	@Id
	@Column(name = "applicantid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int applicantID;
	
	@Column(name="ownerid")
	private int ownerID;
	
	@Column(name="petid")
	private int petID;
	
	private int status = 1;
	
	public adoptionRequest() {
		// TODO Auto-generated constructor stub
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public int getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
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

