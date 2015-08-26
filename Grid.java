import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Grid extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Player player;
    
    private boolean isPlaying = true;

    private Font font;
       
    public Grid() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.BLACK);

        score = new Score();
        add(score);       
        
        player = new Player();
        
        timer = new Timer(5, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;        
        
        if (isPlaying) {
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
            
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void actionPerformed(ActionEvent e) {        
        repaint();  
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    score.addScore(100);
                    break;
                    
                case KeyEvent.VK_LEFT:
                    player.setImage("esquerda");
                    player.setX(player.getX()-20);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    player.setImage("direita");
                    player.setX(player.getX()+20);
                    break;
                    
                case KeyEvent.VK_UP:
                    player.setImage("cima");
                    player.setY(player.getY()-20);
                    break;
                    
                case KeyEvent.VK_DOWN:
                    player.setImage("baixo");
                    player.setY(player.getY()+20);
                    break;
            }
            
        }
    }
    
}