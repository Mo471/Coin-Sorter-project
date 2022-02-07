import java.util.ArrayList;

/**
 * @author anonymous
 * This class extends original CoinSorter class, and overrides some of its methods in order for them to
 * work properly with GUI version of program instead of terminal version of program
 */
public class CoinSorterGUI extends CoinSorter 
{
	// Constructors duplicate constructors from superclass
	public CoinSorterGUI(String currencyIn, int minCoinIn, int maxCoinIn, ArrayList<Integer> coinListIn)
	{
		super(currencyIn, minCoinIn, maxCoinIn, coinListIn);
	}
	
	// Constructors duplicate constructors from superclass
	public CoinSorterGUI()
	{
		super();
	}
	
	/*
	 * Method was overridden in order to adapt it to GUI instead of terminal output
	 */
	@Override
	public boolean validateCalculatorInput(String ValueToExchangeIn, String coinTypeIn) {
		// Firstly, validating if values are numbers
		if (!validateInt(ValueToExchangeIn) || !validateInt(coinTypeIn))
		{
			return false;
		}		
		
		// Secondly, validating value to exchange
		if(Integer.parseInt(ValueToExchangeIn) < getMinCoinIn() || Integer.parseInt(ValueToExchangeIn) > getMaxCoinIn())
		{
			return false;
		}
		
		// Thridly, validating coin type
		for (int i = 0; i < getCoinList().size(); i ++)
		{
			if (getCoinList().get(i) != Integer.parseInt(coinTypeIn))
			{
				continue;
			}
			else return true;
		}
		return false;
	}

	
	/*
	 * Method was overridden in order to adapt it to GUI instead of terminal output 
	 */
	@Override
	public String printCoinList() {
		String temp = "The current coin denominations are in circulation: ";
		if (getCoinList() != null && getCoinList().size() > 0) // evaluating if there are elements to display from coinList
		{
			for (int i = 0; i < getCoinList().size(); i ++)// iterating through all elements of coinList and adding them to temporary String
			{	
				temp += getCoinList().get(i);
				temp += ", ";
			}
			temp = temp.substring(0, temp.length() - 2); // removing last ", "
			return temp;
		}
		temp += "none"; // this case activated in case coinList is empty
		return temp;
	}

}
	

