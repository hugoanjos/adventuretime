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
        Random r = new Random();
        for (int i = 0; i < qntd; i++) {
            int x = 300 + r.nextInt(400);
            int y = 50 + r.nextInt(450);
            inserir(new Enemy(x, y, "esquerda"));
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
