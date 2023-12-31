import java.awt.print.Printable;
import java.io.ObjectInputStream.GetField;
import java.util.List;

import backend.models.Pet;
import backend.models.User;
import backend.util.db.repositories.petOwnershipRepository;
import backend.util.db.repositories.petRepository;
import backend.util.db.repositories.userRepository;

public class Main {
	public static void main(String[] args) {
		
		Pet pet = new Pet(1,"Cat","Destroyer Of Worlds");
		petRepository.insertPet(pet);

		User user = userRepository.getUserByUserID(22);
		System.out.println(user.getUserName());
		petOwnershipRepository.insertIntoUserOwnershipTable(user, pet);

		
		/*
		User user = new User("TEST1","TESTNAME","TESTSURNAME","TESTADRESS");
		userRepository userRepository = new userRepository();
		userRepository.insertUser(user);
		/*
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
