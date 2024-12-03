package Testes;

public class LPCelula<T> {
    T valor;
    int prioridade;
    LPCelula<T> proximo;

    public LPCelula(){
        this(null, -1, null);
    }

    public LPCelula(T valor, int prioridade, LPCelula<T> proximo){
        this.valor = valor;
        this.prioridade = prioridade;
        this.proximo = proximo;
    }

    public LPCelula(T valor, int prioridade){
        this(valor, prioridade, null);
    }
}
