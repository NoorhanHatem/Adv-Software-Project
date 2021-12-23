package project;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		HotelDatabase x = new HotelDatabase();
	

		

		Service service = new Service ("a", 45678);
		x.addService(service);
		
		
		Booking booking = new Booking ("aaa", 200, 12);
		x.bookRoom(booking);
		x.initialFees(booking);
		
		


	
		



		
		

		
		
		


		


		


		

	}

}
