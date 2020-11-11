/**
 * Final Project: The Store - Book
 * This class extends Product to create a way to manage books in the store.  Various methods are provided to make tasks easier.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

public class Book extends Product
{
	// Declare the properties needed for a book.
	protected String genre, binding, author, imageName;
	protected int publicationYear;

	/**
	 * This constructor takes no arguments and initializes the properties of the object.
	 */
	public Book()
	{
		// Initialize values.
		genre = binding = author = imageName = "";
		publicationYear = 0;
	} // end Book()

	/**
	 * Set values of the object based on values passed in.
	 * 
	 * @param name title of the book
	 * @param jitTrigger JIT trigger of the book
	 * @param quantity the book's quantity
	 * @param price the price of the book
	 * @param genre the genre of the book
	 * @param binding the binding of the book
	 * @param author the author of the book
	 * @param publicationYear the year of publication
	 * @param imageName the name of the image for preview
	 */
	public Book(String name, int jitTrigger, int quantity, double price, String genre, String binding, String author, int publicationYear,
		String imageName)
	{
		// Call the appropriate parent constructor.
		super(name, price, quantity, jitTrigger);

		// Set values based on what has been passed in.
		this.genre = genre;
		this.binding = binding;
		this.author = author;
		this.publicationYear = publicationYear;
		this.imageName = imageName;
	} // end Book(String, int, int, double, String, String, String, int, String)

	/**
	 * This constructor sets values based on values of the passed array (used for CSV interpretation).
	 * 
	 * @param information
	 */
	public Book(String information[])
	{
		// Call the appropriate parent constructor.
		super(information);

		// Store the values of the array.
		genre = information[5];
		binding = information[6];
		author = information[7];
		publicationYear = Integer.parseInt(information[8]);
		imageName = information[9];
	} // end Book(String[])

	/**
	 * Get the genre.
	 * 
	 * @return the genre
	 */
	public String getGenre()
	{
		// Return the genre.
		return genre;
	} // end getGenre()

	/**
	 * Set the genre.
	 * 
	 * @param genre the new genre
	 */
	public void setGenre(String genre)
	{
		// Set the genre.
		this.genre = genre;
	} // end setGenre(String)

	/**
	 * Get the binding.
	 * 
	 * @return binding
	 */
	public String getBinding()
	{
		// Return the binding.
		return binding;
	} // end getBinding()

	/**
	 * Set the binding.
	 * 
	 * @param binding the new binding
	 */
	public void setBinding(String binding)
	{
		// Set the new binding.
		this.binding = binding;
	} // end setBinding()

	/**
	 * Get the author.
	 * 
	 * @return the author
	 */
	public String getAuthor()
	{
		// Return the author.
		return author;
	} // end getAuthor()

	/**
	 * Set the author.
	 * 
	 * @param author the new author
	 */
	public void setAuthor(String author)
	{
		// Set the new author.
		this.author = author;
	} // end setAuthor(String)

	/**
	 * Get the publication year.
	 * 
	 * @return the publication year
	 */
	public int getPublicationYear()
	{
		// Return the publication year.
		return publicationYear;
	} // end getPublicationYear()

	/**
	 * Set the publication year.
	 * 
	 * @param publicationYear the new publication year
	 */
	public void setPublicationYear(int publicationYear)
	{
		// Set the publication year.
		this.publicationYear = publicationYear;
	} // end setPublicationYear(int)

	/**
	 * Get the image path.
	 * 
	 * @return the image path
	 */
	public String getImagePath()
	{
		// Return the image path, by adding to the folder which contains the images.
		return Paths.IMAGE_BOOK_FOLDER + imageName;
	} // end getImagePath()

	/**
	 * Set the image name.
	 * 
	 * @param imageName the new image name
	 */
	public void setImageName(String imageName)
	{
		// Set the new image name.
		this.imageName = imageName;
	} // end setImageName(String)

	/**
	 * Get the values of this book as an array that can be used to provide initial values to a form.
	 * 
	 * @return an array of values.
	 */
	public String[] getFormList()
	{
		// Return an array of values that can be used as initial values in a form.
		return new String[] {name, Double.toString(price), Integer.toString(quantity), Integer.toString(jitTrigger), genre, binding,
			author, Integer.toString(publicationYear), imageName};
	} // end getFormList()

	/**
	 * Uses the validated output from a form to set values for this book.
	 * 
	 * @param formList this list to be used.
	 */
	public void applyFormList(String formList[])
	{
		// Set values based on the values in the list.
		name = formList[0];
		price = Double.parseDouble(formList[1]);
		quantity = Integer.parseInt(formList[2]);
		jitTrigger = Integer.parseInt(formList[3]);
		genre = formList[4];
		binding = formList[5];
		author = formList[6];
		publicationYear = Integer.parseInt(formList[7]);
		imageName = formList[8];
	} // end applyFormList(String)

	/**
	 * This function returns the book in the form of a string with keys and values.
	 */
	@Override
	public String toString()
	{
		// Return the book in a readable string format.
		return String.format(
			"Book [id=%d, name=%s, price=%.2f, quantity=%d, jitTrigger=%d, genre=%s, binding=%s, author=%s, publicationYear=%d," +
				"imageName=%s]",
			id, name, price, quantity, jitTrigger, genre, binding, author, publicationYear, imageName
		);
	} // end toString()

	/**
	 * Return the book as CSV file entry.
	 */
	@Override
	public String toCSV()
	{
		// Put the properties of this book to make a CSV file entry and return.
		return Integer.toString(id) + "," + name + "," + price + "," + quantity + "," + jitTrigger + "," + genre + "," + binding + "," +
			author + "," + publicationYear + "," + imageName;
	} // end toCSV()
} // end public class
