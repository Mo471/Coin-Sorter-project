import java.util.ArrayList;

import javafx.application.Application;  
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color; 
import javafx.scene.text.Font; 
import javafx.stage.Stage; 
import javafx.geometry.Pos;

/**
 * @author anonymous
 * This class defines GUI version of menu, initialises all necessary objects and starts the program
 * Can be complied and launched both in IDE and terminal
 */
public class TestCoinSorterGUI extends Application {
	
	// Initialising instance, passing standard parameters according to brief. Interface list initialised as an array list 
	CoinSorterGUI user1 = new CoinSorterGUI("Pound Sterling", 0, 10000, new ArrayList<>());	
	
	// This method defines main menu and starts it
	public void start(Stage stage) 
	{
		// Populating array list parameter according to brief
		user1.setStandardCoinValues();
		
		// Node with output text. Invisible unless text will be set
		Label outputTextLabel= new Label();
		outputTextLabel.setTextFill(Color.BLACK);
		outputTextLabel.setFont(Font.font("Arial", 15));
		outputTextLabel.setText("Coin Sorter - Main Menu");
		
		// Option 1, Coin Calculator. Defining button and action. Called in separate window and using separate method below
		Button buttonCoinCalculator = new Button();         
		buttonCoinCalculator.setText("Coin Calculator");         
		buttonCoinCalculator.setOnAction(e -> coinCalculatorMenu());      
		
		// Option 2, Multiple Coin Calculator. Defining button and action. Called in separate window and using separate method below
		Button buttonMultiCoinCalculator = new Button();         
		buttonMultiCoinCalculator.setText("Multiple Coin Calculator");         
		buttonMultiCoinCalculator.setOnAction(e -> multiCoinCalculatorMenu());
		
		// Option 3, Print coin list. Defining button and action
		Button buttonPrintCoinList = new Button();         
		buttonPrintCoinList.setText("Print coin list");
		buttonPrintCoinList.setOnAction(e -> outputTextLabel.setText(user1.printCoinList()));     
		
		// Option 4, Set details. Opens separate window which defined in separate method below
		Button buttonSetDetails = new Button();         
		buttonSetDetails.setText("Set details");         
		buttonSetDetails.setOnAction(e -> setDetailsMenu());      
		
		// Option 5, Display program configurations. Defining button and action
		Button buttonDisplayConfig = new Button();         
		buttonDisplayConfig.setText("Display program configurations");         
		buttonDisplayConfig.setOnAction(e -> outputTextLabel.setText(user1.displayProgramConfigs()));
		
		// Option 6, Quit the program, alternative way to close program window
		Button buttonQuitProgram = new Button();         
		buttonQuitProgram.setText("Quit the program");
		buttonQuitProgram.setOnAction(e -> stage.close());
		
		// Grouping buttons together in horisontal box
		HBox mainMenuOptions = new HBox(10);
		mainMenuOptions.setAlignment(Pos.CENTER);
		mainMenuOptions.getChildren().addAll(buttonCoinCalculator, buttonMultiCoinCalculator, buttonPrintCoinList, buttonSetDetails, buttonDisplayConfig, buttonQuitProgram);
		
		// Creating root node and adding all elements from main menu to it
		VBox root = new VBox(10); 
		root.setBackground(Background.EMPTY);
		root.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(mainMenuOptions, outputTextLabel);
		Scene scene = new Scene(root, 1000, 300, Color.WHITE);
		
		// Passing scene to the stage and starting menu application
		stage.setScene(scene);         
		stage.setTitle("Coin Sorter");           
		stage.show();
		
	}
	
	// Used to launch application from IDE
	public static void main(String[] args)     
	 {         
		 launch(args);     
	 }
	 
	/*Coin calculator menu defined here as a separate stage
	  * 
	  */
	public void coinCalculatorMenu()
	 {
		 // Initialising new stage
		 Stage coinCalculatorMenu = new Stage();
		 
		 // Creating label for text and putting it VBox in order to be able to position in properly in the centre
		 Label textLabel = new Label();
		 textLabel.setTextFill(Color.BLACK);         
		 textLabel.setFont(Font.font("Arial", 20));
		 textLabel.setText("Type in total value to exchange in the first field and the coin type for exchange in the second field");
		 VBox textBox = new VBox();
		 textBox.getChildren().addAll(textLabel);
		 textBox.setAlignment(Pos.CENTER);
		 
		 // Text field to take first input
		 TextField textFieldTotalValue = new TextField();
		 textFieldTotalValue.setMaxWidth(250);
		 // Text field to take second input
		 TextField textFieldCoinType = new TextField();
		 textFieldCoinType.setMaxWidth(250);
		 
		 // Creating and defining action for calculation button
		 Button buttonCalculateCoins = new Button();         
		 buttonCalculateCoins.setText("Calculate");
		 buttonCalculateCoins.setOnAction(e -> {
			 // Validatin data, if correct, performing calculations and putting them in textLabel
			 if (user1.validateCalculatorInput(textFieldTotalValue.getText(), textFieldCoinType.getText()))
			 {
				 textLabel.setText(user1.coinCalculator(Integer.parseInt(textFieldTotalValue.getText()), Integer.parseInt(textFieldCoinType.getText())));
				 textLabel.setTextFill(Color.BLACK);
			 }
			 // Otherwise, requesting the correct input
			 else
			 {
				 textLabel.setText("ERROR:\nYou have to type in only integers.\nYour value to exchange should be in the range " + user1.getMinCoinIn() + " - " + user1.getMaxCoinIn() + " inclusive. \n"
				 		+ "Supported coin types (denominations in circulation) are 200, 100, 50, 20, 10. \nType in different values and try again");
				 textLabel.setTextFill(Color.RED);
			 }
		 });
		 
		 // Creating root node and addint elements to it
		 VBox root = new VBox(10);
		 root.setSpacing(10);         
		 root.setAlignment(Pos.CENTER); 
		 root.getChildren().addAll(textLabel, textFieldTotalValue, textFieldCoinType, buttonCalculateCoins);
		 
		 // Creating and launching scene
		 Scene scene = new Scene(root, 1000, 300);
		 coinCalculatorMenu.setScene(scene);
		 coinCalculatorMenu.setTitle("Coin calculator");         
		 coinCalculatorMenu.show(); 
		 
	 }
	 
	/*Multiple Coin calculator menu defined here as a separate stage
	  * 
	  */
	public void multiCoinCalculatorMenu()
	 {
		 // Initialising object of Stage
		 Stage multiCoinCalculatorMenu = new Stage();
		 
		 // Creating new text label in order to print output and errors
		 Label textLabel = new Label();
		 textLabel.setTextFill(Color.BLACK);         
		 textLabel.setFont(Font.font("Arial", 20));
		 textLabel.setText("Type in total value to exchange in the first field and which coin should not be used in exchange in the second field");
		 // Using VBox in order to be able to centrally allign output test in the GUI
		 VBox textBox = new VBox();
		 textBox.getChildren().addAll(textLabel);
		 textBox.setAlignment(Pos.CENTER);
		 
		 // Creating text field in order to take input 1
		 TextField textFieldTotalValue = new TextField();
		 textFieldTotalValue.setMaxWidth(250);
		 
		 // Creating text field in order to take input 2
		 TextField textFieldCoinTypeToExclude = new TextField();
		 textFieldCoinTypeToExclude.setMaxWidth(250);
		 
		 // Creating and assigning action to button
		 Button buttonCalculateCoins = new Button();         
		 buttonCalculateCoins.setText("Calculate");
		 buttonCalculateCoins.setOnAction(e -> {
			 
			 // Validating user input and performing calculation
			 if (user1.validateCalculatorInput(textFieldTotalValue.getText(), textFieldCoinTypeToExclude.getText()))
			 {
				 textLabel.setText(user1.multiCoinCalculator(Integer.parseInt(textFieldTotalValue.getText()), Integer.parseInt(textFieldCoinTypeToExclude.getText())));
				 textLabel.setTextFill(Color.BLACK);
			 }
			 
			 // In case of invalid input providing error message with possible problems
			 else
			 {
				 textLabel.setText("ERROR:\nYou have to type in only integers.\nYour value to exchange should be in the range " + user1.getMinCoinIn() + " - " + user1.getMaxCoinIn() + " inclusive. \n"
				 		+ "Supported coin types (denominations in circulation) are 200, 100, 50, 20, 10. \nType in different values and try again");
				 textLabel.setTextFill(Color.RED);
			 }
		 });
		 
		 // Root node to group all nodes together
		 VBox root = new VBox(10);
		 root.setSpacing(10);         
		 root.setAlignment(Pos.CENTER); 
		 root.getChildren().addAll(textLabel, textFieldTotalValue, textFieldCoinTypeToExclude, buttonCalculateCoins);
		 
		 // And initialise and launch a scene
		 Scene scene = new Scene(root, 1200, 300);
		 multiCoinCalculatorMenu.setScene(scene);
		 multiCoinCalculatorMenu.setTitle("Multiple coin calculator");         
		 multiCoinCalculatorMenu.show(); 
		 
	 }
	 
	/* Set details menu defined here as a separate stage
	  * 
	  */
	public void setDetailsMenu()
	 {
		 // Initialising stage
		 Stage setDetailsMenu = new Stage();
		 
		 // Creating text label and putting in the box for allignment functions
		 Label textLabel = new Label();
		 textLabel.setTextFill(Color.BLACK);         
		 textLabel.setFont(Font.font("Arial", 20));
		 textLabel.setText("Type in value and click button to set values");
		 VBox textBox = new VBox();
		 textBox.getChildren().addAll(textLabel);
		 textBox.setAlignment(Pos.CENTER);
		 
		 // Creating new text field for input
		 TextField textFieldSetlValue = new TextField();
		 textFieldSetlValue.setMaxWidth(250);
		 
		 // As terminal version of program, case 4 offers 4 extra options in separate window
		 
		 // Case 4.1, set currency. Creating button and validating input
		 Button buttonSetCurrency = new Button();         
		 buttonSetCurrency.setText("Set currency");
		 buttonSetCurrency.setOnAction(e -> {
			 // As described in brief and by course coordinator, the only possible currencies are GBP and USD
			 // Validating if text is requesting to change currency to GBP
			 if (textFieldSetlValue.getText().trim().equalsIgnoreCase("sterling pound") || textFieldSetlValue.getText().trim().equalsIgnoreCase("pound") || textFieldSetlValue.getText().trim().equalsIgnoreCase("pound sterling") || textFieldSetlValue.getText().trim().equalsIgnoreCase("GBP"))
			 {
				 user1.setCurrency("Pound sterling");
				 textLabel.setText("Currency has been setup to Pound sterling");
				 textLabel.setTextFill(Color.BLACK);
			 }
			 
			// Validating if text is requesting to change currency to USD
			 else if (textFieldSetlValue.getText().trim().equalsIgnoreCase("US dollar") || textFieldSetlValue.getText().trim().equalsIgnoreCase("USD") || textFieldSetlValue.getText().trim().equalsIgnoreCase("Dollar") || textFieldSetlValue.getText().trim().equalsIgnoreCase("dollar US"))
			 {
				 user1.setCurrency("US dollar");
				 textLabel.setText("Currency has been setup to US dollar");
				 textLabel.setTextFill(Color.BLACK);
			 }
			 // Else providing error and giving user possible values to pass
			 else
			 {
				 textLabel.setText("ERROR:\nDue to the brief instructions provided this program \nshould only work with Pound sterling or US dollars. \nTry again, and type in \"Pound sterling\" or \"US dollar\"");
				 textLabel.setTextFill(Color.RED);
			 }
		 });
			 
		 // Case 4.2, set minimum input value
		 Button buttonMinCoinIn = new Button();         
		 buttonMinCoinIn.setText("Set minimum coin input value");
		 buttonMinCoinIn.setOnAction(e -> {
			 
			 // Validating input, in case validated, printing sentence with result
			 if (user1.validateInt(textFieldSetlValue.getText().trim()) && Integer.parseInt(textFieldSetlValue.getText().trim()) >= 0 && Integer.parseInt(textFieldSetlValue.getText().trim()) <= 10000 && Integer.parseInt(textFieldSetlValue.getText().trim()) <= user1.getMaxCoinIn())
			 {
				 user1.setMinCoinIn(Integer.parseInt(textFieldSetlValue.getText().trim()));
				 textLabel.setText("The minimum coin input has been setup to " + user1.getMinCoinIn() +  " coins");
				 textLabel.setTextFill(Color.BLACK);
			 }
			 
			 // In case invalid input, giving error and explaining possible problems, and asking to try again
			 else
			 {
				 textLabel.setText("ERROR:\nDue to brief provided, input value should be integers only. \nThe minimum and maximum coin input should be in range 0 - 10,000 inclusive, \nand the minimum input should not be more than the maximum coin input. \nType in different values and try again");
				 textLabel.setTextFill(Color.RED);
			 }
		 });
		 
		 // Case 4.3 set maximum input value
		 Button buttonMaxCoinIn = new Button();         
		 buttonMaxCoinIn.setText("Set maximum coin input value");
		 buttonMaxCoinIn.setOnAction(e -> {
			 
			 // Validating input, in case validated, printing sentence with result
			 if (user1.validateInt(textFieldSetlValue.getText().trim()) && Integer.parseInt(textFieldSetlValue.getText().trim()) >= 0 && Integer.parseInt(textFieldSetlValue.getText().trim()) <= 10000 && Integer.parseInt(textFieldSetlValue.getText().trim()) >= user1.getMinCoinIn())
			 {
				 user1.setMaxCoinIn(Integer.parseInt(textFieldSetlValue.getText().trim()));
				 textLabel.setText("The maximum coin input has been setup to " + user1.getMaxCoinIn() +  " coins");
				 textLabel.setTextFill(Color.BLACK);
			 }
			 
			 // In case invalid input, giving error and explaining possible problems, and asking to try again
			 else
			 {
				 textLabel.setText("ERROR:\nDue to brief provided, input value should be integers only. \nThe minimum and maximum coin input should be in range 0 - 10,000 inclusive, \nand the maximum input should not be less than the minimum coin input. \nType in different values and try again");
				 textLabel.setTextFill(Color.RED);
			 }
		 });
		 
		 // Case 4.4, additional button to close window
		 Button buttonExitMenu = new Button();         
		 buttonExitMenu.setText("Close window");
		 buttonExitMenu.setOnAction(e -> setDetailsMenu.hide());
		 
		 // Grouping buttons together in horisontal node
		 HBox buttons = new HBox(10);
		 buttons.setSpacing(10);         
		 buttons.setAlignment(Pos.CENTER);
		 buttons.getChildren().addAll(buttonSetCurrency, buttonMinCoinIn, buttonMaxCoinIn, buttonExitMenu);
		 
		 // Creating root node and adding all nodes to it
		 VBox root = new VBox(10);
		 root.setSpacing(10);         
		 root.setAlignment(Pos.CENTER); 
		 root.getChildren().addAll(textLabel, textFieldSetlValue, buttons);
		 
		 // Creating and launching scene
		 Scene scene = new Scene(root, 1200, 300);
		 setDetailsMenu.setScene(scene);
		 setDetailsMenu.setTitle("Set details");         
		 setDetailsMenu.show(); 
		 
	 }
	 
}
