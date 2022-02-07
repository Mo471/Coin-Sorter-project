import java.util.ArrayList;
import java.util.List;

/**
 * @author anonymous
 * This class provides attributes, methods and overall logic for Coin Sorter program.
 * All methods from this class are used for terminal version of program and majority of them are used for
 * program with GUI
 */
public class CoinSorter {
	
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private List<Integer> coinList;
	
	/* First constructor, assigns data to all attributes. Even though this constructor allows flexibility with 
	 * assigning different data to attributes, in the body of program parameters will be passed with accordance to the brief.
	 * This flexibility was provided for future development and to comply with UML diagram in brief 
	 */
	public CoinSorter(String currencyIn, int minCoinIn, int maxCoinIn, ArrayList<Integer> coinListIn)
	{
		this.currency = currencyIn;
		this.minCoinIn = minCoinIn;
		this.maxCoinIn = maxCoinIn;
		this.coinList = coinListIn;
	}
	
	// Second constructor, assigns default data from brief to currency, minCoinIn, maxCoinIn and coinList
	public CoinSorter()
	{
		this.currency = "Pound sterling";
		this.minCoinIn = 0;
		this.maxCoinIn = 10000;
		setStandardCoinValues();
	}
	
	// Method to set currency
	public void setCurrency(String currencyIn)
	{
		this.currency = currencyIn;
	}
	
	// Method to set minCoinIn
	public void setMinCoinIn(int minCoinIn)
	{
		this.minCoinIn = minCoinIn;
	}
	
	// Method to set maxCoinIn
	public void setMaxCoinIn(int maxCoinIn)
	{
		this.maxCoinIn = maxCoinIn;
	}
	
	// Method to get currency
	public String getCurrency()
	{
		return this.currency;
	}
	
	// Method to get minCoinIn
	public int getMinCoinIn()
	{
		return this.minCoinIn;
	}

	// Method to get maxCoinIn
	public int getMaxCoinIn()
	{
		return this.maxCoinIn;
	}
	
	// Method to get coinList(used for GUI)
	public List<Integer> getCoinList()
	{
		return this.coinList;
	}
	
	/* This method both prints and returns coinList elements.
	 * It does both because description of method and return data type in UML diagram contradict each other in brief
	 * Method description requires to print String. UML diagram requires to return String.
	 * In order to comply with brief requirements, this method does both. In real life project, I would return to discuss with client
	 * and suggest using only return String for this method, and use println function in main, if it needs to be printed
	 */ 
	public String printCoinList()
	{
		String temp = "The current coin denominations are in circulation: ";
		if (this.coinList != null && this.coinList.size() > 0) // evaluating if there are elements to display from coinList
		{
			for (int i = 0; i < this.coinList.size(); i ++)// iterating through all elements of coinList and adding them to temporary String
			{	
				temp += this.coinList.get(i);
				temp += ", ";
			}
			temp = temp.substring(0, temp.length() - 2); // removing last ", "
			System.out.println(temp);
			return temp;
		}
		temp += "none"; // this case activated in case coinList is empty
		System.out.println(temp);
		return temp;
		
	}
		
	/* Coin calculator takes total value to calculate and in which coins should calculation happen.
	 * After that method will calculate how many coins of given denomination in total value
	 * and what is the remainder. For instance, with parameters (120, 50), result will be
	 * 2x50p and 20p remainder
	 */
	public String coinCalculator(int totalValueToExchangeIn, int coinTypeIn)
	{		
		int coinTypeCounter = 0;
		String coinCurrency;
		while(totalValueToExchangeIn >= coinTypeIn)
		{
			totalValueToExchangeIn -= coinTypeIn;
			coinTypeCounter ++;
		}
		
		// Short conditional statement to use cents or pennies in printing statement, depending in current currency setting
		if(this.currency.equalsIgnoreCase("Pound sterling"))
		{
			coinCurrency = "p";
		}
		else
		{
			coinCurrency = "c";
		}
		return ("A total of " + coinTypeCounter + " x " + coinTypeIn + coinCurrency + " coints can be exchanged, with a remainder of " + totalValueToExchangeIn + coinCurrency + ".");
	}
	
	/* Multi coin calculator takes total value to calculate and which coin type should not be used in calculations.
	 * After that, calculator will calculate to which denominators total value can be divided, starting from the biggest
	 * and finishing with the smallest denominator, EXCLUDING coinTypeIn denominator. After that, result is returned with remainder.
	 * For instance, with parameters (562, 50) result will be 2 x 200p, 1 x 100p, 0 x 50p, 3 x 20p, 0 x 10p, with a remainder of 2p
	 */
	public String multiCoinCalculator(int totalValueToExchangeIn, int coinTypeIn)
	{
		int coinTypeCounter200 = 0;
		int coinTypeCounter100 = 0;
		int coinTypeCounter50 = 0;
		int coinTypeCounter20 = 0;
		int coinTypeCounter10 = 0;
		String coinCurrency;
		
		// Calculations for 200p
		while(totalValueToExchangeIn >= 200 && coinTypeIn != 200)
		{
			totalValueToExchangeIn -= 200;
			coinTypeCounter200 ++;
		}
		
		// Calculations for 100p
		while(totalValueToExchangeIn >= 100 && coinTypeIn != 100)
		{
			totalValueToExchangeIn -= 100;
			coinTypeCounter100 ++;
		}
		
		// Calculations for 50p
		while(totalValueToExchangeIn >= 50 && coinTypeIn != 50)
		{
			totalValueToExchangeIn -= 50;
			coinTypeCounter50 ++;
		}
		
		// Calculations for 20p
		while(totalValueToExchangeIn >= 20 && coinTypeIn != 20)
		{
			totalValueToExchangeIn -= 20;
			coinTypeCounter20 ++;
		}
		
		// Calculations for 10p
		while(totalValueToExchangeIn >= 10 && coinTypeIn != 10)
		{
			totalValueToExchangeIn -= 10;
			coinTypeCounter10 ++;
		}
		
		// Short conditional statement to use cents or pennies in printing statement, depending on current currency setting
		if(this.currency.equalsIgnoreCase("Pound sterling"))
		{
			coinCurrency = "p";
		}
		else
		{
			coinCurrency = "c";
		}
		
		return ("The coins exchanged are: " + coinTypeCounter200 + " x " + "200" + coinCurrency + ", " + coinTypeCounter100 + " x 100" + coinCurrency + ", " + coinTypeCounter50 + " x 50" + coinCurrency + ", " + coinTypeCounter20 + " x 20" + coinCurrency + ", " + coinTypeCounter10 + " x 10" + coinCurrency + ", with a remainder of " + totalValueToExchangeIn + coinCurrency);
	}
	
	// Returns String with current currency and minimum and maximum value accepted as an input
	public String displayProgramConfigs()
	{
		String coin;
		if(this.currency.equalsIgnoreCase("Pound sterling"))
		{
			coin = "p";
		}
		else
		{
			coin = "c";
		}
		return ("The current currency is " + this.currency + " and the current minimum and maximum values accepted as an input are " + this.minCoinIn + coin + " and " + this.maxCoinIn + coin);
	}
	
	/* This validating method returns true if given coin type is in the coinList AND if given valueToExchange is
	 * in provided range from the brief (from minCoinIn to maxCoinIn) AND if provided values can be used as integers
	 */
	public boolean validateCalculatorInput(String ValueToExchangeIn, String coinTypeIn)
	{
		// Firstly, validating if values are numbers
		if (!validateInt(ValueToExchangeIn) || !validateInt(coinTypeIn))
		{
			System.out.println("You have to type in only integers in order to perform operation");
			return false;
		}		
		
		// Secondly, validating value to exchange
		if(Integer.parseInt(ValueToExchangeIn) < this.minCoinIn || Integer.parseInt(ValueToExchangeIn) > this.maxCoinIn)
		{
			System.out.println("Your value to exchange should be in the range " + this.minCoinIn + " - " + this.maxCoinIn + " inclusive");
			return false;
		}
		
		// Thridly, validating coin type
		for (int i = 0; i < coinList.size(); i ++)
		{
			if (coinList.get(i) != Integer.parseInt(coinTypeIn))
			{
				continue;
			}
			else return true;
		}
		System.out.println("The coin type you have requested is not supported. Supported coin types (denominations in circulation) are 200, 100, 50, 20, 10. "
		+ "In order to do calculation use one of them");
		return false;
	}
	
	/* This validating method returns true if given String is integer
	 * Otherwise, false
	 */
	public boolean validateInt(String stringToValidate)
	{
		try
		{
	        int temp1 = Integer.parseInt(stringToValidate.trim());
	    }
		catch (NumberFormatException nfe)
		{
			return false;
	    }
		if(stringToValidate.trim().contains(",") || stringToValidate.trim().contains("."))
		{
			return false;
		}
		return true;
	}
	
	/* Method to assign values to the second constructor
	 * However, in case first constructor will be initialised, can be called from the main in order to populate coinList with
	 * correct denominators from the brief (200, 100, 500, 30, 10)
	 */
	public void setStandardCoinValues()
	{	
		this.coinList = new ArrayList<Integer>();
		this.coinList.add(200);
		this.coinList.add(100);
		this.coinList.add(50);
		this.coinList.add(20);
		this.coinList.add(10);
	}
}
