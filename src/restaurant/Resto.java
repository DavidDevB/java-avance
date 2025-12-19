package restaurant;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resto {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Hello, how many menus do you want?");
		int nbOfMenus = scan.nextInt();
		
		Map<String, Double> entries = new HashMap<>();
            entries.put("SALAD", 5.50);
            entries.put("SOUP", 4.00);
            entries.put("QUICHE", 6.75);
            entries.put("NONE", 0.0);

		Map<String, Double> dishes = new HashMap<>();
            dishes.put("CHICKEN",     12.50);
            dishes.put("BEEF",        15.00);
            dishes.put("FISH",        14.50);
            dishes.put("VEGETARIAN",  10.00);
            dishes.put("VEGAN",       11.00);
            dishes.put("NONE",         0.00);

		Map<String, Double> trimmings = new HashMap<>();
            trimmings.put("RICE",        2.50);
            trimmings.put("NOODLES",     3.00);
            trimmings.put("FRIES",       3.50);
            trimmings.put("VEGETABLES",  2.00);
            trimmings.put("NONE",        0.00);

		Map<String, Double> drinks = new HashMap<>();
            drinks.put("TAP WATER",        0.00);
            drinks.put("SPARKLING WATER",  2.50);
            drinks.put("SODA",             3.00);
            drinks.put("WINE",             5.50);
            drinks.put("NONE",             0.00);

		Map<String, Double> desserts = new HashMap<>();
            desserts.put("HOMEMADE TART",      5.50);
            desserts.put("CHOCOLATE MOUSSE",   4.50);
            desserts.put("TIRAMISU",           6.00);
            desserts.put("NONE",               0.00);
		
		// Create a new empty list of list of strings.
		List<Map<String, Double>> finalMenus = new ArrayList<>();
	
		// Loop in number of menus.
		for (int i = 0; i < nbOfMenus; i++) {
			
			// Add a new empty list in the list.
			finalMenus.add(new HashMap<>());
			
			System.out.println("Order " + (i + 1));
			
			// Ask what entry you want by looping in the array of entries.
			System.out.println("Entries choices:");
			int entryIndex = 1;
			for (String key : entries.keySet()) {
				System.out.print("[" + entryIndex++ + " - " + key + "]");
			}
			System.out.println("Which entry do you want?");
			int whichEntry = scan.nextInt();
            if (whichEntry < 1 || whichEntry > entries.size()) {
                throw new IllegalArgumentException("Invalid entry choice.");
            }
			finalMenus.get(i).put(new ArrayList<>(entries.keySet()).get(whichEntry - 1).toLowerCase(),
                                  new ArrayList<>(entries.values()).get(whichEntry - 1));
			
			// Ask what entry you want by looping in the array of dishes.
			System.out.println("Dishes choices:");
			int dishIndex = 1;
			for (String key : dishes.keySet()) {
				System.out.print("[" + dishIndex++ + " - " + key + "]");
			}
			System.out.println("Which dish do you want?");
			int whichDish = scan.nextInt();
            if (whichDish < 1 || whichDish > dishes.size()) {
                throw new IllegalArgumentException("Invalid dish choice.");
            }
			finalMenus.get(i).put(new ArrayList<>(dishes.keySet()).get(whichDish - 1).toLowerCase(),
                                  new ArrayList<>(dishes.values()).get(whichDish - 1));
			
			// Ask what entry you want by looping in the array of trimmings.
			System.out.println("Trimmings choices:");
            int trimmingIndex = 1;
			for (String key : trimmings.keySet()) {
				System.out.print("[" + trimmingIndex++ + " - " + key + "]");
			}
			System.out.println("Which dish do you want?");
			int whichTrimming = scan.nextInt();
            if (whichTrimming < 1 || whichTrimming > trimmings.size()) {
                throw new IllegalArgumentException("Invalid trimming choice.");
            }
			finalMenus.get(i).put(new ArrayList<>(trimmings.keySet()).get(whichTrimming - 1).toLowerCase(),
                                  new ArrayList<>(trimmings.values()).get(whichTrimming - 1));
			
			// Ask what entry you want by looping in the array of drinks.
			System.out.println("Drinks choices:");
            int drinkIndex = 1;
			for (String key : drinks.keySet()) {
				System.out.print("[" + drinkIndex++ + " - " + key + "]");
			}
			System.out.println("What do you want to drink?");
			int whichDrink = scan.nextInt();
            if (whichDrink < 1 || whichDrink > drinks.size()) {
                throw new IllegalArgumentException("Invalid drink choice.");
            }
			finalMenus.get(i).put(new ArrayList<>(drinks.keySet()).get(whichDrink - 1).toLowerCase(),
                                  new ArrayList<>(drinks.values()).get(whichDrink - 1));
			
			// Ask what entry you want by looping in the array of desserts.
			System.out.println("Desserts choices:");
            int dessertIndex = 1;
			for (String key : desserts.keySet()) {
				System.out.print("[" + dessertIndex++ + " - " + key + "]");
			}
			System.out.println("What do you want for dessert?");
			int whichDessert = scan.nextInt();
            if (whichDessert < 1 || whichDessert > desserts.size()) {
                throw new IllegalArgumentException("Invalid dessert choice.");
            }
			finalMenus.get(i).put(new ArrayList<>(desserts.keySet()).get(whichDessert - 1).toLowerCase(),
                                  new ArrayList<>(desserts.values()).get(whichDessert - 1));
			try (PrintWriter fileWriter = new PrintWriter(new FileWriter("menus.txt"))) {
				System.out.println("Command " + (i + 1) + " summary:");
				fileWriter.println("Command " + (i + 1) + " summary:");
				for (String key : finalMenus.get(i).keySet()) {
					System.out.println("- " + key + ": " + finalMenus.get(i).get(key) + " $");
					fileWriter.println("- " + key + ": " + finalMenus.get(i).get(key) + " $");
				}
				System.out.println("Total: " + finalMenus.get(i).values().stream().mapToDouble(Double::doubleValue).sum() + " $");
				fileWriter.println("Total: " + finalMenus.get(i).values().stream().mapToDouble(Double::doubleValue).sum() + " $");
			} catch (Exception e) {
				System.out.println("An error occurred while writing to the file: " + e.getMessage());
			}
		}
    	scan.close();
	}
}
	