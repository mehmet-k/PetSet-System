import java.util.List;

import backend.models.Pet;
import backend.models.User;
import backend.util.db.repositories.petRepository;
import backend.util.db.repositories.userRepository;

public class Main {
	public static void main(String[] args) {
		/*
		Pet pet = new Pet(2,"Cat","Duman");
		petRepository petRepo = new petRepository();
		petRepo.insertPet(pet);

		
		
		User user = new User("TEST1","TESTNAME","TESTSURNAME","TESTADRESS");
		userRepository userRepository = new userRepository();
		userRepository.insertUser(user);
		/**/
		userRepository userRepo = new userRepository();
		User user = userRepo.getUserByUserID(22);
		System.out.println(user.getUserName());
		
		user = userRepo.getUserByUserID(22);
		List<Pet> pets = userRepo.getUserPets(user);
		for (Pet pet: pets) {
			System.out.println(pet.getPetName());
		}
		
		
	}
}
