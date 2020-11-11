/**
 * Final Project: The Store - Base Page
 * This class is the base class for all other pages.  This class provides means necessary for setting the background and controlling
 * the content.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.*;
import java.awt.*;

// Prevent serialization warnings.
@SuppressWarnings("serial")
public class BasePage extends JLayeredPane
{
	// Declare variables for the content, background image, whether a background was added, and the gui object.
	protected JPanel content;
	protected JLabel backgroundImage;
	protected boolean backgroundAdded;
	protected GUI gui;
	
	/**
	 * This constructor initializes content and background values for base classes.
	 * 
	 * @param gui the GUI object
	 */
	public BasePage(GUI gui)
	{
		// Store the gui object and store no backgrounds have been added.
		this.gui = gui;
		backgroundAdded = false;

		// Give appropriate settings to this panel.
		setLayout(null);
		setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT));
		setVisible(true);

		// Make the content panel and give appropriate settings.
		content = new JPanel();
		content.setBounds(0, 0, Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT);
		content.setOpaque(false);
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// Add the content panel.
		add(content, JLayeredPane.PALETTE_LAYER);
	} // end BasePage(GUI)
	
	/**
	 * Set the background image given a path.
	 * 
	 * @param backgroundPath the background image to set
	 */
	public void setBackgroundImage(String backgroundPath)
	{
		// If a background image has already been added, remove the previous image.
		if (backgroundAdded)
			remove(backgroundImage);
		else
			backgroundAdded = true;

		// Add the new background image.
		backgroundImage = new Components.BackgroundImage(backgroundPath);
		add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
	} // end setBackgroundImage(String)

	/**
	 * Switch to a new page.
	 * 
	 * @param newPage the new page
	 */
	public void switchPage(BasePage newPage)
	{
		// Redirect the call to the "switchPage" on a GUI object.
		gui.switchPage(newPage);
	} // end switchPage(BasePage)
} // end public class