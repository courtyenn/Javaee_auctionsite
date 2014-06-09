package edu.neumont.csc280.controller;

import edu.neumont.csc280.models.Bid;
import edu.neumont.csc280.models.ModelAndView;
import edu.neumont.csc280.models.iRetrievable;

public class TestGetController {

	private iRetrievable db = null;
	private ModelAndView mav;

	public TestGetController(iRetrievable datab) {
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
		mav = new ModelAndView(bid, "/WEB-INF/jsp/AuctionItem.jsp");
		return mav;
	}
}
