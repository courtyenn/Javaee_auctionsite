package edu.neumont.csc280.controller;

import edu.neumont.csc280.models.Bid;
import edu.neumont.csc280.models.ModelAndView;
import edu.neumont.csc280.models.iRetrievable;

public class TestPostController {

	private iRetrievable db = null;
	private ModelAndView mav;

	public TestPostController(iRetrievable datab) {
		db = datab;
	}

	public ModelAndView editPage(int itemId) {

		Bid bid = db.GetAuctionItemById(itemId);
		mav = new ModelAndView(bid, "/WEB-INF/jsp/create-bid.jsp");

		return mav;

	}

	public ModelAndView createPage() {
		mav = new ModelAndView(null, "/WEB-INF/jsp/create-bid.jsp");
		return mav;

	}

	public ModelAndView retrievePage(int itemId) {
		Bid bid = db.GetAuctionItemById(itemId);
		mav = new ModelAndView(bid, "/WEB-INF/jsp/create-bid.jsp");
		return mav;
	}

	public String bidPage(String money, int itemId) {

		Bid bid = db.GetAuctionItemById(itemId);
		String jsonObj = "";
		if (money != "") {
			System.out.println("what is price2?: " + money);
			double amount = Double.parseDouble(money);
			if (amount > bid.getCurrentBid()) {
				bid.setCurrentBid(amount);
				jsonObj = "{ \"money\": \"" + money + "\", \"time\" : \""
						+ bid.getTime() + "\" }";

			} else {
				jsonObj = "{ \"error\": \"" + money + "\" }";

			}
		} else {
			System.out.println("what is currentBid?: " + bid.getCurrentBid());
			double amount = bid.getCurrentBid();
			amount += 1;
			bid.setCurrentBid(amount);
			jsonObj = "{ \"money\": \"" + amount + "\", \"time\" : \""
					+ bid.getTime() + "\" }";
		}
		return jsonObj;

	}

}
