package math.prime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

public class UI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
					frame.thread = new Thread(() -> frame.doIt());
					frame.thread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JTextArea textArea_1;
	private Thread thread;

	private long num = 3;
	private ArrayList<Long> primes = new ArrayList<>();
	private JTextArea txtrPrimes;
	private long prev = 0;
	private JTextArea textArea;
	
	private void doIt() {
		//primes.add(2l);
		int j = 0;
		outer:
		for(; num < Long.MAX_VALUE; num += 2) {
			if(Thread.interrupted()) {
				System.out.println("interrupted");
				num -= 2;
				return;
			}
			synchronized(primes) {
				for(Long l : primes)
					if(num % l == 0)
						continue outer;
					else if(l * l > num)
						break;
				primes.add(num);
			}
			if(j++ == 50000) {
				j = 0;
				System.out.printf("i : %s (%s%%)%n", num, (double) num / Long.MAX_VALUE * 100);
			}
		}
	}
	
	private void refresh() {
		//synchronized(primes) {
			//for(long i = prev; i < primes.size(); i++)
				//txtrPrimes.append(i + "\n");
			//prev = primes.size();
		{	textArea_1.setText(""+num);
			textArea.setText((double) num / Long.MAX_VALUE * 100 + "%");
		}
	}
	
	private static class Info implements Serializable {
		
		private static final long serialVersionUID = -1492298705952915911L;
		public long number;
		public ArrayList<Long> primes;

		
		public Info(long number, ArrayList<Long> orimes) {
			this.number = number;
			this.primes = orimes;
		}
		
		public void saveToFile(String path) throws FileNotFoundException, IOException {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
			synchronized(primes) {
				stream.writeObject(this);
				stream.flush();
				stream.close();
			}
		}
		
		public static Info loadFromFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));
			Object o = stream.readObject();
			stream.close();
			return (Info) o;
		}
	}
	/**
	 * Create the frame.
	 */
	public UI() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		textArea_1 = new JTextArea();
		panel.add(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		panel.add(textArea);
		//panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{pnlPrimes, pnlNumber, pnlIteration}));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		panel_4.add(btnRefresh);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Info info = new Info(num, primes);
				try {
					info.saveToFile("D:\\Docs\\primes\\info.bin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		panel_4.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					thread.interrupt();
					thread.join();
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				
				try {
					Info info = Info.loadFromFile("D:\\Docs\\primes\\info.bin");
					num = info.number;
					primes = info.primes;
					thread = new Thread(() -> doIt());
					thread.start();
					refresh();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_4.add(btnLoad);
		
		JPanel pnlPrimes = new JPanel();
		contentPane.add(pnlPrimes, BorderLayout.CENTER);
		pnlPrimes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Prime factors found", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlPrimes.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlPrimes.add(scrollPane);
		
		txtrPrimes = new JTextArea();
		txtrPrimes.setLineWrap(true);
		txtrPrimes.setRows(100);
		txtrPrimes.setColumns(100);
		txtrPrimes.setEditable(false);
		txtrPrimes.setText("help");
		scrollPane.add(txtrPrimes);
	}

}
