
/**
 * Write a description of class Lista here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lista
{
    private Ataque inicio;
    private int max = 6;
    
    public Lista() {
        this.inicio = null;
    }
    
    public void inserir(Ataque _ataque) {
        Ataque aux = inicio;
        if (isEmpty()) {
            inicio = _ataque;
        } else {
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
            }
            aux.setProximo(_ataque);
        }
        
    }
    
    public void remover(int pos) {
        if (isEmpty() == false && pos <= getSize() && pos >= 0) {
            if (pos == 0) {
                inicio = inicio.getProximo();
            } else if (pos == getSize()) {
                getAtaque(pos-1).setProximo(null);
            } else {
                getAtaque(pos-1).setProximo(getAtaque(pos+1));
            }
        }   
    }
    
    public int buscar(Ataque _ataque) {
        int pos = 0;
        Ataque aux = inicio;
        while (aux.getProximo() != null) {
            if (aux == _ataque) {
                return pos;
            }
            pos++;
            aux = aux.getProximo();
        }
        
        if (inicio == _ataque) {
            return pos;
        }
        
        return -1;   
    }
    
    public int getSize() {
        int tamanho = 0;
        Ataque aux = inicio;
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
    
    public Ataque getAtaque(int pos) {
        if (isEmpty() || pos < 0 || pos > getSize()) {
            return null;
        }
        
        if (pos == 0) return inicio;
        
        Ataque aux = inicio;
        for (int i = 0; i < pos; i++) {
            aux = aux.getProximo();
        }
        
        return aux;
    }
    
    public int getMax() {
        return max;
    }
    
    public boolean isEmpty() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }
}
