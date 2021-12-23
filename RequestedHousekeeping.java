package project;

public class RequestedHousekeeping {

	private int requestID;
	private int roomID;
	private boolean done;
	RequestedHousekeeping (int requestID, int roomID, boolean done) {
		this.setRequestID(requestID);
		this.setRoomID(roomID);
		this.setDone(done);
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
