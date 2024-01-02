package backend.services;

import java.util.List;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.repositories.adoptionRequestsRepository;
import backend.util.db.repositories.userOwnershipRepository;

public class AdServices {
	
	public static Pet createPetAd(PetType petType,String petName) {
		Pet pet = new Pet(petType.getId(),petType.getPetType(),petName);
		return pet;
	}
	
	public static void publishAd(User user, Pet pet) {
		userOwnershipRepository.addPetToUser(user, pet);
	}
	
	public static void applyToPetAdoption(User owner, User applicant, Pet pet) {
		adoptionRequestsRepository.addUserToAdoptionRequest(owner, applicant, pet);
	}
	
	public static List<User> getAllApplicantsByPet(Pet pet){
		return adoptionRequestsRepository.getAdoptionRequests(pet);
	}
	
	public static List<Pet> getAllApplicationsOfaUser(User user){
		return adoptionRequestsRepository.getAdoptionRequestsOfAnUser(user);
	}

	public static void resignApplicationByPet(User applicant, Pet pet) {
		adoptionRequestsRepository.removeUserFromAdoptionRequest(applicant, pet);
	}
	
	public static void confirmPetAdoption(User applicant , Pet pet) {
		userOwnershipRepository.setPetOwner(pet, applicant);
	}
	
	
	

}
