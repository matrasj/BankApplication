import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangeDataFrame implements MouseListener, ActionListener {
    private Customer currentCustomer;
    private ExitButton exitButton = new ExitButton();
    private Data data;
    private int whatAreWeChanging;
    private JLabel messageLabel = new JLabel();
    private String fieldName;
    private JLabel mainLabel = new JLabel();
    private JFrame frame = new JFrame();
    private List<JTextField> newDataTextFields = new ArrayList<>(2);
    private JTextField firstTextField = new JTextField();
    private JTextField secondTextField = new JTextField();
    private JLabel oldLabel = new JLabel();
    private JLabel newLabel = new JLabel();
    private JButton changeButton = new JButton("Change");
    private JPanel panel = new JPanel(new GridLayout(2, 2, 10 ,100));
    public ChangeDataFrame(Customer currentCustomer, Data data, int whatAreWeChanging) {
        this.currentCustomer = currentCustomer;
        this.data = data;
        this.whatAreWeChanging = whatAreWeChanging;
        exitButton.addActionListener(this::actionPerformed);
        exitButton.addMouseListener(this);
        exitButton.setText("Back");
        exitButton.setBounds(0, 0, 120, 60);
        String changingField="";
        switch (whatAreWeChanging) {
            case 1 -> {
                changingField = currentCustomer.getName();
                fieldName = "name";
            }

            case 2 -> {
                changingField = currentCustomer.getSurname();
                fieldName = "surname";
            }

            case 3 -> {
                changingField = currentCustomer.getPhoneNumber();
                fieldName = "phone number";
            }

            case 4 -> {
                changingField = currentCustomer.getAddress();
                fieldName = "address";
            }

            case 5 -> {
                changingField = currentCustomer.getEmailAddress();
                fieldName = "email address";
            }

            case 6 -> {
                changingField = currentCustomer.getPESEL_ID();
                fieldName = "pesel";
            }

            case 7 -> {
                changingField = currentCustomer.getLogin();
                fieldName = "login";
            }
        }


        newDataTextFields.add(firstTextField);
        newDataTextFields.add(secondTextField);

        for (JTextField textField : newDataTextFields) {
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            textField.setFont(new Font("MV Boli", Font.BOLD, 25));
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            textField.addActionListener(this::actionPerformed);
            textField.addMouseListener(this);
        }
        List<JLabel> labels = new ArrayList<>();
        labels.add(oldLabel);
        labels.add(newLabel);
        for (JLabel label : labels) {
            label.setFont(new Font("MV Boli", Font.BOLD, 25));
            label.setForeground(Color.BLACK);
            label.addMouseListener(this);

        }



        panel.add(oldLabel);
        panel.add(firstTextField);
        panel.add(newLabel);
        panel.add(secondTextField);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(50, 150, 500, 200);

        oldLabel.setText("New " + fieldName);
        newLabel.setText("New " + fieldName + " again");

        mainLabel.setText("Change: " + fieldName);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        mainLabel.setBounds(100, 0, 600, 150);
        mainLabel.setForeground(Color.BLACK);

        changeButton.addActionListener(this::actionPerformed);
        changeButton.addMouseListener(this);
        changeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        changeButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        changeButton.setBackground(Color.LIGHT_GRAY);
        changeButton.setFocusable(false);
        changeButton.setForeground(Color.BLACK);
        changeButton.setBounds(305, 370, 100, 80);

        messageLabel.setBounds(150, 390, 300, 100);
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
        messageLabel.setForeground(Color.BLACK);

        frame.add(messageLabel);
        frame.add(mainLabel);
        frame.add(exitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.add(changeButton);
        frame.setLayout(null);
        frame.setSize(new Dimension(1000, 1000));
        frame.setTitle("Change" + changingField);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(changeButton)) {
            switch (fieldName) {
                case "name" -> {
                   if (firstTextField.getText().equals(secondTextField.getText())) {
                       currentCustomer.setName(secondTextField.getText());
                   }
                }

                case "surname" -> {
                    if (firstTextField.getText().equals(secondTextField.getText())) {
                        currentCustomer.setSurname(secondTextField.getText());
                    }
                }

                case "phone number" -> {
                    if (firstTextField.getText().equals(secondTextField.getText())) {
                        currentCustomer.setPhoneNumber(secondTextField.getText());
                    }
                }

                case "address" -> {
                    if (firstTextField.getText().equals(secondTextField.getText())) {
                        currentCustomer.setAddress(secondTextField.getText());
                    }
                }

                case "email address" -> {
                    if (firstTextField.getText().equals(secondTextField.getText())) {
                        currentCustomer.setEmailAddress(secondTextField.getText());
                    }
                }

                case "login" -> {
                    if (firstTextField.getText().equals(secondTextField.getText())) {
                        currentCustomer.setLogin(secondTextField.getText());

                    }
                }
            }
            messageLabel.setText("Changed :-) for " + secondTextField.getText());

            try {
                data.changeField();
                firstTextField.setText("");
                secondTextField.setText("");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        if (e.getSource().equals(exitButton)) {
            frame.dispose();
            new CustomerDataFrame(currentCustomer, data);
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
        if (e.getSource().equals(firstTextField)) {
            firstTextField.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            firstTextField.setForeground(Color.CYAN);
            oldLabel.setForeground(Color.CYAN);
        }

        if (e.getSource().equals(secondTextField)) {
            secondTextField.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            secondTextField.setForeground(Color.CYAN);
            newLabel.setForeground(Color.CYAN);
        }

        if (e.getSource().equals(changeButton)) {
            changeButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            changeButton.setForeground(Color.CYAN);
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            exitButton.setForeground(Color.CYAN);
        }


    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(firstTextField)) {
            firstTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            firstTextField.setForeground(Color.BLACK);
            oldLabel.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(secondTextField)) {
            secondTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            secondTextField.setForeground(Color.BLACK);
            newLabel.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(changeButton)) {
            changeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            changeButton.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(exitButton)) {
            exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            exitButton.setForeground(Color.BLACK);
        }
    }
}
