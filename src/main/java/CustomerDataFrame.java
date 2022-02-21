import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerDataFrame implements ActionListener, MouseListener {
    private Customer currentCustomer;
    private Data data;
    private JFrame frame = new JFrame("Personal data info");
    private JPanel mainPanel = new JPanel(new GridLayout(8, 2, 20, 10));

    private JLabel nameLabel = new JLabel("Name");
    private JTextField nameTextField = new JTextField();

    private JLabel surnameLabel = new JLabel("Surname");
    private JTextField surnameTextField = new JTextField();

    private JLabel phoneNumberLabel = new JLabel("Phone number");
    private JTextField phoneNumberTextField = new JTextField();

    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailField = new JTextField();

    private JLabel addressLabel = new JLabel("Address");
    private JTextField addressTextField = new JTextField();

    private JLabel peselLabel = new JLabel("Pesel");
    private JTextField peselField = new JTextField();

    private JLabel loginLabel = new JLabel("Username");
    private JTextField loginField = new JTextField();

    private JLabel balanceLabel = new JLabel("Balance");
    private JTextField balanceField = new JTextField();

    private JButton changeNameButton = new JButton("Change");
    private JButton changeSurnameButton = new JButton("Change");
    private JButton changePhoneNumberButton = new JButton("Change");
    private JButton changeAddressButton = new JButton("Change");
    private JButton changeEmailAddressButton = new JButton("Change");
    private JButton changePeselButton = new JButton("Change");
    private JButton changeLoginButton = new JButton("Change");
    private JPanel buttonsPanel = new JPanel(new GridLayout(7, 1, 20, 10));

    private List<JButton> buttonList = new ArrayList<>();

    private JLabel mainLabel = new JLabel("Customers data");
    private ExitButton exitButton = new ExitButton();
    private Map<JLabel, JTextField> customersDataMap = new LinkedHashMap<>();
    public CustomerDataFrame(Customer currentCustomer, Data data) {
        this.currentCustomer = currentCustomer;
        this.data = data;
        exitButton.setBounds(835, 0, 150, 50);
        exitButton.setText("Back");
        exitButton.addMouseListener(this);
        exitButton.addActionListener(this::actionPerformed);

        customersDataMap.put(nameLabel, nameTextField);
        customersDataMap.put(surnameLabel, surnameTextField);
        customersDataMap.put(phoneNumberLabel, phoneNumberTextField);
        customersDataMap.put(emailLabel, emailField);
        customersDataMap.put(addressLabel, addressTextField);
        customersDataMap.put(peselLabel, peselField);
        customersDataMap.put(loginLabel, loginField);
        customersDataMap.put(balanceLabel, balanceField);

        buttonList.add(changeNameButton);
        buttonList.add(changeSurnameButton);
        buttonList.add(changePhoneNumberButton);
        buttonList.add(changeAddressButton);
        buttonList.add(changeEmailAddressButton);
        buttonList.add(changePeselButton);
        buttonList.add(changeLoginButton);


        nameTextField.setText(currentCustomer.getName());
        surnameTextField.setText(currentCustomer.getSurname());
        phoneNumberTextField.setText(currentCustomer.getPhoneNumber());
        emailField.setText(currentCustomer.getEmailAddress());
        addressTextField.setText(currentCustomer.getAddress());
        peselField.setText(currentCustomer.getPESEL_ID());
        loginField.setText(currentCustomer.getLogin());
        balanceField.setText(String.valueOf(currentCustomer.getBalance()));

        for (Map.Entry<JLabel, JTextField> customersDataGUI : customersDataMap.entrySet()) {
            customersDataGUI.getKey().addMouseListener(this);
            customersDataGUI.getKey().setForeground(Color.BLACK);
            customersDataGUI.getKey().setFont(new Font("MV Boli", Font.BOLD, 20));
            customersDataGUI.getKey().setBackground(Color.LIGHT_GRAY);

            customersDataGUI.getValue().setFocusable(false);
            customersDataGUI.getValue().setForeground(Color.BLACK);
            customersDataGUI.getValue().setBackground(Color.LIGHT_GRAY);
            customersDataGUI.getValue().setFont(new Font("Ink Free", Font.BOLD, 20));
            customersDataGUI.getValue().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            customersDataGUI.getValue().addMouseListener(this);
            customersDataGUI.getValue().addActionListener(this::actionPerformed);

            mainPanel.add(customersDataGUI.getKey());
            mainPanel.add(customersDataGUI.getValue());
        }

        for (JButton button : buttonList) {
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
            button.setFocusable(false);
            button.setFont(new Font("MV Boli", Font.BOLD, 20));
            button.addMouseListener(this);
            button.addActionListener(this::actionPerformed);
            buttonsPanel.add(button);
        }
        buttonsPanel.setBounds(510, 102, 120, 560);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        balanceField.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBounds(50, 100, 450, 650);

        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        mainLabel.setForeground(Color.BLACK);
        mainLabel.setBounds(20, 0, 450, 100);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 1000));
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);
        frame.add(mainPanel);
        frame.add(mainLabel);
        frame.add(buttonsPanel);
        frame.add(exitButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitButton)) {
            frame.dispose();
            new MenuFrame(currentCustomer, data);
        }

        if (e.getSource().equals(changeNameButton)) {
            frame.dispose();
            new ChangeDataFrame(currentCustomer, data, 1);
        }

        if (e.getSource().equals(changeSurnameButton)) {
            frame.dispose();
            new ChangeDataFrame(currentCustomer, data, 2);
        }

        if (e.getSource().equals(changePhoneNumberButton)) {
            frame.dispose();
            new ChangeDataFrame(currentCustomer, data, 3);
        }

        if (e.getSource().equals(changeAddressButton)) {
            frame.dispose();
            new ChangeDataFrame(currentCustomer, data, 4);
        }

        if (e.getSource().equals(changeEmailAddressButton)) {
            frame.dispose();
            new ChangeDataFrame(currentCustomer, data, 5);
        }

//        if (e.getSource().equals(changePeselButton)) {
//            frame.dispose();
//            new ChangeDataFrame(currentCustomer, data, 6);
//        }

        if (e.getSource().equals(changeLoginButton)) {
            frame.dispose();
            new ChangeDataFrame(currentCustomer, data, 7);
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
        for (Map.Entry<JLabel, JTextField> customersDataGUI : customersDataMap.entrySet()) {
            if (e.getSource().equals(customersDataGUI.getValue()) || e.getSource().equals(customersDataGUI.getKey())) {
                customersDataGUI.getValue().setForeground(Color.CYAN);
                customersDataGUI.getValue().setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
                customersDataGUI.getKey().setForeground(Color.CYAN);
            }
        }

        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
                button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
                button.setForeground(Color.CYAN);
            }
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            exitButton.setForeground(Color.CYAN);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (Map.Entry<JLabel, JTextField> customersDataGUI : customersDataMap.entrySet()) {
            if (e.getSource().equals(customersDataGUI.getValue()) || e.getSource().equals(customersDataGUI.getKey())) {
                customersDataGUI.getValue().setForeground(Color.BLACK);
                customersDataGUI.getValue().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                customersDataGUI.getKey().setForeground(Color.BLACK);
            }
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            exitButton.setForeground(Color.BLACK);
        }

        for (JButton button : buttonList) {
            if (e.getSource().equals(button)) {
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                button.setForeground(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        new CustomerDataFrame(new Customer("asdfasd", "dsadf", "fadfasdf", "safas", "asdfas",
                "sdfa", "dx", "xd"), null);
    }
}
