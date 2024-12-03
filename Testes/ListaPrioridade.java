package Testes;

public class ListaPrioridade<T>{
    LPCelula<T> inicio;
    int tamanho;

    public ListaPrioridade(){
        this.inicio = null;  // de inicio minha lista eh vazia
    }


    public ListaPrioridade(LPCelula<T> nova_celula){
        this.inicio = nova_celula;
    }

    public boolean inserir(T valor, int prioridade){
        LPCelula<T> novo = new LPCelula<>(valor, prioridade);

        // se a lista esta vazia, eu insiro na primeira posição
        if(this.inicio == null){
            this.inicio = novo;
        }else{
            LPCelula<T> aux = inicio;

            // verificando se a inserçao eh na primeira posicao 
            if(novo.prioridade > inicio.prioridade){
                novo.proximo = inicio;
                inicio = novo;
            }

            // achando a posicao aonde o novo nó sera inserido
            while(aux.proximo != null && novo.prioridade <= aux.proximo.prioridade){
                aux = aux.proximo;
            }

            novo.proximo = aux.proximo;
            aux.proximo = novo;
        }

        tamanho++;

        return true;
    }

    // a lista esta ordenada (em ordem da maior prioridade)
    // logo, o valor com maior prioridade vai estar na primeira posicao da lista
    public T removeMax(){
        LPCelula<T> no_removido = inicio;

        if(no_removido != null){

            no_removido = inicio;

            // atualiza o inicio
            inicio = inicio.proximo;

            tamanho--;
        }

        return no_removido.valor;
    }


    public void printList(){
        LPCelula<T> aux = inicio;

        while(aux != null){

            System.out.println("Valor: " + aux.valor + " | Prioridade: " + aux.prioridade);
            aux = aux.proximo;
            
        }
    }

}