

import java.util.List;
import backend.models.Pet;
import backend.models.User;
import backend.services.AdServices;
import backend.util.db.repositories.adoptionRequestsRepository;
import backend.util.db.repositories.userOwnershipRepository;
import backend.util.db.repositories.userRepository;

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
		
		if(userRepository.areCredientialsCorrect("Ronnie_JD", "password")) System.out.println("yes");
		
		User user = userRepository.getUserFromUserName("Ronnie_JD");
		
		
		List<Pet> pets = userOwnershipRepository.getUserPets(user);
		
		for(Pet pet : pets) {
			System.out.println(pet.getPetName());
		}
		
		User applicantUser;
		applicantUser = userRepository.getUserByUserID(24);
		AdServices.confirmPetAdoption(applicantUser, pets.get(1));
		
		
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
