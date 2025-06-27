/*104791010 - Fathima Nishda Mohamed Simsar
 Assignment 2 - Reservation System*/

package com.asg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class StageD {

	//main method
	public static void main(String[] args) throws AccommodationException{

		Scanner input = new Scanner(System.in);

		//Array List to store room accommodations
		ArrayList<Room> accommodation = new ArrayList<Room>();

		//name of the file
		final String filename = "accommodation_data.txt";

		//Check if the data file exists
		File dataFile = new File(filename);

		if (dataFile.length() != 0) {
			//load data from the txt file
			loadData(accommodation, filename);
			System.out.println("Data loaded successfully.");

			//asking if the user wants to add more data
			String addNewData;
			do {
				System.out.print("Do you want to add new data? (yes / no): ");
				addNewData = input.nextLine().toLowerCase();

				if (addNewData.equals("no")) {
					return;
				} else if (!addNewData.equals("yes")) {
					System.out.println("Invalid input. Please enter 'yes' or 'no'.");
				}
			} while (!addNewData.equals("yes"));
		} else {
			//empty state proceed
			System.out.println("No existing data found. Proceeding in an empty state.");

			System.out.print("Enter the maximum number of items to store: ");
			int maxItems = input.nextInt();
			input.nextLine();
		}

		int userChoice;
		do {
			//displaying menu options
			System.out.println(" \n ---------------------------------------------------");
			System.out.println("|                 Resort by the Sea                 |");
			System.out.println("|                Cairns, Queensland                 |");
			System.out.println(" ---------------------------------------------------");

			System.out.println("| 1. Add New Room                                   |");
			System.out.println("| 2. Display Accommodation Details                  |");
			System.out.println("| 3. View Accommodation Summary                     |");
			System.out.println("| 4. Guest Check-In                                 |");
			System.out.println("| 5. Guest Check-Out                                |");
			System.out.println("| 6. Add Special Room                               |");
			System.out.println("| 7. View Added(Special)Services                    |");
			System.out.println("| 8. Display Checked-In Rooms                       |");
			System.out.println("| 9. Save and Exit                                  |");
			System.out.println(" ---------------------------------------------------");

			System.out.print("Please enter your choice: ");

			//read user choice
			userChoice = input.nextInt();

			input.nextLine();

			System.out.println();

			switch (userChoice) {
			//adding new rooms
			case 1:
				System.out.println("*** ADD NEW ROOMS ***");

				System.out.print("Enter Room Category (Standard / Deluxe / Premium): ");
				String roomCategory = input.nextLine().toLowerCase();

				try {
					//room category validation
					if (!"standard".equals(roomCategory) && !"deluxe".equals(roomCategory) && !"premium".equals(roomCategory)) {
						throw new IllegalArgumentException("Invalid room category. Please enter either standard, deluxe or premium");
					}
					System.out.print("Enter Room type (Single / Double / Triple): "); 
					String roomType = input.nextLine().toLowerCase();

					//room type validation
					if (!"single".equals(roomType) && !"double".equals(roomType) && !"triple".equals(roomType)){
						throw new IllegalArgumentException("Invalid room type. Please enter either single, double or triple");
					}

					System.out.print("Enter Room Name: ");
					String roomName = input.nextLine();

					System.out.print("Enter Room Description: ");
					String roomDescription = input.nextLine();

					double roomCost = 0.0; //initialize cost

					switch (roomCategory) {
					case "standard":
						//create a standard room object
						StandardRoom standardRoom = new StandardRoom(roomCategory, roomType, roomName, roomDescription, roomCost);

						//calculate standard room cost
						roomCost = standardRoom.determinePrice();

						//add the standard room to the array list
						accommodation.add(standardRoom);

						System.out.println("\n" + "Successfully added Standard Room (No. " + (Room.nextId - 1) + ")" );
						break;

					case "deluxe":
						System.out.print("Enter Space Details: ");
						String spaceDetails = input.nextLine();

						System.out.print("Enter Bathtub Details: ");
						String bathtubDetails = input.nextLine();

						//create a deluxe room object
						DeluxeRoom deluxeRoom = new DeluxeRoom(roomCategory, roomType, roomName, roomDescription, roomCost, spaceDetails, bathtubDetails);

						//calculate deluxe room cost
						roomCost = deluxeRoom.determinePrice();

						//add the deluxe room to the array list
						accommodation.add(deluxeRoom);

						System.out.println("\n" + "Successfully added Deluxe Room (No. " + (Room.nextId - 1) + ")");
						break;

					case "premium":
						System.out.print("Enter Spa Details: ");
						String spaDetails = input.nextLine();

						System.out.print("Enter Kitchenette Details: ");
						String kitchenetteDetails = input.nextLine();

						//create a premium room object
						PremiumRoom premiumRoom = new PremiumRoom(roomCategory, roomType, roomName, roomDescription, roomCost, spaDetails,kitchenetteDetails);

						//calculate premium room cost
						roomCost = premiumRoom.determinePrice();

						//add the premium room to the array list
						accommodation.add(premiumRoom);

						System.out.println("\n" + "Successfully added Premium Room (No. " + (Room.nextId - 1) + ")");
						break;
					}
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					return;
				}
				break;


				//display accommodation details
			case 2:
				System.out.println("*** ACCOMMODATION DETAILS ***");

				System.out.print("Enter Room Number: ");
				int roomNumber = input.nextInt();

				showAccommodationDetails(roomNumber, accommodation);
				break;


				//Accommodation summary
			case 3:
				System.out.println("*** ACCOMMODATION SUMMARY ***");

				showSummaryOfAccommodation(accommodation);
				break;

				// check-in
			case 4: 
				boolean isRoomNumber = false;

				System.out.println("*** CHECK-IN PROCEDURE ***");

				System.out.print("Enter Guest ID: ");
				String guestID = input.nextLine();

				//Check if the guest ID has already checked in
				boolean guestAlreadyCheckedIn = false;
				for (Room room : accommodation) {
					if (room.isCheckedIn() && room.getGuestID().equals(guestID)) {
						guestAlreadyCheckedIn = true;
						break;
					}
				}

				if (guestAlreadyCheckedIn) {
					System.out.println("\n" + "Guest with ID " + guestID + " has already checked in.");
					break; 
				}

				System.out.print("Enter Room Category (Standard / Deluxe / Premium): ");
				roomCategory = input.nextLine().toLowerCase();

				try {
					//room category validation
					if (!"standard".equals(roomCategory) && !"deluxe".equals(roomCategory) && !"premium".equals(roomCategory)) {
						throw new IllegalArgumentException("Invalid room category. Please enter either standard, deluxe or premium");
					}

					System.out.print("Enter Room type (Single / Double / Triple): "); 
					String roomType = input.nextLine().toLowerCase();

					//room type validation
					if (!"single".equals(roomType) && !"double".equals(roomType) && !"triple".equals(roomType)){
						throw new IllegalArgumentException("Invalid room type. Please enter either single, double or triple");
					}

					System.out.print("Enter Number of Days: ");
					int numOfDays = input.nextInt();

					input.nextLine();

					System.out.print("Enter Room Number: ");
					roomNumber = input.nextInt();

					//Find the room in the accommodation list
					Room roomToCheckIn = findRoom(roomNumber, accommodation);

					if (roomToCheckIn != null) {
						try {
							//the check-in
							roomToCheckIn.checkinRoom(guestID, numOfDays);
							System.out.println("Check-in successful!");
							System.out.println("\n" + "Thank You for staying with us");
							System.out.println("***** Have a Nice Day *******");
						} catch (AccommodationException e) {
							System.err.println("Error during check-in: " + e.getMessage());
							e.printStackTrace();
							return;
						}
					} else {
						System.out.print("\n" + "Room " + roomNumber + " not found in the system. ");
					}
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					return;
				}
				break;

				// Check-out
			case 5:
				System.out.println("*** CHECK-OUT PROCEDURE ***");

				System.out.print("Enter Room Number: ");
				roomNumber = input.nextInt();

				input.nextLine();

				System.out.println();

				boolean checkedOutSuccessfully = false;

				for (Room room : accommodation) {
					if (room.getRoomNumber() == roomNumber) {
						try {
							room.isCheckedIn(); 

							//check-out
							room.checkoutRoom();
							checkedOutSuccessfully = true;
							System.out.println("Check-out successful!");
						} catch (AccommodationException e) {
							System.err.println("Error during check-out: " + e.getMessage());
							e.printStackTrace();
							return;
						}
						break;
					}
				}
				if (!checkedOutSuccessfully) {
					System.out.println("Room " + roomNumber + " not found in the system.");
				}
				break;

				//Add special services
			case 6:
				System.out.println("*** ADD SPECIAL ROOM - only for WC bound***");

				System.out.print("Enter the number of accompanying guests: ");
				int numberOfGuests = input.nextInt();

				if (numberOfGuests < 1) {
					System.out.println("For your comfort and safety, please bring at least one accompanying guest.");
					break;
				}

				input.nextLine();

				System.out.print("Enter Room type (Double / Triple): "); 
				String roomType = input.nextLine().toLowerCase();

				System.out.print("Enter Room Name: ");
				String roomName = input.nextLine();

				System.out.print("Enter Room Description: ");
				String roomDescription = input.nextLine();

				System.out.print("Enter Ramp Width: ");
				double rampWidth = input.nextDouble();

				System.out.print("Enter Ramp Length: ");
				double rampLength = input.nextDouble();

				input.nextLine();

				System.out.print("Enter an Emergency Number: ");
				String emergencyNumber = input.nextLine();

				try {
					if (roomType.equals("double")) {
						OneStandardDouble specialDoubleRoom = new OneStandardDouble("standard", roomType, roomName, roomDescription, 0.00, rampWidth,  rampLength, emergencyNumber);

						//add the special room to the list
						accommodation.add(specialDoubleRoom);
						System.out.println("\n" + "Special Double Room (No. " + (Room.nextId - 1) + " has been successfully added to the reservation system.");

					} else if (roomType.equals("triple")) {
						OneStandardTriple specialTripleRoom = new OneStandardTriple("standard", roomType, roomName, roomDescription, 0.00, rampWidth,  rampLength, emergencyNumber);

						//add the special room to the list
						accommodation.add(specialTripleRoom);
						System.out.println("\n" + "Special Triple Room has been successfully added to the reservation system.");
					} else {
						System.out.println("\n" + "Invalid room type!");
					}
				} catch (AccommodationException e) {
					System.out.println("Error encountered while adding the special room: " + e.getMessage());
				}
				break;

				//view special services
			case 7:
				System.out.print("Enter Room Number: ");
				int roomNumberForServices = input.nextInt();

				//find the room in the accommodation list
				Room roomForServices = findRoom(roomNumberForServices, accommodation);

				//check if the roomForServices is not null and is an instance of SpecialServices
				if (roomForServices != null && roomForServices instanceof SpecialServices) {
					SpecialServices specialServicesRoom = (SpecialServices) roomForServices;

					//display special services details
					String specialDetails = specialServicesRoom.showSpecialServices();
					System.out.println(specialDetails);
				} else {
					System.out.println("Room does not have special services.");
				}
				break;

				//display all checked in rooms
			case 8:
				checkedInSummary(accommodation);

				break;

				//save and exit	
			case 9:
				System.out.println("Thank you for using our reservation system. Exiting the program...");

				//save the current data
				saveData(accommodation, filename);

				//terminate the program
				System.exit(0);
				break;

			default:
				System.out.println("Invalid input. Please enter a number between 1 and 9.");
			}
		}while(userChoice != 9);
	}



	/****** METHODS *******/

	//show accommodation details
	public static void showAccommodationDetails(int roomNumber, ArrayList<Room> accommodation) {
		boolean found = false;

		//Iterating through accommodation list to find the specific room number
		for (Room room : accommodation) {
			if (room.getRoomNumber() == roomNumber) {
				//Display room details
				System.out.println("\n" + "Room Details: " + "\n");
				System.out.println(room); //Calling the room's toString() method 
				found = true;
				break;
			}else {
				System.out.print("Room " + roomNumber + " is not found" + "\n");
			}
		}
	}

	// Display summary of accommodation
	// Display summary of accommodation
	public static void showSummaryOfAccommodation(ArrayList<Room> rooms) {
	    System.out.println("Number of rooms: " + rooms.size() + "\n");

	    // Check if the room list is empty
	    if (rooms.isEmpty()) {
	        System.out.println("No rooms available.");
	        return;
	    }

	    // To display the summary
	    System.out.printf("| %-13s | %-8s | %-7s | %-11s | %-17s | %-11s | %-20s | %-35s | %-17s |\n",
	            "Room Number", "Category", "Type", "Checked-In", "Cost per day", "No of Days", "Name", "Description", "Special Services");

	    for (Room room : rooms) {
	        String category;
	        // Determine the category
	        if (room instanceof OneStandardDouble || room instanceof OneStandardTriple) {
	            category = "Standard";
	        } else {
	            category = room instanceof StandardRoom ? "Standard" : (room instanceof DeluxeRoom ? "Deluxe" : "Premium");
	        }

	        // Check if the room has special services
	        String specialServicesIndicator = "No";

	        if (room instanceof OneStandardDouble || room instanceof OneStandardTriple) {
	            specialServicesIndicator = ((SpecialServices) room).showSpecialServices();
	        }

	        // Display room information
	        System.out.printf("| %-13d | %-8s | %-7s | %-11s | %-17.2f | %-11d | %-20s | %-35s | %-17s |\n",
	                room.getRoomNumber(), category, room.getRoomType(), room.isCheckedIn() ? "Yes" : "No", room.getCostPerDay(), room.getNumDays(),
	                room.getRoomName(), room.getDescription(), specialServicesIndicator);
	    }
	}

	//to find a room
	public static Room findRoom(int roomNumber, ArrayList<Room> accommodation){
		for (Room room: accommodation){
			if(room!=null&&room.getRoomNumber()==roomNumber){
				return room;
			}
		}
		return null;
	}

	public static void checkedInSummary(ArrayList<Room> accommodation) {
		int totalCheckedInRooms = 0;
		double totalIncome = 0.0;

		System.out.printf("| %-13s | %-15s | %-15s |\n", "Room Number", "Availability", "Income");

		for (Room room : accommodation) {
			System.out.printf("| %-13d | %-15s | %-15s |\n", room.getRoomNumber(),
					room.isCheckedIn() ? "Checked-In" : "Available",
							room.isCheckedIn() ? String.format("$%.2f", room.getCostPerDay() * room.getNumDays()) : "-");

			if (room.isCheckedIn()) {
				totalCheckedInRooms++;
				totalIncome += room.getCostPerDay() * room.getNumDays();
			}
		}

		System.out.println("\nSummary:");
		System.out.println("Total Checked-In Rooms: " + totalCheckedInRooms);
		System.out.println("Total Income from Checked-In Rooms: $" + totalIncome);
	}


	//Save to a text file
	public static void saveData(ArrayList<Room> accommodation, String filename) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			for (Room room : accommodation) {
				writer.println(room.getPersistentString());
			}
		} catch (IOException e) {
			System.err.println("Error saving data to file: " + e.getMessage());
		}
	}

	// Load data from the text file and reconstruct the accommodation list
	public static void loadData(ArrayList<Room> accommodation, String filename) throws AccommodationException {
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				//reconstruct a room object
				Room room = createRoomFromPersistentString(line);
				if (room != null) {
					accommodation.add(room);
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading data from file: " + e.getMessage());
		}
	}

	//reconstructing object
	public static Room createRoomFromPersistentString(String persistentString) throws AccommodationException {
		//split the persistent string into parts
		String[] element = persistentString.split(",");

		//enough parts to create a Room object
		if (element.length < 8) {
			System.err.println("Invalid persistent string: " + persistentString);
			return null;
		}

		//create the corresponding Room object
		try {
			//Extracting
			String roomCategory = element[0];  
			String roomType = element[1];
			String roomName = element[2];
			String roomDescription = element[3];
			double costPerDay = Double.parseDouble(element[4]);
			int roomNumber = Integer.parseInt(element[5]);
			boolean isCheckedIn = Boolean.parseBoolean(element[6]);
			int numberOfDays = Integer.parseInt(element[7]);

			Room room = null;

			//construct the appropriate Room object based on roomType and roomCategory
			switch (roomCategory) {
			case "standard":
				//Create a StandardRoom object
				room = new StandardRoom(roomCategory, roomType, roomName, roomDescription, costPerDay);
				break;

			case "deluxe":
				//Create a DeluxeRoom object
				String spaceDetails = element[8];
				String bathtubDetails = element[9];
				room = new DeluxeRoom(roomCategory, roomType, roomName, roomDescription, costPerDay, spaceDetails, bathtubDetails);
				break;

			case "premium":
				// Create a PremiumRoom object
				String spaDetails = element[8];
				String kitchenetteDetails = element[9];
				room = new PremiumRoom(roomCategory, roomType, roomName, roomDescription, costPerDay, spaDetails,kitchenetteDetails);
				break;

			case "special":
				if (element[7].equals("double")) {
					double rampLength = Double.parseDouble(element[9]);
					double rampWidth = Double.parseDouble(element[10]);
					String emergencyNumber = element[11];

					//Create a OneStandardDouble object
					room = new OneStandardDouble(roomCategory, roomType, roomName, roomDescription, costPerDay, rampWidth,  rampLength, emergencyNumber);

				} else if (element[7].equals("triple")) {
					double rampLength = Double.parseDouble(element[9]);
					double rampWidth = Double.parseDouble(element[10]);
					String emergencyNumber = element[11];

					//Create a OneStandardTriple object
					room = new OneStandardTriple(roomCategory, roomType, roomName, roomDescription, costPerDay, rampWidth,  rampLength, emergencyNumber);
				} else {
					System.err.println("Unknown special room type: " + element[8]);
					return null; 
				}
				break;
			default:
				System.err.println("Unknown room type: " + roomType);
				return null; 
			}
			room.setRoomNumber(roomNumber);
			room.setCheckedIn(isCheckedIn);
			room.setNumberOfDays(numberOfDays);

			return room;
		} catch (NumberFormatException e) {
			return null; 
		}
	}

}





