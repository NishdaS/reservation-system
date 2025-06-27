
/* 104791010 - Fathima Nishda Mohamed Simsar
 Assignment 2 - Reservation System */

package com.asg;

import java.util.ArrayList;

public abstract class Room {

	//member variables
	protected int roomNumber;
	protected String roomName;
	protected String roomDescription;
	protected double costPerDay;
	protected String roomCategory;
	protected String guestID;
	protected String roomType;
	protected int numDays;
	protected int numberOfGuests;
	protected boolean isCheckedIn;

	protected static int nextId = 101; //Starting room number

	//constructor 
	public Room(String roomCategory, String roomType, String roomName, String roomDescription, double costPerDay)throws IllegalArgumentException{
		//room category
		if (!"standard".equals(roomCategory) && !"deluxe".equals(roomCategory) && !"premium".equals(roomCategory)) {
			throw new IllegalArgumentException("Invalid room category. Please enter either standard, deluxe or premium");
		}

		//room type
		if (!"single".equals(roomType) && !"double".equals(roomType) && !"triple".equals(roomType)){
			throw new IllegalArgumentException("Invalid room type. Please enter either single, double or triple");
		}
		this.roomCategory = roomCategory;
		this.roomType = roomType;
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.costPerDay = costPerDay;

		this.roomNumber = nextId++;
		this.guestID = "";
		this.numDays = 0;
		this.isCheckedIn = false;
	}

	//getter methods
	public int getRoomNumber() {
		return roomNumber;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getDescription() {
		return roomDescription;
	}

	public double getCostPerDay() {
		return costPerDay;
	}


	public String getRoomType() {
		return roomType;
	}

	public boolean isCheckedIn() {
		return isCheckedIn;
	}

	public int getNumDays() {
		return numDays;
	}

	public String getGuestID() {
		return guestID;
	}

	//setter methods
	public void setCostPerDay(Double costPerDay) {
		this.costPerDay = costPerDay;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numDays = numberOfDays;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	//Check-In method
	public void checkinRoom(String guestID, int numDays) throws AccommodationException {

		if (isCheckedIn()) {
			throw new AccommodationException("Room " + roomNumber + " is currently booked.");
		}

		//update room status
		this.isCheckedIn = true;
		this.guestID = guestID;
		this.numDays = numDays;

		checkinReciept();
	}


	// Check if a room is checked-in 
	public boolean isCheckedIn(int roomNumber, ArrayList<Room> accommodation) {
		for (Room room : accommodation) {
			if (room.getRoomNumber() == roomNumber) {
				return room.isCheckedIn();
			}
		}
		return false; //Room not found
	}

	//Check-Out method
	public void checkoutRoom() throws AccommodationException {
		if (!isCheckedIn()) {
			throw new AccommodationException("Room " + roomNumber + " is not currently booked.");
		}

		//update room status - after checkout
		this.isCheckedIn = false;
		this.numDays = 0;	    
	}

	//display reservation receipt
	public void checkinReciept () {
		System.out.println("\n" + " ****** RESERVATION RECIEPT ******");
		System.out.println("Guest ID: " + guestID);
		System.out.println("Room ID: " + roomNumber);
		System.out.println("Days : " + numDays);
		System.out.println("Cost per Day: " + costPerDay);
		System.out.println("Total Charge: " + costPerDay * numDays + "\n");
	}

	public String toString() {
	    return String.format("| %-10s | %-8s | %-15s | %-15s | %-15.2f | %-15d | %-30s | %-40s | %-15s |%n",
	            roomNumber, roomCategory, roomType, isCheckedIn ? "Yes" : "No", costPerDay, numDays, roomName, roomDescription);
	}

	//Abstract methods
	abstract double determinePrice();

	abstract String getPersistentString();

}
