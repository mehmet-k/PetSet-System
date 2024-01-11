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
	
	public static List<Items> getItemsByPriceAndType(Integer lowerBound, Integer upperBound,ItemType itemType){
		return itemsRepository.getItemsByPriceAndItemType(itemType, lowerBound, upperBound);
	}
	
	public static List<Items> getItemsByPrice(Integer lowerBound,Integer upperBound){
		return itemsRepository.getItemsByItemsPrice(lowerBound, upperBound);
	}
	
	public static List<Items> getItemsByType(ItemType itemType){
		return itemsRepository.getItemsByItemType(itemType);
	}
	
	public static void confirmUserPurchases(User user, List<Items> items) {
		for(Items i : items) {
			userBuysItem(user, i);
		}
	}
	
}
