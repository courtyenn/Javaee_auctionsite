package edu.neumont.csc280.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.neumont.csc280.controller.DBConnection;
import edu.neumont.csc280.models.ItemDatabase;
import edu.neumont.csc280.models.iRetrievable;

public class ServiceLoader implements ServletContextListener {

	private iRetrievable db = new ItemDatabase();
	private DBConnection conn = new DBConnection();

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		ServletContext ctx = servletContextEvent.getServletContext();

		System.out.println("Database connection closed for Application.");

		// http://www.journaldev.com/1945/servlet-listener-example-servletcontextlistener-httpsessionlistener-and-servletrequestlistener
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		conn.openConnection();
		ServletContext ctx = servletContextEvent.getServletContext();

		ctx.setAttribute("db", db);
	}

}
