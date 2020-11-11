/**
 * Final Project: The Store - Input
 * This class provides a number of functions to validate inputs.  The functions in this class return a validated string to make any
 * correction necessary, and return null if the input is invalid.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import java.time.Year;
import java.io.File;

public class Input
{
	// Declare a variable to keep the last checked username.
	private static String lastCheckedUserName;
	
	/**
	 * Validate a given name.
	 * 
	 * @param name the name to validate
	 * @return the validated name, or null if invalid
	 */
	public static String getValidName(String name)
	{
		// Remove spaces from either end of the name.
		name = name.trim();

		// Return invalid if the name is shorter than two characters or if it contains an invalid character. If valid, return the name
		// capitalized appropriately.
		if (name.length() < 2 || name.matches(".*[0-9_\\W ].*"))
			return null;
		else
			return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	} // end getValidName(String)

	/**
	 * Validate a given username.
	 * 
	 * @param userName the username to validate
	 * @return the validated username, or null if invalid
	 */
	public static String getValidUserName(String userName)
	{
		// Return invalid if the username is shorter than 5 characters, or if the username contains an invalid character.
		if (userName.length() < 5 || userName.matches(".*[^0-9a-zA-Z].*"))
			return null;
		else
			return userName;
	} // end getValidUserName(String)

	/**
	 * Validate a given password.
	 * 
	 * @param password the password to validate
	 * @return the validated password, or null if invalid
	 */
	public static String getValidPassword(String password)
	{
		// Return invalid if the password's length is less than 8, or if the password does not have at least one lowercase and uppercase
		// character, and one digit.
		if (password.length() < 8 || password.matches(".*\\s.*") || !password.matches(".*(?=.*[a-z].*)(?=.*[A-Z].*)(?=.*[0-9].*).*"))
			return null;
		else
			return password;
	} // end getValidPassword(String)

	/**
	 * Validate a given email address.
	 * 
	 * @param email the email to validate
	 * @return the validated email, or null if invalid
	 */
	public static String getValidEmail(String email)
	{
		// Return invalid if the email does not match the standard format, otherwise the email in lowercase.
		if (email.matches("[a-zA-Z0-9\\._-]+@[a-zA-Z]+\\.[a-zA-Z]+"))
			return email.toLowerCase();
		else
			return null;
	} // end getValidEmail(String)

	/**
	 * Store a given username (needed to validate the password that comes next).
	 * 
	 * @param userName the username to store
	 * @return the username
	 */
	public static String storeUserName(String userName)
	{
		// Store and return the passed username.
		lastCheckedUserName = userName;
		return userName;
	} // end storeUserName(String)

	/**
	 * Check a password to see if it and the previously passed username make a valid credential combination.
	 * 
	 * @param gui the GUI object
	 * @param password the password to check
	 * @return the password if the username and password match an exiting account, otherwise null
	 */
	public static String passwordMatchesUserName(GUI gui, String password)
	{
		return gui.userCredentialsCorrect(lastCheckedUserName, password) ? password : null;
	} // end passwordMatchesUserName(GUI, String)

	/**
	 * Validate a given book title.
	 * 
	 * @param title the book title to validate
	 * @return the validated book title
	 */
	public static String getValidBookTitle(String title)
	{
		// Return invalid if the title contains any invalid character, otherwise the title trimmed.
		if (title.matches(".*[^\\w\\s\\.\\-\\(\\)!\\?&].*"))
			return null;
		else
			return title.trim();
	} // end getValidBookTitle(String)

	/**
	 * Validate a given price.
	 * 
	 * @param price the price to validate
	 * @return the validated price
	 */
	public static String getValidPrice(String price)
	{
		double priceDouble = 0d;

		try
		{
			// Try storing the price as a double.
			priceDouble = Double.parseDouble(price);
		} // end try
		catch (NumberFormatException exception)
		{
			// If an error is caught while trying to convert the price, return invalid.
			return null;
		} // end catch

		// If the price is 0, negative, or above 200, return invalid.
		if (priceDouble <= 0 || priceDouble > 200)
			return null;
		else
			return price;
	} // end getValidPrice(String)

	/**
	 * Check if a given integer (in the form of a string) is valid.
	 * 
	 * @param integer the number to check
	 * @return true if the number is valid, otherwise false
	 */
	private static boolean isValidInt(String integer)
	{
		try
		{
			// Try converting the string to an integer.  If it succeeds, return true.
			Integer.parseInt(integer);
			return true;
		} // end try
		catch (NumberFormatException exception)
		{
			// Return false if the string failed to be parsed as an integer.
			return false;
		} // end catch
	} // end isValidInt(String)

	/**
	 * Validate a given quantity of books.
	 * 
	 * @param quantity the given quantity
	 * @return the validated quantity, or null if the quantity is invalid
	 */
	public static String getValidQuantity(String quantity)
	{
		// If the quantity is not a valid integer, return invalid.
		if (!isValidInt(quantity))
			return null;
		
		// Store the quantity as an integer.
		int quantityInt = Integer.parseInt(quantity);

		// Return invalid if the quantity is less than 0 or greater than 200.
		if (quantityInt < 0 || quantityInt > 200)
			return null;
		else
			return quantity;
	} // end getValidQuantity(String)

	/**
	 * Validate a given JIT trigger.
	 * 
	 * @param jitTrigger the JIT trigger to validate
	 * @return the validated JIT trigger, or null if invalid
	 */
	public static String getValidJitTrigger(String jitTrigger)
	{
		// Return invalid if the JIT trigger is not a valid integer.
		if (!isValidInt(jitTrigger))
			return null;

		// Store the JIT trigger as an integer.
		int jitTriggerInt = Integer.parseInt(jitTrigger);

		// Return invalid if the JIT trigger is less than 0 or greater than 100.
		if (jitTriggerInt < 0 || jitTriggerInt > 100)
			return null;
		else
			return jitTrigger;
	} // end getValidJitTrigger(String)

	/**
	 * Validate a given publication year.
	 * 
	 * @param year the year to validate
	 * @return the validated year, or null if invalid
	 */
	public static String getValidYear(String year)
	{
		// Return invalid if the year is not a valid integer.
		if (!isValidInt(year))
			return null;
		
		// Store the year as an integer.
		int yearInt = Integer.parseInt(year);

		// If the year is less than 1700 or greater than the year today, return invalid.
		if (yearInt < 1700 || yearInt > Year.now().getValue())
			return null;
		else
			return year;
	} // end getValidYear(year)

	/**
	 * Validate a given genre.
	 * 
	 * @param genre the genre to validate
	 * @return the validated genre, or null if invalid
	 */
	public static String getValidGenre(String genre)
	{
		// Return invalid if the genre contains an invalid character.
		return genre.matches("[A-Za-z ]+") ? genre : null;
	} // end getValidGenre(String)

	/**
	 * Validate a given book binding.
	 * 
	 * @param binding the binding to validate
	 * @return the validated binding, or null if invalid
	 */
	public static String getValidBinding(String binding)
	{
		// Store the binding trimmed.
		binding = binding.trim();

		// If the binding matches paperback or hardcover regardless of case, return the input in its valid case.  Otherwise, return null.
		if (binding.equalsIgnoreCase("paperback"))
			return "Paperback";
		else if (binding.equalsIgnoreCase("hardcover"))
			return "Hardcover";
		else
			return null;
	} // end getValidBinding(String)

	/**
	 * Validate a given author name.
	 * 
	 * @param author the author name to validate
	 * @return the validated author name
	 */
	public static String getValidAuthor(String author)
	{
		// Return invalid if the author name contains an invalid character.
		return author.matches("[A-Za-z -\\.]+") ? author : null;
	} // end getValidAuthor(String)

	/**
	 * Validate a given image name (that is supposed to be placed in a specific folder).
	 * 
	 * @param imageName the image name to validate.
	 * @return the validated image name, or null if invalid.
	 */
	public static String getValidImageName(String imageName)
	{
		// Check if the image name corresponds to a valid file.  If valid, return the image name.  Otherwise, return null.
		return new File(Paths.IMAGE_BOOK_FOLDER + imageName).isFile() ? imageName : null;
	} // end getValidImageName(String)
} // end public class