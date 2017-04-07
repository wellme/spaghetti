package main.fs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

public class Frame extends JFrame {

	private JPanel mainPanel;
	private JPanel mapPanel;
	private JSeparator separator;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JTextField textField;
	
	private String path = "C:\\Users\\Escoto\\Desktop\\soundtrack";
	private Iterator<Path> directories;
	private Iterator<Path> files;
	private Stuff current;
	private JButton btnSkip;
	
	public Frame() throws IOException {

		directories = Files.list(new File(path).toPath()).iterator();
		directories.forEachRemaining(x -> System.out.println(x));
		directories = Files.list(new File(path).toPath()).iterator();
		files = Files.list(directories.next()).iterator();
		current = next();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		getContentPane().setLayout(new BorderLayout(0, 0));

		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mapPanel = new JPanel();
		mainPanel.add(mapPanel);
		mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("#" + current.number);
		mapPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("FE" + current.fe);
		mapPanel.add(lblNewLabel_1);
		
		label = new JLabel(": " + current.name);
		mapPanel.add(label);


		separator = new JSeparator();
		mainPanel.add(separator);


		panel = new JPanel();
		mainPanel.add(panel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Path destination = new File(String.format("%s\\%02d-%02d-00-%s",
						current.origin.getParent(), Integer.parseInt(current.fe),
						Integer.parseInt(textField.getText()), current.name)).toPath();
				try {
					Files.move(current.origin, destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
				current = next();
				lblNewLabel.setText("#" + current.number);
				lblNewLabel_1.setText("FE" + current.fe);
				label.setText(": " + current.name);
			}
		});
		panel.add(textField);
		textField.setColumns(10);
		
		btnSkip = new JButton("Skip");
		btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				current = next();
				lblNewLabel.setText("#" + current.number);
				lblNewLabel_1.setText("FE" + current.fe);
				label.setText(": " + current.name);
			}
		});
		panel.add(btnSkip);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private Stuff next() {
		if(!files.hasNext()) {
			if(!directories.hasNext())
				return new Stuff(null, "00", "Done!", "00");
			Path path = null;
			while(directories.hasNext() && (path = directories.next()).toFile().isDirectory());
			if(path == null) {
				System.out.println("Cave Johnson, we're done here");
				System.exit(0);
			}
			try {
				files = Files.list(path).iterator();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return next();
		}
		
		Stuff temp = new Stuff();
		temp.origin = files.next();
		System.out.println(temp.origin);
		temp.fe = temp.origin.getParent().toString().replaceAll(".*\\\\", "").replaceAll("fe", "");
		temp.number = temp.origin.toString().replaceAll(".*\\\\", "").replaceAll(" .+", "");
		temp.name = temp.origin.toString().replaceAll(".*\\\\", "").replaceAll("\\d+ ", "");
		
		return temp;
	}

	
	private class Stuff {
		
		public Path origin;
		public String number;
		public String name;
		public String fe;
		
		public Stuff() { }
		
		public Stuff(Path origin, String number, String name, String fe) {
			this.origin = origin;
			this.number = number;
			this.name = name;
			this.fe = fe;
		}
	}
}

