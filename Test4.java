import java.util.LinkedHashMap;

public class Test4
{
	public static void main(String args[])
	{
		// System.out.println("somethingA".matches(".*(?=.*[a-z].*)(?=(.*[A-Z].*))(?=(.*[0-9].*)).*"));

		LinkedHashMap<String, Integer> myMap = new LinkedHashMap<String, Integer>();

		myMap.put("a", 1);
		myMap.put("b", 2);
		myMap.put("c", 3);

		for (int i = 0; i < 3; i++)
			myMap.merge("a", -1, Integer::sum);

		System.out.println(myMap.get("a"));
	}
}
