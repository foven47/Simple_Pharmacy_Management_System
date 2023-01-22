package pharmecy.ms;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    JFrame frame = new JFrame();
    JButton myButton = new JButton("Login");
    JTextField pharmacyIdTf = new JTextField();
    JPasswordField passwordTf = new JPasswordField();
    JComboBox<String> staffTypeCombo = new JComboBox<String>(new String[]{"Staff", "Admin"});

    Login() {

        // Create labels
        JLabel pharmacyIdLbl = new JLabel("Pharmacy ID:");
        JLabel passwordLbl = new JLabel("Password:");
        JLabel staffTypeLbl = new JLabel("User:");
        JLabel titleLbl = new JLabel("PHARMACY MANAGEMENT");
        JLabel titleLbl2 = new JLabel("SYSTEM");


        frame.setBackground(new Color(242,249,241));

        // Set bounds and add labels to the frame
        pharmacyIdLbl.setBounds(50, 100, 100, 25);
        frame.add(pharmacyIdLbl);
        passwordLbl.setBounds(50, 150, 100, 25);
        frame.add(passwordLbl);
        staffTypeLbl.setBounds(50, 200, 100, 25);
        frame.add(staffTypeLbl);
        titleLbl.setBounds(90, 30, 700, 25);
        frame.add(titleLbl);
        titleLbl2.setBounds(170, 55, 700, 25);
        frame.add(titleLbl2);

        titleLbl.setFont(new Font("Arial", Font.BOLD, 18));
        titleLbl2.setFont(new Font("Arial", Font.BOLD, 18));


        // Set bounds and add text fields to the frame
        pharmacyIdTf.setBounds(150, 100, 200, 25);
        frame.add(pharmacyIdTf);
        passwordTf.setBounds(150, 150, 200, 25);
        frame.add(passwordTf);
        staffTypeCombo.setBounds(150, 200, 200, 25);
        frame.add(staffTypeCombo);

        myButton.setBounds(100, 260, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == myButton) {
            frame.dispose();                            // to close login window
            PMS pms = new PMS();

            // get the input from user
            String pharmacyId = pharmacyIdTf.getText();
            char[] password = passwordTf.getPassword();
            String staffType = (String) staffTypeCombo.getSelectedItem();
        }

    }
}
