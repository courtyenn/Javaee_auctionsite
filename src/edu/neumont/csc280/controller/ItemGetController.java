package edu.neumont.csc280.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.csc280.models.Bid;
import edu.neumont.csc280.models.ModelAndView;
import edu.neumont.csc280.models.iRetrievable;

public class ItemGetController {

	private iRetrievable db = null;
	private ModelAndView mav;

	public ItemGetController(HttpServletRequest request,
			HttpServletResponse response, iRetrievable datab)
			throws IOException, ServletException {
		super();
		db = datab;
		String path = request.getPathInfo();
		String[] uriPaths = null;
		if (path != null)
			uriPaths = path.split("/");
		if (uriPaths != null && uriPaths.length > 0) {
			if (isInteger(uriPaths[1])) {
				int itemId = Integer.parseInt(uriPaths[1]);

				Bid bid = db.GetAuctionItemById(itemId);
				if (bid != null && uriPaths.length > 2) {
					if (uriPaths[2].equals("bid")) {

					} else if (uriPaths[2].equals("edit")) {
						System.out.println("Can I edit?: " + bid.getCanEdit());
						mav = updateItem(bid);

						request.setAttribute("bid", bid);
						request.setAttribute("page2", "/item/" + bid.getId()
								+ "/edit");
						try {
							request.getRequestDispatcher(
									"/WEB-INF/" + mav.getViewName()).forward(
									request, response);
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						response.sendError(HttpServletResponse.SC_NOT_FOUND,
								"CUSTOM 404 ERROR");
					}
				} else if (bid != null) {
					mav = retreiveItem(itemId);
					try {
						Bid bid2 = (Bid) mav.getModel();

						request.setAttribute("bid", bid2);
						request.setAttribute("time", bid2.getTime());

						try {
							request.getRequestDispatcher(
									"/WEB-INF/" + mav.getViewName()).forward(
									request, response);
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
						response.sendError(HttpServletResponse.SC_NOT_FOUND,
								"CUSTOM 404 ERROR");
					}

				} else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND,
							"CUSTOM 404 ERROR");
				}
			} else if (uriPaths[1].equals("/")) {
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(
						request, response);
			} else if (uriPaths[1].equals("create")) {

				mav = createItem();
				request.setAttribute("bid", new Bid());
				request.setAttribute("page2", "/item/create");
				request.getRequestDispatcher("/WEB-INF/" + mav.getViewName())
						.forward(request, response);
			} else if (uriPaths[1].equals("css")) {
				response.setContentType("text/html");
				// request.getRequestDispatcher(request.getContextPath() +
				// path);

			} else if (uriPaths[1].equals("js")) {
				response.setContentType("application/javascript");
				// request.getRequestDispatcher(request.getContextPath() +
				// path);

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND,
						"CUSTOM 404 ERROR");
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					"CUSTOM 404 ERROR");
		}
	}

	public ModelAndView retreiveItem(int id) {
		Bid bid = db.GetAuctionItemById(id);

		ModelAndView modelview = new ModelAndView(bid, "AuctionItem.jsp");

		return modelview;
	}

	public ModelAndView updateItem(Bid obj) {
		ModelAndView modelview = new ModelAndView(obj, "create-bid.jsp");
		return modelview;
	}

	public ModelAndView createItem() {

		ModelAndView modelview = new ModelAndView(null, "create-bid.jsp");
		return modelview;
	}

	public ModelAndView deleteItem(Long id) {
		return null;
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

}
