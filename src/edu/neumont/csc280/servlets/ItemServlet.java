package edu.neumont.csc280.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.csc280.controller.TestGetController;
import edu.neumont.csc280.controller.TestPostController;
import edu.neumont.csc280.models.Bid;
import edu.neumont.csc280.models.ItemDatabase;
import edu.neumont.csc280.models.ModelAndView;
import edu.neumont.csc280.models.iRetrievable;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/item/*")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private iRetrievable db;
	private ModelAndView mav;
	private TestGetController tc;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemServlet() {
		super();

		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		db = (ItemDatabase) getServletContext().getAttribute("db");
		tc = new TestGetController(db);

		String uri = request.getRequestURI();
		Pattern pattern = Pattern
				.compile("(/item(/([\\w\\d-]+)?(/edit)?(/bid)?)?)");
		Matcher matcher = pattern.matcher(uri);

		while (matcher.find()) {
			if (matcher.group(4) != null) {
				Bid bid = null;
				int id = 0;
				if (isInteger(matcher.group(3)))
					id = Integer.parseInt(matcher.group(3));

				System.out.println(matcher.group(4) + " is group 4");

				if (matcher.group(4).equals("/edit")) {
					mav = tc.editPage(id);
					bid = (Bid) mav.getModel();
					request.setAttribute("bid", mav.getModel());
					request.setAttribute("page2", "/item/" + bid.getId()
							+ "/edit");
					request.getRequestDispatcher(mav.getViewName()).forward(
							request, response);
				} else {
					// send error
				}
			} else if (matcher.group(3) != null) {
				int id = 0;
				if (isInteger(matcher.group(3)))
					id = Integer.parseInt(matcher.group(3));

				mav = tc.retrievePage(id);
				request.setAttribute("bid", mav.getModel());
				request.getRequestDispatcher(mav.getViewName()).forward(
						request, response);
			} else if (matcher.group(2) != null) {

				if (matcher.group(2).equals("/create")) {
					mav = tc.createPage();
					request.getRequestDispatcher(mav.getViewName()).forward(
							request, response);
				} else {
					// send error
				}
			} else if (matcher.group(0) != null) {

				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(
						request, response);

			} else {

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		db = (ItemDatabase) getServletContext().getAttribute("db");
		TestPostController tp = new TestPostController(db);

		String uri = request.getRequestURI();
		Pattern pattern = Pattern
				.compile("(/item(/([\\w\\d-]+)?((/edit)?(/bid)?)?)?)");
		Matcher matcher = pattern.matcher(uri);

		while (matcher.find()) {
			if (matcher.group(4) != null) {
				Bid bid = null;
				int id = 0;
				if (isInteger(matcher.group(3)))
					id = Integer.parseInt(matcher.group(3));

				System.out.println(matcher.group(4) + " is group 4");

				if (matcher.group(4).equals("/edit")) {
					mav = tc.editPage(id);
					bid = (Bid) mav.getModel();
					request.setAttribute("bid", mav.getModel());
					request.setAttribute("page2", "/item/" + bid.getId()
							+ "/edit");
					request.getRequestDispatcher(mav.getViewName()).forward(
							request, response);
				} else if (matcher.group(4).equals("/bid")) {

					ServletOutputStream os = response.getOutputStream();
					response.setContentType("application/json");

					String toBet = (String) request.getParameter("money");
					String jsonObj = tp.bidPage(toBet, id);
					os.print(jsonObj);
					os.flush();
				} else {
					// send error
				}
			} else if (matcher.group(3) != null) {
				int id = 0;
				if (isInteger(matcher.group(3)))
					id = Integer.parseInt(matcher.group(3));

				mav = tc.retrievePage(id);
				request.setAttribute("bid", mav.getModel());
				request.getRequestDispatcher(mav.getViewName()).forward(
						request, response);
			} else if (matcher.group(2) != null) {

				if (matcher.group(2).equals("/create")) {
					mav = tc.createPage();
					request.getRequestDispatcher(mav.getViewName()).forward(
							request, response);
				} else {
					// send error
				}
			} else if (matcher.group(0) != null) {

				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(
						request, response);

			} else {

			}
		}

	}

	private int getId(String[] path) {
		int id = 0;
		for (int x = 0; x < path.length; x++) {
			if (isInteger(path[x]))
				id = Integer.parseInt(path[x]);
		}

		if (id > 3)
			id = 0;

		return id;
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
