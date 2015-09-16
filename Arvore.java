public class Arvore
{
    private Mapa raiz;
    
    public Arvore() {
        raiz = null;
    }
    
    public void inserir(Mapa _node) {
        _node.setDireito(null);
        _node.setEsquerdo(null);
        
        if (raiz == null) {
            raiz = _node;
        } else {
            Mapa aux = raiz;
            while (aux != null) {
                if (_node.getX() < aux.getX()){
                    if (aux.getEsquerdo() != null) {
                        aux = aux.getEsquerdo();
                    } else {
                        aux.setEsquerdo(_node);
                        break;
                    }
                } else {
                    if (aux.getDireito() != null) {
                        aux = aux.getDireito(); 
                    } else {
                        aux.setDireito(_node);
                        break;
                    }
                }
            }                                  
        }
    }
    
    public void remover(Mapa _node) {
        // isEmpty
        if (raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        } else {
            removerAux(raiz, _node);
        }
    }
    
    private Mapa removerAux(Mapa root, Mapa _node) {
        if (root.getX() != _node.getX()) {
            if (root.getX() > _node.getX()) {
                root.setEsquerdo(removerAux(root.getEsquerdo(), _node));
            } else {
                root.setDireito(removerAux(root.getDireito(), _node));
            }
        } else {
            if ((_node.getEsquerdo() == null) && (_node.getDireito() == null)) {
                // Caso for leaf
                root = null;
                return root;
            } else if (_node.getEsquerdo() == null) {
                // Caso só tenha filhos na direita
                root = root.getDireito();
                return root;
            } else if (_node.getDireito() == null) {
                // Caso só tenha filhos na esquerda
                root = root.getEsquerdo();
                return root;
            } else { 
                // Filhos dos dois lados
                Mapa temp = valorMin(root.getDireito());
                root.setX(temp.getX());
                root.setDireito(removerAux(root.getDireito(), temp));
            }
        }
        return root;
    }
    
    private Mapa valorMin(Mapa root) {
        while (root.getEsquerdo() != null) {
            root = root.getEsquerdo();
        }
        return root;
    }
    
    public Mapa getRaiz() {
        return raiz;
    }
}