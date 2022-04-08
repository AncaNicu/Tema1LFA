import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	
	private static JTextField matchesText;
	
	public static void buildGUI()
	{
		frame = new JFrame();
		//seteaza dimens ferestrei
		frame.setSize(600, 300);
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
		patternText.setBounds(100, 20, 410, 25);
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
		textText.setBounds(100, 50, 410, 25);
		panel.add(textText);
		
		//butonul pe care il vom apasa cand vrem sa cautam pattern-ul
		button = new JButton("Match");
		button.setBounds(10, 80, 80, 25);
		panel.add(button);
		
		
		//eticheta pt rezultatul cautarii
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		//pt caseta unde se introduce pattern-ul
				//textul va avea maxim 20 de caract
		matchesText = new JTextField(100);
				//se adauga limite pt caseta
		matchesText.setBounds(10, 150, 500, 25);
				//adaugam caseta la panel
				panel.add(matchesText);
		
		
		//pt a realiza o actiune la apasarea butonului
		button.addActionListener(new Main());
		
		
	}
	
	public static void main(String[] args) {
		buildGUI();
		
	}
	
	//actiunea realizata cand se apasa pe buton
	@Override
	public void actionPerformed(ActionEvent e) {
		String pattern = patternText.getText();
		String text = textText.getText();
		
		//daca pattern-il introdus are erori
		if(checkPattern(pattern) == false)
		{
			success.setText("The pattern is not valid");
			
			matchesText.setText("");
		}
		else
		{
			//daca avem cel putin o potrivire
			if(matchesNr(pattern, text) > 0)
			{
				//se scriu potrivirile in caseta-text rezultat
				addMatchesToPanel(pattern, text);
			}
			else
			{
				success.setText("No result");
				matchesText.setText("");
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
	
	//pt a vedea cate potriviri s-au gasit
	public static int matchesNr(String regex, String text)
	{
		int matches = 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()==true)
		{
			matches++;
		}
		return matches;
	}
	
	//pt a adauga potrivirile pe panel
	public static void addMatchesToPanel(String regex, String text)
	{
		int i = 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		String match = "";//string-ul care va retine potrivirile
		String space = " ";
		while(matcher.find() == true)
		{
			//daca avem de-a face cu prima potrivire intalnita
			if(i == 0)
			{
				//group() returneaza subsecventa potrivita cu pattern-ul de matcher
				match = matcher.group();
			}
			else
			{
				match = match + space + matcher.group();
			}
			i++;
		}
		//setam textul etichetei success ca fiind match
		success.setText("Matches: ");
		matchesText.setText(match);
	}
	
	
}
