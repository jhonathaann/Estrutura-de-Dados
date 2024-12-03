package FilaDePrioridade;

public class CelulaLP<T extends Comparable<T>>{
    T valor;
    int prioridade;
    CelulaLP<T> proximo;

    public CelulaLP(){
        this(null, -1, null);
    }

    public CelulaLP(T valor, int prioridade){
        this.valor = valor;
        this.prioridade = prioridade;
    }

    public CelulaLP(T valor, int prioridade, CelulaLP<T> proximo){
        this.valor = null;
        this.prioridade = prioridade;
        this.proximo = proximo;
    }
    
}
