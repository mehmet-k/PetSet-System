package backend.models;

import jakarta.persistence.Id;

import java.util.ArrayList;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_HAS_THIS_PET")
public class userHasThisPet {
	 	@EmbeddedId
	    private userHasThisPetID id;

	    @ManyToOne
	    @JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "petid", referencedColumnName = "id", insertable = false, updatable = false)
	    private Pet pet;

	    
	    public userHasThisPet() {
	    	
	    }
	    
		public userHasThisPet(userHasThisPetID id, User user, Pet pet) {
			super();
			this.id = id;
			this.user = user;
			this.pet = pet;
		}

		public userHasThisPetID getId() {
			return id;
		}

		public void setId(userHasThisPetID id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Pet getPet() {
			return pet;
		}

		public void setPet(Pet pet) {
			this.pet = pet;
		}

	    


    
    
}
