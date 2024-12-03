package Testes;

public class TipoABB {
    int valor;
    TipoABB dir;
    TipoABB esq;

    public TipoABB(){
        this(-1, null, null);
    }

    public TipoABB(int valor, TipoABB dir, TipoABB esq){
        this.valor = valor;
        this.dir = dir;
        this.esq = esq;
    }

    public TipoABB(int valor){
        this(valor, null, null);
    }
}
