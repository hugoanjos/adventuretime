
/**
 * Write a description of class Nivel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nivel
{   
    public Nivel(){
        if(level == 4){
            level = 0;
            dificuldade++;
        }
        level++;
        //tree = gerarArvoreAleatoria();
    }
    
    public Arvore gerarArvoreAleatoria(){
        Arvore aux = new Arvore();
        for(int i = 0; i < 5; i++){
            aux.inserir(gerarMapaAleatorio());
        }
        return aux;
    }
    
    public Mapa gerarMapaAleatorio(){
        int x = (int)(Math.random()*100);
        int e = level + ((int)(Math.random()*4)* level)/4;
        return new Mapa(x,e,dificuldade);
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    private static int dificuldade = 1;
    private Arvore tree;
    private static int level = 0;
}
