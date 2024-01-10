package backend.services;

import backend.util.db.repositories.adoptionRequestsRepository;
import backend.util.db.repositories.itemTypeRepository;
import backend.util.db.repositories.itemsRepository;
import backend.util.db.repositories.petRepository;
import backend.util.db.repositories.petTypeRepository;
import backend.util.db.repositories.userOwnershipRepository;
import backend.util.db.repositories.userPurchaseRepository;
import backend.util.db.repositories.userRepository;

public class HardDeleteInactiveRows {
	
	public static void hardDeleteInactiveRowsFromDB() {
		/* JUNCTION TABLES , DELETE FIRST*/
		//depends on user,pet
		adoptionRequestsRepository.hardDeleteinaciveAdoptionRequests();
		//depends on user,pet
		userOwnershipRepository.hardDeleteInactiveOwnerships();
		//depends on user,item
		userPurchaseRepository.hardDeleteInactivePurchases();
		
		//depends on PetType
		petRepository.hardDeleteInactivePets();
		//depends on ItemType
		itemsRepository.hardDeleteInactiveItems();
		
		/* These doensn't depend on anything*/
		itemTypeRepository.hardDeleteInactiveItemTypes();
		petTypeRepository.hardDeleteInactivePetTypes();
		userRepository.hardDeleteInactiveUsers();
	}
}
