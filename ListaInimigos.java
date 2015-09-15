import java.util.Random;
/**
 * Write a description of class ListaInimigos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaInimigos
{
    private Enemy inicio;
    Random r = new Random();
    private Spawnpoint p1 = new Spawnpoint((400+r.nextInt(400)), 100);
    private Spawnpoint p2 = new Spawnpoint((400+r.nextInt(400)), 200);
    private Spawnpoint p3 = new Spawnpoint((400+r.nextInt(400)), 250);
    private Spawnpoint p4 = new Spawnpoint((400+r.nextInt(400)), 300);
    private Spawnpoint p5 = new Spawnpoint((400+r.nextInt(400)), 400);
    private Spawnpoint p6 = new Spawnpoint((400+r.nextInt(400)), 500);
    private Spawnpoint p7 = new Spawnpoint((400+r.nextInt(400)), 550);
    private Spawnpoint p8 = new Spawnpoint((400+r.nextInt(400)), 150);
    private Spawnpoint p9 = new Spawnpoint((400+r.nextInt(400)), 350);
    private Spawnpoint p10 = new Spawnpoint((400+r.nextInt(400)), 450);
    private Spawnpoint[] spawnpoints = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
    
    public ListaInimigos() {
        this.inicio = null;
    }
    
    public void inserir(Enemy _enemy) {
        Enemy aux = inicio;
        if (isEmpty()) {
            inicio = _enemy;
        } else {
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
            }
            aux.setProximo(_enemy);
        }
    }
    
    public void inserirInimigos(int qntd) {
        int i = 0;
        int ponto;
        do {
            ponto = r.nextInt(10);
            if (spawnpoints[ponto].getUse() == false) {
                inserir(new Enemy(spawnpoints[ponto].getX(), spawnpoints[ponto].getY(), "Esquerda"));
                spawnpoints[ponto].setUse(true);
                i++;
            }
        } while (i != qntd);
        
        for (int j = 0; j < spawnpoints.length; j++) {
            spawnpoints[j].setUse(false);
        }
    }
    
    public void remover(int pos) {
        if (isEmpty() == false && pos <= getSize() && pos >= 0) {
            if (pos == 0) {
                inicio = inicio.getProximo();
            } else if (pos == getSize()) {
                getInimigo(pos-1).setProximo(null);
            } else {
                getInimigo(pos-1).setProximo(getInimigo(pos+1));
            }
        }
    }
    
    public int buscar(Enemy _enemy) {
        int pos = 0;
        Enemy aux = inicio;
        while (aux.getProximo() != null) {
            if (aux == _enemy) {
                return pos;
            }
            pos++;
            aux = aux.getProximo();
        }
        
        if (inicio == _enemy) {
            return pos;
        }
        
        return -1;
    }
    
    public int getSize() {
        int tamanho = 0;
        Enemy aux = inicio;
        if (isEmpty()) {
            return tamanho;
        } else {
            tamanho++;
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
                tamanho++;
            }
            return tamanho;
        }
    }
    
    public Enemy getInimigo(int pos) {
        if (isEmpty() || pos < 0 || pos > getSize()) {
            return null;
        }
        
        if (pos == 0) return inicio;
        
        Enemy aux = inicio;
        for (int i = 0; i < pos; i++) {
            aux = aux.getProximo();
        }
        
        return aux;
    }
    
    public boolean isEmpty() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }
}
