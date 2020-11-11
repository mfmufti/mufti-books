/**
 * Final Project: The Store - Product
 * This class is the base class.  Book is the child class.  This class sets only a few of the attributes, that would commonly be 
 * associated with almost any product, and Book extends this class.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

public class Product
{
	// Store the properties needed for a product.
	protected static int maxId = -1;
	protected String name;
	protected int jitTrigger, quantity, id;
	protected double price;

	/**
	 * This constructor takes no arguments and initializes the properties of the object.
	 */
	public Product()
	{
		// Initialize properties of the object. Set a unique id.
		name = "";
		jitTrigger = quantity = 0;
		price = 0d;
		id = ++maxId;
	} // end Product()

	/**
	 * This constructor initializes values based on what is passed.
	 * 
	 * @param name the name of the product
	 * @param price the price of the product
	 * @param quantity the quantity
	 * @param jitTrigger the JIT trigger
	 */
	public Product(String name, double price, int quantity, int jitTrigger)
	{
		// Set the values according to what has been passed in.  Set a unique id.
		this.id = ++maxId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.jitTrigger = jitTrigger;
	} // end Product(String, double, int, int)

	/**
	 * This constructor initializes values of the object based on values of an array (used for CSV interpretation).
	 * 
	 * @param information
	 */
	public Product(String information[])
	{
		// Initialize values based on values in the array.
		id = Integer.parseInt(information[0]);
		name = information[1];
		price = Double.parseDouble(information[2]);
		quantity = Integer.parseInt(information[3]);
		jitTrigger = Integer.parseInt(information[4]);

		// Adjust the max id.
		maxId = Math.max(maxId, id);
	} // end Product(String[])

	/**
	 * Get the id.
	 * 
	 * @return the id
	 */
	public int getId()
	{
		// Return the id.
		return id;
	} // end getId()

	/**
	 * Get the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		// Return the name.
		return name;
	} // end getName()


	/**
	 * Set the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		// Set the name.
		this.name = name;
	} // end setName(String)

	/**
	 * Get the JIT trigger.
	 * 
	 * @return the JIT trigger
	 */
	public int getJitTrigger()
	{
		// Return the JIT trigger.
		return jitTrigger;
	} // end getJitTrigger()

	/**
	 * Set the JIT trigger.
	 * 
	 * @param jitTrigger the new JIT trigger
	 */
	public void setJitTrigger(int jitTrigger)
	{
		// Set the new JIT trigger.
		this.jitTrigger = jitTrigger;
	} // end setJitTrigger(int)

	/**
	 * Get the quantity.
	 * 
	 * @return the quantity
	 */
	public int getQuantity()
	{
		// Return the quantity.
		return quantity;
	} // end getQuantity()

	/**
	 * Set the quantity.
	 * 
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity)
	{
		// Set the quantity.
		this.quantity = quantity;
	} // end setQuantity(int)

	/**
	 * Get the price.
	 * 
	 * @return the price
	 */
	public double getPrice()
	{
		// Return the price.
		return price;
	} // end getPrice()

	/**
	 * Set the price.
	 * 
	 * @param price the price
	 */
	public void setPrice(double price)
	{
		// Set the price.
		this.price = price;
	} // end setPrice(double)

	/**
	 * Return the product in a string format.
	 */
	@Override
	public String toString()
	{
		// Return the product in a string format with keys and values.
		return String.format("Product [id=%d, name=%s, price=%.2f, quantity=%d, jitTrigger=%d]", id, name, price, quantity, jitTrigger);
	} // end toString();

	/**
	 * Return the product in a CSV format.
	 * 
	 * @return the product in a CSV format
	 */
	public String toCSV()
	{
		// Return the product in a CSV format.
		return Integer.toString(id) + "," + name + "," + price + "," + quantity + "," + jitTrigger;
	} // end toCSV()
} // end public class