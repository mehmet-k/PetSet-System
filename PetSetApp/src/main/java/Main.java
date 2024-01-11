

import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.services.AdServices;
import backend.util.db.repositories.adoptionRequestsRepository;
import backend.util.db.repositories.petRepository;
import backend.util.db.repositories.petTypeRepository;
import backend.util.db.repositories.userOwnershipRepository;
import backend.util.db.repositories.userRepository;
import frontend.PublishAd;

public class Main {
	public static void main(String[] args) {
		/*
		Pet pet = new Pet(1,"Cat","Destroyer Of Worlds");
		petRepository.insertPet(pet);
		*/
		if(userRepository.isUserExists("RJ_Dio"))
			System.out.println("yes");

		if(userRepository.isUserExists("Ronnie_JD"))
			System.out.println("yes Ronnie_JD");
		
		AdServices.getPetListByCityAndPetType("Ankara", "Cat");

		
		List<Pet> pets = AdServices.getAllApplicationsOfaUser(userRepository.getUserByUserID(24)); 
	
		for( Pet p : pets){
			System.out.println(p.getPetName());
		}
	
		/*
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
