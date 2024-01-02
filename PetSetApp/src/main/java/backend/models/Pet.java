package backend.models;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "PET")
public class Pet {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "pettypeid")
	private int petTypeId;
	
	@Column(name = "status")
	private int status=1;
	
	@Column(name = "pettype")
	private String petType;
	
	@Column(name = "petname")
	private String petName;
	
	@Column(name = "isadopted")
	private int isAdopted=0;

	
	public Pet(){
		
	}
	
	public Pet(int petTypeId, String petType, String petName) {
		super();
		this.petTypeId = petTypeId;
		this.petType = petType;
		this.petName = petName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(int petTypeId) {
		this.petTypeId = petTypeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getIsAdopted() {
		return isAdopted;
	}

	public void setIsAdopted(int isAdopted) {
		this.isAdopted = isAdopted;
	}
	
}
