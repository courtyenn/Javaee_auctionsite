package edu.neumont.csc280.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import edu.neumont.csc280.models.Bid;
import edu.neumont.csc280.models.ModelAndView;
import edu.neumont.csc280.models.iRetrievable;
import edu.neumont.csc280.servlets.ItemServlet;

public class ItemPostController {

	private iRetrievable db = null;
	private ModelAndView mav;
	private final String UPLOAD_DIRECTORY = "C:/uploads";
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ItemServlet servlet;

	public ItemPostController(HttpServletRequest request,
			HttpServletResponse response, iRetrievable datab)
			throws IOException, ServletException, FileUploadException {
		this.request = request;
		this.response = response;
		// String c = request.getParameter("money");
		// System.out.println("c: " + request.getParameter("money"));
		db = datab;

		String path = request.getPathInfo();
		String[] uriPaths = null;

		String title = request.getParameter("title");
		String tempPrice = request.getParameter("price");
		String price2 = request.getParameter("money");
		String desc = request.getParameter("desc");

		System.out.println("pricing: " + tempPrice + " and other... " + price2);
		double price = .00; // basically null so I can use price later.
		String[] date = request.getParameter("endDate").split("-");
		String[] startDate = request.getParameter("startDate").split("-");
		String imageUrl = request.getParameter("pic");
		if (date.length > 1) {
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);

			int year2 = Integer.parseInt(startDate[0]);
			int month2 = Integer.parseInt(startDate[1]);
			int day2 = Integer.parseInt(startDate[2]);

			if (tempPrice != null && isDouble(tempPrice))
				price = Double.parseDouble(tempPrice);

			// bid.setDateStart(day2, month2, year2);
			if (path != null)
				uriPaths = path.split("/");

			// ItemGetController getController = new ItemGetController(request,
			// response);

			if (uriPaths != null && uriPaths.length > 0) {

				if (isInteger(uriPaths[1])) {

					int itemId = Integer.parseInt(uriPaths[1]);

					Bid dbBid = db.GetAuctionItemById(itemId);

					if (dbBid != null && uriPaths.length > 1) {
						long time = dbBid.getTime();

						if (uriPaths[2].equals("bid")) {

							itemId = Integer.parseInt(uriPaths[1]);
							ServletOutputStream os = response.getOutputStream();
							response.setContentType("application/json");
							if (price2 != "") {
								System.out
										.println("what is price2?: " + price2);
								double amount = Double.parseDouble(price2);
								if (amount > dbBid.getCurrentBid()) {
									dbBid.setCurrentBid(amount);
									String jsonObj = "{ \"money\": \"" + price2
											+ "\", \"time\" : \"" + time
											+ "\" }";
									os.print(jsonObj);
									os.flush();
								} else {
									String jsonObj = "{ \"error\": \"" + price2
											+ "\" }";
									os.print(jsonObj);
									os.flush();
								}
							} else {
								System.out.println("what is tempPrice?: "
										+ tempPrice);
								double amount = dbBid.getCurrentBid();
								amount += 1;
								dbBid.setCurrentBid(amount);
								String jsonObj = "{ \"money\": \"" + amount
										+ "\", \"time\" : \"" + time + "\" }";
								os.print(jsonObj);
								os.flush();
							}

						} else if (uriPaths[2].equals("edit")) {

							// if(imageUrl != "" || imageUrl != null){
							//
							// }
							// Bid bid = new Bid(day, month, year, day2, month2,
							// year2, price, title, desc, imageUrl);
							dbBid.setDateEnd(day, month - 1, year);
							dbBid.setDateStart(day2, month2 - 1, year2);
							dbBid.setImageUrl(imageUrl);
							dbBid.setTitle(title);
							dbBid.setCurrentBid(price);
							dbBid.setCanEdit(true);
							dbBid.setDesc(desc);
							mav = updateItem(dbBid);

							request.setAttribute("page2", "/item/" + itemId
									+ "/edit");
							request.setAttribute("id", dbBid.getId());

							try {
								request.getRequestDispatcher(
										"/WEB-INF/" + mav.getViewName())
										.forward(request, response);
							} catch (ServletException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else {
							response.sendError(
									HttpServletResponse.SC_NOT_FOUND,
									"CUSTOM 404 ERROR");
						}
					} else {
						response.sendError(HttpServletResponse.SC_NOT_FOUND,
								"CUSTOM 404 ERROR");
					}
				} else if (uriPaths[1].equals("create")) {
					Bid bid = new Bid(day, month, year, day2, month2, year2,
							price, title, desc, imageUrl);
					db.add(bid);
					request.setAttribute("page2", "/item/create");
					request.setAttribute("id", bid.getId());
					mav = createItem(bid);
					request.getRequestDispatcher(
							"/WEB-INF/" + mav.getViewName()).forward(request,
							response);
				} else if (uriPaths[1].equals("index.html")) {

				}

				// request.setAttribute("item", mav.getModel());
				// request.getRequestDispatcher("/WEB-INF/" + mav.getViewName())
				// .forward(request, response);
				// response.setContentType("text/html");
			}

			// request.getRequestDispatcher(request.getContextPath() +
			// path);

		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					"CUSTOM 404 ERROR");
		}
	}

	public ModelAndView createItem(Bid obj) {
		// Bid bid = manage.GetAuctionItemById(obj.getId());
		// if (obj != null)
		// bid.setCurrentBid(obj.getCurrentBid());
		// bid.setDateStart(obj.getDateStart());
		// bid.setImageUrl(obj.getImageUrl());
		// bid.setDateEnd(obj.getDateEnd());
		// bid.setTitle(obj.getTitle());

		ModelAndView temp = new ModelAndView(obj, "posted-form.jsp");
		return temp;
	}

	public ModelAndView updateItem(Bid obj) {

		// System.out.println("Before entered object: " +
		// newobj.getDateStart());
		// System.out.println("Before previous object : " + obj.getDateStart());

		// obj.setCurrentBid(newobj.getCurrentBid());
		// obj.setDateStart(newobj.getDateStart());
		// obj.setImageUrl(newobj.getImageUrl());
		// obj.setDateEnd(newobj.getDateEnd());
		// obj.setTitle(newobj.getTitle());

		// + "\n");

		// db.update(newobj, obj.getId());
		ModelAndView temp = new ModelAndView(obj, "posted-form.jsp");
		return temp;
	}

	// Is not used
	public ModelAndView placeBid(int id) {

		Bid bid = db.GetAuctionItemById(id);

		ModelAndView modelview = new ModelAndView(bid, "AuctionItem.jsp");
		return modelview;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}
