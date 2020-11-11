/**
 * Final Project: The Store - Forms
 * This class contains the information needed to present all the forms in the store.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.JOptionPane;
import java.io.*;

public class Forms
{
	// Declare a variable to store the location of the folder that contains the pictures of book covers.
	private static String bookImagesFolder;

	static
	{
		try
		{
			// Try to get the absolute path of the book images folder.
			bookImagesFolder = new File(Paths.IMAGE_BOOK_FOLDER).getCanonicalPath();
		} // end try
		catch (IOException exception)
		{
			// Set the path to a blank String if it failed to get the absolute path of the book images folder.
			bookImagesFolder = "";
		} // end catch
	} // end static

	// Store the form data for the customer sign in.
	public static final FormData NORMAL_SIGN_IN = new FormData(
		new ValidationField[] {
			// Add a field for the user's username.
			new ValidationField("Username:", "", (gui, userName) -> Input.storeUserName(userName)),

			// Add a field for the user's password.
			new ValidationField(
				"Password:", "The username or password is incorrect.",
				true, (gui, password) -> Input.passwordMatchesUserName(gui, password)
			)
		},
		// Set the appropriate background image.
		Paths.IMAGE_MAIN_BACKGROUND,

		// Set the text for the buttons and title.
		"Sign in",
		"Return to menu",
		"Sign In",

		// Add an action for when the form is submitted and valid.
		(gui, fixedInputs) -> {
			// Sign the user in.
			gui.signInUser(fixedInputs[0]);

			// Switch to the second menu.
			gui.switchPage(new Pages.SecondMenu(gui));

			// Tell the user that they have successfully signed in.
			JOptionPane.showMessageDialog(
				null, "You have successfully signed in as " + fixedInputs[0] + ".", "Signed In", JOptionPane.INFORMATION_MESSAGE
			);

			// If any of the items in cart exceeds the number of items available, inform the user that the number of items in their
			// cart has been adjusted based on availability.
			if (gui.getActiveUser().adjustCartQuantities())
			{
				JOptionPane.showMessageDialog(
					null, "The quantities in your cart have been updated to reflect the availability of items.", "Cart Updated",
					JOptionPane.INFORMATION_MESSAGE
				);

				// Write the changes to disk.
				gui.forceUserDataWrite();
			} // end if
		},
		// Switch to the main menu if the user exits the form.
		(gui) -> {gui.switchPage(new Pages.MainMenu(gui));}
	);

	// Store the form data for the admin sign in.
	public static final FormData ADMIN_SIGN_IN = new FormData(
		new ValidationField[] {
			// Add a field for the password.
			new ValidationField(
				"Password:", "The admin password entered is incorrect.", true,
				(gui, password) -> gui.adminPasswordCorrect(password) ? "" : null
			)
		},
		// Set the appropriate background image.
		Paths.IMAGE_MAIN_BACKGROUND,

		// Set the text for the buttons and title.
		"Admin sign in",
		"Return to menu",
		"Admin Sign In",
		
		// Add an action for when the form is submitted and valid.
		(gui, fixedInputs) -> {
			// Sign the admin in.
			gui.signInAdmin();
			
			// Tell the admin that they have been logged in.
			JOptionPane.showMessageDialog(
				null, "You have successfully entered administrator mode.", "Admin Mode Entered", JOptionPane.INFORMATION_MESSAGE
			);

			// Switch to the second menu.
			gui.switchPage(new Pages.SecondMenu(gui));
		},

		// Switch to the main menu if the form is exited.
		(gui) -> {gui.switchPage(new Pages.MainMenu(gui));}
	);

	// Store the form data for the register form.
	public static final FormData REGISTER = new FormData(
		new ValidationField[] {
			// Add a field for the user's first name.
			new ValidationField(
				"First name:", "Please enter a valid first name.",
				(gui, firstName) -> Input.getValidName(firstName)
			),

			// Add a field for the user's last name.
			new ValidationField(
				"Last name:", "Please enter a valid last name.",
				(gui, lastName) -> Input.getValidName(lastName)
			),

			// Add a field for the user's username.
			new ValidationField(
				"Username:", "Your username must be at least 5 characters long, and must only be composed of letters and numbers.",
				(gui, userName) -> Input.getValidUserName(userName)
			),

			// Add a field for the user's password.
			new ValidationField(
				"Password:",
				"Your password must be at least 8 characters, and contain at least one uppercase and lowercase letter, and one digit.",
				true,
				(gui, password) -> Input.getValidPassword(password)
			),

			// Add a field for the user's email address.
			new ValidationField(
				"Email address:", "Please enter a valid email address.",
				(gui, email) -> Input.getValidEmail(email)
			)
		},
		// Set the appropriate background image.
		Paths.IMAGE_MAIN_BACKGROUND,

		// Set the text for the buttons and title.
		"Submit",
		"Cancel",
		"Register",
		
		// Add an action for when the form is submitted and valid.
		(gui, fixedInputs) -> {
			// Add the user as a new user, and sign the user in.
			gui.addUser(new User(fixedInputs[0], fixedInputs[1], fixedInputs[2], fixedInputs[3], fixedInputs[4]));
			gui.signInUser(fixedInputs[2]);

			// Show the user that they have successfully registered the account.
			JOptionPane.showMessageDialog(
				null, "You have successfully registered the account " + fixedInputs[2] + ".", "Account Registered",
				JOptionPane.INFORMATION_MESSAGE
			);

			// Switch to the second menu.
			gui.switchPage(new Pages.SecondMenu(gui));
		},

		// Switch to the main menu if the form is exited.
		(gui) -> {gui.switchPage(new Pages.MainMenu(gui));}
	);

	// Add a form to be able to edit books.
	public static final FormData EDIT_BOOK = new FormData(
		new ValidationField[] {
			// Add a field for the title.
			new ValidationField(
				"Title:", "Please enter a valid book title.",
				(gui, title) -> Input.getValidBookTitle(title)
			),

			// Add a field for the price.
			new ValidationField(
				"Price:", "Please enter a valid price below $200.",
				(gui, price) -> Input.getValidPrice(price)
			),

			// Add a field for the quantity.
			new ValidationField(
				"Quantity:", "Please enter a valid quantity that does not exceed 200.",
				(gui, quantity) -> Input.getValidQuantity(quantity)
			),

			// Add a field for the JIT trigger.
			new ValidationField(
				"JIT trigger:", "Please enter a valid JIT trigger that does not exceed 100.",
				(gui, jitTrigger) -> Input.getValidJitTrigger(jitTrigger)
			),

			// Add a field for the genre.
			new ValidationField(
				"Genre:", "Please enter a valid genre name.",
				(gui, genre) -> Input.getValidGenre(genre)
			),

			// Add a field for the binding.
			new ValidationField(
				"Binding:", "The book binding must be either \"Paperback\" or \"Hardcover\".",
				(gui, binding) -> Input.getValidBinding(binding)
			),

			// Add a field for the author.
			new ValidationField(
				"Author:", "Please enter a valid author's name.",
				(gui, author) -> Input.getValidAuthor(author)
			),

			// Add a field for the publication year.
			new ValidationField(
				"Year:", "Please enter a valid publication year.",
				(gui, year) -> Input.getValidYear(year)
			),

			// Add field for the image name.
			new ValidationField(
				"Image name:", "The image you entered does not exist. Please make sure the image is in the \"" + bookImagesFolder +
					"\" folder.",
				(gui, imageName) -> Input.getValidImageName(imageName)
			),
		},
		// Set the appropriate background image.
		Paths.IMAGE_SECOND_BACKGROUND,

		// Set the text for the buttons and title.
		"Finish",
		"Cancel",
		"Edit Book",

		// Add an action for when the form is submitted and valid.
		(gui, fixedInputs) -> {
			// Apply the edits to the book, and write the changes to disk.
			gui.getEditingBook().applyFormList(fixedInputs);
			gui.forceBookDataWrite();

			// Tell the uer that the book has successfully been edited.
			JOptionPane.showMessageDialog(
				null, "You have successfully edited this book.", "Book Edited", JOptionPane.INFORMATION_MESSAGE
			);

			// Switch to the book catalog page.
			gui.switchPage(new Pages.BookCatalog(gui));
		},

		// Switch to the book catalog page when the form is exited.
		(gui) -> {gui.switchPage(new Pages.BookCatalog(gui));}
	);

	// Add a form to add a new book.
	public static final FormData ADD_BOOK = new FormData(
		// Copy the fields from the book editing form.
		EDIT_BOOK.getFields(),

		// Set the appropriate background image.
		Paths.IMAGE_SECOND_BACKGROUND,

		// Set the text for the buttons and title.
		"Add",
		"Cancel",
		"Add Book",
		
		// Add an action for when the form is submitted.
		(gui, fixedInputs) -> {
			// Make a new book and apply the inputs.
			Book newBook = new Book();
			newBook.applyFormList(fixedInputs);

			// Add the book.
			gui.addBook(newBook);

			// Tell the user that they have successfully added the book.
			JOptionPane.showMessageDialog(
				null, "You have successfully added the book.", "Book Edited", JOptionPane.INFORMATION_MESSAGE
			);

			// Switch to the second menu.
			gui.switchPage(new Pages.SecondMenu(gui));
		},

		// Switch to the second menu when the form is exited.
		(gui) -> {gui.switchPage(new Pages.SecondMenu(gui));}
	);

	// Add a form for changing the user's password.
	public static final FormData CHANGE_PASSWORD = new FormData(
		new ValidationField[] {
			// Add a field for the old password.
			new ValidationField(
				"Old password:", "You have not correctly entered your old password.", true,
				(gui, oldPassword) -> gui.userCredentialsCorrect(gui.getActiveUser().getUserName(), oldPassword) ? "" : null
			),
			
			// Add a field for the new password.
			new ValidationField (
				"New password:",
				"Your new password must be at least 8 characters, and contain at least one uppercase and lowercase letter, and one digit.",
				true,
				(gui, newPassword) -> Input.getValidPassword(newPassword)
			)
		},
		// Set the appropriate background image.
		Paths.IMAGE_SECOND_BACKGROUND,

		// Set the text for the buttons and title.
		"Change",
		"Cancel",
		"Edit Password",

		// Add an action for when the form is submitted and valid.
		(gui, fixedInputs) -> {
			// Set the new password and write the change to disk.
			gui.getActiveUser().setPassword(fixedInputs[1]);
			gui.forceUserDataWrite();
			gui.forceAdminDataWrite();

			// Tell the user that they have successfully updated their password.
			JOptionPane.showMessageDialog(
				null, "You have successfully changed your password.", "Password Changed", JOptionPane.INFORMATION_MESSAGE
			);

			// Switch to the second menu.
			gui.switchPage(new Pages.SecondMenu(gui));
		},

		// If the form is exited, switch to the second menu.
		(gui) -> {gui.switchPage(new Pages.SecondMenu(gui));}
	);
} // end public class