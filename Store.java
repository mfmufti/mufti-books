/**
 * Final Project: The Store - Main Program
 * In this project, the user is shown a graphical user interface for a book store.  The book store contains many features, such as
 * accounts, saving and retrieving data from files, adding books to cart, viewing books, buying books, etc.  An admin mode is also
 * included in which the admin can monitor and modify the books.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

public class Store
{
	// Store the window width and height.
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 563;

	public static void main(String args[])
	{
		// Check if the resources folder exists.  Abort if it does not.
		Resources.checkResourcesExist();

		// Make a new GUI object, and switch to the main menu.
		GUI gui = new GUI();
		gui.switchPage(new Pages.MainMenu(gui));
	} // end main(String[])
} // end public class