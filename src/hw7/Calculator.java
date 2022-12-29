/**
 * this class is a basic calculator with all basic functionalities
 * @author Sharon Le
 * @since 11/28/22
 * @version 1.0
 * @source https://www.geeksforgeeks.org/java-swing-simple-calculator/
 * @source2 https://www.section.io/engineering-education/how-to-build-a-java-gui-calculator-from-scratch-using-box-layout/
 * @source3 https://www.tutorialspoint.com/Create-a-simple-calculator-using-Java-Swing
 */
package hw7;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
public class Calculator extends Frame implements ActionListener
{
	private ArrayList<Color> colour;
	private TextField outputField;
	private ArrayList<Button> digits;
	private JButton plusSign;
	private JButton minusSign;
	private JButton multSign;
	private JButton divSign;
	private JButton jEQ;
	private String currentOperation;
	private double firstOp;
	private JPanel joperatorBut;
	private JMenuItem plusKey;
	private JMenuItem minusKey;
	private JMenuItem multKey;
	private JMenuItem divKey;
	private JMenuItem trash;
	public Calculator()
	{
		this.currentOperation = "";
		this.firstOp = 0.0;
		
		Panel displayPanel = new Panel(new FlowLayout());
		outputField = new TextField("0", 20);
		displayPanel.add(outputField);
		
		Panel buttonPanel = new Panel(new GridLayout(1,1));
		//I got lazy so now its called this instead of digitButtonPanel
		Panel digitBut = new Panel(new GridLayout(4,3));
		digits = new ArrayList<Button>();
		digits.add(new Button("0"));
		digits.add(new Button("1"));
		digits.add(new Button("2"));
		digits.add(new Button("3"));
		digits.add(new Button("4"));
		digits.add(new Button("5"));
		digits.add(new Button("6"));
		digits.add(new Button("7"));
		digits.add(new Button("8"));
		digits.add(new Button("9"));
		digits.add(new Button("."));
		digits.add(new Button("AC"));
		//COLORS （＾∀＾●）ﾉｼ
		setBackground(Color.GRAY);
		digits.get(10).setForeground(Color.BLUE);
		digits.get(11).setForeground(Color.RED);
		for (int x = 0; x <12; x++) {digitBut.add(digits.get(x));}
		for (int x = 0; x <12; x++) {digits.get(x).addActionListener(this);}
		
		buttonPanel.add(digitBut); // all done for digits!!!
		//images
		jEQ = new JButton(new ImageIcon("data/icons8-equal-sign-80.png"));
		plusSign = new JButton(new ImageIcon("data/icons8-add-new-80.png"));
		minusSign = new JButton(new ImageIcon("data/icons8-subtract-80.png"));
		multSign = new JButton(new ImageIcon("data/icons8-multiplication-80.png"));
		divSign = new JButton(new ImageIcon("data/icons8-division-80.png"));
		joperatorBut = new JPanel(new GridLayout(5,1));
		
		joperatorBut.add(plusSign);
		joperatorBut.add(minusSign);
		joperatorBut.add(multSign);
		joperatorBut.add(divSign);
		joperatorBut.add(jEQ);
		buttonPanel.add(joperatorBut);
		displayPanel.add(buttonPanel);
		add(displayPanel);
		//adding functions to panel
		jEQ.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Double outputResult = 0.0;
				String current = outputField.getText();
				try {
					Double secondOp = new Double(current);
						if (currentOperation == "+")
						{
							outputResult = firstOp + secondOp;
						}
						else if (currentOperation == "-")
						{
							outputResult = firstOp - secondOp;
						}
						else if (currentOperation == "*")
						{
							outputResult = firstOp * secondOp;
						}
						else if (currentOperation == "/")
						{
							if (secondOp != 0) {outputResult = firstOp / secondOp;}
						}
					outputField.setText(outputResult.toString());
					firstOp = outputResult;
				} catch (NumberFormatException ev) {resetValues();}
			}
		});
		
		digits.get(11).addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent event)
				{
					resetValues();
				}
			});
		digits.get(10).addActionListener(new ActionListener()
			{
				@Override //add a decimal point
				public void actionPerformed(ActionEvent e) {
					String current = outputField.getText();
					if(current.indexOf(".")<0) { outputField.setText(current+".");}
				}
			});
		
		OperatorListener opListener = new OperatorListener();
		plusSign.addActionListener(opListener);
		minusSign.addActionListener(opListener);
		divSign.addActionListener(opListener);
		multSign.addActionListener(opListener);
		
		//making a menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Operations");
		menuBar.add(menu);
		
		
		plusKey = new JMenuItem(new ImageIcon("data/icons8-add-new-80.png"));
		minusKey = new JMenuItem(new ImageIcon("data/icons8-subtract-80.png"));
		multKey = new JMenuItem(new ImageIcon("data/icons8-multiplication-80.png"));
		divKey = new JMenuItem(new ImageIcon("data/icons8-division-80.png"));
		trash = new JMenuItem(new ImageIcon("data/icons8-trash-bin-80.png"));
		
		menu.add(plusKey);
		menu.add(minusKey);
		menu.add(multKey);
		menu.add(divKey);
		menu.add(trash);
		OperatorListener Menux = new OperatorListener();
		plusKey.addActionListener(Menux);
		minusKey.addActionListener(Menux);
		multKey.addActionListener(Menux);
		divKey.addActionListener(Menux);
		trash.addActionListener(Menux);
		
		
		buttonPanel.add(menuBar);
				
		setTitle("Calculator");
		setSize(400,550);
		setVisible(true);
		//EXIT （︶^︶）
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event) {System.exit(0);}
		});
	}
	/**
	 * this method reset all operations to zero
	 */
	private void resetValues()
	{
		currentOperation = "";
		firstOp = 0.0;
		outputField.setText("0");
		
	}
	/**
	 * tester main
	 * @param args
	 */
	public static void main(String [] args)
	{
		
		Calculator m = new Calculator();
		
	}
	/**
	 * this mini class listens for operations buttons that has been clicked and upload it
	 * to the current operation thats running.
	 */
	private class OperatorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource() == plusSign || event.getSource() == plusKey)
					currentOperation = "+";
			else if (event.getSource() == minusSign || event.getSource() == minusKey)
					currentOperation = "-";
			else if (event.getSource() == multSign || event.getSource() == multKey)
					currentOperation = "*";
			else if (event.getSource() == divSign || event.getSource() == divKey)
					currentOperation = "/";
			else if (event.getSource() == digits.get(11))
					resetValues();
			String current = outputField.getText();
			try {
					Double currentTextDouble = new Double(current);
					firstOp = currentTextDouble;
					outputField.setText("0");
			} catch (NumberFormatException e) { resetValues();}
		}
	}
	/**
	 * this method listens for any new digits that has been inputted
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String currentText = outputField.getText();
		String num = "";
		for (int x = 0; x < digits.size(); x++) {
			if (e.getSource() == digits.get(x))
			{
				num = "" + x;
				
			}
		}
		currentText = currentText + num;
		currentText = currentText.replaceFirst("^0+(?!$)","");
		outputField.setText(currentText);
		
		
	}
	
}


