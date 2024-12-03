package AB;

public class NoAB<T extends Comparable<T>>{
    T valor;
    NoAB<T> direita;   // referencia para o filho a direita
    NoAB<T> esquerda;   // referencia para o filho a esquerda

    public NoAB(){
        this.valor = null;
        this.direita = null;
        this.esquerda = null;
    }

    public NoAB(T valor){
        this.valor = valor;
    }

    public NoAB(NoAB<T> direita, NoAB<T> esquerda, T valor){
        this.valor = valor;
        this.direita = direita;
        this.esquerda = esquerda;
    }

    
}