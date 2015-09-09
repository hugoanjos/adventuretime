import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Write a description of class Ataque here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ataque
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private String direcao;
    private Rectangle hitbox;
    private ImageIcon ii = new ImageIcon("images/attack.png");
    private Image image = ii.getImage();
    private Ataque proximo;
    private int width = image.getWidth(null);
    private int height = image.getHeight(null);
    
    /**
     * Constructor for objects of class Ataque
     */
    public Ataque(int x, int y, String direcao)
    {
        this.direcao = direcao;
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
    
    public void setProximo(Ataque _ataque) {
        this.proximo = _ataque;
    }
    
    public Ataque getProximo() {
        return this.proximo;
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
    
    public void mover() {
        switch(direcao) {
            case "cima":
                setY(y-5);
                break;
            case "baixo":
                setY(y+5);
                break;
            case "direita":
                setX(x+5);
                break;
            case "esquerda":
                setX(x-5);
                break;
        }
    }
}
