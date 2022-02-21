import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExitButton extends JButton implements ActionListener, MouseListener {
    public ExitButton() {
        this.setBounds(5, 5, 100, 50);
        this.setText("Exit");
        this.setFont(new Font("MV Boli", Font.BOLD, 30));
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.BLACK);
        this.setFocusable(false);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
