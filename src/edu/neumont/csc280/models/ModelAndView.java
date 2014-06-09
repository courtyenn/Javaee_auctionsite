package edu.neumont.csc280.models;

public class ModelAndView {

	private String view;
	private Object obj;

	public ModelAndView(Object model, String view) {
		this.view = view;
		this.obj = model;

	}

	public Object getModel() {

		return this.obj;
	}

	public String getViewName() {
		return this.view;
	}
}
