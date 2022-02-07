import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author anonymous
 * This class defines all elements of terminal menu, initialises all necessary objects and starts program
 * Can be complied and launched both in IDE and terminal
 */
public class TestCoinSorter {
	
	// Main method to initialise objects and launch menu
	public static void main(String[] args) {
		// Initialising user1. Interface list initialised as an array list
		CoinSorter user1 = new CoinSorter("Pound Sterling", 0, 10000, new ArrayList<>());
		user1.setStandardCoinValues();
		
		// Starting main menu and passing object instance as a parameter
		startMenu(user1);
		
	}

	// This method defines main menu and starts it
	public static void startMenu(CoinSorter objectIn)
	{

		// Printing menu
		System.out.println("\n*** Coin Sorter - Main Menu ***\n");
		System.out.println("1 - Coin Calculator\n");
		System.out.println("2 - Multiple Coin Calculator\n");
		System.out.println("3 - Print coin list\n");
		System.out.println("4 - Set details\n");
		System.out.println("5 - Display program configurations\n");
		System.out.println("6 - Quit the program\n");
		System.out.print("\nPlease type corresponding number in terminal: ");
		
		// Initialising scanner to take input
		Scanner sc = new Scanner(System.in);
		String input = sc.next().trim();
		
		/* Below menu for each choice in menu defined. As section 4 (set details) opens up
		 * another menu, it was created as another class.
		 * Important to not that menu is using recursion on order to be able to repeatedly prompt user for input after
		 * getting invalid input or after some operations were finished
		 */
		
		// Case 1, coin calculator
		if(input.equalsIgnoreCase("1"))
		{
			// Variables to take user input
			String totalValueToExchangeIn;
			String coinTypeIn;
			
			// This loop validates input data
			do
			{
				System.out.print("\nType in total value to exchange: ");
				totalValueToExchangeIn = sc.next();
				System.out.print("\nType in the coin type for exchange: ");
				coinTypeIn = sc.next();
			} 
			while (!objectIn.validateCalculatorInput(totalValueToExchangeIn, coinTypeIn));
			
			// When input was validated, coinCalculator is called in order to perform calculation and print result
			System.out.print("\n");
			System.out.println(objectIn.coinCalculator(Integer.parseInt(totalValueToExchangeIn), Integer.parseInt(coinTypeIn)));
			System.out.print("\n");
			startMenu(objectIn);
		}
		
		// Case 2, Multiple Coin Calculator
		else if(input.equalsIgnoreCase("2"))
		{
			// Variables to take user input
			String totalValueToExchangeIn;
			String coinTypeIn;
			
			// This loop validates input data
			do
			{
				System.out.print("\nType in total value to exchange: ");
				totalValueToExchangeIn = sc.next();
				System.out.print("\nType in which coin should not be used in exchange: ");
				coinTypeIn = sc.next();
			} 
			while (!objectIn.validateCalculatorInput(totalValueToExchangeIn, coinTypeIn));
			
			// When input was validated, coinCalculator is called in order to perform calculation and print result
			System.out.print("\n");
			System.out.println(objectIn.multiCoinCalculator(Integer.parseInt(totalValueToExchangeIn), Integer.parseInt(coinTypeIn)));
			System.out.print("\n");
			startMenu(objectIn);
		}
		
		// Case 3, print coin list
		else if(input.equalsIgnoreCase("3"))
		{
			System.out.print("\n");
			objectIn.printCoinList();
			System.out.print("\n");
			startMenu(objectIn);
		}
		
		// Case 4, set details. As this option is the most complicated, it starts in separate method with separate menu
		else if (input.equalsIgnoreCase("4"))
		{
			choice4(objectIn);
		}
		
		// Case 5, display program configurations
		else if (input.equalsIgnoreCase("5"))
		{
			System.out.print("\n");
			System.out.println(objectIn.displayProgramConfigs());
			System.out.print("\n");
			startMenu(objectIn);
		}
		
		// Case 6, exit
		else if (input.equalsIgnoreCase("6"))
		{
			System.out.println("\nThank you for using Coin Sorter program");
			sc.close(); // Closing scanner to avoid memory leak
		}
		
		// This part handles invalid input in main menu and uses recursion
		else
		{
			System.out.println("\nInvalid input. Please type number 1 - 6");
			System.out.println("Restarting program\n\n");
			startMenu(objectIn); // Recursive call to get input 1 - 6, and if not provided keep prompting user
		}
	}
	
	// Separate method for case 4, set details
	public static void choice4(CoinSorter objectIn)
	{
		// Printing menu
		System.out.println("\n*** Set Details Sub-Menu ***\n");
		System.out.println("1 - Set currency\n");
		System.out.println("2 - Set minimum coin input value\n");
		System.out.println("3 - Set maximum coin input value\n");
		System.out.println("4 - Return to main menu\n");
		System.out.print("\nPlease type corresponding number in terminal: ");
		
		// Scanner for user input
		Scanner sc = new Scanner(System.in);
		String input = sc.next().trim();
		
		// Case 4.1
		if(input.equalsIgnoreCase("1"))
		{
			System.out.print("Type in the currency you would like to set. GBP or USD: ");
			input = sc.next().trim();
			// As provided per brief and per extra instructions from from course coordinator, only two currencies will be accepted in this section: GBP and USD
			// Checking if user input requests to change currency to GBP
			if (input.equalsIgnoreCase("sterling") || input.equalsIgnoreCase("pound") || input.equalsIgnoreCase("pounds") || input.equalsIgnoreCase("GBP"))
			{
				objectIn.setCurrency("Pound sterling");
				System.out.print("\n");
				System.out.println("Currency has been setup to Pound sterling");
				System.out.print("\n");
			}
			// Checking if user input requests to change currency to USD
			else if(input.equalsIgnoreCase("US") || input.equalsIgnoreCase("USD") || input.equalsIgnoreCase("Dollar") || input.equalsIgnoreCase("Dollars"))
			{
				objectIn.setCurrency("US dollar");
				System.out.print("\n");
				System.out.println("Currency has been setup to US dollar");
				System.out.print("\n");
			}
			// In all other cases user will be explained which currencies are possible and how to set them up
			else
			{
				System.out.print("\n");
				System.out.println("Due to the brief instructions provided this program should only work with Pound sterling or US dollars. Try again, and type in \"GBP\" or \"USD\"");
				System.out.print("\n");
			}
			// Returning to set details menu
			choice4(objectIn);
		}
		
		// Case 4.2, set minimum coin input
		
		else if(input.equalsIgnoreCase("2"))
		{
			// Explaining user which input is allowed and taking input
			System.out.print("Type in the minimum coin input you would like to set. Should be in range 0 - 10,000 inclusive, "
					+ "and no more than the maximum coin input: ");
			String inputInt = sc.next();
			
			// Validating that input is an integer (not data type, passed data type is String). Printing error if not
			if(!objectIn.validateInt(inputInt))
			{
				System.out.print("\n");
				System.out.println("You have to type in only integers in order to perform operation. Try again");
				System.out.print("\n");
				choice4(objectIn);
			}
			
			// Validating that input is within given parameters (0 - 10,000, no more than MaxCoinInput.
			if (Integer.parseInt(inputInt) >= 0 && Integer.parseInt(inputInt) <= 10000 && Integer.parseInt(inputInt) <= objectIn.getMaxCoinIn())
			{
				// If validated, setting up value
				objectIn.setMinCoinIn(Integer.parseInt(inputInt));
				System.out.print("\n");
				System.out.println("The minimum coin input has been setup to " + inputInt + " coins");
				System.out.print("\n");
			}
			
			// In case input invalid, explaining error and prompting again
			else
			{
				System.out.print("\n");
				System.out.println("Due to brief provided, the minimum and maximum coin input should be in range 0 - 10,000 inclusive, and the minimum input "
						+ "should not be more than the maximum coin input. Try again, and set value from this range as in input");
				System.out.print("\n");
			}
			choice4(objectIn);
		}
		
		// Case 4.2, set maximum coin input
		else if(input.equalsIgnoreCase("3"))
		{
			// Explaining user which input is allowed and taking input
			System.out.print("Type in the maximum coin input you would like to set. Should be in range 0 - 10,000 inclusive, "
					+ "and no less than the minimum coin input: ");
			String inputInt = sc.next();
			
			// Validating that input is an integer (not data type, passed data type is String). Printing error if not
			if(!objectIn.validateInt(inputInt))
			{
				System.out.print("\n");
				System.out.println("You have to type in only integers in order to perform operation. Try again");
				System.out.print("\n");
				choice4(objectIn);
			}
			
			// Validating that input is within given parameters (0 - 10,000, no less than MinCoinInput.
			if (Integer.parseInt(inputInt) >= 0 && Integer.parseInt(inputInt) <= 10000 && Integer.parseInt(inputInt) >= objectIn.getMinCoinIn())
			{
				// If validated, setting up value
				objectIn.setMaxCoinIn(Integer.parseInt(inputInt));
				System.out.print("\n");
				System.out.println("The maximum coin input has been setup to " + inputInt + " coins");
				System.out.print("\n");
			}
			
			// In case input invalid, explaining error and prompting again
			else
			{
				System.out.print("\n");
				System.out.println("Due to brief provided, the minimum and maximum coin input should be in range 0 - 10,000 inclusive, and the maximum input "
						+ "should not be less than the minimum coin input. Try again, and set value from this range as in input");
				System.out.print("\n");
			}
			choice4(objectIn);
		}
		
		// Case 4.4, exit to main menu
		else if(input.equalsIgnoreCase("4"))
		{
			startMenu(objectIn);
			sc.close(); // Closing scanner to avoid memory leak
		}
		
		// Handling invalid input in case 4 menu
		else
		{
			System.out.println("\nInvalid input. Please type number 1 - 4");
			System.out.println("Restarting program\n\n");
			choice4(objectIn); // Recursive call to get input 1 - 4, and if not provided keep prompting user
		}
	}

}
