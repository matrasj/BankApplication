import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class RegisterPage implements ActionListener, MouseListener {
    private ExitButton exitButton = new ExitButton();
    private JFrame frame = new JFrame("Register page");
    private Map<JLabel, JTextField> dataTextFieldMap = new LinkedHashMap<>();
    private JLabel nameLabel = new JLabel("Name");
    private JLabel surnameLabel = new JLabel("Surname");
    private JLabel phoneNumberLabel = new JLabel("Phone number");
    private JLabel addressLabel = new JLabel("Address");
    private JLabel emailAddressLabel = new JLabel("Email");
    private JLabel peselIDLabel = new JLabel("PESEL");
    private JLabel loginLabel = new JLabel("Login");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel secondTimePasswordLabel = new JLabel("Repeat password");
    private Data data;
    private JTextField nameField = new JTextField();
    private JTextField surnameField = new JTextField();
    private JTextField phoneNumberField = new JTextField();
    private JTextField addressField = new JTextField();
    private JTextField emailAddressField = new JTextField();
    private JTextField peselIDField = new JTextField();
    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField secondTimePasswordField = new JPasswordField();

    private JPanel mainPanel = new JPanel(new GridLayout(9 ,2, 10, 20));

    private JButton registerButton = new JButton();
    private JLabel message = new JLabel();
    
    
    public RegisterPage(Data data) {
        this.data = data;
        exitButton.addActionListener(this::actionPerformed);
        exitButton.addMouseListener(this);

        frame.setIconImage(new ImageIcon("formulage.png").getImage());
        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);

        dataTextFieldMap.put(nameLabel, nameField);
        dataTextFieldMap.put(surnameLabel, surnameField);
        dataTextFieldMap.put(phoneNumberLabel, phoneNumberField);
        dataTextFieldMap.put(addressLabel, addressField);
        dataTextFieldMap.put(emailAddressLabel, emailAddressField);
        dataTextFieldMap.put(peselIDLabel, peselIDField);
        dataTextFieldMap.put(loginLabel, loginField);
        dataTextFieldMap.put(passwordLabel, passwordField);
        dataTextFieldMap.put(secondTimePasswordLabel, secondTimePasswordField);

        for (Map.Entry<JLabel, JTextField> menuMap : this.dataTextFieldMap.entrySet()) {
            menuMap.getValue().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            menuMap.getValue().setForeground(Color.BLACK);
            menuMap.getValue().setFont(new Font("MV Boli", Font.BOLD, 20));
            menuMap.getValue().setBackground(Color.LIGHT_GRAY);
            menuMap.getValue().addActionListener(this::actionPerformed);
            menuMap.getValue().addMouseListener(this);

            menuMap.getKey().setFont(new Font("MV Boli", Font.BOLD, 30));
            menuMap.getKey().setForeground(Color.BLACK);
            menuMap.getKey().addMouseListener(this);

            mainPanel.add(menuMap.getKey());
            mainPanel.add(menuMap.getValue());
        }
        mainPanel.setBounds(0, 60, 800, 650);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);

        message.setBounds(0, 710, 340, 80);
        message.setFont(new Font("MV Boli", Font.BOLD, 18));
        message.setForeground(Color.BLACK);
        message.setFocusable(false);
        message.setBackground(Color.LIGHT_GRAY);

        registerButton.setBounds(405, 730, 150, 50);
        registerButton.setText("Create");
        registerButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusable(false);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        registerButton.addMouseListener(this);
        registerButton.addActionListener(this::actionPerformed);
        frame.add(registerButton);
        frame.add(message);
        frame.add(exitButton);
        frame.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitButton)) {
            frame.dispose();
            new LoginFrame(data);
        }

        if (e.getSource().equals(registerButton)) {

            boolean canAdd = true;
            for (Customer customer : data.getCustomerList()) {
                if (customer.getLogin().equals(loginField.getText()) || customer.getPESEL_ID().toLowerCase(Locale.ROOT).equals(peselIDField.getText().toLowerCase(Locale.ROOT))) {
                    canAdd = false;
                }
            }
            if (!passwordField.getText().equals(secondTimePasswordField.getText())) {
                message.setText("Wrong again password!");
                message.setForeground(Color.RED);
            }
            if (canAdd) {
                if (!ifSomethingInvalid()) {
                    try {
                        data.addNewCustomer(nameField.getText(),
                                surnameField.getText(),
                                phoneNumberField.getText(),
                                addressField.getText(),
                                emailAddressField.getText(),
                                peselIDField.getText(),
                                loginField.getText(),
                                passwordField.getText(), 0.0);
                        message.setText("User added :)");
                        message.setForeground(Color.GREEN);

                    } catch (IOException ioException) {
                        message.setText(ioException.getMessage());
                    }
                }

            } else {
                message.setText("Such user currently exist");
                message.setForeground(Color.RED);
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
        for (Map.Entry<JLabel, JTextField> menuMap : dataTextFieldMap.entrySet()) {
            if (e.getSource().equals(menuMap.getValue())) {
                menuMap.getKey().setForeground(Color.CYAN);
                menuMap.getValue().setForeground(Color.CYAN);
                menuMap.getValue().setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            }
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.CYAN);
            exitButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        }
        
        if (e.getSource().equals(registerButton)) {
            registerButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            registerButton.setForeground(Color.CYAN);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (Map.Entry<JLabel, JTextField> menuMap : dataTextFieldMap.entrySet()) {
            if (e.getSource().equals(menuMap.getValue())) {
                menuMap.getKey().setForeground(Color.BLACK);
                menuMap.getValue().setForeground(Color.BLACK);
                menuMap.getValue().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.BLACK);
            exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }

        if (e.getSource().equals(registerButton)) {
            registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            registerButton.setForeground(Color.BLACK);
        }
    }

    private boolean ifSomethingInvalid() {
        boolean isSomethingInvalid = false;
        PhoneNumberValidation phoneNumberValidation = new PhoneNumberValidation(phoneNumberField.getText());
        PasswordValidator passwordValidator = new PasswordValidator(passwordField.getText());
        PeselValidator peselValidator = new PeselValidator(peselIDField.getText());
        StringBuilder validation = new StringBuilder();
        validation.append("<html>Invalid: ");
        if (!phoneNumberValidation.isValid()) {
            phoneNumberField.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            phoneNumberLabel.setForeground(Color.RED);
            validation.append("PHONE NUMBER, ");
            isSomethingInvalid = true;
        }

        if (!passwordValidator.isValid()) {
            passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            passwordLabel.setForeground(Color.RED);
            validation.append("PASSWORD, <br/>");
            isSomethingInvalid = true;
        }

        if (!peselValidator.isValid()) {
            peselIDField.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            peselIDLabel.setForeground(Color.RED);
            validation.append("PESEL, </html>");
            isSomethingInvalid = true;
        }

        message.setText(validation.toString());
        return isSomethingInvalid;

    }





}
