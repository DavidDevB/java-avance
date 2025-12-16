package exercice4;

import java.util.Scanner;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class Grades {
	
	
	public static void main(String[] args) {
		
		/**
		 * Main method running the program.
		 */
		
		boolean allowLoop = true;
		Scanner scan = new Scanner(System.in);
		Hashtable<String, String[]> students = new Hashtable<>();
		
		while (allowLoop) {
			String[] names = getNames(scan);
			
			System.out.println("What are your grades?");
			String grades = scan.nextLine();
			String[] gradesArray = grades.split(" ");
			
			String joinedNames = String.join(" ", names);
			
			try {
				students.put(joinedNames,  gradesArray);
			}
			catch (Exception e) { // CATCH EXCEPTION ADDED HERE
				System.out.println("An error occured while saving your grades.");
				continue;
			}
			
			System.out.println(names[0] + " " + names[1] + ", your average is " + findAverageFromStudent(gradesArray));
			
			while (true) {
				/**
				 * Loop asking if the user wants to add another student.
				 */
				System.out.println("Add a student?: Y/n");
				String doYouContinue = scan.nextLine();
				
				if (doYouContinue.equalsIgnoreCase("y")) {
					break;
				} else if (doYouContinue.equalsIgnoreCase("n")) {
					allowLoop = false;
					break;
				} else {
					System.out.println("Enter 'Y' or 'n'");
				}
			}
		}
		
		
		searchOneStudent(scan, students);
		
		displayAllStudents(scan, sortStudents(scan, students));
		
		
		scan.close();
		return;
	}
	
	
	public static void displayAllStudents(Scanner scan, TreeMap<String, String[]> students) {
		
		/**
		 * Loop displaying all students registered with their grades and average grades.
		 */

		while (true) {
		System.out.println("Display all students? Y/n");
		String answer = scan.nextLine();
		if (answer.equalsIgnoreCase("y")) {
			for (Map.Entry<String, String[]> entry : students.entrySet()) {
				/**
				 * Loop inside the Hashtable.
				 */
				String studentName = entry.getKey();
				String[] grades = entry.getValue();
				
				System.out.println("Student: " + studentName);
				System.out.println("Grades: " + String.join(", ", grades));
				System.out.println("Average: " + findAverageFromStudent(grades));
				System.out.println("----------------------------");
			}
			break;
		} 
		else if (answer.equalsIgnoreCase("n")) {
			break;
		}
		else if (answer.isEmpty()){ // THROW EXCEPTION ADDED HERE
			throw new IllegalArgumentException("Input cannot be empty.");
		} else {
			System.out.println("Enter 'Y' or 'n'");
		}
	}
}
	
	
	public static void searchOneStudent(Scanner scan, Hashtable<String, String[]> students) {
		
		/**
		 * Allow the search of a student then displays his grades and average.
		 */
		
		while (true) {
			System.out.println("Which student are you looking for?");
			String studentToFind = scan.nextLine();
			String studentToFindLower = studentToFind.toLowerCase();
			
			if (isTwoWords(studentToFindLower)) {
				System.out.println("Student: " + studentToFindLower);
				if (!students.containsKey(studentToFindLower)) {
					System.out.println("Student is not listed.");
					continue;
				}
				System.out.println("Grades: " + String.join(", ", students.get(studentToFindLower)));
				System.out.println("Average: " + findAverageFromStudent(students.get(studentToFindLower)));
				break;
			}
		}
	}
	
	
	public static TreeMap<String, String[]> sortStudents(Scanner scan, Hashtable<String, String[]> students) {
		
		/**
		 * Convert the students Hashtable in to a Treemâp then sort it in ascending or descending order.
		 */
		
		while (true) {
			System.out.println("Do you want to sort in ascending or descending order?");
			String answer = scan.nextLine();
			
			TreeMap<String, String[]> treeMap;
			
			if (answer.toLowerCase().trim().equals("ascending")) {
				treeMap = new TreeMap<>(students);
				return treeMap;
			}
			else if (answer.toLowerCase().trim().equals("descending")) {
				treeMap = new TreeMap<>(Collections.reverseOrder());
				treeMap.putAll(students);
				return treeMap;
			} else {
				System.out.println("Please choose between the two options.");
			}
		}
		
		
	}
	
	public static boolean isTwoWords(String input) throws IllegalArgumentException {
		
		/**
		 * Checks if a string is composed of two words.
		 */
		
		// THROW EXCEPTION ADDED HERE
	    if (input == null || input.trim().isEmpty()) {
	        throw new IllegalArgumentException("Input cannot be null or empty.");	
	    }
	    String[] words = input.trim().split("\\s+");
	    return words.length == 2;
	}
	
	
	
	public static int findMin(int[] grades) {
		
		/**
		 * Returns the smallest number of an array of integers.
		 */
		
		int min = grades[0];
		for (int i = 0; i < grades.length; i++) {
			
			if (grades[i] < min) {
				min = grades[i];
			}
		}
		return min;
	}
	
	
	public static int findMax(int[] grades) {
		
		/**
		 * Returns the largest number of an array of integers.
		 */
		
		int max = grades[0];
		for (int i = 0; i < grades.length; i++) {
			
			if (grades[i] > max) {
				max = grades[i];
			}
		}
		return max;
	}
	
	
	public static float findAverage(int[] grades) {
		
		/**
		 * Return the average from an array of integers.
		 */
		
		int total = 0;
		for (int i = 0; i < grades.length; i++) {
			total += grades[i];
		}
		return (float) total / grades.length; 
	}
	
	
	public static String[] getNames(Scanner scan) {
		
		/**
		 * Get the lastname and firstname when user enters them and return an array of the two.
		 */
		
		boolean answered = false;
		String lastname = "";
		String firstname = "";
		while (!answered) {
			System.out.println("What is your lastname?");
			lastname = scan.nextLine().toLowerCase().trim();
			boolean invalid = false;
			for (char c: lastname.toCharArray()) {
				/**
				 * Checks if each character is a letter.
				 */
				if (Character.isDigit(c)) {
					System.out.println("Please enter only letters.");
					invalid = true;
					break;
				}
			}
			
			if (invalid) continue;
			
			System.out.println("What is your firstname?");
			firstname = scan.nextLine().toLowerCase().trim();
			for (char c: firstname.toCharArray()) {
				/**
				 * Checks if each character is a letter.
				 */
				if (Character.isDigit(c)) {
					System.out.println("Please enter only letters.");
					invalid = true;
					break;
				}
			}
			
			if (invalid) continue;
			
			answered = true;
			
		}
		return new String[] { firstname, lastname };
	}

	
	public static float findAverageFromStudent(String[] grades) {
		
		/**
		 * Return the average from an array of string integers by using findAverage() method.
		 */
		System.out.println(grades.length);
		int[] intGrades = new int[grades.length];
		
		for (int i = 0; i < grades.length; i++) {
			intGrades[i] = Integer.parseInt(grades[i]);
		}
		return findAverage(intGrades);
	}
}
