import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Math;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Grid extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Player player;
    private Enemy enemy;
    private Ataque attack;
    private String direcao = "direita";
    private Lista lista = new Lista();
    public int enemyMove;
    private boolean enemyAlive = true;
    private boolean gameOver = false;
    
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
            if(enemyAlive)g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            
            player.setHitbox(player.getX(), player.getY(), player.getWidth(), player.getHeight());
            
            if (lista.isEmpty() == false) {
                for (int i = 0; i < lista.getSize(); i++) {
                        lista.getAtaque(i).mover();
                        lista.getAtaque(i).setHitbox(lista.getAtaque(i).getX(), lista.getAtaque(i).getY(), lista.getAtaque(i).getWidth(), lista.getAtaque(i).getHeight());
                        g2d.drawImage(lista.getAtaque(i).getImage(), lista.getAtaque(i).getX(), lista.getAtaque(i).getY(), this);
                        if (lista.getAtaque(i).getX() < 0 || lista.getAtaque(i).getX() > 800 || lista.getAtaque(i).getY() < 0 || lista.getAtaque(i).getY() > 600) {
                            lista.remover(i);
                        } else if (lista.getAtaque(i).getHitbox().intersects(enemy.getHitbox())) {
                            lista.remover(i);
                            enemyAlive = false;
                        }
                }

            }
            
            if (enemy != null){
                if (enemyAlive) {
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
                        enemy.setHitbox(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
                        g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
                        enemyMove = 0;
                        
                        if (player.getHitbox().intersects(enemy.getHitbox())) {
                            gameOver = true;
                        }
                    }
                }
            }
            
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }
    
    
    public void actionPerformed(ActionEvent e) {        
        if (gameOver == false) {
            repaint();
        } else {
            JOptionPane.showMessageDialog (null, "Game over!");
            System.exit(0);
        }
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
                    if (lista.getSize() < 6) {
                        lista.inserir(new Ataque(player.getX()+10, player.getY()+10, direcao));
                    }
                    break;
            
                }
            }
    }

}