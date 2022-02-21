import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements ActionListener, MouseListener {
    private Customer currentCustomer;
    private Data data;
    private GamePanel gamePanel;
    public GameFrame(Customer currentCustomer, Data data) {
        this.currentCustomer = currentCustomer;
        this.data = data;
        GamePanel gamePanel = new GamePanel(currentCustomer, data);
        this.gamePanel = gamePanel;
        this.gamePanel.getExitButton().addMouseListener(this);
        this.gamePanel.getExitButton().addActionListener(this::actionPerformed);
        this.add(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource().equals(gamePanel.getExitButton())) {
           this.dispose();
           new MenuFrame(currentCustomer, data);
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
        if (e.getSource().equals(gamePanel.getExitButton())) {
            gamePanel.getExitButton().setForeground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(gamePanel.getExitButton())) {
            gamePanel.getExitButton().setForeground(Color.WHITE);
        }
    }




}
