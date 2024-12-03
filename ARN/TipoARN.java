package ARN;

public class TipoARN<T extends Comparable<T>> {
    T valor;
    TipoARN<T> direita;
    TipoARN<T> esquerda;
    boolean corVermelha;  // true = vermelho, false = preto

    public TipoARN(T valor){
        this.valor = valor;
        this.corVermelha = true;  // todo nó inserido eh vermelho por padrão
    }

    public TipoARN(T valor, TipoARN<T> direita, TipoARN<T> esquerda){
        this.valor = valor;
        this.direita = direita;
        this.esquerda = esquerda;
        this.corVermelha = true;
    }
    
}
