/**
 * Final Project: The Store - Resources
 * This class manages resources for the rest of the program.  Images and fonts are provided by this class, and file I/O is performed by
 * this class.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Resources
{
	// Initialize hash maps needed to store images that have already been loaded and fonts that have already been loaded.
	private static HashMap<String, ImageIcon> loadedImages = new HashMap<String, ImageIcon>();
	private static HashMap<String, Font> loadedFonts = new HashMap<String, Font>();

	/**
	 * This function checks if the base folder that contains all the resources exists.
	 */
	public static void checkResourcesExist()
	{
		// Proceed to display an error if path to the resources folder does not point to a directory.
		if (!new File(Paths.BASE_FOLDER).isDirectory())
		{
			// Display an error.
			JOptionPane.showMessageDialog(
				null, "Failed to locate the resources folder! Move the resources folder to \"" + System.getProperty("user.dir") + "\".",
				"FATAL ERROR", JOptionPane.ERROR_MESSAGE
			);

			// Exit the program.
			System.exit(1);
		} // end if
	} // end checkResourcesExist()

	/**
	 * Get an image given the path, width, and height.
	 * 
	 * @param imagePath the path to the image
	 * @param width the width of the image
	 * @param height the height of the image
	 * @return the image requested
	 */
	public static ImageIcon getImage(String imagePath, int width, int height)
	{
		ImageIcon imageIcon;

		if (loadedImages.containsKey(imagePath))
		{
			// If the image is already loaded, prepare to return that image.
			imageIcon = loadedImages.get(imagePath);
		} // end if
		else
		{
			try
			{
				// Try reading the path to the image as an image.
				imageIcon = new ImageIcon(ImageIO.read(new File(imagePath)));
			} // end try
			catch(IOException e)
			{
				imageIcon = null;
			} // end catch

			// Store the image in the list of stored images.
			loadedImages.put(imagePath, imageIcon);
		} // end else

		// Resize the image if requested, and return the image.
		if (width == -1 || imageIcon == null)
			return imageIcon;
		else
			return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	} // end getImage(String, int, int)

	/**
	 * Get an image without passing in the dimensions.
	 * 
	 * @param imagePath the path to the image
	 * @return the image
	 */
	public static ImageIcon getImage(String imagePath)
	{
		// Call the other version of this function to get the image.
		return getImage(imagePath, -1, -1);
	} // end getImage(String)

	/**
	 * Get a font given the path and size.
	 * 
	 * @param fontPath the path to the font
	 * @param size the font size
	 * @return the font
	 */
	public static Font getFont(String fontPath, int size)
	{
		Font font;

		if (loadedFonts.containsKey(fontPath))
		{
			// If the font is already loaded, prepare to return that object.
			font = loadedFonts.get(fontPath);
		} // end if
		else
		{
			try
			{
				// Try making a font out of the given path.
				font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
			} // end try
			catch (IOException|FontFormatException exception)
			{
				// Set the font as null if an error occurred.
				font = null;
			} // end catch

			// Store the font in the list of loaded fonts.
			loadedFonts.put(fontPath, font);
		} // end else

		// Return the font with the appropriate size.
		if (font == null)
			return null;
		else
			return font.deriveFont((float) size);
	} // end getFont(String, int)

	/**
	 * Read all the files that are storing information about this program.
	 * 
	 * @param gui the GUI object
	 * @param users the list of users to read into
	 * @param books the list of books to read into
	 * @param admin the admin
	 */
	public static void readFiles(GUI gui, ArrayList<User> users, ArrayList<Book> books, User admin)
	{
		// Read all the necessary files.
		readBookFile(books);
		readUserFile(gui, users);
		readAdminFile(admin);
	} // end readFiles(GUI, ArrayList<User>, ArrayList<Book>, User)

	/**
	 * Read the user file to get information about the users.
	 * 
	 * @param gui the GUI object
	 * @param users the list of users to read into
	 */
	public static void readUserFile(GUI gui, ArrayList<User> users)
	{
		File userFile = new File(Paths.DATA_USER_FILE);
		BufferedReader userStream;
		String currentLine;
		String[] currentLineData;
		
		try
		{
			// Try opening a read stream, and reading a line.
			userStream = new BufferedReader(new FileReader(userFile));
			currentLine = userStream.readLine();

			// Iterate until the last line of the file has been reached.
			while (currentLine != null)
			{
				// Split the current line to get the individual data columns.
				currentLineData = currentLine.split("\\s*,\\s*", -1);

				// Try adding a user based on the input given.  Skip the current line if the input fails to parse.
				try
				{
					users.add(new User(gui, currentLineData));
				} // end try
				catch (NumberFormatException|ArrayIndexOutOfBoundsException exception) {}

				// Get the next line in the file.
				currentLine = userStream.readLine();
			} // end while

			// Close the stream.
			userStream.close();
		} // end try
		catch (FileNotFoundException exception)
		{
			// Try creating the file.
			try
			{
				userFile.createNewFile();
			} // end try
			catch (IOException e) {}
		} // end catch
		catch (IOException exception) {}
	} // end readUserFile(GUI, ArrayList<User>)

	/**
	 * Read the file containing information about the books.
	 * 
	 * @param books the list to read into
	 */
	public static void readBookFile(ArrayList<Book> books)
	{
		File bookFile = new File(Paths.DATA_PRODUCT_FILE);
		BufferedReader bookStream;
		String currentLine;
		String[] currentLineData;

		try
		{
			// Try opening the books file, and reading a line.
			bookStream = new BufferedReader(new FileReader(bookFile));
			currentLine = bookStream.readLine();

			// Iterate until no lines are left.
			while (currentLine != null)
			{
				// Store the split data.
				currentLineData = currentLine.split("\\s*,\\s*", -1);

				// Try adding a book based on the input given.  Skip the current line if the input fails to parse.
				try
				{
					books.add(new Book(currentLineData));
				} // end try
				catch (NumberFormatException|ArrayIndexOutOfBoundsException exception) {System.out.println("Let me introduce myself...2");}

				// Get the next line from the file.
				currentLine = bookStream.readLine();
			} // end while
			
			// Close the stream.
			bookStream.close();
		} // end try
		catch (FileNotFoundException exception)
		{
			// Try creating the file.
			try
			{
				bookFile.createNewFile();
			} // end try
			catch (IOException e) {}
		} // end catch
		catch (IOException exception) {}
	} // end readBookFile(ArrayList<Book>)

	/**
	 * Read the file with the information on the admin.
	 * 
	 * @param admin the User object to store th password
	 */
	public static void readAdminFile(User admin)
	{
		File adminFile = new File(Paths.DATA_ADMIN_FILE);
		BufferedReader adminStream;

		try
		{
			// Try opening the stream, reading a line, and storing the password.
			adminStream = new BufferedReader(new FileReader(adminFile));
			admin.setPassword(adminStream.readLine().trim());
		} // end try
		catch (FileNotFoundException exception)
		{
			try
			{
				adminFile.createNewFile();
			} // end try
			catch (IOException e) {}
		} // end catch
		catch (IOException exception) {}
	} // end readAdminFile(User)

	/**
	 * Write all the stored changes back to their files.
	 * 
	 * @param users
	 * @param books
	 * @param admin
	 */
	public static void writeFiles(ArrayList<User> users, ArrayList<Book> books, User admin)
	{
		// Write the information.
		writeUserFile(users);
		writeBookFile(books);
		writeAdminFile(admin);
	} // end writeFiles(ArrayList<User>)

	/**
	 * Write the user information to its file.
	 * 
	 * @param users the users list.
	 */
	public static void writeUserFile(ArrayList<User> users)
	{
		PrintWriter userStream;
		
		try
		{
			// Try opening the stream.
			userStream = new PrintWriter(new FileWriter(Paths.DATA_USER_FILE));
		} // end try
		catch (IOException exception)
		{
			// Return if the stream did not open.
			return;
		} // end catch

		// For each of the users, write the user's csv value to the file.
		for (User user : users)
			userStream.println(user.toCSV());

		// Close the stream.
		userStream.close();
	} // end writeUserFile(ArrayList<User>)

	/**
	 * Write the book information to its file.
	 * 
	 * @param books the books list
	 */
	public static void writeBookFile(ArrayList<Book> books)
	{
		PrintWriter bookStream;
		
		try
		{
			// Try opening the book stream.
			bookStream = new PrintWriter(new FileWriter(Paths.DATA_PRODUCT_FILE));
		} // end try
		catch (IOException exception)
		{
			// If the book stream did not open, stop.
			return;
		} // end catch

		// For each of the books, write the book's csv value to the file.
		for (Book book : books)
			bookStream.println(book.toCSV());

		// Close the book stream.
		bookStream.close();
	} // end writeBookFile(ArrayList<Book>)

	/**
	 * Write the admin password to the admin file.
	 * 
	 * @param admin the User object representing the admin
	 */
	public static void writeAdminFile(User admin)
	{
		PrintWriter adminStream;
		
		try
		{
			// Try opening the admin stream.
			adminStream = new PrintWriter(new FileWriter(Paths.DATA_ADMIN_FILE));
		} // end try
		catch (IOException exception)
		{
			// If the admin stream does not open, stop.
			return;
		} // end catch

		// Write the password and close the stream.
		adminStream.print(admin.getPassword());
		adminStream.close();
	} // end writeAdminFile(User)
} // end public class