package pharmecy.ms;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton("Login");
    Login(){

        myButton.setBounds(100, 160, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(myButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == myButton){
            frame.dispose();                            // to close login window
            PMS pms = new PMS();

        }

    }
}
