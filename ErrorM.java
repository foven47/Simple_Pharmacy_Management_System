package pharmecy.ms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorM {
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JButton closeButton = new JButton("OK");
    ImageIcon icon = new ImageIcon("xerror.png");

    ErrorM() {
        frame.setSize(345, 180);
        frame.setResizable(false);
        frame.setFocusable(true );
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setIcon(icon);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        closeButton.setPreferredSize(new Dimension(75, 25));
        frame.add(label, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(closeButton, gbc);
    }

    public void displayError(String message) {
        label.setText("Error: " + message);
        frame.setVisible(true);
    }
}




