package Hash;
import java.util.LinkedList;

public class HashTable<K, V> {
    private  LinkedList<HashEntry<K, V>> [] tabelaHash; // tabela hash
    private int tamanho;   // tamanho da tabela hash

    public HashTable(int tamanho){
        tabelaHash = new LinkedList[tamanho];
        this.tamanho = tamanho;
    }

    // funcao que calcula a posicao onde vc vai inserir o valor na tabela
    // funcao hash???
    public int getPosition(int valor){
        return valor % tamanho;
    }

    // retorna os valores na tabela que estao associados a chave passada
    public LinkedList<V> get(K chave){
        int posicao;
        LinkedList<V> valoresPosicao = new LinkedList<V>();

        // pegando a posicao associada a chave em questao
        posicao = getPosition(Math.abs(chave.hashCode()));
        
        // se a posicao for nula, ent nenhum elemento com aquela chave foi adicionado
        if(tabelaHash[posicao] == null){
            return null;
        }else{

            LinkedList<HashEntry<K, V>> lista = tabelaHash[posicao];

            for(int i = 0; i < lista.size(); i++){

                HashEntry<K, V> lista_atual = lista.get(i);

                if(chave.equals(lista_atual.chave)){
                    valoresPosicao.add(lista_atual.valor);
                }
            }
        }

        return valoresPosicao;

    }

    // metodo que realiza a insercao de um valor na tabela hash
    public boolean insercao(K chave, V valor){
        int posicao;

        // se a chave em questao for nula, nao tem como inserir o elemento na tabela
        if(chave == null){
            return false;
        }

        // antes de inserir, precisamos checar se ja nao existe na colecao uma chave com o valor identico
        // ao que estamos tentando inserir no momento. pq se sim, nao inserimos
        LinkedList<V> currentValueKey = get(chave);
        if(currentValueKey != null && currentValueKey.contains(valor)){
            return false;
        }

        // pegando a posicao associada a esta chave
        posicao = getPosition(Math.abs(chave.hashCode()));

        // pegando a lista da posicao em que precisamos inserir
        LinkedList<HashEntry<K,V>> lista = tabelaHash[posicao];

        // testando se a posicao eh nula ou se ja contem algum valor
        if(lista == null){
            lista = new LinkedList<HashEntry<K, V>>();
        }

        // se nao for vazia, eu so faço a insercao no final
        lista.add(new HashEntry<K, V>(chave, valor));  // inserindo o valor na lista
        tabelaHash[posicao] = lista;  // guardando a lista na posicao

        return true;
    }

    // metodo que retorna a quantidade de elementos que estao em uma posicao da tabela
    // exercicio 3 da lista de hash
    int elementosPosicao(int posicao){

        // pegando a lista associada a esta posicao
        LinkedList<HashEntry<K, V>> lista = tabelaHash[posicao];

        // se a lista estiver vazia, nao ha ninguem nessa posicao na tabela
        if(lista == null){
            return 0;
        }

        int i;
        for(i = 0; i < lista.size(); i++){
            //System.out.println("teste");
        }

        return i;
    }

    // metodo para remover um elemento da tabela hash com chave e valor associado
    // exercicio 4 da lista de hash
    public boolean remover(K chave, V valor){
        int posicao;

        // se a chave em questao for nula, nao ha ninguem para ser removido
        if(chave == null){
            return false;
        }

        // pegando a posicao associada a esta chave
        posicao = getPosition(Math.abs(chave.hashCode()));

        // pegando a lista associada a esta posicao
        LinkedList<HashEntry<K, V>> lista = tabelaHash[posicao];

        // se essa lista estiver vazia, nao ha ninguem para ser removido
        if(lista == null){
            return false;
        }

        // agora eu preciso descobrir em que posicao se encontra o elemento que eu quero remover
        for(int i = 0; i < lista.size(); i++){

            HashEntry<K, V> lista_atual = lista.get(i);

            if(valor.equals(lista_atual.valor)){
                // achei o valor a ser removido
                lista.remove(i); // removendo o valor
                tabelaHash[posicao] = lista; // atualizando tabela
                return true;
            }
        }

        return false;
    }

    // exercicio 5 da lista
    public void print(){
        // percorrendo a tabela hash
        for(int i = 0; i < tabelaHash.length; i++){
            System.out.println("--------------------");
            System.out.println("Posicao " + i + ":");

            // verificando a posicao atual esta vazia
            if(tabelaHash[i] == null){
                System.out.println("posicao vazia na tabela");
            }else{
                // pegando a lista da posicao atual
                LinkedList<HashEntry<K, V>> lista_atual = tabelaHash[i];

                // percorrendo a lista
                for(int j = 0; j < lista_atual.size(); j++){
                    System.out.println(lista_atual.get(j).toString() + " - ");
                }
            }
        }
    }
}

