/*104791010 - Fathima Nishda Mohamed Simsar
 Assignment 2 - Reservation System*/

package com.asg;

//supports wheel char bound guest 

public class OneStandardDouble extends Room implements SpecialServices {

	//member variables
	private Double rampLength;
	private Double rampWidth;
	private String emergencyCallNumber;

	//constructor
	public OneStandardDouble(String roomCategory, String roomType, String roomName, String roomDescription, double costPerDay, Double rampWidth, Double rampLength, String emergencyCallNumber) throws AccommodationException {
		super(roomCategory, "double", roomName,roomDescription ,0.00);
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
	    return 350.00 + specialServicesCost;
	}


	//display room details
	@Override
	public String toString() {
	    return String.format("| %-10s | %-8s | %-9s | %-13s | %-13s | %-11s | %-17s | %-40s | %-16s |%n",
	            "Room No.", "Category", "Type", "Checked-In", "Cost Per Day", "No of Days", "Name", "Description", "Special Services") +
	            String.format("| %-10s | %-8s | %-9s | %-13s | %-13.2f | %-11d | %-17s | %-40s | %-16s |%n",
	                    roomNumber, "Standard", roomType, isCheckedIn ? "Yes" : "No", getCostPerDay(), numDays, roomName, roomDescription, "Yes");
	}

	//display the room services
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