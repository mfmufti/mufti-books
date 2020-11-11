/**
 * Final Project: The Store - Paths
 * This file lists a number of constants for the paths to files and folders the program needs to function.
 * 
 * @author Musab Mufti (44252muf)
 * @date 11 November 2020
 */

public class Paths
{
	// Store paths related to base folders.
	public static final String BASE_FOLDER = "./resources/";
	public static final String IMAGE_BASE_FOLDER = BASE_FOLDER + "img/";
	public static final String DATA_BASE_FOLDER = BASE_FOLDER + "dat/";
	public static final String FONT_BASE_FOLDER = BASE_FOLDER + "ttf/";

	// Store paths related to the images.
	public static final String IMAGE_BACKGROUND_FOLDER = IMAGE_BASE_FOLDER + "backgrounds/";
	public static final String IMAGE_BOOK_FOLDER = IMAGE_BASE_FOLDER + "books/";
	public static final String IMAGE_ICON_FOLDER = IMAGE_BASE_FOLDER + "icons/";
	public static final String IMAGE_PROGRAM_ICON = IMAGE_ICON_FOLDER + "favicon.png";	
	public static final String IMAGE_STORE_LOGO = IMAGE_ICON_FOLDER + "logo.png";
	public static final String IMAGE_MAIN_BACKGROUND = IMAGE_BACKGROUND_FOLDER + "PolygonBackground1.png";
	public static final String IMAGE_SECOND_BACKGROUND = IMAGE_BACKGROUND_FOLDER + "PolygonBackground2.png";
	public static final String IMAGE_BOOK_NOT_FOUND = IMAGE_BOOK_FOLDER + "NotFound.jpg";
	
	// Store paths related to fonts
	public static final String FONT_REGULAR = FONT_BASE_FOLDER + "OpenSans-Bold.ttf";
	public static final String FONT_REGULAR_BOLD = FONT_BASE_FOLDER + "OpenSans-ExtraBold.ttf";
	public static final String FONT_MAIN_TITLE = FONT_BASE_FOLDER + "Anton-Regular.ttf";
	public static final String FONT_SECOND_TITLE = FONT_BASE_FOLDER + "Exo2-Bold.ttf";
	public static final String FONT_RECEIPT = FONT_BASE_FOLDER + "NanumGothicCoding-Bold.ttf";

	public static final String DATA_USER_FILE = DATA_BASE_FOLDER + "users.csv";
	public static final String DATA_PRODUCT_FILE = DATA_BASE_FOLDER + "books.csv";
	public static final String DATA_ADMIN_FILE = DATA_BASE_FOLDER + "adminPassword.txt";
} // end public class