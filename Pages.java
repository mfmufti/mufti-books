/**
 * Final Project: The Store - Pages
 * This class provides the other classes all the "pages" that this program can display.  Each "page" is an interface the user can use.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Prevent serialization warnings.
@SuppressWarnings("serial")
public class Pages
{
	// This class shows the main menu to the user.
	public static class MainMenu extends BasePage
	{
		// Store the list of buttons that the main menu will display.
		private final Components.MenuButton MENU_BUTTONS[] = {
			// Switch to a form that allows the user to sign in when this button is clicked.
			new Components.MenuButton("Sign in", () -> {switchPage(new Form(gui, Forms.NORMAL_SIGN_IN));}),
			
			// Switch to a form that allows the user to register when this button is clicked.
			new Components.MenuButton("Register", () -> {switchPage(new Form(gui, Forms.REGISTER));}),

			// Switch to a form that allows the admin to sign in when this button is clicked.
			new Components.MenuButton("Administrative tools", () -> {switchPage(new Form(gui, Forms.ADMIN_SIGN_IN));}),

			// Switch to the help page when this button is clicked.
			new Components.MenuButton("Help", () -> {switchPage(new Help(gui));}),

			// Ask the user if they would like to exit the program when this button is clicked.
			new Components.MenuButton("Exit the program", () -> {gui.askExitProgram();})
		};

		/**
		 * This constructor adds all the necessary components to display the main menu.
		 * 
		 * @param gui the GUI object
		 */
		public MainMenu(GUI gui)
		{
			// Call the base class's constructor.
			super(gui);

			// Declare the top and bottom panels needed for the menu.
			JPanel topPanel = new JPanel(), bottomPanel = new JPanel();

			// Set the appropriate background image.
			setBackgroundImage(Paths.IMAGE_MAIN_BACKGROUND); 

			// Enforce the necessary settings for the top panel.
			topPanel.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, 150));
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
			topPanel.setOpaque(false);

			// Add an empty box for padding and the logo and name.
			topPanel.add(Box.createRigidArea(new Dimension(1, 30)));
			topPanel.add(new Components.LogoAndName());

			// Enforce the necessary settings for the bottom panel.
			bottomPanel.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT - 165));
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
			bottomPanel.setOpaque(false);

			// Iterate through the menu buttons to add them.
			for (JButton button : MENU_BUTTONS)
			{
				// Add the menu button, and an empty box.
				bottomPanel.add(button);
				bottomPanel.add(Box.createRigidArea(new Dimension(1, 20)));
			} // end for

			// Enforce the necessary settings for the content.
			content.setLayout(new BorderLayout());

			// Add the top and bottom panels.
			content.add(bottomPanel, BorderLayout.SOUTH);
			content.add(topPanel, BorderLayout.NORTH);
		} // end MainMenu(GUI)
	} // end class MainMenu

	// Shows the second menu to the user (comes after the first menu).
	public static class SecondMenu extends BasePage
	{
		// Store the list of buttons that would be shown to a customer.
		private final Components.MenuButton NORMAL_BUTTONS[] = {
			// Switch to the book catalog when this button is clicked.
			new Components.MenuButton("Book catalog", () -> {switchPage(new BookCatalog(gui));}),

			// Switch to the cart when this button is clicked.
			new Components.MenuButton("My cart", () -> {switchPage(new Cart(gui));}),

			// Switch to a form that allows the user to change their password when this button is clicked.
			new Components.MenuButton("Change password", () -> {switchPage(new Form(gui, Forms.CHANGE_PASSWORD));}),

			// Switch to the session sales page when this button is clicked.
			new Components.MenuButton("Session sales", () -> {switchPage(new SessionSales(gui));}),

			// Ask the user if they would like to sign out when this button is clicked.
			new Components.MenuButton("Sign out", () -> {askSignOut();}),

			// Switch to the help page when this button is clicked.
			new Components.MenuButton("Help", () -> {switchPage(new Help(gui));}),

			// Ask the user if they would like to exit the program when this button is clicked.
			new Components.MenuButton("Exit the program", () -> {gui.askExitProgram();})
		};

		// Store the list of button that would be shown to the admin.
		private final Components.MenuButton ADMIN_BUTTONS[] = {
			// Switch to the book catalog when this button is clicked.
			new Components.MenuButton("Monitor books", () -> {switchPage(new BookCatalog(gui));}),
			
			// Switch to a form that allows the admin to add a book when this button is clicked.
			new Components.MenuButton("Add a book", () -> {switchPage(new Form(gui, Forms.ADD_BOOK));}),

			// Switch to a form that allows the admin to change their password when this button is clicked.
			new Components.MenuButton("Change password", () -> {switchPage(new Form(gui, Forms.CHANGE_PASSWORD));}),

			// Ask the user if they would like to sign out when this button is clicked.
			new Components.MenuButton("Sign out", () -> {askSignOut();}),

			// Switch to the help page when this button is clicked.
			new Components.MenuButton("Help", () -> {switchPage(new Help(gui));}),

			// Ask the user if they would like to exit the program when this button is clicked.
			new Components.MenuButton("Exit the program", () -> {gui.askExitProgram();})
		};

		/**
		 * This constructor adds needed components to display the second menu.
		 * 
		 * @param gui the GUI object
		 */
		public SecondMenu(GUI gui)
		{
			// Call the base class constructor.
			super(gui);

			// Initialize the menu panel.
			JPanel menuPanel = new JPanel();

			// Set the appropriate background image.
			setBackgroundImage(Paths.IMAGE_SECOND_BACKGROUND);

			// Set the necessary settings for the menu panel.
			menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
			menuPanel.setOpaque(false);
			menuPanel.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT - 85));
			menuPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

			// Iterate through the appropriate buttons based on whether the user is admin.
			for (Components.MenuButton button : (gui.getActiveUser().isAdmin() ? ADMIN_BUTTONS : NORMAL_BUTTONS))
			{
				// Add the button and an empty box for padding.
				menuPanel.add(button);
				menuPanel.add(Box.createRigidArea(new Dimension(1, 20)));
			} // end for

			content.add(new Components.TopPanel(gui, "Menu", false));
			content.add(menuPanel);
		} // end SecondMenu(GUI)

		/**
		 * Ask the user if they would like to sign out.
		 */
		private void askSignOut()
		{
			// Get the user's choice for whether they would like to sign out.
			int userChoice = JOptionPane.showOptionDialog(
				null, "Are you sure you want to sign out?", "Signing Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null
			);

			// If the user clicks on yes, proceed to sign out.
			if (userChoice == 0)
			{
				// Sign the user out and switch to the main menu.
				gui.signOutUser();
				gui.switchPage(new MainMenu(gui));

				// Tell the user that they have signed out.
				JOptionPane.showMessageDialog(null, "You have successfully signed out.", "Signed Out", JOptionPane.INFORMATION_MESSAGE);
			} // end if
		} // end askSignOut()
	} // end class SecondMenu

	// This class shows a form to the user.
	public static class Form extends BasePage
	{
		// Declare an array to keep track of the input fields.
		private Components.TextInput inputFields[];

		/**
		 * This constructor adds the necessary components to display a form based on the requirements passed in.
		 * 
		 * @param gui the GUI object
		 * @param form the FormData object to hold information about the form
		 * @param initialFields the values of the fields to be displayed initially (null if there are no initial values)
		 */
		Form(GUI gui, FormData form, String initialFields[])
		{
			// Call the parent constructor.
			super(gui);

			// Initialize the form panel, input field, and validation fields.
			JPanel formPanel = new JPanel();
			Components.TextInput currentInput;
			ValidationField fields[] = form.getFields();

			// Initialize the list of input fields.
			inputFields = new Components.TextInput[fields.length];

			// Set the appropriate background image.
			setBackgroundImage(form.getBackgroundImage()); 

			// Give the form panel the necessary settings.
			formPanel.setPreferredSize(new Dimension(
				Store.WINDOW_WIDTH, 15 + 60 * (int) Math.ceil(1.0 * fields.length / (fields.length > 5 ? 2 : 1))
			));
			formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			formPanel.setOpaque(false);

			// Add an empty box to the form panel.
			formPanel.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 15)));

			// Iterate through the required fields for this form.
			for (int i = 0; i < fields.length; i++)
			{
				// Initialize the current field with the appropriate label and settings.
				currentInput = new Components.TextInput(
					fields[i].getLabel(), fields[i].isPasswordField(),
					fields.length > 5 ? 400 : 540,
					form.hasDarkBackground()
				);

				// Set the initial value if initial values were provided.
				if (initialFields != null)
					currentInput.setValue(initialFields[i]);

				// Add the input field to the form panel, and store the input field in the list of input fields.
				formPanel.add(currentInput);
				inputFields[i] = currentInput;

				// Add spacing based on how many fields exist in total to make best use of the space.
				if (fields.length <= 5 || i % 2 == 1)
					formPanel.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 30)));
				else
					formPanel.add(Box.createRigidArea(new Dimension(30, 1)));
			} // end for

			// Add the top panel, form panel, buttons, and empty boxes for padding.
			content.add(new Components.TopPanel(gui, form.getTitleText(), form.hasDarkBackground()));
			content.add(formPanel);
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 1)));
			
			// Submit the form when this button is clicked.
			content.add(new Components.MenuButton(form.getSubmitButtonText(), () -> {form.submit(gui, getInputs());}));
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 10)));

			// Exit the form when this button is clicked.
			content.add(new Components.MenuButton(form.getExitButtonText(), () -> {form.exit(gui);}));
		} // end Form(GUI, FormData, String[])

		/**
		 * Overloaded constructor to allow for initial values to not be passed in.
		 * 
		 * @param gui the GUI object
		 * @param form the FormData object to hold information about the form
		 */
		public Form(GUI gui, FormData form)
		{
			// Call the other constructor with the initial values as null.
			this(gui, form, null);
		} // end Form(GUI, FormData)

		/**
		 * Get the values of all input fields in an array of Strings.
		 * 
		 * @return the array of Strings representing the values of the input fields
		 */
		private String[] getInputs()
		{
			// Initialize the array of input field values.
			String inputs[] = new String[inputFields.length];

			// Iterate through the input fields and store their values.
			for (int i = 0; i < inputs.length; i++)
				inputs[i] = inputFields[i].getValue();
			
			// Return the array of input field values.
			return inputs;
		} // end getInputs()
	} // end class Form

	// This class displays the books as a catalog to the user.
	public static class BookCatalog extends BasePage
	{
		/**
		 * This constructor adds the necessary components to display a book catalog. 
		 * 
		 * @param gui
		 */
		public BookCatalog(GUI gui)
		{
			// Call the parent constructor.
			super(gui);

			// Set the appropriate background image.
			setBackgroundImage(Paths.IMAGE_SECOND_BACKGROUND);

			// Initialize the inner content, scrollable panel, and catalog.
			JPanel contentInner = new JPanel();
			JScrollPane contentScroll = new JScrollPane(
				contentInner, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			);
			JPanel catalog = new JPanel();

			// Get the list of all books.
			ArrayList<Book> allBooks = gui.getBooks();

			// This button switches back to the menu when clicked.
			Components.MenuButton backButton = new Components.MenuButton("Back", () -> {switchPage(new SecondMenu(gui));});

			// Store the row count based on the number of books, and the scrollbar width.
			int rowCount = (int) Math.ceil(1.0 * allBooks.size() / 4);
			int scrollbarWidth = UIManager.getInt("ScrollBar.width");

			// Give necessary settings to the catalog.
			catalog.setLayout(new GridLayout(rowCount, 4, 10, 10));
			catalog.setOpaque(false);

			// For each of the books, add a book preview component to the catalog.
			for (Book book : allBooks)
				catalog.add(new Components.BookPreview(gui, book));
			
			// Give necessary settings to the scroll panel.
			contentScroll.setOpaque(false);
			contentScroll.getViewport().setOpaque(false);
			contentScroll.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT));

			// Give necessary settings to the inner content.
			contentInner.setPreferredSize(new Dimension(Store.WINDOW_WIDTH - scrollbarWidth, 185 + 340 * rowCount + 10 * (rowCount - 1)));
			contentInner.setOpaque(false);

			// Add the top panel, catalog, back button, and empty box to the inner content.
			contentInner.add(new Components.TopPanel(gui, "Books", false, Store.WINDOW_WIDTH - scrollbarWidth));
			contentInner.add(catalog);
			contentInner.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 30)));
			contentInner.add(backButton);

			// Add the scrollable content to the main content.
			content.add(contentScroll);
		} // end BookCatalog(GUI)
	} // end class BookCatalog

	// This class displays a page that shows information about a given book.
	public static class BookFullView extends BasePage
	{
		/**
		 * This constructor adds the necessary components to display the information about the book.
		 * 
		 * @param gui the GUI object
		 * @param book the Book object containing information about the book
		 */
		BookFullView(GUI gui, Book book)
		{
			// Call the parent constructor.
			super(gui);

			// Initialize the main content, book cover, and book information components.
			JPanel mainContent = new JPanel();
			JLabel bookCover = new JLabel();
			JPanel bookInformation = new JPanel();

			// Store the information that will be shown as an array of separate lines.
			String informationText[] = new String[] {
				"Title: " + book.getName(),
				"Author: " + book.getAuthor(),
				"Genre: " + book.getGenre(),
				"Binding: " + book.getBinding(),
				"Published: " + book.getPublicationYear(),
				"Price: $" + String.format("%.2f", book.getPrice()),
				"Availability: " + (book.getQuantity() == 0 ? "OUT OF STOCK" : book.getQuantity() < book.getJitTrigger() ?
					"LAST FEW" : book.getQuantity() < book.getJitTrigger() * 2 ? "Running Low" : "In Stock")
			};

			// This button switches back to the book catalog when clicked.
			Components.MenuButton backButton = new Components.MenuButton("Back", () -> {gui.switchPage(new BookCatalog(gui));});

			// Add this book to cart when this button is clicked.
			Components.MenuButton addToCartButton = new Components.MenuButton("Add to cart", () -> {handleAddToCart(book);});

			// Store the image that will be shown as preview for the book.
			ImageIcon coverImage = Resources.getImage(book.getImagePath(), 299, 460);

			// If the cover image did not load properly, load an alternative.
			if (coverImage == null)
				coverImage = Resources.getImage(Paths.IMAGE_BOOK_NOT_FOUND, 299, 460);

			// Set the necessary background image.
			setBackgroundImage(Paths.IMAGE_SECOND_BACKGROUND);

			// Give the book cover its necessary settings.
			bookCover.setBounds(30, 10, 299, 460);
			bookCover.setIcon(coverImage);

			// Give the buttons necessary settings.
			backButton.setBounds(400, 350, 540, 50);
			addToCartButton.setBounds(400, 420, 540, 50);

			// Give the book information panel necessary settings.
			bookInformation.setForeground(Color.BLACK);
			bookInformation.setBounds(400, 10, 580, 400);
			bookInformation.setOpaque(false);
			bookInformation.setLayout(new BoxLayout(bookInformation, BoxLayout.Y_AXIS));

			// Iterate through the lines of text to be shown as information about the book.
			for (String line : informationText)
			{
				// Initialize the component to show the current line.
				JLabel lineLabel = new JLabel();

				// Give the current line necessary settings.
				lineLabel.setText(line);
				lineLabel.setFont(Resources.getFont(Paths.FONT_REGULAR, 32));
				lineLabel.setForeground(Color.BLACK);

				// Add the line to the book information panel.
				bookInformation.add(lineLabel);
			} // end for

			// Give the main content necessary settings.
			mainContent.setLayout(null);
			mainContent.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT - 65));
			mainContent.setOpaque(false);

			// Add the book cover, book information, back button, and add to cart button to the main content.
			mainContent.add(bookCover);
			mainContent.add(bookInformation);
			mainContent.add(backButton);
			mainContent.add(addToCartButton);

			// Add the top panel and main content to the upper level component.
			content.add(new Components.TopPanel(gui, "Books", false));
			content.add(mainContent);
		} // end BookFullView(GUI, Book)

		/**
		 * Add a specified book to cart.
		 * 
		 * @param book
		 */
		private void handleAddToCart(Book book)
		{
			// Declare the variables to hold the book count that the user enters.
			String bookCountRaw;
			int bookCount;

			// Get the currently signed in user.
			User user = gui.getActiveUser();
			
			// Store the number of items in the user's cart.
			int itemsInCart = user.getAllCartItemsCount();

			// Initialize an array that holds all the possible values the user can choose from.
			String selectionValues[] = new String[Math.min(book.getQuantity() - user.getCartItemCount(book), 10 - itemsInCart)];

			// Check to see if the user cannot add this item to cart for some reason.
			if (book.getQuantity() == 0)
			{
				// If the item is out of stock, tell the user and prevent from adding this item.
				JOptionPane.showMessageDialog(
					null, "This item is out of stock!", "Error Adding to Cart", JOptionPane.ERROR_MESSAGE
				);

				return;
			} // end if
			else if (book.getQuantity() == user.getCartItemCount(book))
			{
				// If the user has already added as many of this item as there are in stock, prevent from adding more, and inform
				// the user.
				JOptionPane.showMessageDialog(
					null, "You have already added as many of this item as there are in stock!", "Error Adding to Cart",
					JOptionPane.ERROR_MESSAGE
				);

				return;
			} // end else if
			else if (itemsInCart >= 10)
			{
				// If the user has already reached the maximum number of items in cart, tell the user, and prevent from adding this item.
				JOptionPane.showMessageDialog(
					null, "You have already reached a max of 10 items in your cart.", "Error Adding to Cart", JOptionPane.ERROR_MESSAGE
				);

				return;
			} // end else if

			// Store all the possible values the user can choose for the number of items to add to cart so that their total number of
			// items does not exceed 10.
			for (int i = 1; i <= selectionValues.length; i++)
				selectionValues[i - 1] = Integer.toString(i);

			// Get the user's choice.
			bookCountRaw = (String) JOptionPane.showInputDialog(
				null, "Enter the number of items you wish to add to cart:", "Add to Cart", JOptionPane.PLAIN_MESSAGE,
				null, selectionValues, 0
			);
			
			// If the user chose to cancel, prevent from adding the item to cart.
			if (bookCountRaw == null)
				return;

			// Convert the user's choice to an integer.
			bookCount = Integer.parseInt(bookCountRaw);

			// Add the item to cart and update the necessary file.
			gui.getActiveUser().addToCart(book, bookCount);
			gui.forceUserDataWrite();

			// Tell the user that they have successfully added the item to cart.
			JOptionPane.showMessageDialog(
				null, "You have successfully added " + bookCount + " item" + (bookCount != 1 ? "s" : "") + " to cart.", "Added to Cart",
				JOptionPane.INFORMATION_MESSAGE
			);
		} // end handleAddToCart(Book)
	} // end class BookFullView

	// This class shows the user's cart.
	public static class Cart extends BasePage
	{
		/**
		 * This constructor adds the necessary components to display the user's cart.
		 * 
		 * @param gui the GUI object
		 */
		public Cart(GUI gui)
		{
			// Call the parent constructor.
			super(gui);

			// Set the appropriate background image.
			setBackgroundImage(Paths.IMAGE_SECOND_BACKGROUND);

			// Initialize the inner content, scroll panel, and the label shown if there is no item in cart.
			JPanel contentInner = new JPanel();
			JScrollPane contentScroll = new JScrollPane(
				contentInner, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			);
			JLabel noItemsDisplay = new JLabel();

			// This button causes the user to checkout when clicked.
			Components.MenuButton checkoutButton = new Components.MenuButton("Checkout", () -> {handleCheckout();});

			// This button switches back to the second menu when clicked.
			Components.MenuButton backButton = new Components.MenuButton("Back", () -> {switchPage(new SecondMenu(gui));});

			// Get the list of items in cart.
			Book cartItems[] = gui.getActiveUser().getCartItems();

			// Add the top panel.
			contentInner.add(new Components.TopPanel(gui, "Cart", false));

			// Display a "cart panel" for each of the items in cart.
			for (Book book : cartItems)
			{
				// Add the "cart panel" and an empty box for padding.
				contentInner.add(new Components.CartPanel(gui, book));
				contentInner.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 20)));
			} // end for

			// Add the buttons and empty boxes.
			contentInner.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 20)));
			contentInner.add(checkoutButton);
			contentInner.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 20)));
			contentInner.add(backButton);

			// Give necessary settings to the inner content.
			contentInner.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			contentInner.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, 230 + cartItems.length * 280));
			contentInner.setOpaque(false);

			// Give necessary settings to the scroll panel.
			contentScroll.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT));
			contentScroll.setOpaque(false);
			contentScroll.getViewport().setOpaque(false);
			
			// Give the "no items display" component necessary settings.
			noItemsDisplay.setText("You have no items in cart!");
			noItemsDisplay.setFont(Resources.getFont(Paths.FONT_REGULAR_BOLD, 68));
			noItemsDisplay.setHorizontalAlignment(JLabel.CENTER);
			noItemsDisplay.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, 300));

			// Display something based on whether the user has any items in cart.
			if (cartItems.length == 0)
			{
				// Add the top panel, the "no items display", and a back button if there is no item in cart.
				content.add(new Components.TopPanel(gui, "Cart", false, Store.WINDOW_WIDTH - 17));
				content.add(noItemsDisplay);
				content.add(backButton);
			} // end if
			else
			{
				// Add all cart panels if the user has something in cart.
				content.add(contentScroll);
			} // end else
		} // end Cart(GUI)

		/**
		 * Cause the user to checkout.
		 */
		private void handleCheckout()
		{
			// Ask the user whether they would like to checkout.
			int shouldCheckout = JOptionPane.showOptionDialog(
				null, "Are you sure you want to checkout?", "Checking Out", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null
			);

			// If the user clicks yes, switch to the invoice page.
			if (shouldCheckout == 0)
				switchPage(new Invoice(gui));
		} // end handleCheckout()
	} // end class Cart

	// This class displays an invoice to the user based on what they are buying.
	public static class Invoice extends BasePage
	{
		/**
		 * Add necessary components to display the invoice.
		 * 
		 * @param gui the GUI object
		 */
		public Invoice(GUI gui)
		{
			// Call the parent constructor.
			super(gui);

			// Initialize the receipt header and receipt content panels.
			JPanel receiptHeader = new JPanel(), receiptContent = new JPanel();

			// Get the signed in user.
			User user = gui.getActiveUser();

			// Get the items in the user's cart.
			Book boughtBooks[] = user.getCartItems();

			// This button switches to the second menu when clicked.
			Components.MenuButton backButton = new Components.MenuButton("Back", () -> {gui.switchPage(new SecondMenu(gui));});

			// Initialize the arrays to hold the receipt item names on the left and the money values on the right.
			String receiptIdentifiers[] = new String[boughtBooks.length + 3];
			double receiptMoneyValues[] = new double[receiptIdentifiers.length];

			// Initialize the subtotal of all items.
			double subtotal = 0d;

			// Store the header text, with each line as a separate entry in this array.
			String headerText[] = {
				"Store: MUFTIBOOKS (Cambridge Location)",
				"Customer: " + user.getFullName(),
				"Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date())
			};

			// Set the appropriate background image.
			setBackgroundImage(Paths.IMAGE_SECOND_BACKGROUND);

			// Iterate through the books to buy to build a receipt out of them.
			for (int i = 0; i < boughtBooks.length; i++)
			{
				// Get the current book and quantity in cart.
				Book book = boughtBooks[i];
				int quantity = user.getCartItemCount(book);

				// Store the book name, and identify the quantity if the quantity is greater than 1.
				receiptIdentifiers[i] = book.getName() + (quantity > 1 ? " X " + quantity : "");

				// Store the amount that will be paid for this book.
				receiptMoneyValues[i] = book.getPrice() * quantity;

				// Add to the subtotal.
				subtotal += receiptMoneyValues[i];
			} // end for

			// Store the subtotal, HST, and total as identifiers at the bottom of the receipt.
			receiptIdentifiers[receiptIdentifiers.length - 3] = "Subtotal:";
			receiptIdentifiers[receiptIdentifiers.length - 2] = "HST:";
			receiptIdentifiers[receiptIdentifiers.length - 1] = "TOTAL:";

			// Store the values of the subtotal, HST, and total at the bottom of the receipt.
			receiptMoneyValues[receiptMoneyValues.length - 3] = subtotal;
			receiptMoneyValues[receiptMoneyValues.length - 2] = subtotal * 0.13;
			receiptMoneyValues[receiptMoneyValues.length - 1] = subtotal * 1.13;

			// Give the receipt header necessary settings.
			receiptHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			receiptHeader.setOpaque(false);
			receiptHeader.setPreferredSize(new Dimension(500, 77));
			receiptHeader.setBackground(Color.ORANGE);

			// Give the receipt content necessary settings, and enough rows based on the number of items being purchased.
			receiptContent.setLayout(new GridLayout(boughtBooks.length + 3, 2, 30, 2));
			receiptContent.setOpaque(false);
			receiptContent.setPreferredSize(new Dimension(500, 27 * receiptIdentifiers.length));

			// Iterate through the lines of header text.
			for (String headerLine : headerText)
			{
				// Make a label for the current line of header text.
				JLabel currentLineLabel = new JLabel();

				// Apply the necessary settings for this line of header text.
				currentLineLabel.setText(headerLine);
				currentLineLabel.setFont(Resources.getFont(Paths.FONT_RECEIPT, 18));
				currentLineLabel.setForeground(Color.BLACK);
				currentLineLabel.setPreferredSize(new Dimension(500, 22));

				// Add the current line, and an empty box.
				receiptHeader.add(currentLineLabel);
				receiptHeader.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 2)));
			} // end for

			// Iterate through the rows of the receipt.
			for (int i = 0; i < receiptIdentifiers.length; i++)
			{
				// Make a label for the left and right sides of the current row.
				JLabel receiptLeft = new JLabel(), receiptRight = new JLabel();

				// Give necessary settings to the left side of this row.
				receiptLeft.setText(receiptIdentifiers[i]);
				receiptLeft.setHorizontalAlignment(JLabel.LEFT);
				receiptLeft.setFont(Resources.getFont(Paths.FONT_RECEIPT, 18));
				receiptLeft.setForeground(Color.BLACK);
				receiptLeft.setPreferredSize(new Dimension(250, 20));

				// Give necessary settings to the right side of this row.
				receiptRight.setText(String.format("$%.2f", receiptMoneyValues[i]));
				receiptRight.setHorizontalAlignment(JLabel.RIGHT);
				receiptRight.setFont(Resources.getFont(Paths.FONT_RECEIPT, 18));
				receiptRight.setForeground(Color.BLACK);
				receiptLeft.setPreferredSize(new Dimension(250, 20));

				// Add the left and right sides of this row to the receipt.
				receiptContent.add(receiptLeft);
				receiptContent.add(receiptRight);
			} // end for

			// Add the top panel, receipt header, receipt contents, and empty boxes to the main content.
			content.add(new Components.TopPanel(gui, "Your Receipt", false));
			content.add(receiptHeader);
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 3)));
			content.add(receiptContent);
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 10)));
			content.add(backButton);

			// Make the user purchase the items in cart, and write the changes to disk.
			user.makePurchase();
			gui.forceUserDataWrite();
			gui.forceBookDataWrite();
		} // end Invoice(GUI)
	} // end class Invoice

	// This class displays the amounts the user has sent in this session and in total.
	public static class SessionSales extends BasePage
	{
		/**
		 * This constructor adds the necessary components to display the amounts the user has spent in this session and in total.
		 * 
		 * @param gui the GUI object
		 */
		public SessionSales(GUI gui)
		{
			// Call the parent constructor.
			super(gui);

			// Initialize the panel to hold the information.
			JPanel informationPanel = new JPanel();

			// Get the signed in user.
			User user = gui.getActiveUser();
			
			// This button will ask to reset the amounts for this session when clicked.
			Components.MenuButton resetButton = new Components.MenuButton("Start new session", () -> {handleReset();});

			// This button will switch to the second menu when clicked.
			Components.MenuButton backButton = new Components.MenuButton("Back", () -> {switchPage(new Pages.SecondMenu(gui));});

			// Store the lines of information that will be displayed.
			String informationLines[] = {
				"Amount spent this session: " + String.format("$%.2f", user.getSessionSales()),
				"Books purchased this session: " + user.getSessionItems(),
				"Amount spent in all: " + String.format("$%.2f", user.getTotalSales()),
				"Books purchased in all: " + user.getTotalItems()
			};

			// Set an appropriate background image.
			setBackgroundImage(Paths.IMAGE_SECOND_BACKGROUND);

			// Iterate through the lines of information text to display to the user.
			for (String line : informationLines)
			{
				// Initialize a label for the current line of text.
				JLabel lineLabel = new JLabel();

				// Apply settings necessary for the current line of text.
				lineLabel.setText(line);
				lineLabel.setFont(Resources.getFont(Paths.FONT_REGULAR_BOLD, 42));
				lineLabel.setForeground(Color.BLACK);
				lineLabel.setPreferredSize(new Dimension(Store.WINDOW_WIDTH, 58));
				lineLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				// lineLabel.setHorizontalAlignment(JLabel.RIGHT);

				// Add the current line to the information panel.
				informationPanel.add(lineLabel);
			} // end for

			// Give necessary settings to the information panel.
			informationPanel.setOpaque(false);
			informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
			informationPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

			// Add the top panel, information panel, reset button, back button, and empty boxes to the main content.
			content.add(new Components.TopPanel(gui, "Session Sales", false));
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 20)));
			content.add(informationPanel);
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 50)));
			content.add(resetButton);
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 20)));
			content.add(backButton);
		} // end SessionSales(GUI)

		/**
		 * Reset the session amounts for the user.
		 */
		private void handleReset()
		{
			// Ask the user if they would like to reset amounts and store their response.
			int shouldReset = JOptionPane.showOptionDialog(
				null, "Are you sure you want to start a new session? This will result in your session amounts being reset to 0.",
				"Starting New Session", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null
			);

			// Proceed to reset if the user clicks on yes.
			if (shouldReset == 0)
			{
				// Start a new session for the user, and write the changes to disk.
				gui.getActiveUser().startNewSession();
				gui.forceUserDataWrite();

				// Reload the current page.
				gui.switchPage(new SessionSales(gui));

				// Tell the user that they have successfully began a new session.
				JOptionPane.showMessageDialog(
					null, "You have successfully began a new session.", "New Session Started", JOptionPane.INFORMATION_MESSAGE
				);
			} // end if
		} // end handleReset()
	} // end class SessionSales

	// This class displays help text to the user.
	public static class Help extends BasePage
	{
		// Store the text that will be displayed if no user is signed in.
		private static final String NORMAL_HELP_LINES[] = {
			"Welcome to MuftiBooks!  To begin, please make an account",
			"by clicking on \"Register\".  Fill out the form, and once you",
			"are registered, you will be taken to another menu.  If you",
			"already have an account, click on \"Sign in\", and you will yet",
			"be presented with another menu.  If you are the admin, click",
			"on \"Administrative tools\", and sign in there.  If you need",
			"further help after you have registered or signed in, click on",
			"the \"Help\" in the new menu that will be presented to you.",
			"Have a good shopping experience!!!"
		};

		// Store the text that will be displayed if a normal user is signed in.
		private static final String CUSTOMER_HELP[] = {
			"Hello customer!  Begin shopping by clicking on \"Book catalog\".",
			"When you see a book you like, you can click the \"View\" button",
			"on the book, and you will be shown more information.  There,",
			"you can also add the book to cart by clicking the \"Add to cart\"",
			"button.  To view your cart, click on the \"My cart\" button in",
			"the menu.  There, you can change the quantity of an item, and",
			"remove items from your cart.  You can also checkout by clicking",
			"the \"Checkout\" button at the bottom of the page.  To monitor",
			"the amount you have spent, click on \"Session sales\" in the",
			"menu.  You can also reset your session amounts.  If you feel",
			"you need a change of password, that is also possible through",
			"the menu by filling out a small form."
		};

		// Store the text that will be displayed if the admin is signed in.
		private static final String ADMIN_HELP[] = {
			"Hello admin!  As manager of this program, you have the ability",
			"to add and edit books.  If you click on \"Edit books\" in the",
			"menu, you will be presented with all the books, along with",
			"whether they are in need of reordering.  You can edit the",
			"properties of any book by clicking the \"Edit\" button on the",
			"book, and delete any book by clicking the \"Delete\" button on",
			"the book.  To add a new book, click on \"Add a book\" in the",
			"menu.  Here, you will be shown a form you can fill out to",
			"add the book, and your input will be validated to make sure",
			"there are no mistakes.  If at any time you feel a change of",
			"password, click on \"Change password\" in the menu, and you",
			"will be presented a short form to complete the process."
		};

		/**
		 * This constructor adds the necessary components to display help to the user.
		 * 
		 * @param gui
		 */
		Help(GUI gui)
		{
			// Call the parent constructor.
			super(gui);

			// Initialize the panel that will contain the help text.
			JPanel helpPanel = new JPanel();

			// Get the signed in user.
			User user = gui.getActiveUser();

			// Get the help text that will be displayed based on who is signed in.
			String helpText[] = user == null ? NORMAL_HELP_LINES : user.isAdmin() ? ADMIN_HELP : CUSTOMER_HELP;

			// This button will go back to either the main menu, or second menu, based on whether anyone is signed in.
			Components.MenuButton backButton = new Components.MenuButton("Back", () -> {
				switchPage(user == null ? new MainMenu(gui) : new SecondMenu(gui));
			});

			// Set the appropriate background image.
			setBackgroundImage(user == null ? Paths.IMAGE_MAIN_BACKGROUND : Paths.IMAGE_SECOND_BACKGROUND);

			// Iterate through the lines of help text.
			for (String line : helpText)
			{
				// Initialize the label for the current line of text.
				JLabel lineLabel = new JLabel();

				// Set the necessary settings for the current label.
				lineLabel.setText(line);
				lineLabel.setForeground(user == null ? Color.BLACK : Color.BLACK);
				lineLabel.setFont(Resources.getFont(Paths.FONT_REGULAR, user == null ? 30 : 24));
				lineLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

				// Add the current line of text.
				helpPanel.add(lineLabel);
			} // end for

			// Give appropriate settings to the help panel.
			helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
			helpPanel.setOpaque(false);

			// Add the top panel, back button, and an empty box.
			content.add(new Components.TopPanel(gui, "Help", user == null));
			content.add(helpPanel);
			content.add(Box.createRigidArea(new Dimension(Store.WINDOW_WIDTH, 20)));
			content.add(backButton);
		} // end Help(GUI)
	} // end class Help
} // end public class