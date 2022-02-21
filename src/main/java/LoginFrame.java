import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginFrame implements ActionListener, MouseListener  {
    private Data data = new Data();
    private JFrame frame = new JFrame("Login Page");
    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel loginLabel = new JLabel("Login");
    private JLabel passwordLabel = new JLabel("Password");
    private JPanel panel = new JPanel(new GridLayout(2, 2, 10 , 40));
    private JButton loginButton = new JButton("Log");
    private JButton registerButton = new JButton("Register");
    private JButton resetTextLoginFieldButton = new JButton("Reset");
    private JButton deleteOneLetterLoginFieldButton = new JButton("Del");
    private JButton resetPasswordLoginFieldButton = new JButton("Reset");
    private JButton deleteOneLetterPasswordFieldButton = new JButton("Del");
    private List<JButton> menuButtons = new ArrayList<>(); 
    private ExitButton exitButton = new ExitButton();
    private JLabel messageLabel = new JLabel();
    public LoginFrame(Data data) {
        this.data = data;
        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);
        panel.setBounds(100, 200, 600, 200);

        exitButton.addMouseListener(this);
        exitButton.addActionListener(this::actionPerformed);

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.setBackground(Color.LIGHT_GRAY);

        loginField.setSize(new Dimension(300, 50));
        loginField.setFont(new Font("MV Boli", Font.BOLD, 40));
        loginField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        loginField.setSize(new Dimension(300, 50));
        loginField.addMouseListener(this);

        loginLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        loginLabel.setForeground(Color.BLACK);
        
        passwordField.setSize(new Dimension(300, 50));
        passwordField.setFont(new Font("MV Boli", Font.BOLD, 40));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        passwordField.setSize(new Dimension(300, 50));
        passwordField.addMouseListener(this);

        passwordLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        passwordLabel.setForeground(Color.BLACK);
        
        menuButtons.add(resetTextLoginFieldButton);
        menuButtons.add(resetPasswordLoginFieldButton);
        menuButtons.add(deleteOneLetterLoginFieldButton);
        menuButtons.add(deleteOneLetterPasswordFieldButton);

        loginButton.setBounds(400, 420, 200, 80);
        loginButton.setHorizontalTextPosition(JButton.CENTER);
        loginButton.setFocusable(false);
        loginButton.setForeground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        loginButton.setForeground(Color.BLACK);
        loginButton.addMouseListener(this);
        loginButton.addActionListener(this::actionPerformed);
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        registerButton.setBounds(605, 420, 200, 80);
        registerButton.setHorizontalTextPosition(JButton.CENTER);
        registerButton.setFocusable(false);
        registerButton.setForeground(Color.LIGHT_GRAY);
        registerButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        registerButton.setForeground(Color.BLACK);
        registerButton.addMouseListener(this);
        registerButton.addActionListener(this::actionPerformed);
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        
        
        for (JButton button : menuButtons) {
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
            button.setFont(new Font("MV Boli", Font.BOLD, 30));
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setForeground(Color.BLACK);
            button.setFocusable(false);
            button.addMouseListener(this);
            button.addActionListener(this::actionPerformed);
            button.setBackground(Color.LIGHT_GRAY);
            frame.add(button);
        }

        resetTextLoginFieldButton.setBounds(750, 200, 100, 80);
        resetPasswordLoginFieldButton.setBounds(750, 320, 100, 80);
        deleteOneLetterLoginFieldButton.setBounds(855, 200, 100, 80);
        deleteOneLetterPasswordFieldButton.setBounds(855, 320, 100, 80);

        messageLabel.setBounds(400, 550, 500, 150);
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        messageLabel.setHorizontalTextPosition(JLabel.CENTER);

        frame.add(messageLabel);
        frame.add(panel);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(exitButton);
        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(registerButton)) {
            frame.dispose();
            new RegisterPage(data);
        }

        if (e.getSource().equals(loginButton)) {

          for (Customer customer : data.getCustomerList()) {
              if (customer.getLogin().equals(loginField.getText())) {
                  if (customer.getPassword().equals(passwordField.getText())) {
                      frame.dispose();
                      new MenuFrame(customer, data);
                  } else {
                      messageLabel.setText("<html>Incorrect password :-(</html>");
                  }

              } else {
                      messageLabel.setText("<html>Such user <br/>does not exist :-(</html>");
              }
          }
        }

        if (e.getSource().equals(exitButton)) {
            System.exit(0);
        }

        if (e.getSource().equals(resetTextLoginFieldButton)) {
            loginField.setText("");
        }

        if (e.getSource().equals(deleteOneLetterLoginFieldButton)) {
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<loginField.getText().length()-1; i++) {
                builder.append(loginField.getText().charAt(i));
            }
            loginField.setText(builder.toString());
        }

        if (e.getSource().equals(resetPasswordLoginFieldButton)) {
            passwordField.setText("");
        }

        if (e.getSource().equals(deleteOneLetterPasswordFieldButton)) {
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<passwordField.getText().length()-1; i++) {
                builder.append(passwordField.getText().charAt(i));
            }
            passwordField.setText(builder.toString());
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
        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.CYAN);
            exitButton.setForeground(Color.CYAN);
        }

        if (e.getSource().equals(loginField)) {
            loginField.setForeground(Color.CYAN);
            loginLabel.setForeground(Color.CYAN);
        }

        if (e.getSource().equals(passwordField)) {
            passwordField.setForeground(Color.CYAN);
            passwordLabel.setForeground(Color.CYAN);
        }

        for (JButton button:menuButtons) {
            if (e.getSource().equals(button)) {
                button.setForeground(Color.CYAN);
                button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
            }
        }
        
        if (e.getSource().equals(loginButton)) {
            loginButton.setForeground(Color.CYAN);
            loginButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        }

        if (e.getSource().equals(registerButton)) {
            registerButton.setForeground(Color.CYAN);
            registerButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.BLACK);
            exitButton.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(loginField)) {
            loginField.setForeground(Color.BLACK);
            loginLabel.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(passwordField)) {
            passwordField.setForeground(Color.BLACK);
            passwordLabel.setForeground(Color.BLACK);
        }


        for (JButton button:menuButtons) {
            if (e.getSource().equals(button)) {
                button.setForeground(Color.BLACK);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }
        }

        if (e.getSource().equals(loginButton)) {
            loginButton.setForeground(Color.BLACK);
            loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }

        if (e.getSource().equals(registerButton)) {
            registerButton.setForeground(Color.BLACK);
            registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }
    }
    public static void main(String[] args) {
        new LoginFrame(new Data());
    }
}
