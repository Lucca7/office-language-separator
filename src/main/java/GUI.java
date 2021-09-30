import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;

public class GUI {

	private JFrame frmLanguageSeparator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmLanguageSeparator.setVisible(true);
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLanguageSeparator = new JFrame();
		frmLanguageSeparator.setTitle("Language Separator");
		frmLanguageSeparator.setBounds(100, 100, 600, 400);
		frmLanguageSeparator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLanguageSeparator.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the file type:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 144, 20);
		frmLanguageSeparator.getContentPane().add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton(".docx");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(240, 10, 63, 21);
		frmLanguageSeparator.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(".xlsx");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(359, 10, 103, 21);
		frmLanguageSeparator.getContentPane().add(rdbtnNewRadioButton_1);
	}
}
