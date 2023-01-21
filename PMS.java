package pharmecy.ms;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
        c.setBackground(Color.PINK);

        Font font = new Font("Arial",Font.BOLD,16);

        titleLable = new JLabel("Medicine Details");
        titleLable.setFont(font);
        titleLable.setBounds(140,10,250,50);
        c.add(titleLable);

        mnLabel = new JLabel("Medicine Name");
        mnLabel.setFont(font);
        mnLabel.setBounds(10,80,140,30);
        c.add(mnLabel);

        String[] medical ={"Aspirin","Paracetamol","Ibuprofen","Amoxicillin","Metformin", "Lisinopril", "Simvastatin", "Levothyroxine", "Alprazolam", "Omeprazole"};
        mnTf = new JComboBox<>(medical);
        mnTf.setFont(font);
        mnTf.setBounds(180,80,200,30);
        c.add(mnTf);

        cnLabel = new JLabel("Company Name");
        cnLabel.setFont(font);
        cnLabel.setBounds(10,130,150,30);
        c.add(cnLabel);

        cnTf = new JTextField();
        cnTf.setFont(font);
        cnTf.setBounds(180,130,200,30);
        c.add(cnTf);

        expLabel = new JLabel("EXP Date");
        expLabel.setFont(font);
        expLabel.setBounds(10,180,150,30);
        c.add(expLabel);

        expTf = new JDateChooser();
        expTf.setFont(font);
        expTf.setBounds(180,180,200,30);
        c.add(expTf);

        stockLable = new JLabel("Stock Amount");
        stockLable.setFont(font);
        stockLable.setBounds(10,230,150,30);
        c.add(stockLable);

        stockTf = new JSpinner();
        stockTf.setFont(font);
        stockTf.setBounds(180,230,200,30);
        c.add(stockTf);

        priceLabel = new JLabel("Price of Medicine");
        priceLabel.setFont(font);
        priceLabel.setBounds(10,280,180,30);
        c.add(priceLabel);

        priceTf = new JSpinner(numberModel);
        editor = (JSpinner.NumberEditor)priceTf.getEditor();
        format = editor.getFormat();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        priceTf.setFont(font);
        priceTf.setBounds(180,280,200,30);
        c.add(priceTf);


        aimg = new ImageIcon(getClass().getResource("addimg.png"));
        uimg = new ImageIcon(getClass().getResource("updateimg.png"));
        dimg = new ImageIcon(getClass().getResource("deleteimg.png"));
        cimg = new ImageIcon(getClass().getResource("clearimg.png"));

        addButton = new JButton(aimg);
        addButton.setFont(font);
        addButton.setBounds(400,80,120,35);
        c.add(addButton);

        updateButton = new JButton(uimg);
        updateButton.setFont(font);
        updateButton.setBounds(400,130,120,35);
        c.add(updateButton);

        deleteButton = new JButton(dimg);
        deleteButton.setFont(font);
        deleteButton.setBounds(400,180,120,35);
        c.add(deleteButton);

        clearButton = new JButton(cimg);
        clearButton.setFont(font);
        clearButton.setBounds(400,230,120,35);
        c.add(clearButton);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LocalDate date = expTf.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(e.getSource()==addButton) {

            rows[0] = mnTf.getSelectedItem();
            rows[1] = cnTf.getText();
            rows[2] = date;
            rows[3] = stockTf.getValue();
            rows[4] = priceTf.getValue();

            model.addRow(rows);

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
                model.removeRow(numberOfROW);
            }
            else {
                JOptionPane.showMessageDialog(null, "nothig select");
            }

        }
        else if(e.getSource()==updateButton){

            int numberOfROW = table.getSelectedRow();

            model.setValueAt(mnTf.getSelectedItem(),numberOfROW,0);
            model.setValueAt(cnTf.getText(),numberOfROW,1);
            model.setValueAt(date,numberOfROW,2);
            model.setValueAt(stockTf.getValue(),numberOfROW,3);
            model.setValueAt(priceTf.getValue(),numberOfROW,4);

        }

    }

}
