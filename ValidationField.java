/**
 * Final Project: The Store - Validation Field
 * This class stores the information needed for a field in a form.  The information includes the label, error message if the response is
 * invalid, whether the field is for a password, and validators to validate the field.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

import javax.swing.JOptionPane;

public class ValidationField
{
	// Declare variables for the label, error message, validators, and whether the field is for a password.
	private String label, error;
	private Validator validators[];
	private boolean isPassword;

	// Make an interface to allow validators to be passed in as lambdas.
	@FunctionalInterface
	public static interface Validator
	{
		// The lambda will take a GUI object, and the input.
		String validate(GUI gui, String input);
	} // end interface

	/**
	 * This constructor stores the information given to it.
	 * 
	 * @param label the label of the field
	 * @param error the error message shown if the input is invalid
	 * @param isPassword whether the field is for a password
	 * @param validators the validators
	 */
	ValidationField(String label, String error, boolean isPassword, Validator... validators)
	{
		// Store the given information.
		this.label = label;
		this.error = error;
		this.validators = validators;
		this.isPassword = isPassword;
	} // end ValidationField(String, String, boolean, Validator...)

	/**
	 * This constructor overloads the other to allow the "isPassword" field to be omitted.
	 * 
	 * @param label the label of the field
	 * @param error the error message shown if the input is invalid
	 * @param validators the validators
	 */
	ValidationField(String label, String error, Validator... validators)
	{
		// Redirect the call to the other constructor, with "isPassword" as false.
		this(label, error, false, validators);
	} // end ValidationField(String, String, Validator...)

	/**
	 * Validate the field given the input.
	 * 
	 * @param gui the GUI object
	 * @param input the input from the input field
	 * @return a string representing the validated input (null if invalid)
	 */
	public String validate(GUI gui, String input)
	{
		// Iterate through the stored validators, validating the input with each.
		for (Validator validator : validators)
		{
			// Get the validated input.
			input = validator.validate(gui, input);

			if (input == null)
			{
				// If the input is invalid, show the user, using the appropriate error message, and stop iterating.
				JOptionPane.showMessageDialog(null, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
				break;
			} // end if
		} // end for

		return input;
	} // end validate(GUI, String)

	/**
	 * Get the label of the field.
	 * 
	 * @return the label of the field
	 */
	public String getLabel()
	{
		// Return the label of this field.
		return label;
	} // end getLabel()

	/**
	 * Get whether this field is for a password.
	 * 
	 * @return whether this field is for a password
	 */
	public boolean isPasswordField()
	{
		// Return whether this field is for a password.
		return this.isPassword;
	} // end isPasswordField()
} // end public class