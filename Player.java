import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private String direcao;
    private String left = "images/left.png";
    private String right = "images/right.png";
    private String up = "images/up.png";
    private String down = "images/down.png";;
    private Rectangle hitbox;
    
    public Player() {
        this(50, 300, "direita");
    }
    
    public Player(int x, int y, String direcao) {
        this.direcao = direcao;
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
        
    public Image getImage() {
        return image;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
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
        }
    }
}
