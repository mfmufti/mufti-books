import javax.swing.*;
import java.awt.*;

public class Test
{
	public static void main(String args[])
	{
		JFrame window = new JFrame();
		window.setSize(1000, 533);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
		window.setLayout(null);

		JPanel wrapper = new JPanel();
		wrapper.setBackground(Color.ORANGE);
		wrapper.setLayout(new BorderLayout());
		wrapper.setBounds(0, 0, 1000, 533);

		JPanel lower = new JPanel();
		lower.setPreferredSize(new Dimension(1000, 200));
		lower.setBackground(Color.GREEN);

		String buttonNames[] = {"Button 1", "Button 2", "Button 3"};

		for (String buttonName : buttonNames)
			lower.add(new JButton(buttonName));

		JPanel upper = new JPanel();
		upper.setSize(1000, 200);
		upper.setPreferredSize(new Dimension(1000, 533 - 200));
		upper.setBackground(Color.BLUE);

		wrapper.add(upper, BorderLayout.NORTH);
		wrapper.add(lower, BorderLayout.SOUTH);

		window.getContentPane().removeAll();
		window.add(wrapper);
		window.revalidate();
		window.repaint();
	}
}