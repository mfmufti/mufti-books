/**
 * Final Project: The Store - Components
 * This class separates many of the GUI components that are to be reused several times, or take a lot of code to implement.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

// Prevent serialization warnings.
@SuppressWarnings("serial")
public class Components
{
	// This class will display a background image.
	public static class BackgroundImage extends JLabel
	{
		/**
		 * This constructor will modify a JLabel to display a background image.
		 * 
		 * @param backgroundPath the path to the background image
		 */
		public BackgroundImage(String backgroundPath)
		{
			// Give necessary settings to display the background image properly.
			setBounds(0, 0, Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT);
			setIcon(Resources.getImage(backgroundPath, Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT));
			setOpaque(false);
		} // end BackgroundImage
	} // end class BackgroundImage

	// This class will display a button. NOTE: the name is "MenuButton" because is was originally intended for the menu only.  The name
	// was kept to prevent ambiguity between this class and java.awt.Button.
	public static class MenuButton extends JButton
	{
		// Make an interface that button click handlers to be passed in as lambdas.
		@FunctionalInterface
		public static interface ButtonClickHandler
		{
			// The lambda will accept no arguments, and return nothing.
			void handleClick();
		} // end interface

		/**
		 * This constructor will apply the necessary settings to make a button.
		 * 
		 * @param text the button text
		 */
		public MenuButton(String text)
		{
			// Give the button necessary settings.
			setText(text);
			setBackground(Color.WHITE);
			setForeground(new Color(0.07f, 0.85f, 0.43f));
			setBorder(BorderFactory.createLineBorder(new Color(0.07f, 0.85f, 0.43f), 10));
			setMaximumSize(new Dimension(540, 50));
			setPreferredSize(getMaximumSize());
			setAlignmentX(JComponent.CENTER_ALIGNMENT);
			setFont(Resources.getFont(Paths.FONT_REGULAR, 20));
		} // end MenuButton(String)

		/**
		 * This constructor will add on to the other constructor by adding a click listener to the button.
		 * 
		 * @param text the button text
		 * @param handler the button click handler
		 */
		public MenuButton(String text, ButtonClickHandler handler)
		{
			// Call the other constructor.
			this(text);

			// Add the button click listener.
			addActionListener((ActionEvent event) -> {handler.handleClick();});
		} // end MenuButton(String, ButtonClickHandler)
	} // end class MenuButton

	// This class will display the MuftiStore logo and name.
	public static class LogoAndName extends JPanel
	{
		/**
		 * This constructor applies the necessary settings to display the logo and name.
		 */
		public LogoAndName()
		{
			// Initialize the logo and name labels.
			JLabel storeLogo = new JLabel(), storeName = new JLabel();

			// Give the logo necessary settings.
			storeLogo.setIcon(Resources.getImage(Paths.IMAGE_STORE_LOGO, 90, 90));
			storeLogo.setPreferredSize(new Dimension(90, 90));

			// Give the name necessary settings.
			storeName.setText("MuftiBooks");
			storeName.setForeground(Color.WHITE);
			storeName.setFont(Resources.getFont(Paths.FONT_MAIN_TITLE, 72));

			// Give necessary settings to the containing panel.
			setLayout(new FlowLayout());
			setOpaque(false);
			setAlignmentX(JComponent.CENTER_ALIGNMENT);

			// Add the logo, name, and empty boxes for padding.
			add(storeLogo);
			add(Box.createRigidArea(new Dimension(10, 1)));
			add(storeName);
			add(Box.createRigidArea(new Dimension(10, 1)));
		} // end LogoAndName()
	} // end class LogoAndName

	// This class displays the top panel that shows a smaller logo and name, title, and currently logged in user (if any).
	public static class TopPanel extends JPanel
	{
		/**
		 * This constructor adds the necessary component to display the top panel.
		 * 
		 * @param gui the GUI object
		 * @param titleText the title text
		 * @param isDarkBackground whether the current background image is the dark version
		 * @param width the desired width of the top panel
		 */
		public TopPanel(GUI gui, String titleText, boolean isDarkBackground, int width)
		{
			// Initialize the store logo, store name, title, and user information labels.
			JLabel storeLogo = new JLabel(), storeName = new JLabel(), storeLogoNameWrapper = new JLabel();
			JLabel title = new JLabel(), userInformation = new JLabel();

			// Get the currently signed in user.
			User user = gui.getActiveUser();

			// Give necessary settings to the store logo.
			storeLogo.setIcon(Resources.getImage(Paths.IMAGE_STORE_LOGO, 50, 50));
			storeLogo.setPreferredSize(new Dimension(50, 50));

			// Give necessary settings to the name.
			storeName.setText("MuftiBooks");
			storeName.setForeground(isDarkBackground ? Color.WHITE : Color.BLACK);
			storeName.setFont(Resources.getFont(Paths.FONT_MAIN_TITLE, 36));

			// Give necessary settings to the container of the store logo and name.
			storeLogoNameWrapper.setLayout(new FlowLayout(FlowLayout.LEFT));

			// Add the store logo, store name, and an empty box for padding.
			storeLogoNameWrapper.add(storeLogo);
			storeLogoNameWrapper.add(Box.createRigidArea(new Dimension(3, 1)));
			storeLogoNameWrapper.add(storeName);

			// Give necessary settings to the title.
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setText(titleText);
			title.setFont(Resources.getFont(Paths.FONT_SECOND_TITLE, 48));
			title.setForeground(isDarkBackground ? Color.WHITE : Color.BLACK);
			title.setBorder(new EmptyBorder(0, 0, 10, 0));

			// Give necessary settings to the user information.
			userInformation.setText(user == null ? "" : user.isAdmin() ? "ADMIN MODE" : "You are: " + user.getFullName() + ".");
			userInformation.setFont(Resources.getFont(Paths.FONT_REGULAR, 16));
			userInformation.setForeground(user != null && user.isAdmin() ? Color.RED : isDarkBackground ? Color.WHITE : Color.BLACK);
			userInformation.setHorizontalAlignment(JLabel.RIGHT);
			userInformation.setBorder(new EmptyBorder(0, 0, 10, 20));

			// Set necessary settings for the containing panel.
			setLayout(new GridLayout(1, 3));
			setPreferredSize(new Dimension(width, 65));
			setOpaque(false);

			// Add the store logo, name, and title.
			add(storeLogoNameWrapper);
			add(title);
			
			// Add the user information if the user is signed in.  Otherwise, add an empty box.
			if (user == null)
				add(Box.createRigidArea(new Dimension(1, 1)));
			else
				add(userInformation, BorderLayout.EAST);
		} // end TopPanel(GUI, String, boolean, int)

		/**
		 * Allow a "TopPanel" object to be made without specifying the width, maximum width is taken.
		 * 
		 * @param gui the GUI object
		 * @param titleText the title text
		 * @param isDarkBackground whether the background being used is the dark background
		 */
		public TopPanel(GUI gui, String titleText, boolean isDarkBackground)
		{
			// Call the other constructor with the width as the maximum width.
			this(gui, titleText, isDarkBackground, Store.WINDOW_WIDTH);
		} // end TopPanel(GUI, String, boolean)
	} // end TopPanel

	// This class displays a text input (used in forms).
	public static class TextInput extends JPanel
	{
		// Declare the object that will hold the text field that will be used.
		private JTextField textField;

		/**
		 * This constructor adds necessary components to display a text input with a label.
		 * 
		 * @param inputLabel the label of the text input
		 * @param isPassword whether the input is meant for a password
		 * @param width the width of the input field
		 * @param hasDarkBackground whether the current background is the dark background
		 */
		public TextInput(String inputLabel, boolean isPassword, int width, boolean hasDarkBackground)
		{
			// Initialize the label for the text input.
			JLabel label = new JLabel();

			// Set the text field to a password field or normal text field based on the passed in option.
			textField = isPassword ? new JPasswordField() : new JTextField();

			// Give the label its necessary settings.
			label.setText(inputLabel);
			label.setFont(Resources.getFont(Paths.FONT_REGULAR_BOLD, 20));
			label.setForeground(hasDarkBackground ? Color.WHITE : new Color(0.07f, 0.85f, 0.43f));
			label.setPreferredSize(new Dimension((int) (width * 0.4), 30));

			// Give the text field its necessary settings.
			textField.setFont(Resources.getFont(Paths.FONT_REGULAR, 20));
			textField.setForeground(Color.BLACK);
			textField.setPreferredSize(new Dimension((int) (width * 0.6), 30));

			// Give necessary settings to the containing panel.
			setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			setPreferredSize(new Dimension(width, 30));
			setOpaque(false);

			// Add the label and the text field.
			add(label);
			add(textField);
		} // end TextInput(String, boolean, int, boolean)

		/**
		 * Get the current value of the text field.
		 * 
		 * @return the value of the text field
		 */
		public String getValue()
		{
			// Return the current value of the text field.
			return textField.getText();
		} // end getValue()

		/**
		 * Set the value for the text field.
		 * 
		 * @param value the value to set on the text field
		 */
		public void setValue(String value)
		{
			// Set the passed value.
			textField.setText(value);
		} // end setValue(String)
	} // end class TextInput

	// This class displays the preview box for a book.
	public static class BookPreview extends JLayeredPane
	{
		/**
		 * This constructor adds necessary components to display the preview box for the book.
		 * 
		 * @param gui the GUI object
		 * @param book the Book object
		 */
		public BookPreview(GUI gui, Book book)
		{
			// Store the image that will be shown as preview.
			ImageIcon image = Resources.getImage(book.getImagePath(), 208, 320);

			// Initialize labels for the image preview and the stock preview.
			JLabel imagePreview = new JLabel(), stockPreview = new JLabel();

			// Declare the needed buttons.
			MenuButton next, delete;

			// If the image failed to load, load an alternative.
			if (image == null)
				image = Resources.getImage(Paths.IMAGE_BOOK_NOT_FOUND, 208, 320);

			
			// Add components based on whether a customer is signed in, or the admin.
			if (gui.getActiveUser() != null && gui.getActiveUser().isAdmin())
			{
				// Initialize the edit button.  This button will switch to a form on click.
				next = new MenuButton("Edit", () -> {
					gui.setEditingBook(book);
					gui.switchPage(new Pages.Form(gui, Forms.EDIT_BOOK, book.getFormList()));
				});

				// Initialize the delete button.  This button will delete the book on click.
				delete = new MenuButton("Delete", () -> {deleteBookHandler(gui, book);});

				// Set the bounds for the delete button.
				delete.setBounds(30, 220, 168, 45);

				// Give the necessary text to the stock preview.
				stockPreview.setText(
					book.getQuantity() < book.getJitTrigger() ? "ORDER NOW" :
					book.getQuantity() < book.getJitTrigger() * 2 ? "ORDER SOON" :
					"IN STOCK"
				);

				// Give necessary settings to the stock preview.
				stockPreview.setForeground(book.getQuantity() < book.getJitTrigger() * 2 ? Color.RED : Color.WHITE);
				stockPreview.setBounds(30, 20, 168, 30);
				stockPreview.setFont(Resources.getFont(Paths.FONT_REGULAR, 24));
				stockPreview.setBackground(new Color(0.07f, 0.85f, 0.43f));
				stockPreview.setOpaque(true);
				stockPreview.setHorizontalAlignment(JLabel.CENTER);

				// Add the stock preview and delete button.
				add(stockPreview, JLayeredPane.PALETTE_LAYER);
				add(delete, JLayeredPane.PALETTE_LAYER);
			} // end if
			else
			{
				// Set the next button's action to viewing the individual book.
 				next = new MenuButton("View", () -> {gui.switchPage(new Pages.BookFullView(gui, book));});
			} // end else

			// Give necessary bounds to the next button.
			next.setBounds(30, 275, 168, 45);

			// Give necessary settings to the image preview.
			imagePreview.setIcon(image);
			imagePreview.setBounds(10, 10, 208, 320);

			// Give necessary settings to the containing panel.
			setPreferredSize(new Dimension(228, 340));
			setBorder(BorderFactory.createLineBorder(new Color(0.07f, 0.85f, 0.43f), 10));
			setLayout(null);

			// Add the image preview, and the next button.
			add(imagePreview, JLayeredPane.DEFAULT_LAYER);
			add(next, JLayeredPane.PALETTE_LAYER);
		} // end BookPreview(GUI, Book)

		/**
		 * Delete a given book, first asking for the user's choice.
		 * 
		 * @param gui the GUI object
		 * @param book a Book object
		 */
		private void deleteBookHandler(GUI gui, Book book)
		{
			// Ask the user whether they would like to delete the book.
			int shouldDelete = JOptionPane.showOptionDialog(
				null, "Are you sure you want to delete this book?", "Deleting Book", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null
			);

			// If the user clicks yes, proceed to delete.
			if (shouldDelete == 0)
			{
				// Remove the book, and reload the page.
				gui.removeBook(book);
				gui.switchPage(new Pages.BookCatalog(gui));
			} // end if
		} // end deleteBookHandler(GUI, Book)
	} // end class BookPreview

	// This class displays a panel for one item in the cart.
	public static class CartPanel extends JPanel
	{
		/**
		 * This constructor adds necessary components to display a cart panel.
		 * 
		 * @param gui the GUI object
		 * @param book a Book object
		 */
		public CartPanel(GUI gui, Book book)
		{
			// Initialize the book cover and information.
			JLabel bookCover = new JLabel();
			JPanel bookInformation = new JPanel();

			// Get the image for the book.
			ImageIcon bookImage = Resources.getImage(book.getImagePath(), 156, 240);

			// This button will ask to edit the quantity on click.
			MenuButton editQuantityButton = new MenuButton("Edit quantity", () -> {handleEditCartQuantity(gui, book);});

			// This button will remove the item from cart on click.
			MenuButton removeButton = new MenuButton("Remove", () -> {handleRemoveCartItem(gui, book);});

			// Store the individual lines of information that will be shown as separate entries in an array.
			String informationLines[] = {
				"Title: " + book.getName(),
				"Author: " + book.getAuthor(),
				"Quantity: " + gui.getActiveUser().getCartItemCount(book),
				"Unit price: $" + String.format("%.2f", book.getPrice()),
				"Total price: $" + String.format("%.2f", gui.getActiveUser().getCartItemCount(book) * book.getPrice())
			};

			// If the image failed to load, load an alternative.
			if (bookImage == null)
				bookImage = Resources.getImage(Paths.IMAGE_BOOK_NOT_FOUND, 156, 240);
			
			// Give necessary settings to the book cover.
			bookCover.setIcon(bookImage);
			bookCover.setBounds(10, 10, 156, 240);

			// Give necessary settings to the book information.
			bookInformation.setOpaque(false);
			bookInformation.setLayout(new BoxLayout(bookInformation, BoxLayout.Y_AXIS));
			bookInformation.setBounds(180, 38, 400, 200);

			// Loop through the lines of information that are to be displayed.
			for (String line : informationLines)
			{
				// Initialize the current line's label.
				JLabel currentInformation = new JLabel();

				// Give necessary settings to the current line.
				currentInformation.setText(line);
				currentInformation.setForeground(new Color(0.07f, 0.85f, 0.43f));
				currentInformation.setFont(Resources.getFont(Paths.FONT_REGULAR_BOLD, 26));
				currentInformation.setPreferredSize(new Dimension(400, 40));

				// Add the current line of information to information container.
				bookInformation.add(currentInformation);
			} // end for

			// Give necessary settings to the buttons.
			editQuantityButton.setBounds(590, 82, 170, 45);
			removeButton.setBounds(590, 153, 170, 45);

			// Add the book cover, book information, quantity editing button, and removing button.
			add(bookCover);
			add(bookInformation);
			add(editQuantityButton);
			add(removeButton);

			// Give necessary settings to the containing panel.
			setLayout(null);
			setPreferredSize(new Dimension(800, 260));
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(new Color(0.07f, 0.85f, 0.43f), 10));
		} // end CartPanel(GUI, book)

		/**
		 * Ask the user how much the quantity of an item should be changed.
		 * 
		 * @param gui the GUI object
		 * @param book a Book object
		 */
		private void handleEditCartQuantity(GUI gui, Book book)
		{
			String bookCountRaw;

			// Get the signed in user.
			User user = gui.getActiveUser();

			// Initialize the array of possible selection values.
			String selectionValues[] = new String[Math.min(book.getQuantity(), 10 - (user.getAllCartItemsCount() -
				user.getCartItemCount(book))) + 1];

			// Fill the array of possible selection values.
			for (int i = 0; i < selectionValues.length; i++)
				selectionValues[i] = Integer.toString(i);

			// Get the user's input.
			bookCountRaw = (String) JOptionPane.showInputDialog(
				null, "Enter the new number of this item in cart:", "Edit Item Quantity", JOptionPane.PLAIN_MESSAGE,
				null, selectionValues, 0
			);

			// If the user close the prompt, don't edit the quantity.
			if (bookCountRaw == null)
				return;

			// Edit the cart quantity to what the user chose and makes changes in disk.
			user.editCartItemQuantity(book, Integer.parseInt(bookCountRaw));
			gui.forceUserDataWrite();

			// Reload the current page.
			gui.switchPage(new Pages.Cart(gui));

			// Inform the user that they have updated the quantity.
			JOptionPane.showMessageDialog(
				null, "You have successfully changed the quantity to " + bookCountRaw + ".", "Item Quantity Updated",
				JOptionPane.INFORMATION_MESSAGE
			);
		} // end handleEditCartQuantity(GUI, Book)

		/**
		 * Remove an item from the cart after prompting the user.
		 * 
		 * @param gui the GUI object
		 * @param book a Book object
		 */
		private void handleRemoveCartItem(GUI gui, Book book)
		{
			// Ask the user if the item should be removed from cart.
			int shouldRemove = JOptionPane.showOptionDialog(
				null, "Are you sure you want to remove this book from cart?", "Removing Item", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null
			);

			// Proceed to remove the item if the user clicks yes.
			if (shouldRemove == 0)
			{
				// Remove the book from cart, and write the changes to disk.
				gui.getActiveUser().removeFromCart(book);
				gui.forceUserDataWrite();

				// Refresh the cart page.
				gui.switchPage(new Pages.Cart(gui));

				// Tell the user that they have removed the item from cart.
				JOptionPane.showMessageDialog(
					null, "You have successfully removed the item from your cart.", "Item Removed", JOptionPane.INFORMATION_MESSAGE
				);
			} // end if
		} // end handleRemoveCartItem(GUI, Book)
	} // end class CartPanel
} // end public class