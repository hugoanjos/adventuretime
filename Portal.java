import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Write a description of class Portal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portal
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private String portal1 = "images/portal1.png";
    private String portal2 = "images/portal2.png";
    private Rectangle hitbox;
    
    /**
     * Constructor for objects of class Portal
     */
    public Portal(int x, int y, String portal)
    {
        this.x = x;
        this.y = y;
        setImage(portal);
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
    
    public void setImage(String portal) {
        ImageIcon ii;
        switch(portal) {
            case "portal1":
                ii = new ImageIcon(this.getClass().getResource(portal1));
                image = ii.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
                break;
            case "portal2":
                ii = new ImageIcon(this.getClass().getResource(portal2));
                image = ii.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
                break;
                
        }
    }
}
