# Reservation System – Java Console App

> **Swinburne University of Technology**  
> COS10033 – Advanced Programming  
> **Student Name:** Fathima Nishda Mohamed Simsar

---

## Description

This is a Java console-based **Reservation System** for a resort called **Resort by the Sea (RBS)**, implemented up to **Stage D** as per assignment requirements.

The system allows:
- Creating different types of rooms (Standard, Deluxe, Premium)
- Managing guest check-in and check-out
- Adding wheelchair-accessible special rooms
- Exception handling for invalid operations
- Data persistence via text files (manual reconstruction)

---

## Technologies Used

- Java (OOP, Exception Handling, Inheritance, Interfaces)
- Console-based UI
- File I/O for saving/loading room data

---

##  How to Run

1. Open the project in any Java IDE (e.g., Eclipse or IntelliJ).
2. Make sure your working directory includes:
   - `src/com/asg/` containing `.java` files
   - `accommodation_data.txt` if you’re persisting data
3. Run `StageD.java` from the `com.asg` package.

---

## Project Files

- `Room.java` – Abstract base class for all room types
- `StandardRoom`, `DeluxeRoom`, `PremiumRoom` – Subclasses with specific features
- `OneStandardDouble`, `OneStandardTriple` – Special rooms implementing accessibility features via `SpecialServices` interface
- `AccommodationException.java` – Custom checked exception
- `StageD.java` – Main class with menu, logic, persistence, and UI

---

## Report

Included: `104791010_assignment2.docx`

Contains:
- Code (copied, not screenshot)
- Test output
- Description of implementation decisions

---

## Sample Output

Console screenshots and test data are available in the Word report. The system allows:
- Validating room type/category
- Checking availability
- Saving and restoring bookings

---

## Notes

- Fully object-oriented with appropriate encapsulation
- Persistence is implemented **without Java serialization**

---

> Repository created and maintained by [Fathima Nishda](https://github.com/NishdaS) for academic use.
