import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class PassMain extends JFrame {

	private JPanel contentPane;
	static SecureRandom random;
	static String password;
	private JTextField textField;
	private JButton btnNewButton_1;
	private static JSpinner lengthSpinner;
	private static JSpinner amountLoop;
	private static JTextArea passTextField;
	private static int lengthCount = 16;
	public static int originalHeight = 121;
	public static int appendAmount = 0;
	public static PassMain frame;

	static {
		random = new SecureRandom();
		password = "";
	}

	public static String generatePassword(final int len, final String dic) {
		String result = "";
		for (int i = 0; i < len; ++i) {
			final int index = random.nextInt(dic.length());
			result = String.valueOf(result) + dic.charAt(index);
		}
		return result;
	}


	public static void expandFrame(int expandLength)
	{
		frame.setBounds(100, 100, 362, expandLength);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PassMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PassMain() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 126);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setAlwaysOnTop(true);

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				passTextField.setText("");;
				
				lengthCount = (int) lengthSpinner.getValue();
				
				
				
				int loopAmount = (int) amountLoop.getValue();
				
				if(loopAmount > 1)
				{
					textField.setText("");
					expandFrame(500);
					
					for(int i = 0; i < loopAmount; i++)
					{
//						password = generatePassword(lengthCount, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+=|][}{~?/><,.");
						password = generatePassword(lengthCount, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
						appendAmount++;
						passTextField.append(password);
						
						if(i != (loopAmount - 1))
						{
							passTextField.append("\n");
						}
						
					}
					
					
					
					
				}
				else
				{
//					password = generatePassword(lengthCount, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+=|][}{~?/><,.");
					password = generatePassword(lengthCount, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
					
					textField.setText(password);
				}
				
			}
		});

		btnNewButton_1 = new JButton("Copy");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(appendAmount > 1)
				{
					final String myString = passTextField.getText();
					final StringSelection stringSelection = new StringSelection(myString);
					final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
				else
				{
					final String myString = password;
					final StringSelection stringSelection = new StringSelection(myString);
					final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
				
			}
		});
		
		lengthSpinner = new JSpinner();
		lengthSpinner.setValue(lengthCount);
		lengthSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lengthCount = (int) lengthSpinner.getValue();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Password Length");
		
		amountLoop = new JSpinner();
		amountLoop.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		
		JLabel lblNewLabel_1 = new JLabel("Password Amount");
		
		JScrollPane scrollPane = new JScrollPane();
		
		passTextField = new JTextArea();
		scrollPane.setViewportView(passTextField);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				passTextField.setText("");
				setBounds(100, 100, 362, 125);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lengthSpinner, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(111)
							.addComponent(amountLoop, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(254)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lengthSpinner, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(amountLoop, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(28)
					.addComponent(btnClear)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
