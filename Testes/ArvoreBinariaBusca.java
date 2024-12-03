package Testes;

public class ArvoreBinariaBusca {
    TipoABB inicio;
    int quantNos;

    // de inicio, minha arvore esta vazia
    public ArvoreBinariaBusca(){
        this.inicio = null;
        this.quantNos = 0;

    }

    public void inserir(int valor){
        TipoABB novo_no = new TipoABB(valor);

        // se a minha arvore esta vazia, esse novo no sera a raiz
        if(this.inicio == null){
            inicio = novo_no;
        }else{
            
            inserirRecursivo(this.inicio, novo_no);
        }
        quantNos++;
    }


    public void inserirRecursivo(TipoABB atual, TipoABB novo_no){
        // se o valor é menor que o meu no atual, eu vou para a esquerda
        if(novo_no.valor < atual.valor){
            // verificando se eu estou agora em uma folha
            if(atual.esq == null){
                atual.esq = novo_no;

            }else{
                // se eu não estou em uma folha, eu faço a chamada recursiva
                inserirRecursivo(atual.esq, novo_no);
            }

            
        }else if(novo_no.valor > atual.valor){

            // verificando se eu estou agora em uma folha
            if(atual.dir == null){
                atual.dir = novo_no;
            }else{
                // se não, eu faço a chamada recursiva para a direita
                inserirRecursivo(atual.dir, novo_no);
            }

        }
    }

    public void imprimir(TipoABB raiz){

        if(raiz != null){
            System.out.printf("%d ", raiz.valor);

            // vai para a esquerda da minha arvore
            imprimir(raiz.dir);

            // vai para a direita da minha arvore
            imprimir(raiz.esq);
        }
    }

    public void imprimirOrdenado(TipoABB raiz){
        if(raiz != null){
            
            imprimirOrdenado(raiz.esq);

            System.out.printf("%d ", raiz.valor);

            imprimirOrdenado(raiz.dir);
        }
    }

    public boolean busca(TipoABB raiz, int valor) {
        if (raiz == null) {
            return false;  // valor nao encontrado
        }
    
        if (raiz.valor == valor) {

            return true;  // valor nao encontrado
        } else if (valor < raiz.valor) {

            return busca(raiz.esq, valor);  // busca na subarvore esquerda
        } else {

            return busca(raiz.dir, valor);  // busca na subarvore direita
        }
    }
    
}
