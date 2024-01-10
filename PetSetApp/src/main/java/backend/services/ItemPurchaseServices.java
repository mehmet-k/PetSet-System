package backend.services;


import java.util.List;

import backend.models.ItemType;
import backend.models.Items;
import backend.models.User;
import backend.util.db.repositories.itemsRepository;
import backend.util.db.repositories.userPurchaseRepository;


public class ItemPurchaseServices {
	
	public static void userBuysItem(User user, Items item) {
		userPurchaseRepository.addItemToUser(user, item);
	}
	
	public static List<Items> getUserPurchases(User user) {
		return userPurchaseRepository.getUserItems(user);
	}
	
	public static void createNewItem(ItemType itemType ,String name,int price ) {
		Items item = new Items(itemType.getId(),itemType.getItemType(),name,price);
		itemsRepository.insertItem(item);
	}
	
}
