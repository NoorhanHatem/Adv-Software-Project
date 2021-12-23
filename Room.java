package project;

public class Room {
	
	private int roomID;
	private String roomType;
	private int pricePerDay;
	private boolean available;
	private String occupiedBy;

	Room (int roomID, String roomType, int pricePerDay) {
		this.setRoomID(roomID);
		this.setRoomType(roomType);
		this.setPricePerDay(pricePerDay);
		this.setAvailable(true);
		this.setOccupiedBy("none");
	}
	Room (int roomID, String roomType, int pricePerDay, boolean available, String occupiedby) {
		this.setRoomID(roomID);
		this.setRoomType(roomType);
		this.setPricePerDay(pricePerDay);
		this.setAvailable(available);
		this.setOccupiedBy(occupiedby);
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(String occupiedBy) {
		this.occupiedBy = occupiedBy;
	}
}

