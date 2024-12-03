package ABB;

public class TipoABB<T extends Comparable<T>>{
    T valor;
    TipoABB<T> direita;
    TipoABB<T> esquerda;

    public TipoABB(){
        this.valor = null;
        this.direita = null;
        this.esquerda = null;
    }

    public TipoABB(T valor){
        this.valor = valor;
    }

    public TipoABB(T valor, TipoABB<T> direita, TipoABB<T> esquerda){
        this.valor = valor;
        this.direita = direita;
        this.esquerda = esquerda;
    }
}
