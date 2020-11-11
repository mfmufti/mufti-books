/**
 * Final Project: The Store - Main Frame
 * This class controls the frame in which the graphical interface is displayed.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Prevent serialization warnings.
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	/**
	 * This constructor enforces necessary settings for the graphical frame.
	 * 
	 * @param gui the GUI object
	 */
	MainFrame(GUI gui)
	{
		// Set necessary settings for the graphical frame.
		getContentPane().setPreferredSize(new Dimension(Store.WINDOW_WIDTH, Store.WINDOW_HEIGHT));
		pack();
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("MuftiBooks - The Book Store of Today!");
		setLocationRelativeTo(null);
		setIconImage(Resources.getImage(Paths.IMAGE_PROGRAM_ICON).getImage());

		// Make the program prompt the user when they try to close the window.
		addWindowListener(new WindowAdapter() {
			/**
			 * This function runs when the window is about to close.
			 * 
			 * @param event the event object, containing information about the event
			 */
			@Override
			public void windowClosing(WindowEvent event)
			{
				// Ask if the program should be closed.
				gui.askExitProgram();
			} // end windowClosing(WindowEvent)
		});
	} // end MainFrame(GUI)

	/**
	 * Switch to a new "page" (JComponent).
	 * 
	 * @param newPage the page to switch to
	 */
	public void switchPage(JComponent newPage)
	{
		// Remove all the current components, and add the new component.
		getContentPane().removeAll();
		add(newPage);

		// Revalidate and repaint (if these lines are not used, there may be errors in displaying).
		revalidate();
		repaint();
	} // end switchPage(JComponent)
} // end public class