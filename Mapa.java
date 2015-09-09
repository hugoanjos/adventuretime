public class Mapa
{
    private int x;
    private Mapa esquerdo;
    private Mapa direito;
    private int numInimigos;
    private int dificuldade;
    
    public Mapa(int x, int numInimigos, int dificuldade) {
        this.x = x;
        this.numInimigos = numInimigos;
        this.dificuldade = dificuldade;
    }
    
    public Mapa(int x) {
        this.x = x;
    }
    
    public void setX(int _x) {
        this.x = _x;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setEsquerdo(Mapa _esquerdo) {
        this.esquerdo = _esquerdo;
    }
    
    public Mapa getEsquerdo() {
        return this.esquerdo;
    }
    
    public void setDireito(Mapa _direito) {
        this.direito = _direito;
    }
    
    public Mapa getDireito() {
        return this.direito;
    }
    
    public void setNumInimigos(int numInimigos){
        this.numInimigos = numInimigos;
    }
    
    public int getNumInimigos(){
        return this.numInimigos;
    }
    
    public void setDificuldade(int dificuldade){
        this.dificuldade = dificuldade;
    }
    
    public int getDificuldade(){
        return this.dificuldade;
    }
}
