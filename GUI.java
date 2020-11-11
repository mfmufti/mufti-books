/**
 * Final Project: The Store - GUI
 * At its base, this class creates a graphical user interface for the store.  However, this program also creates a class that provides
 * an interface to all other classes for managing file reading and writing, user queries, admin and book management, etc.  In essence,
 * this class creates a communication gateway for all other classes, and for this reason, this class is passed to many functions and
 * other classes.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.*;
import java.util.*;

public class GUI
{
	// Declare the variables needed to store the users, books, admin, active user, window (GUI object) and a book that is being actively
	// edited.
	private ArrayList<User> users;
	private ArrayList<Book> books;
	private User admin, activeUser;
	private MainFrame window;
	private Book editingBook;

	/**
	 * This constructor initializes the variables this class stores, and reads necessary information from files.
	 */
	public GUI()
	{
		// Initialize the users and book lists.
		users = new ArrayList<User>();
		books = new ArrayList<Book>();

		// Initialize the admin as a user.
		admin = new User();
		admin.setAdmin();

		// Initialize the active user and book that is being edited as null.
		activeUser = null;
		editingBook = null;

		// Make a frame for display of GUI elements.
		window = new MainFrame(this);

		// Read the necessary files to get information about the users, books, and admin.
		Resources.readFiles(this, users, books, admin);
	} // end GUI()

	/**
	 * This constructor does calls on the other constructor, and also switches to the provided "page".
	 * 
	 * @param page the page to switch to
	 */
	public GUI(JComponent page)
	{
		// Call on the other constructor for initialization of variables, and switch to the provided page.
		this();
		switchPage(page);
	} // end GUI(JComponent)

	/**
	 * Given a "page", this function switches the GUI so that that page is displayed.
	 * 
	 * @param newPage
	 */
	public void switchPage(JComponent newPage)
	{
		// Call on the window object to switch the page.
		window.switchPage(newPage);
	} // end switchPage(JComponent)

	/**
	 * Return the books stored.
	 * 
	 * @return the list of books stored
	 */
	public ArrayList<Book> getBooks()
	{
		// Return the list of books.
		return books;
	} // end getBooks()

	/**
	 * Add a book to the list of books stored.
	 * 
	 * @param book the book to add to the list
	 */
	public void addBook(Book book)
	{
		// Add the book to the stored list, and update the changes in necessary file.
		books.add(book);
		Resources.writeBookFile(books);
	} // end addBook(Book)

	/**
	 * Remove a book from the list of stored books.
	 * 
	 * @param book the book to remove from the list
	 */
	public void removeBook(Book book)
	{
		// Remove the book from the list and make necessary changes to the file.
		books.remove(book);
		Resources.writeBookFile(books);
	} // end removeBook(Book)

	/**
	 * Given an id, return the book associated with that id.
	 * 
	 * @param id the id associated with the book
	 * @return the book that matches the id, or null if there is no such book
	 */
	public Book getBookById(int id)
	{
		// Iterate through the list of books to find a book with the specified id.
		for (Book book : books)
			if (book.getId() == id)
				return book;
		
		// Return null if the requested book does not exist.
		return null;
	} // end getBookById(int)

	/**
	 * Get the book that is being edited.
	 * 
	 * @return the book that is being edited
	 */
	public Book getEditingBook()
	{
		// Return the book that is being edited.
		return editingBook;
	} // end getEditingBook()

	/**
	 * Set the book that is being edited.
	 * 
	 * @param book the book that is being edited
	 */
	public void setEditingBook(Book book)
	{
		// Set the book that is being edited as the book that is passed in.
		editingBook = book;
	} // end setEditingBook(Book)

	/**
	 * Update changes to the file for books (needed when this class does not notice changes being made).
	 */
	public void forceBookDataWrite()
	{
		// Write changes to the file with information about the books.
		Resources.writeBookFile(books);
	} // end forceBookDataWrite()

	/**
	 * Get the list of users stored.
	 * 
	 * @return the list of users
	 */
	public ArrayList<User> getUsers()
	{
		// Return the stored list of users.
		return users;
	} // end getUsers()

	/**
	 * Add a user to the list of users.
	 * 
	 * @param user the user to add
	 */
	public void addUser(User user)
	{
		// Add the user to the list of user and update the users file.
		users.add(user);
		Resources.writeUserFile(users);
	} // end addUser(User)

	/**
	 * Change the administrator's password.
	 * 
	 * @param newPassword the new password for the admin
	 */
	public void updateAdminPassword(String newPassword)
	{
		// Set the new password for the administrator and write the changes to the necessary file.
		admin.setPassword(newPassword);
		Resources.writeAdminFile(admin);
	} // end updateAdminPassword(newPassword)

	/** 
	 * Update changes to the file for users (needed when this class does not notice changes being made).
	 */
	public void forceUserDataWrite()
	{
		// Write changes to the users file.
		Resources.writeUserFile(users);
	} // end forceUserDataWrite()

	/**
	 * Update changes to the file for the admin (needed when this class does not notice changes being made).
	 */
	public void forceAdminDataWrite()
	{
		// Write changes to the admin file.
		Resources.writeAdminFile(admin);
	} // end forceAdminDataWrite()

	/**
	 * Check if a username already exists.
	 * 
	 * @param userName the username to check
	 * @return true if the username already exists, otherwise false
	 */
	public boolean userNameExists(String userName)
	{
		// Iterate through the list of users, and check if any username is the same.  If so, return true.
		for (User user : users)
			if (user.getUserName().equals(userName))
				return true;
		
		// Return false if no such user exists.
		return false;
	} // end userNameExists(String)

	/**
	 * Check if the given credentials are correct for any user.
	 * 
	 * @param userName the username to check
	 * @param password the password to check
	 * @return whether the credentials are correct
	 */
	public boolean userCredentialsCorrect(String userName, String password)
	{
		// If the username is null, check if the given password matches with the administrator's password.
		if (userName == null)
			return password.equals(admin.getPassword());

		// Iterate the list of users to see if the username matches with the passed username.  If a match if found, then the return
		// value is based on whether the passwords are the same.
		for (User user : users)
			if (user.getUserName().equals(userName))
				return user.getPassword().equals(password);
		
		// Return false if no matching account has been found.
		return false;
	} // end userCredentialsCorrect(String, String)

	/**
	 * Check if the the given password is the same as the administrator's password.
	 * 
	 * @param adminPassword the password to check
	 * @return whether the given password matches with the administrator's password
	 */
	public boolean adminPasswordCorrect(String adminPassword)
	{
		// Return whether the given password matches with the administrator's password.
		return adminPassword.equals(admin.getPassword());
	} // end adminPasswordCorrect(String)

	/**
	 * Sign in a user given their username.
	 * 
	 * @param userName the username of the user to sign in
	 */
	public void signInUser(String userName)
	{
		// Iterate through the list of users to find a match for the username.
		for (User user : users)
		{
			// When a match is found, set the user as the signed in user, and stop searching.
			if (user.getUserName().equals(userName))
			{
				activeUser = user;
				break;
			} // end if
		} // end for
	} // end signInUser(String)

	/**
	 * Sign in the admin.
	 */
	public void signInAdmin()
	{
		// Set the admin as the signed in user.
		activeUser = admin;
	} // end signInAdmin()

	/**
	 * Get the signed in user.
	 * 
	 * @return the current signed in user (null if no user is signed in)
	 */
	public User getActiveUser()
	{
		// Return the signed in user.
		return activeUser;
	} // end getActiveUser()

	/**
	 * Sign out the signed in user.
	 */
	public void signOutUser()
	{
		// Set the signed in user as null.
		activeUser = null;
	} // end signOutUser()

	/**
	 * Ask the user if they would like to quit the program.
	 */
	public void askExitProgram()
	{
		// Display a prompt asking the user if they would like to exit the program.
		int shouldExit = JOptionPane.showOptionDialog(
			null, "Are you sure you want to exit MuftiBooks?", "Exiting Program", JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE, null, null, null
		);

		// If the user clicks yes, exit the program.
		if (shouldExit == 0)
			System.exit(0);
	} // end askExitProgram()
} // end public class