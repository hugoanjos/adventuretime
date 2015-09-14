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
    private ListaInimigos listaInimigos = new ListaInimigos();
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
        //enemy = new Enemy();
        //mover essa linha para outro lugar quando a troca de níveis estiver pronta
        listaInimigos.inserirInimigos(3);
        
        enemyMove = 0;
        
        timer = new Timer(6, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;        
        
        if (isPlaying) {
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
            if(listaInimigos.isEmpty() == false) {
                for (int i = 0; i < listaInimigos.getSize(); i++) {
                    g2d.drawImage(listaInimigos.getInimigo(i).getImage(), listaInimigos.getInimigo(i).getX(), listaInimigos.getInimigo(i).getY(), this);
                }
            } else {
                enemyAlive = false;
            }
            player.setHitbox(player.getX(), player.getY(), player.getWidth(), player.getHeight());
            
            if (lista.isEmpty() == false) {
                for (int i = 0; i < lista.getSize(); i++) {
                        //move o ataque
                        lista.getAtaque(i).mover();
                        //set hitbox do ataque
                        lista.getAtaque(i).setHitbox(lista.getAtaque(i).getX(), lista.getAtaque(i).getY(), lista.getAtaque(i).getWidth(), lista.getAtaque(i).getHeight());
                        //desenha o ataque
                        g2d.drawImage(lista.getAtaque(i).getImage(), lista.getAtaque(i).getX(), lista.getAtaque(i).getY(), this);
                        if (lista.getAtaque(i).getX() < 0 || lista.getAtaque(i).getX() > 800 || lista.getAtaque(i).getY() < 0 || lista.getAtaque(i).getY() > 600) {
                            //remove o ataque caso ele saia da tela
                            lista.remover(i);
                        } else {
                            if (listaInimigos.isEmpty() == false) {
                                for (int j = 0; j < listaInimigos.getSize(); j++) {
                                    if (lista.getAtaque(i).getHitbox().intersects(listaInimigos.getInimigo(j).getHitbox())) {
                                        //caso um ataque colida com um inimigo, remover ambos
                                        lista.remover(i);
                                        listaInimigos.remover(j);
                                        score.addScore(100);
                                    }
                                }
                            }

                        }
                }
            }
            
            if (listaInimigos.isEmpty() == false){ 
                for (int i = 0; i < listaInimigos.getSize(); i++) {
                    if(listaInimigos.getInimigo(i).getEnemyMove() < 50){
                        listaInimigos.getInimigo(i).setEnemyMove(listaInimigos.getInimigo(i).getEnemyMove()+1);
                    } else {
                        int maiorDist = player.getX() - listaInimigos.getInimigo(i).getX();
                        if(Math.abs(maiorDist) < Math.abs(player.getY() - listaInimigos.getInimigo(i).getY())){
                            maiorDist = player.getY() - listaInimigos.getInimigo(i).getY();
                            if(maiorDist < 0) {
                                listaInimigos.getInimigo(i).setImage("cima");
                                listaInimigos.getInimigo(i).setDirecao("cima");
                            } else {
                                listaInimigos.getInimigo(i).setImage("baixo");
                                listaInimigos.getInimigo(i).setDirecao("baixo");
                            }
                        } else {
                            if(maiorDist < 0) {
                                listaInimigos.getInimigo(i).setImage("esquerda");
                                listaInimigos.getInimigo(i).setDirecao("esquerda");
                            } else {
                                listaInimigos.getInimigo(i).setImage("direita");
                                listaInimigos.getInimigo(i).setDirecao("direita");
                            }
                        }
                        listaInimigos.getInimigo(i).mover();
                        listaInimigos.getInimigo(i).setHitbox(listaInimigos.getInimigo(i).getX(), listaInimigos.getInimigo(i).getY(), listaInimigos.getInimigo(i).getWidth(), listaInimigos.getInimigo(i).getHeight());
                        g2d.drawImage(listaInimigos.getInimigo(i).getImage(), listaInimigos.getInimigo(i).getX(), listaInimigos.getInimigo(i).getY(), this);
                        listaInimigos.getInimigo(i).setEnemyMove(0);
                            
                        if (player.getHitbox().intersects(listaInimigos.getInimigo(i).getHitbox())) {
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
            JOptionPane.showMessageDialog (null, "Game over!\n Your score was: " + score.getScore());
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
                    if((player.getX()-20) > 0) {
                        player.setX(player.getX()-50);
                    }
                    direcao = "esquerda"; 
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    player.setImage("direita");
                    if((player.getX()+50) < 800) {
                        player.setX(player.getX()+50);
                    }
                    direcao = "direita";
                    break;
                    
                case KeyEvent.VK_UP:
                    player.setImage("cima");
                    if((player.getY()-20) > 0) {
                        player.setY(player.getY()-50);
                    }
                    direcao = "cima";
                    break;
                    
                case KeyEvent.VK_DOWN:
                    player.setImage("baixo");
                    if((player.getY()+50) < 600) {
                        player.setY(player.getY()+50);
                    }
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