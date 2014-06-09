package edu.neumont.csc280.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Bid")
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;

	@Column
	private double bid = 0;

	@Column
	private String imageUrl = "";

	@Column
	private String title;

	@Column
	private String description;

	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startTime = new GregorianCalendar();

	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endTime = new GregorianCalendar();

	@Column(name = "boolColumn", columnDefinition = "INT(1)")
	private boolean canEdit = true;

	@Transient
	public static int base = 0;

	public Bid() {
		// incrementId();
		// id = base;
		// title = "Default";
		// bid = 0.01;
		// description = "Write here...";
		// this.imageUrl = "/auction2/images/default.jpg";
		//
		// startTime = GregorianCalendar.getInstance();
		//
		// Calendar cal = GregorianCalendar.getInstance();
		// cal.add(Calendar.DAY_OF_MONTH, 7);
		// endTime = cal;
	}

	public Bid(int endDay, int endMonth, int endYear, int startDay,
			int startMonth, int startYear, double price, String name,
			String desc, String imageUrl) {
		incrementId();
		id = base;
		title = name;
		bid = price;
		description = desc;
		this.imageUrl = imageUrl;

		startTime.set(startYear, startMonth - 1, startDay);

		endTime.set(endYear, endMonth - 1, endDay);
	}

	public Bid(int endDay, int endMonth, int endYear, double price,
			String name, String desc, String imageUrl) {
		incrementId();
		id = base;
		title = name;
		bid = price;
		description = desc;
		this.imageUrl = imageUrl;

		Calendar calendar = new GregorianCalendar();
		int day = 0;

		startTime = new GregorianCalendar();

		endTime = new GregorianCalendar();
		endTime.set(Calendar.YEAR, endYear);
		endTime.set(Calendar.MONTH, endMonth - 1);
		endTime.set(Calendar.DAY_OF_MONTH, endDay);
	}

	public double getCurrentBid() {
		double tempBid = this.bid;
		return tempBid;
	}

	public void setCurrentBid(double amount) {
		bid = amount;

		if (canEdit)
			canEdit = false;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDateEnd(int day, int month, int year) {
		if (canEdit) {
			endTime = new GregorianCalendar();
			endTime.set(Calendar.YEAR, year);
			endTime.set(Calendar.MONTH, month);
			endTime.set(Calendar.DAY_OF_MONTH, day);
		}
	}

	public void setDateEnd(String date) {

		String[] parseDate = date.split("-");
		// System.out
		// .println("hello from inside setDateEnd in bid class.\nBeing set to: "
		// + date);
		int day = Integer.parseInt(parseDate[0]);
		int month = Integer.parseInt(parseDate[1]);
		int year = Integer.parseInt(parseDate[2]);
		setDateEnd(day, month, year);
		endTime.set(Calendar.YEAR, year);
		endTime.set(Calendar.MONTH, month);
		endTime.set(Calendar.DAY_OF_MONTH, day);

		// }

	}

	public String getDateEnd() {

		return new SimpleDateFormat("MM-dd-yyyy").format(endTime.getTime());
	}

	public String getFormDateEnd() {
		return new SimpleDateFormat("yyyy-MM-dd").format(endTime.getTime());
	}

	public long getTime() {
		long time = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		return time;
	}

	public void setDateStart(int day, int month, int year) {
		// if (!beenBidded) {
		startTime.set(Calendar.YEAR, year);
		startTime.set(Calendar.MONTH, month);
		startTime.set(Calendar.DAY_OF_MONTH, day);
		// }

	}

	public void setDateStart(String date) {

		String[] parseDate = date.split("-");

		// try{
		if (parseDate.length > 1) {
			int month = Integer.parseInt(parseDate[1]) - 1;
			if (month == -1)
				month = 11;

			int day = (Integer.parseInt(parseDate[2]));
			int year = Integer.parseInt(parseDate[0]);

			System.out.println("all parts to SET START DATE: " + parseDate[0]
					+ " : month, " + parseDate[1] + ": day " + parseDate[2]
					+ " : year");
			startTime.set(Calendar.YEAR, year);
			startTime.set(Calendar.MONTH, month);
			startTime.set(Calendar.DAY_OF_MONTH, day);
		}
		System.out
				.println("now startTIme is: "
						+ new SimpleDateFormat("MM-dd-yyyy").format(startTime
								.getTime()));

		// }
	}

	public String getDateStart() {
		return new SimpleDateFormat("MM-dd-yyyy").format(startTime.getTime());
	}

	public String getFormDateStart() {
		return new SimpleDateFormat("yyyy-MM-dd").format(startTime.getTime());
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		String tempUrl = imageUrl;
		return tempUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String set) {
		title = set;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String txt) {
		description = txt;
	}

	public String toString() {
		return "Id: " + id + "Name: " + title + " Price: " + bid;
	}

	public boolean getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canedit) {

		canEdit = canedit;
	}

	public static void incrementId() {
		base++;
	}

}