package Testes;

public class NoRBN<T extends Comparable<T>> {
    T valor;
    NoRBN<T> direita;
    NoRBN<T> esquerda;
    NoRBN<T> pai;
    boolean corVermelho;  // true = vermelho, se  false = preto

    public NoRBN(){
        this.valor = null;
        this.direita = null;
        this.esquerda = null;
        this.pai = null;
        this.corVermelho = false;  // cor preta
    }

    public NoRBN(T valor){
        this.valor = valor;
        this.direita = null;
        this.esquerda = null;
        this.pai = null;
        this.corVermelho = false;
    }

}
