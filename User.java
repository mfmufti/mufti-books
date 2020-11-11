/**
 * Final Project: The Store - User
 * This class provides a way of storing information about users and account in the store.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import java.util.LinkedHashMap;

public class User
{
	// Store values necessary for a user.
	private String firstName, lastName, userName, password, email;
	private double sessionSales = 0d, totalSales = 0d;
	private int sessionItems = 0, totalItems = 0;

	// Initialize an ordered hash map to keep track of books in cart.
	private LinkedHashMap<Book, Integer> cartBooks = new LinkedHashMap<Book, Integer>();

	// Initialize a variable to hold whether the user is admin.
	private boolean admin = false;

	/**
	 * Initialize values of this class to null.
	 */
	public User()
	{
		// Initialize values to null.
		this.firstName = this.lastName = this.userName = this.password = this.email = null; 
	} // end User()

	/**
	 * Initialize values based on values passed in.
	 * 
	 * @param firstName the user's first name
	 * @param lastName the user's last name
	 * @param userName the user's username
	 * @param password the user's password
	 * @param email the user's email
	 */
	public User(String firstName, String lastName, String userName, String password, String email)
	{
		// Set values based on what has been passed in.
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	} // end User(String, String, String, String, String)

	/**
	 * Set values based on an array of information (used for CSV parsing).
	 * 
	 * @param gui the GUI object
	 * @param information the information as an array
	 */
	public User(GUI gui, String information[])
	{
		// Store information from the array.
		firstName = information[0];
		lastName = information[1];
		userName = information[2];
		password = information[3];
		email = information[4];
		sessionSales = Double.parseDouble(information[5]);
		totalSales = Double.parseDouble(information[6]);
		sessionItems = Integer.parseInt(information[7]);
		totalItems = Integer.parseInt(information[8]);

		// Deserialize the last value of the CSV entry and use to initialize the cart.
		deserializeCart(gui, information[9]);
	} // end User(GUI, String)
	
	/**
	 * Get the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName()
	{
		// Return the first name.
		return firstName;
	} // end getFirstName()

	/**
	 * Set the first name.
	 * 
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName)
	{
		// Set the new first name.
		this.firstName = firstName;
	} // end setFirstName(String)

	/**
	 * Get the last name.
	 * 
	 * @return the last name
	 */
	public String getLastName()
	{
		// Return the last name.
		return lastName;
	} // end getLastName()

	/**
	 * Set the last name.
	 * 
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName)
	{
		// Set the new last name.
		this.lastName = lastName;
	} // end setLastName(String)

	/**
	 * Get the full name.
	 * 
	 * @return the full name
	 */
	public String getFullName()
	{
		// Return the full name by combining the first and last names.
		return firstName + " " + lastName;
	} // end getFullName()

	/**
	 * Get the username.
	 * 
	 * @return the username
	 */
	public String getUserName()
	{
		// Return the username.
		return userName;
	} // end getUserName()

	/**
	 * Set the username.
	 * 
	 * @param userName the new username
	 */
	public void setUserName(String userName)
	{
		// Set the new username.
		this.userName = userName;
	} // end setUserName(String)

	/**
	 * Get the password.
	 * 
	 * @return the password
	 */
	public String getPassword()
	{
		// Return the password.
		return password;
	} // end getPassword()

	/**
	 * Set the password.
	 * 
	 * @param password the new password
	 */
	public void setPassword(String password)
	{
		// Set the new password.
		this.password = password;
	} // end setPassword(String)

	/**
	 * Get the email address.
	 * 
	 * @return the email address
	 */
	public String getEmail()
	{
		// Return the email address.
		return email;
	} // end getEmail()

	/**
	 * Set the email address.
	 * 
	 * @param email the new email address
	 */
	public void setEmail(String email)
	{
		// Set the new email address.
		this.email = email;
	} // end setEmail(String)

	/**
	 * Get the amount spent in this session.
	 * 
	 * @return the amount spent in this session.
	 */
	public double getSessionSales()
	{
		// Return the amount spent in this session.
		return sessionSales;
	} // end getSessionSales()

	/**
	 * Get the number of items purchased this session.
	 * 
	 * @return the number of items purchased this session
	 */
	public int getSessionItems()
	{
		// Return the number of items purchased this session.
		return sessionItems;
	} // end getSessionItems()

	/**
	 * Get the total amount spent.
	 * 
	 * @return the total amount spent.
	 */
	public double getTotalSales()
	{
		// The total amount spent.
		return totalSales;
	} // end getTotalSales()

	/**
	 * Get the total items purchased.
	 * 
	 * @return the total items purchased
	 */
	public int getTotalItems()
	{
		// Return the total items purchased.
		return totalItems;
	} // end getTotalItems()

	/**
	 * Start a new session by resetting amounts.
	 */
	public void startNewSession()
	{
		// Reset the session amounts.
		sessionSales = 0d;
		sessionItems = 0;
	} // end startNewSession()

	/**
	 * Add an item to cart.
	 * 
	 * @param book the book to add
	 * @param quantity the number of the book to add
	 */
	public void addToCart(Book book, int quantity)
	{
		// Add the specified number of items.
		cartBooks.merge(book, quantity, Integer::sum);
	} // end addToCart()

	/**
	 * Remove an item from the cart.
	 * 
	 * @param book the item to remove
	 */
	public void removeFromCart(Book book)
	{
		// Remove the specified item.
		cartBooks.remove(book);
	} // end removeFromCart(Book)

	/**
	 * Get the count for a specific book.
	 * 
	 * @param book the book to count
	 * @return the count of the passed in book
	 */
	public int getCartItemCount(Book book)
	{
		// Return the quantity if it exists in the map, otherwise 0.
		return cartBooks.getOrDefault(book, 0);
	} // end getCartItemCount(Book)

	/**
	 * Edit the quantity for a specified book.
	 * 
	 * @param book the book to modify
	 * @param quantity the new quantity
	 */
	public void editCartItemQuantity(Book book, int quantity)
	{
		// Remove the book if the quantity is 0.  Otherwise, store the quantity.
		if (quantity == 0)
			cartBooks.remove(book);
		else
			cartBooks.put(book, quantity);
	} // end editCartItemQuantity(Book, int)

	/**
	 * Adjust the quantities of books in cart based on changes to the quantities of the books.
	 * 
	 * @return true if any quantity had to be adjusted, otherwise false
	 */
	public boolean adjustCartQuantities()
	{
		boolean adjusted = false;

		// Loop through all books in cart.
		for (Book book : cartBooks.keySet())
		{
			// If the there are more of the current book in cart then there are at all, update the quantity to the amount that are
			// available.
			if (book.getQuantity() < cartBooks.get(book))
			{
				adjusted = true;
				editCartItemQuantity(book, book.getQuantity());
			} // end if
		} // end for

		// Return whether any adjustment had to be made.
		return adjusted;
	} // end adjustCartQuantities()

	/**
	 * Get a list of all items in cart (does not include quantity).
	 * 
	 * @return list of items in cart
	 */
	public Book[] getCartItems()
	{
		// Return the list of items in cart as an array.
		return cartBooks.keySet().toArray(new Book[cartBooks.size()]);
	} // end getCartItems()

	/**
	 * Purchase all the items currently in cart.
	 */
	public void makePurchase()
	{
		int quantity;

		// Iterate through all books in cart.
		for (Book book : cartBooks.keySet())
		{
			// Get the quantity of the book in cart.
			quantity = cartBooks.get(book);

			// Add the price including tax to both session sales, and total sales for the current book.
			sessionSales += book.price * quantity * 1.13;
			totalSales += book.price * quantity * 1.13;

			// Add the number of books purchased.
			sessionItems += quantity;
			totalItems += quantity;

			// Set a new quantity on the book.
			book.setQuantity(book.getQuantity() - quantity);
		} // end for

		// Clear all the books in cart.
		cartBooks.clear();
	} // end makePurchase()

	/**
	 * Get the total number of items in cart.
	 * 
	 * @return the total number of items in cart
	 */
	public int getAllCartItemsCount()
	{
		// Initialize the count to 0.
		int count = 0;

		// For each of the values in the map of cart books, add to the count.
		for (int currentCount : cartBooks.values())
			count += currentCount;
		
		// Return the count.
		return count;
	} // end getAllCartItemsCount()

	/**
	 * Serialize the cart, by separating the book ids, and amounts by vertical bars.
	 * 
	 * @return the serialized cart
	 */
	public String serializeCart()
	{
		// Initialize the serialized cart.
		String serializedCart = "";

		// Iterate through the books in the cart.
		for (Book book : cartBooks.keySet())
		{
			// Add a vertical bar character to separate from whatever has been added before, if anything has been added before.
			if (!serializedCart.isEmpty())
				serializedCart += "|";

			// Add the current book's id and quantity to the serialized cart.
			serializedCart += Integer.toString(book.getId()) + "|" + cartBooks.get(book);
		}

		// Return the serializedCart.
		return serializedCart;
	} // end serializeCart()

	/**
	 * Deserialize a string representing cart items.
	 * 
	 * @param gui the GUI object
	 * @param serializedCart the serialized cart to deserialize
	 */
	public void deserializeCart(GUI gui, String serializedCart)
	{
		// Split the cart by vertical bars.
		String data[] = serializedCart.split("\\s*\\|\\s*");

		// Clear the current cart entries.
		cartBooks.clear();

		// Iterate through the data two pieces at a time.
		for (int i = 0; i + 1 < data.length; i += 2)
		{
			// Attempt to find a book with the given id.
			Book book = gui.getBookById(Integer.parseInt(data[i]));

			// If the book exists, add it along with the quantity to cart.
			if (book != null)
				cartBooks.put(book, Integer.parseInt(data[i + 1]));
		} // end for
	} // end deserializeCart(GUI, String)

	/**
	 * Set the current user as an admin.
	 */
	public void setAdmin()
	{
		// Store that the user is admin.
		admin = true;
	} // end setAdmin()
	
	/**
	 * Set the current user as non-admin.
	 */
	public void unsetAdmin()
	{
		// Store that the user is not admin.
		admin = false;
	} // end setAdmin()
	
	/**
	 * Return whether the current user is admin.
	 * 
	 * @return whether the current user is admin
	 */
	public boolean isAdmin()
	{
		// Return whether the user is admin
		return admin;
	} // end isAdmin()

	/**
	 * Use the properties of this object to create a human-readable string.
	 * 
	 * @return a human-readable string
	 */
	@Override
	public String toString()
	{
		// Create a string to display information about this user.
		return String.format(
			"User [firstName=%s, lastName=%s, userName=%s, password=%s, email=%s, sessionSales=%.2f, totalSales=%.2f," +
			 	"sessionItems=%d, totalItems=%d]",
			firstName, lastName, userName, password, email, sessionSales, totalSales, sessionItems, totalItems
		);
	} // end toString()

	/**
	 * Use the properties of this object to create a CSV entry.
	 * 
	 * @return a CSV entry as a String
	 */
	public String toCSV()
	{
		// Return a CSV entry by separating fields by commas.
		return firstName + "," + lastName + "," + userName + "," + password + "," + email + "," + sessionSales + "," +
			totalSales + "," + sessionItems + "," + totalItems + "," + serializeCart();
	} // end toCSV()
} // end public class