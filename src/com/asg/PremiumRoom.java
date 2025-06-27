/*104791010 - Fathima Nishda Mohamed Simsar
 Assignment 2 - Reservation System*/

package com.asg;

public class PremiumRoom extends Room{

	private String spaDetails;
	private String kitchenetteDetails;

	//constructor
	public PremiumRoom(String roomCategory, String roomType, String roomName, String roomDescription, double costPerDay, String spaDetails,
			String kitchenetteDetails) throws AccommodationException {
		super(roomCategory, roomType, roomName, roomDescription, costPerDay);
		this.setCostPerDay(determinePrice());
		this.spaDetails = spaDetails;
		this.kitchenetteDetails = kitchenetteDetails;
	}

	@Override
	public double determinePrice() {
		Double roomCost = 0.00;

		switch (getRoomType()){
		case "single":
			roomCost = 350.00;
			break;

		case "double":
			roomCost = 500.00;
			break;

		case "triple":
			roomCost = 690.00;
			break;
		}
		//calculated cost
		return roomCost;
	}

	// Display premium room details
	@Override
	public String toString() {
	    return String.format("| %-10s | %-8s | %-9s | %-13s | %-13s | %-11s | %-17s | %-40s | %-16s |%n",
	            "Room No.", "Category", "Type", "Checked-In", "Cost Per Day", "No of Days", "Name", "Description", "Special Services") +
	            String.format("| %-10s | %-8s | %-9s | %-13s | %-13.2f | %-11d | %-17s | %-40s | %-16s |%n",
	                    roomNumber, roomCategory, roomType, isCheckedIn ? "Yes" : "No", costPerDay, numDays, roomName, roomDescription, "No");
	}

	//persistent representation of the room
	@Override
	public String getPersistentString() {
		return roomCategory + "," + roomType + "," + roomName + "," + roomDescription + "," +
				costPerDay + "," + roomNumber + "," + isCheckedIn + "," + numDays + "," + spaDetails + "," + kitchenetteDetails;
	}

}