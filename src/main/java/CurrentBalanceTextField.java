import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CurrentBalanceTextField extends JTextField implements MouseListener {
    private Customer currentCustomer;
    public CurrentBalanceTextField() {
        this.setBounds(800, 0, 200, 50);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.addMouseListener(this);
        this.setFocusable(false);
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void setBalance() {
        this.setText(String.valueOf(currentCustomer.getBalance()));
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
