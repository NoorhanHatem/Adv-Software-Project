package project;

public class Reciept {

	private String Username;
	private int recieptID;
	private int TotalFees;
	private int price_of_booking;
	private int days_car_rented;
	private int total_car_rental_price;
	private int price_of_requested_services;
	private boolean paid;
	Reciept (String Username, int recieptID, int price_of_booking, int days_car_rented, int total_car_rental_price, int price_of_requested_services, int totalFees, boolean paid) {
		this.setUsername(Username);
		this.setRecieptID(recieptID);
		this.setPrice_of_booking(price_of_booking);
		this.setDays_car_rented(days_car_rented);
		this.setTotal_car_rental_price(total_car_rental_price);
		this.setPrice_of_booking(price_of_booking);
		this.setTotalFees(totalFees);
		this.setPaid(paid);
	}
	Reciept (int recieptID, int price_of_booking, int days_car_rented, int total_car_rental_price, int price_of_requested_services, int totalFees, boolean paid) {
		this.setRecieptID(recieptID);
		this.setPrice_of_booking(price_of_booking);
		this.setDays_car_rented(days_car_rented);
		this.setTotal_car_rental_price(total_car_rental_price);
		this.setPrice_of_booking(price_of_booking);
		this.setTotalFees(totalFees);
		this.setPaid(paid);
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public int getRecieptID() {
		return recieptID;
	}
	public void setRecieptID(int recieptID) {
		this.recieptID = recieptID;
	}
	public int getTotalFees() {
		return TotalFees;
	}
	public void setTotalFees(int totalFees) {
		TotalFees = totalFees;
	}
	public int getPrice_of_booking() {
		return price_of_booking;
	}
	public void setPrice_of_booking(int price_of_booking) {
		this.price_of_booking = price_of_booking;
	}
	public int getDays_car_rented() {
		return days_car_rented;
	}
	public void setDays_car_rented(int days_car_rented) {
		this.days_car_rented = days_car_rented;
	}
	public int getTotal_car_rental_price() {
		return total_car_rental_price;
	}
	public void setTotal_car_rental_price(int total_car_rental_price) {
		this.total_car_rental_price = total_car_rental_price;
	}
	public int getPrice_of_requested_services() {
		return price_of_requested_services;
	}
	public void setPrice_of_requested_services(int price_of_requested_services) {
		this.price_of_requested_services = price_of_requested_services;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
