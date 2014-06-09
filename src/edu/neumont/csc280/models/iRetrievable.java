package edu.neumont.csc280.models;

public interface iRetrievable {

	public Bid GetAuctionItemById(int id);

	public void add(Bid obj);

	public void update(Bid obj, int itemId);
}
