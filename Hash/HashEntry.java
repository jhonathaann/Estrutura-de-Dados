package Hash;

public class HashEntry<K, V>{
    K chave;
    V valor;

    public HashEntry(K chave, V valor){
        this.chave = chave;
        this.valor = valor;
    }

    public String toString(){
        return "(" + chave + ", " + valor + ")";
    }
}