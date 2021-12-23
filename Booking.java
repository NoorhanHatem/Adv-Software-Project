package project;

import java.sql.Date;
import java.util.Calendar;

public class Booking {
	
	private String guestUsername;
	private int roomID;
	private int numOfDays;
	private int costOfBooking;
	private Date startDate;
	private Date endDate;
	
	private int bookingID;
	
	Booking (String guestUsername, int roomID, int numOfDays) {
		this.setGuestUsername(guestUsername);
		this.setRoomID(roomID);
		this.setNumOfDays(numOfDays);
		this.startDate = new Date(Calendar.getInstance().getTime().getTime());
		this.setEndDate(calculateEndDate(this.startDate, this.numOfDays));
		
		if (this.getRoomID() < 300) {
			if (this.getRoomID() < 200) {
				this.setCostOfBooking(this.getNumOfDays() * 500);
			} else {
				this.setCostOfBooking(this.getNumOfDays() * 1000);
			}
		} else {
			this.setCostOfBooking(this.getNumOfDays() * 2000);
		}
	}
	
	Booking (int bookingID, int roomID, int numOfDays, String guestUsername, Date startDate, Date endDate) {
		this.setBookingID(bookingID);
		this.roomID = roomID;
		this.guestUsername = guestUsername;
		this.numOfDays = numOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getNumOfDays() {
		return numOfDays;
	}

	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}

	public Date calculateEndDate(Date startDate, int numOfDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, numOfDays);
        return new Date(c.getTimeInMillis());
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCostOfBooking() {
		return costOfBooking;
	}

	public void setCostOfBooking(int costOfBooking) {
		this.costOfBooking = costOfBooking;
	}



	public String getGuestUsername() {
		return guestUsername;
	}



	public void setGuestUsername(String guestUsername) {
		this.guestUsername = guestUsername;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
}

