package backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class userHasThisPetID{

	@Column(name = "user_id")
    private int userId;

    @Column(name = "pet_id")
    private int petId;

    
    public userHasThisPetID() {
    	
    }
    
	public userHasThisPetID(int userId, int petId) {
		super();
		this.userId = userId;
		this.petId = petId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

    
    
}
