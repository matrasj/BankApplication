import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class MoneyInputFrame implements ActionListener, MouseListener {
    private JFrame frame = new JFrame("MoneyInputFrame");
    private Customer currentCustomer;
    private JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 600,40 ));
    private List<JButton> buttonList = new LinkedList<>();
    private JLabel mainLabel = new JLabel("Click your preferable input");
    private JButton fiftyButton = new JButton("50");
    private JButton oneHundredButton = new JButton("100");
    private JButton twoHundredButton = new JButton("200");
    private JButton threeHundredButton = new JButton("300");
    private JButton fiveHundredButton = new JButton("500");
    private JButton oneThousandButton = new JButton("1000");
    private Stack<Integer> inputs = new Stack<>();
    private JButton previousButton = new JButton("Prev");
    private JButton resetButton = new JButton("Reset");
    private JButton inputButton = new JButton("Input");
    private List<JButton> helpButtonList = new LinkedList<>();
    private JTextField currentInputTextField = new JTextField("0.0");
    private JLabel finishLabel = new JLabel("");
    private ExitButton exitButton = new ExitButton();
    private CurrentBalanceTextField currentBalanceTextField = new CurrentBalanceTextField();
    private Data data;
    public MoneyInputFrame(Customer currentCustomer, Data data) {
        this.currentCustomer = currentCustomer;
        this.data = data;
        currentBalanceTextField.setCurrentCustomer(currentCustomer);
        currentBalanceTextField.setBalance();

        exitButton.setText("Back");
        exitButton.setBounds(0, 0, 100, 100);
        exitButton.addMouseListener(this);
        exitButton.addActionListener(this::actionPerformed);

        buttonList.add(fiftyButton);
        buttonList.add(oneHundredButton);
        buttonList.add(twoHundredButton);
        buttonList.add(threeHundredButton);
        buttonList.add(fiveHundredButton);
        buttonList.add(oneThousandButton);
        frame.setLayout(null);
        frame.setSize(new Dimension(1000,1000 ));
        helpButtonList.add(inputButton);
        helpButtonList.add(previousButton);
        helpButtonList.add(resetButton);
        for (JButton helpButton : helpButtonList) {
            helpButton.addActionListener(this::actionPerformed);
            helpButton.addMouseListener(this);
            helpButton.setFocusable(false);
            helpButton.setBackground(Color.LIGHT_GRAY);
            helpButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            helpButton.setFont(new Font("Arial", Font.BOLD, 30));
        }
        //400, 300, 200, 50);
        previousButton.setBounds(395, 355, 100, 80);
        resetButton.setBounds(505, 355, 100, 80);
        inputButton.setBounds(395, 440, 210, 80);


        for (JButton button : buttonList) {
            button.addActionListener(this::actionPerformed);
            button.addMouseListener(this);
            button.setFocusable(false);
            button.setBackground(Color.LIGHT_GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            button.setFont(new Font("Arial", Font.BOLD, 40));
            buttonsPanel.add(button);
        }

        mainLabel.setBounds(100, 0, 650, 100);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 45));
        mainLabel.setForeground(Color.BLACK);

        currentInputTextField.setFocusable(false);
        currentInputTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        currentInputTextField.setBounds(395, 300, 210, 50);
        currentInputTextField.setFont(new Font("Arial", Font.BOLD, 30));
        currentInputTextField.addActionListener(this::actionPerformed);

        buttonsPanel.setBackground(Color.LIGHT_GRAY);
        buttonsPanel.setBounds(0, 150, 1000, 600);

        finishLabel.setBounds(400, 600, 310, 100);
        finishLabel.setFont(new Font("Mv Boli", Font.BOLD, 40));
        finishLabel.setForeground(Color.BLACK);

        frame.add(previousButton);
        frame.add(resetButton);
        frame.add(inputButton);
        frame.add(currentBalanceTextField);
        frame.add(finishLabel);
        frame.add(exitButton);
        frame.add(currentInputTextField);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainLabel);
        frame.add(buttonsPanel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitButton)) {
            frame.dispose();
            new MenuFrame(currentCustomer, data);
        }
        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
               currentInputTextField.setText(String.valueOf(Double.parseDouble(currentInputTextField.getText()) + Double.parseDouble(button.getText())));
               inputs.push(Integer.parseInt(button.getText()));
            }
        }

        if (e.getSource().equals(resetButton)) {
            currentInputTextField.setText("0.0");
        }

        if (e.getSource().equals(previousButton)) {
            Integer lastOperation = inputs.pop();
            currentInputTextField.setText(String.valueOf(Double.parseDouble(currentInputTextField.getText()) - lastOperation));
        }

        if (e.getSource().equals(inputButton)) {
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), Double.parseDouble(currentInputTextField.getText()), TransactionsTypes.INPUT);
            try {
                data.writeInputInfo(transaction,String.valueOf(currentInputTextField.getText()),
                        TransactionsTypes.INPUT, currentCustomer);
            } catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
            currentCustomer.setBalance(currentCustomer.getBalance() + Double.parseDouble(currentInputTextField.getText()));
            currentInputTextField.setText("0.0");
            finishLabel.setText("Input made :-)");
            finishLabel.setForeground(Color.GREEN);
            currentBalanceTextField.setBalance();
            try {
                data.changeField();
            } catch (IOException exception) {
                System.err.println(exception.getMessage());
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
        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
                button.setForeground(Color.CYAN);
                button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            }
        }

        for (JButton button : helpButtonList) {
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
        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
                button.setForeground(Color.BLACK);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }
        }

        for (JButton button : helpButtonList) {
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
        new MoneyInputFrame(null, null);
    }
}
