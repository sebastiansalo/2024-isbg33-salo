package movies;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	private JTextField genreInput;
	private JTextArea resultOutput;
	private JButton searchButton;

	// initiera UI'n
	public GUI() {
		setTitle("Filmförslag");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(420, 290);
		setMinimumSize(new Dimension(400, 150));

		JPanel inputPanel = new JPanel();
		genreInput = new JTextField(22);
		searchButton = new JButton("Sök");
		inputPanel.add(genreInput);
		inputPanel.add(searchButton);

		resultOutput = new JTextArea();
		resultOutput.setEditable(false);
		resultOutput.setText("Vänligen sök efter en genre.");
		JScrollPane scrollPane = new JScrollPane(resultOutput);

		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
	    
	// hämta och trimma input
	public String getGenreInput() {
		return genreInput.getText().trim();
	}
	    
	// sätt output resultatet
	public void setResultOutput(String text) {
		resultOutput.setText(text);
	}
	   
	// för lyssnaren i controller
	public JButton getSearchButton() {
		return searchButton;
	}
}