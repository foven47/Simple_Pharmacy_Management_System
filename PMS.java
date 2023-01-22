package pharmecy.ms;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class PMS extends JFrame implements ActionListener {

    private Container c;
    private JLabel titleLable,mnLabel,cnLabel,expLabel,stockLable,priceLabel;
    protected JComboBox<String> mnTf;
    private JTextField cnTf;
    protected JDateChooser expTf;
    protected JSpinner priceTf;
    protected JSpinner stockTf;
    private JButton addButton,updateButton,deleteButton,clearButton;
    private ImageIcon aimg, uimg, dimg, cimg;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private String[] colums = {"Medicine Name","Company Name","EXP Date","Stock Amount","Price"};
    private Object[] rows = new Object[5];
    protected SpinnerNumberModel numberModel = new SpinnerNumberModel(0.00, 0.00, 10000.0, 1.00);
    protected SpinnerNumberModel stockModel = new SpinnerNumberModel(1, 1, 10000, 1);
    protected JSpinner.NumberEditor editor;
    protected NumberFormat format;

    PMS(){
        pranto ();
    }


    public void pranto(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(780, 690);
        this.setLocationRelativeTo(null);
        this.setTitle("Pharmecy Management System");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new java.awt.Color(242, 249, 241));

        Font font = new Font("Arial",Font.BOLD,16);
        Font font1 = new Font("Lato", Font.PLAIN, 16);
        Font numberFont = new Font("Digit-only", Font.PLAIN, 16);

        titleLable = new JLabel("MEDICINE DETAILS");
        titleLable.setFont(font);
        titleLable.setBounds(310,10,250,50);
        c.add(titleLable);

        mnLabel = new JLabel("Medicine Name");
        mnLabel.setFont(font);
        mnLabel.setBounds(10,80,140,30);
        c.add(mnLabel);

        String[] medical ={"Aspirin","Paracetamol","Ibuprofen","Amoxicillin","Metformin", "Lisinopril", "Simvastatin", "Levothyroxine", "Alprazolam", "Omeprazole"};
        mnTf = new JComboBox<>(medical);
        mnTf.setFont(font);
        mnTf.setBounds(170,80,200,30);
        c.add(mnTf);

        cnLabel = new JLabel("Company Name");
        cnLabel.setFont(font);
        cnLabel.setBounds(390,80,150,30);
        c.add(cnLabel);

        cnTf = new JTextField();
        cnTf.setText("Enter company name");
        cnTf.setForeground(Color.GRAY);
        cnTf.setToolTipText("Enter company name");
        cnTf.setFont(font1);
        cnTf.setBounds(550,80,200,30);
        c.add(cnTf);

        cnTf.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (cnTf.getText().equals("Enter company name")) {
                    cnTf.setText("");
                    cnTf.setForeground(Color.BLACK);
                    cnTf.setFont(font);
                }
            }
            public void focusLost(FocusEvent e) {
                if (cnTf.getText().isEmpty()) {
                    cnTf.setText("Enter company name");
                    cnTf.setForeground(Color.GRAY);
                }
            }
        });

        expLabel = new JLabel("EXP Date");
        expLabel.setFont(font);
        expLabel.setBounds(10,130,150,30);
        c.add(expLabel);

        expTf = new JDateChooser();
        expTf.setFont(font);
        expTf.setBounds(170,130,200,30);
        c.add(expTf);

        stockLable = new JLabel("Stock Amount");
        stockLable.setFont(font);
        stockLable.setBounds(390,130,150,30);
        c.add(stockLable);

        stockTf = new JSpinner(stockModel);
        stockTf.setToolTipText("Enter stock amount");
        stockTf.setFont(numberFont);
        stockTf.setBounds(550,130,200,30);
        c.add(stockTf);

        priceLabel = new JLabel("Price of Medicine");
        priceLabel.setFont(font);
        priceLabel.setBounds(10,180,180,30);
        c.add(priceLabel);

        priceTf = new JSpinner(numberModel);
        editor = (JSpinner.NumberEditor)priceTf.getEditor();
        format = editor.getFormat();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        priceTf.setToolTipText("Enter medicine price");
        priceTf.setFont(numberFont);
        priceTf.setBounds(170,180,200,30);
        c.add(priceTf);


        aimg = new ImageIcon(getClass().getResource("Asset 6.png"));
        uimg = new ImageIcon(getClass().getResource("Asset 7.png"));
        cimg = new ImageIcon(getClass().getResource("Asset 8.png"));
        dimg = new ImageIcon(getClass().getResource("Asset 9.png"));

        addButton = new JButton(aimg);
        addButton.setFont(font);
        addButton.setBounds(50,260,120,35);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        c.add(addButton);

        updateButton = new JButton(uimg);
        updateButton.setFont(font);
        updateButton.setBounds(225,260,120,35);
        updateButton.setContentAreaFilled(false);
        updateButton.setBorderPainted(false);
        c.add(updateButton);

        clearButton = new JButton(cimg);
        clearButton.setFont(font);
        clearButton.setBounds(400,260,120,35);
        clearButton.setContentAreaFilled(false);
        clearButton.setBorderPainted(false);
        c.add(clearButton);

        deleteButton = new JButton(dimg);
        deleteButton.setFont(font);
        deleteButton.setBounds(575,260,120,35);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        c.add(deleteButton);

        table = new JTable();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(colums);
        table.setModel(model);
        table.setFont(font);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);

        scroll = new JScrollPane(table);
        scroll.setBounds(10,360,740,265);
        c.add(scroll);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me) {

                int numberOfROW  = table.getSelectedRow();

                LocalDate localDate = (LocalDate) model.getValueAt(numberOfROW, 2);
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                mnTf.setSelectedItem(model.getValueAt(numberOfROW, 0).toString());
                cnTf.setText(model.getValueAt(numberOfROW, 1).toString());
                expTf.setDate(Date.from(instant));
                stockTf.setValue(model.getValueAt(numberOfROW, 3));
                priceTf.setValue(model.getValueAt(numberOfROW, 4));

            }
        });
        this.setVisible(true);

        // Create a button to open frame2
        JButton buttonChartFrame = new JButton("Open Bar Chart");
        buttonChartFrame.setBounds(10,10,120,35);
        buttonChartFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFrame2();
            }
        });
        add(buttonChartFrame);

    }

    ArrayList<String> medName = new ArrayList<>();
    ArrayList<Integer> medStock = new ArrayList<>();
    int barTotal = 0;
    @Override
    public void actionPerformed(ActionEvent e) {

        boolean found = false;

        if(Objects.equals(cnTf.getText(), "Enter company name")){ cnTf.setText("");}
        if(e.getSource()==addButton) {

            if (expTf.getDate() == null ) {
                ErrorM errorM = new ErrorM();
                errorM.displayError("You must input the expired date");
            } else if (Objects.equals(cnTf.getText(), "")) {
                ErrorM errorM = new ErrorM();
                errorM.displayError("You must input your company name");
                if(Objects.equals(cnTf.getText(), "")){ cnTf.setText("Enter company name");}
            } else if (Objects.equals(priceTf.getValue(), 0.0)) {
                ErrorM errorM = new ErrorM();
                errorM.displayError("You must input the price of medicine");
            } else {
                LocalDate date = expTf.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                rows[0] = mnTf.getSelectedItem();
                rows[1] = cnTf.getText();
                rows[2] = date;
                rows[3] = stockTf.getValue();
                rows[4] = priceTf.getValue();

                model.addRow(rows);

                // if same medicine, total with current stock
                for (int i = 0; i < medName.size(); i++) {
                    if (medName.get(i).equals((String) rows[0])) {
                        System.out.println("Same Name");
                        int currentValue = medStock.get(i);
                        medStock.set(i, currentValue + (Integer) rows[3]);
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    medName.add((String) rows[0]);
                    medStock.add((Integer) rows[3]);
                    barTotal++;
                }

            }

        }
        else if(e.getSource()==clearButton){

            mnTf.setSelectedItem("");
            cnTf.setText("");
            expTf.setDate(new Date());
            stockTf.setValue(0);
            priceTf.setValue(0);

        }
        else if(e.getSource()==deleteButton){

            int numberOfROW =  table.getSelectedRow();

            if(numberOfROW >= 0){

                Object selectedName = model.getValueAt(numberOfROW, 0);
                int selectedValue = (Integer) model.getValueAt(numberOfROW, 3);

                for (int i = 0; i < medName.size(); i++) {
                    if (medName.get(i).equals(selectedName)) {
                        medStock.set(i, medStock.get(i) - selectedValue);
                        if (medStock.get(i) == 0) {
                            medStock.remove(i);
                            medName.remove(i);
                            barTotal--;
                        }
                    }
                }

                model.removeRow(numberOfROW);
            }
            else {
                JOptionPane.showMessageDialog(null, "nothig select");
            }

        }
        else if(e.getSource()==updateButton){

            int numberOfROW = table.getSelectedRow();

            Object selectedName = model.getValueAt(numberOfROW, 0);
            int selectedValue = (Integer) model.getValueAt(numberOfROW, 3);

            //when user change selected name,
            if ( (selectedName != mnTf.getSelectedItem()) || (selectedValue != (Integer) stockTf.getValue()) ) {
                for (int i = 0; i < medName.size(); i++) {
                    if(selectedName==medName.get(i)){
                        medStock.set(i, medStock.get(i)-selectedValue);
                        if(medStock.get(i)==0){
                            medStock.remove(i);
                            medName.remove(i);
                            barTotal--;
                        }
                        for (int j = 0; j < medName.size(); j++) {
                            if(medName.get(j)==mnTf.getSelectedItem()){ //total selected stock with stock changed
                                medStock.set(j, medStock.get(j)+(Integer) stockTf.getValue());
                                found=true;
                            }
                        }
                        if(found==false){
                            medName.add((String) mnTf.getSelectedItem());
                            medStock.add((Integer) stockTf.getValue());
                            barTotal++;
                        }
                    }
                }
            }


            LocalDate date = expTf.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (expTf.getDate() == null ) {
                ErrorM errorM = new ErrorM();
                errorM.displayError("You must input the expired date");
            } else if (Objects.equals(cnTf.getText(), "")) {
                ErrorM errorM = new ErrorM();
                errorM.displayError("You must input your company name");
            } else if (Objects.equals(priceTf.getValue(), 0.0)) {
                ErrorM errorM = new ErrorM();
                errorM.displayError("You must input the price of medicine");
            } else {
                model.setValueAt(mnTf.getSelectedItem(),numberOfROW,0);
                model.setValueAt(cnTf.getText(),numberOfROW,1);
                model.setValueAt(date,numberOfROW,2);
                model.setValueAt(stockTf.getValue(),numberOfROW,3);
                model.setValueAt(priceTf.getValue(),numberOfROW,4);
            }

        }

    }
    private void openFrame2() {
        // Initialize frame2
        JFrame frame2 = new JFrame();
        frame2.setTitle("frame2");
        frame2.setSize(680, 590);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setVisible(true);
        frame2.setLayout(null);
        frame2.setBackground(Color.white);

        for (int i = 0, boundValX = 20; i < barTotal; i++, boundValX += 70) {
            JPanel newPanel = new JPanel();
            newPanel.setBackground(Color.WHITE);
            int boundValY = medStock.get(i) * 10;
            newPanel.setBounds(boundValX, 20, 50, boundValY);
            frame2.add(newPanel);
        }
    }
}
