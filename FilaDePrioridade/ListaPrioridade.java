package FilaDePrioridade;

public class ListaPrioridade<T extends Comparable<T>> {
    CelulaLP<T> inicio;
    int tamanho;

    // inicializando a minha lista
    public ListaPrioridade(){
        inicio = null;
        tamanho = 0;
    }

    public ListaPrioridade(CelulaLP<T> nova_celula){
        this.inicio = nova_celula;
    }

    // insercao no inicio
    public boolean insercao(T valor, int prioridade){
        // cria a nova celula
        CelulaLP<T> nova = new CelulaLP<T>(valor, prioridade);

        // verificando se a lista esta vazia
        if(this.inicio == null){
            // se sim, eu faço a insercao desta nova celula aqui
            this.inicio = nova;
            this.tamanho++;

        }else{
            // lista NAO esta vazia. preciso achar aonde eu vou inserir a nova celula
            CelulaLP<T> aux = inicio;

            // verificando se a insercao eh na primeira posicao. e para isso, eu verifico
            // se a prioridade da nova celula eh maior que a prioridade da celula que esta no inicio
            if(nova.prioridade >= aux.prioridade){
                // se sim, a nova->proximo aponta para o inicio, ou seja, aponta para a celula
                // que ate entao estava na primeira posicao
                nova.proximo = inicio;
                inicio = nova;   // o inicio passa a ser a nova celula

                tamanho++;
                return true;

                // obs: se eu quiser mudar a prioridade do menor para o maior
                // nova.prioridade <= inicio.prioridade
            }

            // se nao, eu preciso achar a posicao correta
            while(aux.proximo != null && nova.prioridade < aux.proximo.prioridade){
                aux = aux.proximo;
            }

            nova.proximo = aux.proximo;
            aux.proximo = nova;
            tamanho++;

        }

        return true;
    }

    public T removeMax(){
        // se a lista estiver vazia, nao ha ninguem para ser removido
        if(this.inicio == null){
            return null;
        }

        CelulaLP<T> celulaRemovida = inicio;

        // atualiza o inicio da lista
        inicio = inicio.proximo;
        tamanho--;  // diminui o tamanho da lista

        return celulaRemovida.valor;
    }

    public  boolean estaVazia(){
        if(this.inicio == null){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        int tamanho = 0;

        CelulaLP<T> aux = inicio;

        while (aux != null) {
            aux = aux.proximo;
            tamanho++;
        }

        return tamanho;
    }


    // remove todos os elementos da lista
    public void Clear(){
        int tamanho_aux = tamanho;
        for(int i = 0; i < tamanho_aux; i++){
            removeMax();
        }
    }

    public boolean busca(T valor){
        CelulaLP<T> aux = inicio;
        int i = 0;
        
        while(i < tamanho && valor.compareTo(aux.valor) != 0){
            i++;
            aux = aux.proximo;
        }

        if(i == tamanho){
            return false;
        }else{
            return true;
        }
    }

        public void printList(){
        CelulaLP<T> aux = inicio;

        while(aux != null){

            System.out.println("Valor: " + aux.valor + " | Prioridade: " + aux.prioridade);
            aux = aux.proximo;
            
        }
    }
}
