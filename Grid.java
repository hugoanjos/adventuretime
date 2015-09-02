import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Math;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Grid extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Player player;
    private Enemy enemy;
    private Ataque attack;
    private String direcao;
    public int enemyMove;
    
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
        enemy = new Enemy();
        
        enemyMove = 0;
        
        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;        
        
        if (isPlaying) {
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
            g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            if (attack!=null) attack.mover();
            if (attack!=null) g2d.drawImage(attack.getImage(), attack.getX(), attack.getY(), this);
            if (enemy != null){
                if(enemyMove < 50){
                    enemyMove++;
                } else {
                    int maiorDist = player.getX() - enemy.getX();
                    if(Math.abs(maiorDist) < Math.abs(player.getY() - enemy.getY())){
                        maiorDist = player.getY() - enemy.getY();
                        if(maiorDist < 0) {
                            enemy.setImage("cima");
                            enemy.setDirecao("cima");
                        } else {
                            enemy.setImage("baixo");
                            enemy.setDirecao("baixo");
                        }
                    } else {
                        if(maiorDist < 0) {
                            enemy.setImage("esquerda");
                            enemy.setDirecao("esquerda");
                        } else {
                            enemy.setImage("direita");
                            enemy.setDirecao("direita");
                        }
                    }
                    enemy.mover();
                    g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
                    enemyMove = 0;
                }
            }
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
                    player.setX(player.getX()-50);
                    direcao = "esquerda"; 
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    player.setImage("direita");
                    player.setX(player.getX()+50);
                    direcao = "direita";
                    break;
                    
                case KeyEvent.VK_UP:
                    player.setImage("cima");
                    player.setY(player.getY()-50);
                    direcao = "cima";
                    break;
                    
                case KeyEvent.VK_DOWN:
                    player.setImage("baixo");
                    player.setY(player.getY()+50);
                    direcao = "baixo";
                    break;
                    
                case KeyEvent.VK_SPACE:
                    attack = new Ataque(player.getX(), player.getY(), direcao);
                    break;
            
                }
            }
    }

}