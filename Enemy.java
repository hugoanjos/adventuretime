import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private Enemy proximo;
    private String direcao;
    private String left = "images/enemyleft.png";
    private String right = "images/enemyright.png";
    private String up = "images/enemyup.png";
    private String down = "images/enemydown.png";
    private Rectangle hitbox;
    private int enemyMove = 0;
    
    public Enemy() {
        this(200, 300, "esquerda");
    }
    
    public Enemy(int x, int y, String direcao) {
        this.direcao = direcao;
        Random sorteio = new Random();
        if(sorteio.nextInt(50) % 2 == 0){
            left = "images/enemy2left.png";
            right = "images/enemy2right.png";
            up = "images/enemy2up.png";
            down = "images/enemy2down.png";
        }
        setImage(direcao);
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setEnemyMove(int enemyMove) {
        this.enemyMove = enemyMove;
    }
        
    public Image getImage() {
        return image;
    }
    
    public Enemy getProximo() {
        return proximo;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getEnemyMove() {
        return enemyMove;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public String getDirecao() {
        return direcao;
    }
    
    public void setProximo(Enemy _enemy) {
        this.proximo = _enemy;
    }
    
    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
   
    public void setHitbox(int p1, int p2, int largura, int altura) {
        hitbox = new Rectangle(p1, p2, largura, altura);
    }
    
    public void setImage(String direcao) {
        ImageIcon ii;
        switch(direcao) {
            case "cima":
                ii = new ImageIcon(this.getClass().getResource(up));
                image = ii.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
                break;
            case "baixo":
                ii = new ImageIcon(this.getClass().getResource(down));
                image = ii.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
                break;
            case "direita":
                ii = new ImageIcon(this.getClass().getResource(right));
                image = ii.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);                  
                break;
            case "esquerda":
                ii = new ImageIcon(this.getClass().getResource(left));
                image = ii.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
                break;
            default:
                break;
        }
    }
    
    public void mover() {
        switch(direcao) {
            case "cima":
                setY(y-50);
                break;
            case "baixo":
                setY(y+50);
                break;
            case "direita":
                setX(x+50);
                break;
            case "esquerda":
                setX(x-50);
                break;
            default:
                break;
        }
    }
}