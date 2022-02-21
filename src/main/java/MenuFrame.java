import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuFrame implements ActionListener, MouseListener {
    private JFrame frame = new JFrame("Menu frame");
    private JPanel mainPanel = new JPanel(new GridLayout(6, 1, 30, 10));
    private JLabel menuLabel = new JLabel("Menu frame");
    private CurrentBalanceTextField currentBalanceTextField = new CurrentBalanceTextField();
    private JLabel currentBalanceLabel = new JLabel("Balance");
    private ExitButton exitButton = new ExitButton();
    private JButton seePersonalDataButton = new JButton("My data");
    private JButton withdrawalButton = new JButton("Money withdrawal");
    private JButton inputButton = new JButton("Money input");
    private JButton transactionsHistory = new JButton("Transactions history");
    private JButton internalTransaction = new JButton("Internal transaction");
    private JButton myButton = new JButton("Click me :)");
    private List<JButton> menuButtonsList = new LinkedList<>();
    private Customer currentCustomer;
    private Data data;
    public MenuFrame(Customer currentCustomer, Data data) {
        this.currentCustomer = currentCustomer;
        this.data = data;
        currentBalanceTextField.setCurrentCustomer(currentCustomer);
        currentBalanceTextField.setBalance();

        exitButton.setText("Log out");
        exitButton.setBounds(5, 5, 140, 50);
        exitButton.addMouseListener(this);
        exitButton.addActionListener(this::actionPerformed);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 1000));
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        menuLabel.setBounds(200, 20, 600, 100);
        menuLabel.setForeground(Color.darkGray);
        menuLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        menuLabel.setVerticalAlignment(JLabel.TOP);
        menuLabel.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.setBounds(50, 120, 450, 650);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        menuButtonsList.add(seePersonalDataButton);
        menuButtonsList.add(internalTransaction);
        menuButtonsList.add(inputButton);
        menuButtonsList.add(withdrawalButton);
        menuButtonsList.add(transactionsHistory);
        menuButtonsList.add(myButton);

        for (JButton button : menuButtonsList) {
            button.setBackground(Color.LIGHT_GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            button.setForeground(Color.BLACK);
            button.setFont(new Font("MV Boli", Font.BOLD, 30));
            button.setHorizontalTextPosition(JButton.LEFT);
            button.addActionListener(this::actionPerformed);
            button.addMouseListener(this);
            button.setFocusable(false);
            mainPanel.add(button);
        }

        currentBalanceTextField.addActionListener(this::actionPerformed);
        currentBalanceTextField.addMouseListener(this);
        currentBalanceLabel.setBounds(785, 55, 200, 50);
        currentBalanceLabel.setBackground(Color.LIGHT_GRAY);
        currentBalanceLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
        currentBalanceLabel.setHorizontalAlignment(JTextField.LEFT);

        currentBalanceLabel.setForeground(Color.BLACK);
        frame.add(exitButton);
        frame.add(currentBalanceLabel);
        frame.add(menuLabel);
        frame.add(mainPanel);
        frame.add(currentBalanceTextField);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitButton)) {
            frame.dispose();
            new LoginFrame(data);
        }

        if (e.getSource().equals(seePersonalDataButton)) {
            frame.dispose();
            new CustomerDataFrame(currentCustomer, data);
        }

        if (e.getSource().equals(inputButton)) {
            frame.dispose();
            new MoneyInputFrame(currentCustomer, data);
        }

        if (e.getSource().equals(myButton)) {
            frame.dispose();
            new GameFrame(currentCustomer, data);
        }

        if (e.getSource().equals(internalTransaction)) {
            frame.dispose();
            new InternalTransactionByPhoneFrame(currentCustomer, data);
        }

        if (e.getSource().equals(withdrawalButton)) {
            frame.dispose();
            new MoneyWithdrawalFrame(currentCustomer, data);
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
        for (JButton button : menuButtonsList) {
            if (e.getSource().equals(button)) {
                button.setForeground(Color.CYAN);
                button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            }
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.CYAN);
            exitButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (JButton button : menuButtonsList) {
            if (e.getSource().equals(button)) {
                button.setForeground(Color.BLACK);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.BLACK);
            exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }
    }

    public static void main(String[] args) {
        new MenuFrame(null, null);
    }

}
