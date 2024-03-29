package backend.services;

import java.util.List;

import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.repositories.adoptionRequestsRepository;
import backend.util.db.repositories.petRepository;
import backend.util.db.repositories.petTypeRepository;
import backend.util.db.repositories.userOwnershipRepository;

public class AdServices {
	
	public static Pet createPetAd(String petTypeName,String petName) {
		PetType petType = petTypeRepository.returnPetTypeByPetTypeName(petTypeName);
		Pet pet = new Pet(petType.getId(),petType.getPetType(),petName);
		petRepository.insertPet(pet);
		return pet;
	}
	
	public static void publishAd(User user, Pet pet) {
		userOwnershipRepository.addPetToUser(user, pet);
	}
	
	public static void applyToPetAdoption(User applicant, Pet pet) {
		adoptionRequestsRepository.addUserToAdoptionRequest(applicant, pet);
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
	
	public static List<Pet> getPetListByCityAndPetType(String city, String petTypeName){
		PetType petType = petTypeRepository.returnPetTypeByPetTypeName(petTypeName);
		List<Pet> pets = petRepository.getAllPetsByCityAndPetType(petType, city);
		return pets;
	}
	
	public static User getOwnerByPet(Pet pet) {
		return userOwnershipRepository.getOwner(pet);
	}
	
}
