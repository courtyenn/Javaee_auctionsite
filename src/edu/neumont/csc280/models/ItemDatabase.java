package edu.neumont.csc280.models;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class ItemDatabase implements iRetrievable {

	HashMap<Integer, Bid> auctionItems = new HashMap<Integer, Bid>();

	public ItemDatabase() {

		double startAmount = 5; // hours...
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 07);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		Bid bid = new Bid(05, 05, 2014, 12.00, "LoL", "League of Legends",
				"/lab5/images/1.jpg");
		// Bid bid = new Bid();
		// auctionItems.put(bid.getId(), bid);
		// bid.setImageUrl("/auction/images/" + bid.getId() + ".jpg");
		// bid.setCurrentBid(5.00);

		Bid bid2 = new Bid(05, 05, 2014, 11.00, "Minecraft",
				"An addictive mining/crafting game", "/lab5/images/2.jpg");
		// Bid bid2 = new Bid();
		auctionItems.put(bid2.getId(), bid2);
	}

	public void add(Bid item) {
		auctionItems.put(item.getId(), item);
	}

	//
	// public void update(Item item) {
	// Item prod = auctionItems.get(item.getId());
	// if (prod == null) {
	//
	// } else {
	// prod.setCurrentBid(item.getCurrentBid());
	// prod.setImageUrl(item.getImageUrl());
	// prod.setPictureArray(item.getPictureArray());
	// }
	// }
	//
	// public void delete(int id) {
	// auctionItems.remove(id);
	// }

	public Bid GetAuctionItemById(int id) {
		Bid tempBid = auctionItems.get(id);
		return tempBid;
	}

	public String toString() {
		String toReturn = "";

		Iterator iter = auctionItems.keySet().iterator();
		while (iter.hasNext()) {
			Integer key = (Integer) iter.next();
			Bid val = (Bid) auctionItems.get(key);
			toReturn += ("key,val: " + key + "," + val.toString());
		}

		return toReturn;
	}

	@Override
	public void update(Bid obj, int itemId) {

		auctionItems.put(itemId, obj);

	}
}
