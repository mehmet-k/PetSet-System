

import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import backend.models.ItemType;
import backend.models.Items;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.services.AdServices;
import backend.services.HardDeleteInactiveRows;
import backend.util.db.repositories.adoptionRequestsRepository;
import backend.util.db.repositories.itemTypeRepository;
import backend.util.db.repositories.itemsRepository;
import backend.util.db.repositories.petRepository;
import backend.util.db.repositories.petTypeRepository;
import backend.util.db.repositories.userOwnershipRepository;
import backend.util.db.repositories.userRepository;
import frontend.PublishAd;
import frontend.SimpleLoginPage;

public class Main {
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new SimpleLoginPage();
	            }
	        });
		
		
/*
		if(userRepository.isUserExists("RJ_Dio"))
			System.out.println("yes");

		if(userRepository.isUserExists("Ronnie_JD"))
			System.out.println("yes Ronnie_JD");
		
		ItemType itemType = itemTypeRepository.getItemTypeByString("dog food");
		System.out.println(itemType.getItemType());
		
		
		
		
		List<Items> items = itemsRepository.getItemsByPriceAndItemType(itemType,0,200);
		for (Items i : items) {
			System.out.println(i.getItemName());
		}
		//HardDeleteInactiveRows.hardDeleteInactiveRowsFromDB();
		
		
		
	
		System.out.println(pet.getId());
		AdServices.confirmPetAdoption(userRepository.getUserByUserID(25), pet);
	*/
		
		//petOwnershipRepository.insertIntoUserOwnershipTable(user, pet);

		
		/*
		User user = new User("TEST1","TESTNAME","TESTSURNAME","TESTADRESS");
		userRepository userRepository = new userRepository();
		userRepository.insertUser(user);
		
		userRepository userRepo = new userRepository();
		User user = userRepo.getUserByUserID(22);
		System.out.println(user.getUserName());
		
		user = userRepo.getUserByUserID(22);
		List<Pet> pets = userRepo.getUserPets(user);
		for (Pet pet: pets) {
			System.out.println(pet.getPetName());
		}*/
		
		
	}
}
