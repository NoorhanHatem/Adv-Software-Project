package project;
import java.sql.*;

public class HotelDatabase {
	static Connection con;
	static Statement st;
	HotelDatabase() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
		st = con.createStatement();
	}
	public boolean adminPassCheck(String username, String password) throws SQLException {
		String q = String.format("select * from admin where username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		String x;
		if(r.next()) {
			x = r.getString("passw");
			if(x.equals(password)) {
				return true;
			}
			System.out.println("Invalid password. Please try again.");
			return false;
		}else {
			System.out.println("This account does not exist.");
		}
		return false;
	}
	public boolean adminUsernameCheck(String username) throws SQLException {
		String q = String.format("select * from admin where username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		return !r.next();
	}
	public void addAdmin(Admin admin) throws SQLException {
		if(adminUsernameCheck(admin.getUsername())) {
		String q = String.format("INSERT INTO admin  (firstname, lastname, username, passw) VALUES('%s','%s','%s','%s');",admin.getFirstName(),admin.getLastName(),admin.getUsername(),admin.getPassword());
		st.executeUpdate(q);
		}else {
			System.out.println("Invalid Username. Try another one.");
		}
	}
	
	public boolean staffPassCheck(String username, String password) throws SQLException {
		String q = String.format("select * from staff where username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		String x;
		if(r.next()) {
			x = r.getString("passw");
			if(x.equals(password)) {
				return true;
			}
			System.out.println("Invalid password. Please try again.");
			return false;
		}else {
			System.out.println("This account does not exist.");
		}
		return false;
	}
	public boolean staffUsernameCheck(String username) throws SQLException {
		String q = String.format("select * from staff where username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		return !r.next();
	}
	public void addStaff(Housekeeping staff) throws SQLException {
		if(staffUsernameCheck(staff.getUsername())) {
		String q = String.format("INSERT INTO staff  (firstname, lastname, username, passw) VALUES('%s','%s','%s','%s');",staff.getStaffFirstName(),staff.getStaffLastName(),staff.getUsername(),staff.getPassword());
		st.executeUpdate(q);
		}else {
			System.out.println("Invalid Username. Try another one.");
		}
	}
	
	public void assignStaffToRoom (String username, int roomID) throws SQLException {
		String q = String.format("UPDATE staff Set available = %b, room_assigned = %d WHERE username = '%s';", false, roomID,username);
		st.executeUpdate(q);
	}
	public void assignStaffService(String username,String service, int roomID) throws SQLException {
		String q = String.format("UPDATE staff Set available = %b, service_assigned = '%s', room_assigned = '%d' WHERE username = '%s';", false,service, roomID,username);
		st.executeUpdate(q);
	}
	
	public int getRoomsCleanedToday (String username) throws SQLException {
		String q = String.format("SELECT * from staff where username = '%s';", username);
		ResultSet result = st.executeQuery(q);
		int RoomsCleanedToday;
		if(result.next()) {
			RoomsCleanedToday= result.getInt("rooms_cleaned_today");
			return RoomsCleanedToday;
		}
		return 0;
	}
	
	public void updateStaffRooms (String username) throws SQLException {
		String q = String.format("UPDATE staff Set available = %b, room_assigned = %d, rooms_cleaned_today = '%s' WHERE username = '%s';", true, 0, getRoomsCleanedToday(username)+1, username);
		st.executeUpdate(q);
	}
	public void updateStaffService (String username) throws SQLException {
		String q = String.format("UPDATE staff Set available = %b, service_assigned ='%s', room_assigned = %d WHERE username = '%s';", true, "none", 0, username);
		st.executeUpdate(q);
	}
	public void clearAllStaffStatus () throws SQLException {
		String q = String.format("UPDATE staff Set available = %b, room_assigned = %d, service_assigned ='%s',rooms_cleaned_today = '%d';", true, 0, "none", 0);
		st.executeUpdate(q);
	}
	
	public boolean guestPassCheck(String username, String password) throws SQLException {
		String q = String.format("select * from guest where username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		String x;
		if(r.next()) {
			x = r.getString("passw");
			if(x.equals(password)) {
				return true;
			}
			System.out.println("Invalid password. Please try again.");
			return false;
		}else {
			System.out.println("This account does not exist.");
		}
		return false;
	}
	public boolean guestUsernameCheck(String username) throws SQLException {
		String q = String.format("select * from guest where username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		return !r.next();
	}
	public void addGuest(Guest guest) throws SQLException {
		if(guestUsernameCheck(guest.getUsername())) {
		String q = String.format("INSERT INTO guest  (firstname, lastname, username, passw, email, phone_number) VALUES('%s','%s','%s','%s', '%s', '%s');",guest.getFirstname(),guest.getLastname(),guest.getUsername(),guest.getPassword(),guest.getEmail(),guest.getPhoneNum());
		st.executeUpdate(q);
		}else {
			System.out.println("Invalid Username. Try another one.");
		}
	}
	
	public boolean serviceNameCheck(String serviceName) throws SQLException {
		String q = String.format("select * from service where service_name = '%s';",serviceName);
		ResultSet r = st.executeQuery(q);
		return !r.next();
	}
	public void addService(Service service) throws SQLException {
		if(serviceNameCheck(service.getServiceName())) {
		String q = String.format("INSERT INTO service  (service_name, service_cost) VALUES('%s','%s');",service.getServiceName(),service.getServiceCost());
		st.executeUpdate(q);
		}else {
			System.out.println("The service already exists.");
		}
	}
	public void deleteService(String service) throws SQLException {
		String q = String.format("DELETE from service where service_name ='%s';", service);
		st.executeUpdate(q);
	}
	public void deleteAllServices() throws SQLException {
		String q = String.format("DELETE from service;");
		st.executeUpdate(q);
	}
	public int getGuestID (String username) throws SQLException {
		String q = String.format("select guest_id from guest WHERE username = '%s';",username);
		ResultSet r = st.executeQuery(q);
		if(r.next()) {
		      return r.getInt(1);
		}
		return 0;
	}
	public void bookRoom (Booking booking) throws SQLException {
		String q = String.format("INSERT INTO booking  (guest_username, room_ID, number_of_days, start_date, end_date) VALUES('%s','%s','%s','%s', '%s');",booking.getGuestUsername(),booking.getRoomID(),booking.getNumOfDays(),booking.getStartDate(),booking.getEndDate());
		st.executeUpdate(q);

	}
	public void updateRoomStatus (String username, int roomID) throws SQLException {
		String q = String.format("UPDATE room Set available = %b, occupied_by = '%s' WHERE room_ID = '%d';", false, username, roomID);
		st.executeUpdate(q);

	}
	public void clearRoomStatus (int roomID) throws SQLException {
		String q = String.format("UPDATE room Set available = %b, occupied_by = '%s' WHERE room_ID = '%d';", true, "none", roomID);
		st.executeUpdate(q);

	}
	public void endRoomBooking (String username) throws SQLException {
		String q = String.format("DELETE from booking where guest_username = '%s' ;", username);
		st.executeUpdate(q);
	}
	public void deleteAllBookings () throws SQLException {
		String q = String.format("DELETE from booking;");
		st.executeUpdate(q);
	}
	public void clearAllRooms () throws SQLException {
		String q = String.format("UPDATE room Set available = %b, occupied_by = '%s'", true, "none");
		st.executeUpdate(q);
	}
	public void initialFees (Booking booking) throws SQLException {
		String q = String.format("INSERT INTO fees (guest_username, price_of_booking, days_car_rented, total_car_rental_price, price_of_requested_services, total, paid) VALUES('%s','%d','%d','%d','%d','%d',%b);",booking.getGuestUsername(), booking.getCostOfBooking(), 0, 0, 0, booking.getCostOfBooking(),false);
		st.executeUpdate(q);
		
	}
	public int getCurrentCarRentalDays (String username) throws SQLException{
		String q = String.format("SELECT * from fees where guest_username = '%s';", username);
		ResultSet result = st.executeQuery(q);
		int currentCarRentalDays;
		if(result.next()) {
			currentCarRentalDays = result.getInt("days_car_rented");
			return currentCarRentalDays;
		}
		return 0;
	}
	public void requestCarRental (int numDays, String username) throws SQLException {
		int currentCarRentalDays = getCurrentCarRentalDays(username);
		int newCarRentalDays = currentCarRentalDays+numDays;
		String q = String.format("UPDATE fees SET days_car_rented ='%d', total_car_rental_price ='%d' WHERE guest_username ='%s';",newCarRentalDays, newCarRentalDays*100 ,username);
		st.executeUpdate(q);
	}
	public int getCurrentServiceFees (String username) throws SQLException{
		String q = String.format("SELECT * from fees where guest_username = '%s';", username);
		ResultSet result = st.executeQuery(q);
		int currentServiceFees;
		if(result.next()) {
			currentServiceFees = result.getInt("price_of_requested_services");
			return currentServiceFees;
		}
		return 0;
	}
	public int getCurrentTotalFees (String username) throws SQLException{
		String q = String.format("SELECT * from fees where guest_username = '%s';", username);
		ResultSet result = st.executeQuery(q);
		int currentTotalFees;
		if(result.next()) {
			currentTotalFees = result.getInt("total");
			return currentTotalFees;
		}
		return 0;
	}
	public Service getSpecifcService(String name) throws SQLException {
		String q = String.format("SELECT * from service where service_name = '%s';",name);
		ResultSet result = st.executeQuery(q);
		String service_name;
		int service_cost;
		while(result.next()) {
			service_name = result.getString("service_name"); 
			service_cost = result.getInt("service_cost");
			Service service = new Service(service_name, service_cost);
			return service;
		}
		return null;
	}
	public void addServiceFees (String serviceName, String username) throws SQLException {
		try {
		Service x = getSpecifcService(serviceName);
		int currentServiceFees = getCurrentServiceFees (username);
		int currentTotalFees = getCurrentTotalFees (username);
		String q = String.format("UPDATE fees SET price_of_requested_services = '%d', total = '%d' WHERE guest_username ='%s';",x.getServiceCost()+currentServiceFees,x.getServiceCost()+currentTotalFees, username);
		st.executeUpdate(q);
		} catch (Exception e) {
			System.out.println ("This service Does not exit.");
		}
	}
	public void addRoom (Room room) throws SQLException {
		try {
			String q = String.format("INSERT INTO room (room_id, room_type, price, available) VALUES('%d','%s','%d', %b);",room.getRoomID(), room.getRoomType(), room.getPricePerDay(),true);
			st.executeUpdate(q);
		} catch (Exception e) {
			System.out.println ("This room ID is already in use");
		}
	}
	public void deleteRoom (int roomID) throws SQLException {
		String q = String.format("DELETE FROM room WHERE room_id = %d;",roomID);
		st.executeUpdate(q);
	}
	public void deleteAllRooms () throws SQLException {
		String q = String.format("DELETE FROM room;");
		st.executeUpdate(q);
	}
	public void addReview (String username, int rating, String review) throws SQLException {
		if (rating <= 5 && rating >=1) {
			String q = String.format("INSERT INTO reviews (guest_username, rating_out_of_five, review) VALUES('%s','%d','%s');",username, rating, review);
			st.executeUpdate(q);
		} else {
			System.out.println("Invalid rating value. Enter a value between 1 and 5");
		}
	}
	public void deleteReview (String username) throws SQLException {
		String q = String.format("DELETE FROM reviews WHERE guest_username = '%s';",username);
		st.executeUpdate(q);
	}
	public void deleteAllReviews () throws SQLException {
		String q = String.format("DELETE FROM reviews;");
		st.executeUpdate(q);
	}
	public void requestHousekeeping (int roomID) throws SQLException {
		String q = String.format("INSERT INTO requestedHousekeeping (room_ID, done) VALUES('%d',%b);", roomID, false);
		st.executeUpdate(q);
	}
	public void housekeepingDone (int roomID) throws SQLException {
		String q = String.format("UPDATE requestedHousekeeping SET done = %b WHERE room_ID = '%d';", true,roomID);
		st.executeUpdate(q);
	}
	public void deletehousekeeping () throws SQLException {
		String q = String.format("DELETE FROM requestedHousekeeping WHERE done = %b;", true);
		st.executeUpdate(q);
	}
	public void requestService (int roomID, String serviceName) throws SQLException {
		try {
			Service x = getSpecifcService(serviceName);
			String q = String.format("INSERT INTO requestedServices (room_ID, service_name, done) VALUES('%d','%s',%b);", roomID, x.getServiceName(),false);
			st.executeUpdate(q);
			} catch (Exception e) {
				System.out.println ("This service Does not exit.");
			}
	}
	public void serviceDone (int roomID) throws SQLException {
		String q = String.format("UPDATE requestedServices SET done =%b WHERE room_ID ='%d';", true, roomID);
		st.executeUpdate(q);
	}
	public void deleteReqService () throws SQLException {
		String q = String.format("DELETE FROM requestedServices WHERE done = %b;", true);
		st.executeUpdate(q);
	}
	public Guest getSpecifcGuest(String username) throws SQLException {
		String q = String.format("SELECT * from guest where username = '%s';", username);
		ResultSet result = st.executeQuery(q);
		String firstName;
		String lastName;
		String password;
		String email;
		String phoneNum;
		while(result.next()) {
			firstName = result.getString("firstname");
			lastName = result.getString("lastname");
			password = result.getString("passw");
			email = result.getString("email");
			phoneNum = result.getString("phone_number");
			Guest guest = new Guest(firstName, lastName, username, password, email, phoneNum);
			return guest;
		}
		return null;
	}
	public Room getSpecifcRoom(int roomID) throws SQLException {
		String q = String.format("SELECT * from room where room_ID = '%d';", roomID);
		ResultSet result = st.executeQuery(q);
		String roomType;
		int pricePerDay;
		boolean available;
		String occupiedBy;
		while(result.next()) {
			roomType = result.getString("room_type");
			pricePerDay = result.getInt("price");
			available = result.getBoolean("available");
			occupiedBy = result.getString("occupied_by");
			Room room = new Room(roomID,roomType,pricePerDay,available,occupiedBy);
			return room;
		}
		return null;
	}

	public Booking[] getAllBookings() throws SQLException {
		String q = "Select count(*) FROM booking";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from booking ;";
		result = st.executeQuery(q);
		int bookingID, roomID, numOfDays;
		String guestUsername;
		Date startDate, endDate;
		Booking[] bookingTable = new Booking[length];
		int i = 0;
		while(result.next()) {
			guestUsername = result.getString("guest_username");
			bookingID = result.getInt("booking_ID");
			roomID = result.getInt("room_ID");
			numOfDays = result.getInt("number_of_days");
			startDate = result.getDate("start_date");
			endDate = result.getDate("end_date");
			bookingTable[i]=new Booking(bookingID,roomID,numOfDays,guestUsername,startDate,endDate);
			i++;
		}
		return bookingTable;
	}
	public Room[] getAllRooms() throws SQLException {
		String q = "Select count(*) FROM room";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from room ;";
		result = st.executeQuery(q);
		int roomID;
		String roomType;
		int pricePerDay;
		boolean available;
		String occupiedBy;
		Room[] roomTable = new Room[length];
		int i = 0;
		while(result.next()) {
			roomID = result.getInt("room_ID");
			roomType = result.getString("room_type");
			pricePerDay = result.getInt("price");
			available = result.getBoolean("available");
			occupiedBy = result.getString("occupied_by");
			roomTable[i]=new Room(roomID,roomType,pricePerDay,available,occupiedBy);
			i++;
		}
		return roomTable;
	}
	
	public Service[] getAllServices() throws SQLException {
		String q = "Select count(*) FROM service";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from service ;";
		result = st.executeQuery(q);
		String serviceName;
		int serviceCost;
		Service[] serviceTable = new Service[length];
		int i = 0;
		while(result.next()) {
			serviceName = result.getString("service_name");
			serviceCost = result.getInt("service_cost");
			serviceTable[i]=new Service(serviceName,serviceCost);
			i++;
		}
		return serviceTable;
	}
	public Guest[] getAllGuests() throws SQLException {
		String q = "Select count(*) FROM guest";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from guest ;";
		result = st.executeQuery(q);
		String firstName;
		String lastName;
	    String username;
		String password;
		String email;
		String phoneNum;
		Guest[] guestTable = new Guest[length];
		int i = 0;
		while(result.next()) {
			firstName = result.getString("firstname");
			lastName = result.getString("lastname");
			username = result.getString("username");
			password = result.getString("passw");
			email = result.getString("email");
			phoneNum = result.getString("phone_number");
			guestTable[i]=new Guest(firstName, lastName, username, password, email, phoneNum);
			i++;
		}
		return guestTable;
	}
	public Housekeeping[] getAllStaff() throws SQLException {
		String q = "Select count(*) FROM staff";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from staff ;";
		result = st.executeQuery(q);
		String firstName;
		String lastName;
	    String username;
		String password;
		Housekeeping[] staffTable = new Housekeeping[length];
		int i = 0;
		while(result.next()) {
			firstName = result.getString("firstname");
			lastName = result.getString("lastname");
			username = result.getString("username");
			password = result.getString("passw");
			staffTable[i]=new Housekeeping (firstName, lastName, username, password);
			i++;
		}
		return staffTable;
	}
	public Admin[] getAllAdmins() throws SQLException {
		String q = "Select count(*) FROM admin";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from admin ;";
		result = st.executeQuery(q);
		String firstName;
		String lastName;
	    String username;
		String password;
		Admin[] adminTable = new Admin[length];
		int i = 0;
		while(result.next()) {
			firstName = result.getString("firstname");
			lastName = result.getString("lastname");
			username = result.getString("username");
			password = result.getString("passw");
			adminTable[i]=new Admin (username, password, firstName, lastName);
			i++;
		}
		return adminTable;
	}
	public Review[] getAllReviews() throws SQLException {
		String q = "Select count(*) FROM reviews";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from reviews ;";
		result = st.executeQuery(q);
		int reviewID;
		String guestUsername;
		int rating;
		String rev;
		Review[] reviewTable = new Review[length];
		int i = 0;
		while(result.next()) {
			reviewID = result.getInt("review_ID");
			guestUsername = result.getString("guest_username");
			rating = result.getInt("rating_out_of_five");
			rev = result.getString("review");
			reviewTable[i]=new Review (reviewID, guestUsername, rating, rev);
			i++;
		}
		return reviewTable;
	}
	public RequestedHousekeeping[] getAllHousekeepingRequests() throws SQLException {
		String q = "Select count(*) FROM requestedHousekeeping";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from requestedHousekeeping ;";
		result = st.executeQuery(q);
		int requestID;
		int roomID;
		boolean done;
		RequestedHousekeeping[] housekeepingTable = new RequestedHousekeeping[length];
		int i = 0;
		while(result.next()) {
			requestID = result.getInt("request_ID");
			roomID = result.getInt("room_ID");
			done = result.getBoolean("done");
			housekeepingTable[i]=new RequestedHousekeeping (requestID, roomID, done);
			i++;
		}
		return housekeepingTable;
	}
	public RequestedHousekeeping[] getAllUnfinishedHousekeeping() throws SQLException {
		String q = String.format("Select count(*) FROM requestedHousekeeping where %b ;",false);
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = String.format("Select * FROM requestedHousekeeping where %b ;",false);
		result = st.executeQuery(q);
		int requestID;
		int roomID;
		boolean done;
		RequestedHousekeeping[] UnfinishedHousekeepingTable = new RequestedHousekeeping[length];
		int i = 0;
		while(result.next()) {
			requestID = result.getInt("request_ID");
			roomID = result.getInt("room_ID");
			done = result.getBoolean("done");
			UnfinishedHousekeepingTable[i]=new RequestedHousekeeping (requestID, roomID, done);
			i++;
		}
		return UnfinishedHousekeepingTable;
	}
	public Reciept getReciept (String username) throws SQLException{
		String q = String.format("SELECT * from fees where guest_username = '%s';", username);
		ResultSet result = st.executeQuery(q);
		int recieptID;
		int totalFees;
		int price_of_booking;
		int days_car_rented;
		int total_car_rental_price;
		int price_of_requested_services;
		boolean paid;
		if(result.next()) {
			recieptID = result.getInt("receipt_ID");
			price_of_booking = result.getInt("price_of_booking");
			days_car_rented = result.getInt("days_car_rented");
			total_car_rental_price = result.getInt("total_car_rental_price");
			price_of_requested_services = result.getInt("price_of_requested_services");
			totalFees = result.getInt("total");
			paid = result.getBoolean("paid");
			Reciept reciept = new Reciept (username, recieptID, price_of_booking, days_car_rented, total_car_rental_price, price_of_requested_services, totalFees, paid); 
			return reciept;
		}
		System.out.println("This username is not currently a guest");
		return null;
	}
	public Reciept[] getAllReciepts() throws SQLException {
		String q = "Select count(*) FROM fees";
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = "select * from fees ;";
		result = st.executeQuery(q);
		int recieptID;
		int totalFees;
		int price_of_booking;
		int days_car_rented;
		int total_car_rental_price;
		int price_of_requested_services;
		boolean paid;
		Reciept[] recieptTable = new Reciept[length];
		int i = 0;
		while(result.next()) {
			recieptID = result.getInt("receipt_ID");
			price_of_booking = result.getInt("price_of_booking");
			days_car_rented = result.getInt("days_car_rented");
			total_car_rental_price = result.getInt("total_car_rental_price");
			price_of_requested_services = result.getInt("price_of_requested_services");
			totalFees = result.getInt("total");
			paid = result.getBoolean("paid");
			recieptTable[i] = new Reciept (recieptID, price_of_booking, days_car_rented, total_car_rental_price, price_of_requested_services, totalFees, paid); 
			i++;
		}
		return recieptTable;
	}
	public Reciept[] getAllUnpaidReciepts() throws SQLException {
		String q = String.format("Select count(*) FROM fees where %b ;",false);
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = String.format("Select * FROM fees where %b ;",false);
		result = st.executeQuery(q);
		int recieptID;
		int totalFees;
		int price_of_booking;
		int days_car_rented;
		int total_car_rental_price;
		int price_of_requested_services;
		boolean paid;
		Reciept[] recieptTable = new Reciept[length];
		int i = 0;
		while(result.next()) {
			recieptID = result.getInt("receipt_ID");
			price_of_booking = result.getInt("price_of_booking");
			days_car_rented = result.getInt("days_car_rented");
			total_car_rental_price = result.getInt("total_car_rental_price");
			price_of_requested_services = result.getInt("price_of_requested_services");
			totalFees = result.getInt("total");
			paid = result.getBoolean("paid");
			recieptTable[i] = new Reciept (recieptID, price_of_booking, days_car_rented, total_car_rental_price, price_of_requested_services, totalFees, paid); 
			i++;
		}
		return recieptTable;
	}
	public void pay (String username) throws SQLException {
		String q = String.format("UPDATE fees SET paid = %b WHERE guest_username ='%s';",true,username);
		st.executeUpdate(q);
	}
	public void payAllReceipts () throws SQLException {
		String q = String.format("UPDATE fees SET paid = %b;",true);
		st.executeUpdate(q);
	}
	public Housekeeping[] getAllAvailableStaff() throws SQLException {
		String q = String.format("Select count(*) FROM staff where %b ;",true);
		ResultSet result = st.executeQuery(q);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		q = String.format("Select * FROM staff where %b ;",true);
		result = st.executeQuery(q);
		String firstName;
		String lastName;
	    String username;
		String password;
		Housekeeping[] AvailableStaffTable = new Housekeeping[length];
		int i = 0;
		while(result.next()) {
			firstName = result.getString("firstname");
			lastName = result.getString("lastname");
			username = result.getString("username");
			password = result.getString("passw");
			AvailableStaffTable[i]=new Housekeeping (firstName, lastName, username, password);
			i++;
		}
		return AvailableStaffTable;
	}
	public void CheckIn (int roomID) throws SQLException {
		String q = String.format("INSERT INTO checking (room_ID, checked) VALUES('%d',%b);",roomID,true);
		st.executeUpdate(q);
	}

	public void CheckOut (int roomID) throws SQLException {
		String q = String.format("UPDATE checking SET checked = %b WHERE room_ID = '%d';", false, roomID);
		st.executeUpdate(q);
	}
	public void addMarketingStrategy(String name, String descr) throws SQLException { 
		String q = String.format("INSERT INTO marketingStrategy (strategy_name, strategy_description) VALUES('%s','%s');",name,descr);
		st.executeUpdate(q);	
	}
	public void updateMarketingStrategy(String name, String feedback) throws SQLException {  
		String q = String.format("UPDATE marketingStrategy SET strategy_feedback = '%s' WHERE strategy_name = '%s';",feedback, name);
		st.executeUpdate(q);	
	}
	public void deleteAllGuests () throws SQLException {
		String q = String.format("DELETE FROM guest;");
		st.executeUpdate(q);
	}
	public void deleteAllStaff () throws SQLException {
		String q = String.format("DELETE FROM staff;");
		st.executeUpdate(q);
	}
	public void deleteAllAdmins () throws SQLException {
		String q = String.format("DELETE FROM admin;");
		st.executeUpdate(q);
	}
	public void deleteAllReciepts () throws SQLException {
		String q = String.format("DELETE FROM fees;");
		st.executeUpdate(q);
	}
	public void deleteAllReqHousekeeping () throws SQLException {
		String q = String.format("DELETE FROM requestedServices;");
		st.executeUpdate(q);
	}
	public void deleteAllReqServices () throws SQLException {
		String q = String.format("DELETE FROM requestedHousekeeping;");
		st.executeUpdate(q);
	}
	public void deleteAllMarketing() throws SQLException {
		String q = String.format("DELETE FROM marketingStrategy;");
		st.executeUpdate(q);
	}
	public void deleteAllChecking() throws SQLException {
		String q = String.format("DELETE FROM checking;");
		st.executeUpdate(q);
	}
	public void clearHotelDB() throws SQLException {
		deleteAllAdmins();
		deleteAllGuests();
		deleteAllStaff();
		deleteAllRooms();
		deleteAllServices();
		deleteAllReviews();
		deleteAllReciepts();
		deleteAllReqHousekeeping();
		deleteAllReqServices();
		deleteAllMarketing();
		deleteAllChecking();
		deleteAllBookings();
	}
}
