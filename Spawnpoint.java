
/**
 * Write a description of class Spawnpoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spawnpoint
{
    private int x;
    private int y;
    private boolean usado = false;
    
    public Spawnpoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setUse(boolean usado) {
        this.usado = usado;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean getUse() {
        return usado;
    }
}
