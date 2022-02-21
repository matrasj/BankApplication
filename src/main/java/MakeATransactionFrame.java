import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MakeATransactionFrame extends JFrame implements ActionListener, MouseListener {
    private Customer customer;
    private Data data;
    private Customer targetCustomer;
    private JTextField moneyTextField = new JTextField();
    private JButton makeATransactionButton = new JButton("Apply");
    private JLabel infoLabel = new JLabel("");

    public MakeATransactionFrame(Customer currentCustomer, Customer targetCustomer, Data data) {
        this.targetCustomer = targetCustomer;
        this.customer = currentCustomer;
        this.data = data;
        this.setSize(new Dimension(500, 300));
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        moneyTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        moneyTextField.setFont(new Font("MV Boli", Font.BOLD, 25));
        moneyTextField.setBounds(30, 50, 200, 50);
        makeATransactionButton.setBounds(240, 50, 80, 70);
        makeATransactionButton.setText("Apply");
        makeATransactionButton.setFocusable(false);
        makeATransactionButton.setBackground(Color.LIGHT_GRAY);
        makeATransactionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        makeATransactionButton.setFont(new Font("MV Boli", Font.BOLD, 25));

        makeATransactionButton.addMouseListener(this);
        makeATransactionButton.addActionListener(this::actionPerformed);
        infoLabel.setBounds(30, 150, 300, 140);
        infoLabel.setFont(new Font("MV Boli", Font.BOLD, 30));

        this.add(infoLabel);
        this.add(moneyTextField);
        this.add(makeATransactionButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(targetCustomer.getName() + " " + targetCustomer.getSurname() + " transaction");
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(makeATransactionButton)) {
            infoLabel.setForeground(Color.GREEN);
            try {
                double value = Double.parseDouble(moneyTextField.getText());
                if (value >= 0) {
                    customer.makeTransaction(value, TransactionsTypes.WITHDRAWAL);
                    targetCustomer.makeTransaction(value, TransactionsTypes.INPUT);
                    infoLabel.setText("Done :)");
                } else {
                    infoLabel.setText("INVALID VALUE");
                    infoLabel.setForeground(Color.RED);
                }

                try {
                    data.changeField();
                } catch (IOException ioException) {
                    System.err.println(ioException.getMessage());
                }
            } catch (NumberFormatException | NullPointerException exception){
                infoLabel.setText("INVALID VALUE");
                infoLabel.setForeground(Color.RED);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(makeATransactionButton)) {
            makeATransactionButton.setForeground(Color.CYAN);
            makeATransactionButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(makeATransactionButton)) {
            makeATransactionButton.setForeground(Color.BLACK);
            makeATransactionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }
    }
}
