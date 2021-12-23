package project;

public class Housekeeping {

	private String staffFirstName;
	private String staffLastName;
	private String username;
	private String password;
	private int roomAssignedToClean;
	private int numOfRoomsCleanedToday;
	private boolean available;
	
	Housekeeping (String staffFirstName, String staffLastName, String username, String password) {
		this.setStaffFirstName(staffFirstName);
		this.setStaffLastName(staffLastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setRoomAssignedToClean(0);
		this.setNumOfRoomsCleanedToday(0);
		this.setAvailable(true);
	}

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoomAssignedToClean() {
		return roomAssignedToClean;
	}

	public void setRoomAssignedToClean(int roomAssignedToClean) {
		this.roomAssignedToClean = roomAssignedToClean;
	}

	public int getNumOfRoomsCleanedToday() {
		return numOfRoomsCleanedToday;
	}

	public void setNumOfRoomsCleanedToday(int numOfRoomsCleanedToday) {
		this.numOfRoomsCleanedToday = numOfRoomsCleanedToday;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
