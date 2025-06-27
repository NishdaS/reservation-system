/*104791010 - Fathima Nishda Mohamed Simsar
 Assignment 2 - Reservation System*/

package com.asg;

//supports wheel char bound guest 
//These rooms have ramps at the entrance and rear (direct beach access)
//and an emergency calling facility in the washroom.

public class OneStandardTriple extends Room implements SpecialServices {

	private double rampLength;
	private double rampWidth;
	private String emergencyCallNumber;

	//Constructor
	public OneStandardTriple(String roomCategory, String roomType, String roomName, String roomDescription, double costPerDay, double rampWidth, double rampLength, String emergencyCallNumber) throws AccommodationException {
		super(roomCategory, "triple", roomName, roomDescription, 0.00);
		setCostPerDay(determinePrice());
		this.rampLength = rampLength;
		this.rampWidth = rampWidth;
		this.emergencyCallNumber = emergencyCallNumber;
	}

	@Override
	public double getRampLength() {
		return rampLength;
	}

	@Override
	public double getRampWidth() {
		return rampWidth;
	}

	@Override
	public String getEmergencyCallNumber() {
		return emergencyCallNumber;
	}

	@Override
	double determinePrice() {
	    double specialServicesCost = 50.00;  
	    return 500.00 + specialServicesCost;
	}


	@Override
	public String toString() {
	    return String.format("| %-10s | %-8s | %-9s | %-13s | %-13s | %-11s | %-17s | %-40s | %-16s |%n",
	            "Room No.", "Category", "Type", "Checked-In", "Cost Per Day", "No of Days", "Name", "Description", "Special Services") +
	            String.format("| %-10s | %-8s | %-9s | %-13s | %-13.2f | %-11d | %-17s | %-40s | %-16s |%n",
	                    roomNumber, "Standard", roomType, isCheckedIn ? "Yes" : "No", costPerDay, numDays, roomName, roomDescription, "Yes");
	}

	//display room services
	@Override
	public String showSpecialServices() {
		String specialDetails = String.format("| %-26s | %32f |", "Ramp Length:", getRampLength()) + "\n" +
				String.format("| %-26s | %32f |", "Ramp Width:", getRampWidth()) + "\n" +
				String.format("| %-26s | %32s |", "Emergency calling Number:", getEmergencyCallNumber()) + "\n";

		return specialDetails; 
	}

	//persistent representation of the room
	@Override
	public String getPersistentString() {
		return roomCategory + "," + roomType + "," + roomName + "," + roomDescription + "," +
				costPerDay + "," + roomNumber + "," + isCheckedIn + "," + numDays + "," + rampLength + "," + rampWidth + "," + emergencyCallNumber;
	}
}