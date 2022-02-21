import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class InternalTransactionByPhoneFrame extends JFrame implements ActionListener, MouseListener {
    private ExitButton exitButton = new ExitButton();
    private Data data;
    private JTextField numberTextField = new JTextField();
    private Customer currentCustomer;
    private JButton deleteButton = new JButton("Del");
    private JButton resetButton = new JButton("Reset");
    private JLabel welcomingLabel = new JLabel("Choose number :-)");
    private JPanel buttonsPanel = new JPanel(new GridLayout(3, 3, 10, 10));
    List<JButton> buttonList = new ArrayList<>();
    List<JButton> helpButtonList = new ArrayList<>();
    private JButton searchButton = new JButton("Search");
    private JButton lastButton = new JButton("0");
    private JLabel messageLabel = new JLabel();
    private Customer targetCustomer;
    public InternalTransactionByPhoneFrame(Customer currentCustomer, Data data) {
        this.currentCustomer = currentCustomer;
        this.data = data;
        this.setSize(new Dimension(1000, 1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setTitle("Internal transaction");
        for (int i=1; i<10; i++) {
            buttonList.add(new JButton(String.valueOf(i)));
        }
//        buttonList.add(new JButton("0"));

        for (JButton button : buttonList) {
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            button.setBackground(Color.LIGHT_GRAY);
            button.setFocusable(false);
            button.setFont(new Font("MV Boli", Font.BOLD, 30));
            button.addMouseListener(this);
            button.addActionListener(this::actionPerformed);
            button.setForeground(Color.BLACK);
            buttonsPanel.add(button);
        }

        lastButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        lastButton.setBackground(Color.LIGHT_GRAY);
        lastButton.setFocusable(false);
        lastButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        lastButton.addMouseListener(this);
        lastButton.addActionListener(this::actionPerformed);
        lastButton.setForeground(Color.BLACK);
        lastButton.setBounds(237, 560, 126, 120);

        welcomingLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        welcomingLabel.setForeground(Color.BLACK);
        welcomingLabel.setBounds(100, 50, 400, 50);
        buttonsPanel.setBounds(100, 150, 400, 400);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        numberTextField.setFont(new Font("MV Boli", Font.BOLD, 35));
        numberTextField.setForeground(Color.BLACK);
        numberTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        numberTextField.setBounds(550, 150, 300, 70);
        numberTextField.setFocusable(false);

        helpButtonList.add(resetButton);
        helpButtonList.add(deleteButton);
        helpButtonList.add(searchButton);

        for (JButton button : helpButtonList) {
            button.setBackground(Color.LIGHT_GRAY);
            button.setFocusable(false);
            button.setFont(new Font("MV Boli", Font.BOLD, 30));
            button.addMouseListener(this);
            button.addActionListener(this::actionPerformed);
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }
        searchButton.setBounds(550, 290, 300, 50);
        searchButton.setVerticalTextPosition(JButton.CENTER);
        resetButton.setBounds(550, 225, 145, 60);
        deleteButton.setBounds(700, 225, 150, 60);

        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 25));
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setBounds(550, 370, 350, 100);
        messageLabel.setVerticalTextPosition(JLabel.CENTER);
        messageLabel.addMouseListener(this);

        this.add(buttonsPanel);
        this.add(welcomingLabel);
        this.add(resetButton);
        this.add(messageLabel);
        this.add(deleteButton);
        this.add(lastButton);
        this.add(numberTextField);
        this.add(searchButton);
        this.setLayout(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
                if (numberTextField.getText().length() < 9) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(numberTextField.getText());
                    builder.append(String.valueOf(button.getText()));
                    numberTextField.setText(builder.toString());
                }
            }
        }

        if (e.getSource().equals(searchButton)) {
            targetCustomer = data.findCustomerByPhone(numberTextField.getText());

            if (targetCustomer != null) {
                messageLabel.setText(targetCustomer.getName() + " " + targetCustomer.getSurname());


            } else {
                messageLabel.setText("No such user found :(");
            }
        }

        if (e.getSource().equals(resetButton)) {
            numberTextField.setText("");
        }

        if (e.getSource().equals(deleteButton)) {
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<numberTextField.getText().length() - 1; i++) {
                builder.append(String.valueOf(numberTextField.getText().charAt(i)));
            }
            numberTextField.setText(builder.toString());
        }

        if (e.getSource().equals(lastButton)) {
            if (numberTextField.getText().length() < 9) {
                StringBuilder builder = new StringBuilder();
                builder.append(numberTextField.getText());
                builder.append(String.valueOf(lastButton.getText()));
                numberTextField.setText(builder.toString());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(messageLabel)) {
            new MakeATransactionFrame(currentCustomer, targetCustomer, data);
        }
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
                button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
                button.setForeground(Color.CYAN);
            }
        }

        for (JButton button : helpButtonList) {
            if (e.getSource().equals(button)) {
                button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
                button.setForeground(Color.CYAN);
            }
        }

        if (e.getSource().equals(lastButton)) {
            lastButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            lastButton.setForeground(Color.CYAN);
        }

        if (!messageLabel.getText().isEmpty()) {
            if (e.getSource().equals(messageLabel)) {
                messageLabel.setForeground(Color.CYAN);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                button.setForeground(Color.BLACK);
            }
        }

        for (JButton button : helpButtonList) {
            if (e.getSource().equals(button)) {
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                button.setForeground(Color.BLACK);
            }
        }

        if (e.getSource().equals(lastButton)) {
            lastButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            lastButton.setForeground(Color.BLACK);
        }

        if (!messageLabel.getText().isEmpty()) {
            if (e.getSource().equals(messageLabel)) {
                messageLabel.setForeground(Color.BLACK);
            }
        }
    }


}
