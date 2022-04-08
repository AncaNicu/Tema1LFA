import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//Main implementeaza ActionListener
//pt a putea realiza actiuni atunci cand este apasat butonul
public class Main implements ActionListener{

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel patternLabel;
	private static JTextField patternText;
	private static JLabel textLabel;
	private static JTextField textText;
	private static JButton button;
	private static JLabel success;
	
	
	public static void main(String[] args) {
		
		
		frame = new JFrame();
		//seteaza dimens ferestrei
		frame.setSize(550, 300);
		//pt ca fereastra sa se inchida cand se apasa pe X
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pt a face vizibila fereastra
		frame.setVisible(true);
		
		//panel-ul este portiunea din fereastra unde se va pune continurul ferestrei
		panel = new JPanel();
		
		//adaugam panel-ul la fereastra
		frame.add(panel);
		
		panel.setLayout(null);
		
		//setam eticheta pt pattern
		patternLabel = new JLabel("Pattern");
		
		//setam limitele pt eticheta
		patternLabel.setBounds(10, 20, 80, 25);
		
		//adaugam eticheta la panel
		panel.add(patternLabel);
		
		//pt caseta unde se introduce pattern-ul
		//textul va avea maxim 20 de caract
		patternText = new JTextField(20);
		//se adauga limite pt caseta
		patternText.setBounds(100, 20, 165, 25);
		//adaugam caseta la panel
		panel.add(patternText);
		
		//eticheta pt text
		textLabel = new JLabel("Text");
		//setam limitele pt eticheta (x, y, lungime, latime)
		textLabel.setBounds(10, 50, 80, 25);
		//adaugam eticheta la panel
		panel.add(textLabel);
		
		//pt caseta text asociata textului
		textText = new JTextField(100);
		textText.setBounds(100, 50, 165, 25);
		panel.add(textText);
		
		//butonul care declanseaza actiunea de cautare a potrivirilor
		button = new JButton("Match");
		button.setBounds(10, 80, 80, 25);
		panel.add(button);
		
		
		//eticheta pt rezultat
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		
		button.addActionListener(new Main());
	}
	
	//actiunea realizata cand se apasa pe buton
		@Override
		public void actionPerformed(ActionEvent e) {
			String pattern = patternText.getText();
			String text = textText.getText();
			
			//daca pattern-ul introdus are erori
			if(checkPattern(pattern) == false)
			{
				success.setText("The pattern is not valid");
			}
			else
			{
				//daca avem cel putin o potrivire
				if(matches(pattern, text) == true)
				{
					success.setText("The text matches the pattern");
				}
				else
				{
					success.setText("No matches");
				}
			}
		}
		
		
		//pt a verifica daca pattern-ul este corect
		public static boolean checkPattern(String regex)
		{
			try {
				Pattern.compile(regex);
			}catch(PatternSyntaxException e)
			{
				return false;
			}
			return true;
		}
	
		//verif daca textul respecta un pattern
	public static boolean matches(String regex, String text)
	{
		boolean matches = false;
		//creeaza pattern-ul pe baza lui regex folosind met compile
		Pattern pattern = Pattern.compile(regex);
		//creeaza un matcher care va potrivi textul cu pattern-ul
		Matcher matcher = pattern.matcher(text);
		//verifica daca textul respecta pattern-ul
		if(matcher.matches() == true)
		{
			matches = true;
		}
		return matches;
	}

}
