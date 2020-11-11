/**
 * Final Project: The Store - Form Data
 * This class provides a way to store information about a form.  The various fields, the error messages, the button and title texts, as
 * well as the background image are all stored in objects of this class.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

public class FormData
{
	// Make an interface so that lambdas may be used when trying to submit.
	@FunctionalInterface
	public static interface SubmitAction
	{
		// The lambda will take a GUI object and String object and return nothing.
		void submitAction(GUI gui, String fixedInputs[]);
	} // end interface

	// Make an interface so that lambdas may be used when trying to exit the form.
	@FunctionalInterface
	public static interface ExitFormAction
	{
		// The lambda will take a GUI object and return nothing.
		void exitForm(GUI gui);
	} // end interface

	// Declare variables for the fields, background image, button and title texts, and submit and exit actions.
	private ValidationField fields[];
	private String backgroundImage, submitButtonText, exitButtonText, titleText;
	private SubmitAction onSubmit;
	private ExitFormAction onExit;

	/**
	 * This constructor stores all the given parameters.
	 * 
	 * @param fields the fields the form should contain
	 * @param backgroundImage the background image path
	 * @param submitButtonText the text for the submit button
	 * @param exitButtonText the text for the exit button
	 * @param titleText the text for the title
	 * @param onSubmit the action to perform when the submit button is clicked
	 * @param onExit the action to perform when the exit button is clicked
	 */
	public FormData(ValidationField fields[], String backgroundImage, String submitButtonText, String exitButtonText, String titleText,
		SubmitAction onSubmit, ExitFormAction onExit)
	{
		// Store all the given values.
		this.fields = fields;
		this.backgroundImage = backgroundImage;
		this.submitButtonText = submitButtonText;
		this.exitButtonText = exitButtonText;
		this.titleText = titleText;
		this.onSubmit = onSubmit;
		this.onExit = onExit;
	} // end FormData(ValidationField[], String, String, String, String, SubmitAction, ExitFormAction)

	/**
	 * Get the stored fields.
	 * 
	 * @return the stored fields
	 */
	public ValidationField[] getFields()
	{
		// Return the stored fields.
		return fields;
	} // end getFields()

	/**
	 * Get the background image.
	 * 
	 * @return the background image
	 */
	public String getBackgroundImage()
	{
		// Return the stored background image.
		return backgroundImage;
	} // end getBackgroundImage()

	/**
	 * Return whether the background image is the dark one.
	 * 
	 * @return whether the background image is the dark one
	 */
	public boolean hasDarkBackground()
	{
		// Return whether the background image is the dark one.
		return backgroundImage == Paths.IMAGE_MAIN_BACKGROUND;
	} // end hasDarkBackground()

	/**
	 * Get the submit button text.
	 * 
	 * @return the submit button text
	 */
	public String getSubmitButtonText()
	{
		// Return the submit button text.
		return submitButtonText;
	} // end getSubmitButtonText()

	/**
	 * Get the exit button text.
	 * 
	 * @return the exit button text
	 */
	public String getExitButtonText()
	{
		// Return the exit button text.
		return exitButtonText;
	} // end getExitButtonText()

	/**
	 * Get the title text.
	 * 
	 * @return the title text
	 */
	public String getTitleText()
	{
		// Return the title text.
		return titleText;
	} // end getTitleText()

	/**
	 * Invoke the submit action.
	 * 
	 * @param gui the GUI object
	 * @param inputs the values of the input fields
	 */
	public void submit(GUI gui, String inputs[])
	{
		String fixedInputs[] = new String[inputs.length];
		String fixedInput;
		boolean allInputsValid = true;

		// Loop through the inputs, checking if each is valid.
		for (int i = 0; i < inputs.length; i++)
		{
			// Store the fixed input by passing through the validator.
			fixedInput = fields[i].validate(gui, inputs[i]);

			// Check whether the input was valid.
			if (fixedInput == null)
			{
				// If the input was valid, stop checking the inputs and store that not all inputs are valid.
				allInputsValid = false;
				break;
			} // end if
			else
			{
				// If the input is valid, store the adjusted input.
				fixedInputs[i] = fixedInput;
			} // end else
		} // end for

		if (allInputsValid)
			this.onSubmit.submitAction(gui, fixedInputs);
	} // end submit(GUI, String[])

	/**
	 * Invoke the exit form action.
	 * 
	 * @param gui the GUI object
	 */
	public void exit(GUI gui)
	{
		// Invoke the exit action.
		onExit.exitForm(gui);
	} // end exit(GUI)
} // end public class