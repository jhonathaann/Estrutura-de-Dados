package ABB;

import java.util.LinkedList;
import java.util.Queue;


public  class ArvoreBinariaBusca<T extends Comparable<T>>{
    TipoABB<T> raiz;


    public ArvoreBinariaBusca(){
        this.raiz = null;
    }
    
    public void insercao(T valor){
        raiz = insercao(raiz, valor);
    }

    public TipoABB<T> insercao(TipoABB<T> no_atual, T valor){
        // se a arvore estiver vazia, eu faço a insercao logo na raiz
        if(no_atual == null){
            return new TipoABB<T>(valor);
        }

        int resultado = valor.compareTo(no_atual.valor);

        // se o valor que eu quero inserir eh igual ao valor do meu no atual, eu apenas sobrescrevo
        if(resultado == 0){
            no_atual.valor = valor;
        }
        else if(resultado < 0){
            // se ele eh menor que o meu no atual, eu vou para a esquerda do meu no atual
            no_atual.esquerda = insercao(no_atual.esquerda, valor);
        }else{ 
            // se ele nao eh igual e nem menor, ele eh maior, dai eu vou para a direita do meu no atual
            no_atual.direita = insercao(no_atual.direita, valor);
        }

        return no_atual;  // retorno o no_atual atualizado
    }
    public void imprimir(){
        imprimirOrdenado(raiz, "        ");
    }

    // exercicio 1 (em ordem)
    public void imprimirOrdenado(TipoABB<T> raiz, String espaco){
        if(raiz == null){
            return;
        }else{
            imprimirOrdenado(raiz.esquerda, "         " + espaco);
            System.out.println(espaco + raiz.valor);
            imprimirOrdenado(raiz.direita, "        " + espaco);
        }
    }

    // exercicio 2 (pre ordem)
    public void imprimir(TipoABB<T> raiz){
        if(raiz == null){
            return;
        }else{

            System.out.printf("%d ", raiz.valor);
            imprimir(raiz.direita);
            imprimir(raiz.esquerda);
        }
    }
    
    // exercicio 2 (pos ordem)
    public void imprimirPosOrdem(TipoABB<T> raiz){
        if(raiz == null){
            return;
        }else{
            imprimirPosOrdem(raiz.esquerda);
            imprimirPosOrdem(raiz.direita);
            System.out.printf("%d ", raiz.valor);
        }
    }

    public int altura(){
        return altura(raiz);
    }

    public int altura(TipoABB<T> raiz){
        if(raiz == null){
            return -1;
        }else{

            return 1 + Math.max(altura(raiz.direita), altura(raiz.esquerda));
        }
    }
    
    public void remove(T valor){
        raiz = remove(raiz, valor);
    }

    public TipoABB<T> remove(TipoABB<T> raiz, T valor){
        // se a raiz == null, nao ha ninguem para ser removido
        if(raiz == null){
            return null;
        }else{

            // verificando se o valor a ser removido se encontra no meu no atual
            if(valor.compareTo(raiz.valor) == 0){

                // Caso 1. eh uma folha
                if(raiz.esquerda == null && raiz.direita == null){
                    // eu removo ele retornando null para a raiz do outro metodo remove
                    return null;
                }

                // Caso 2. tem apenas 1 filho (filho para a direita)
                else if(raiz.esquerda == null){
                    // raiz.direita vai substituir o no que vai ser removido
                    return raiz.direita;
                }

                // Tem apenas filho para a esquerda
                else if(raiz.direita == null){
                    // raiz.esquerda vai substituir o no que vai ser removido
                    return raiz.esquerda;
                }

                // Caso 3. tem dois filhos;
                else{
                    // vou colocar o menor da direita no lugar do no que vai ser removido
                    // e lembrando que "raiz" eh no que vai ser removido. mas o que acontece aqui mesmo
                    // eh que "raiz" vai ter o seu valor substituido pelo menor valor da subarvore a sua direita
                    raiz.valor = minimo(raiz.direita);

                    // agora, eu preciso remover o no que contem (ou continha) o menor valor da subarvore a direita

                    raiz.direita = remove(raiz.direita, minimo(raiz.direita));
                }
            }

            // verificando se o no que eu quero remover eh menor que o meu no atual
            else if(valor.compareTo(raiz.valor) < 0){
                // se sim, eu vou para a esquerda
                raiz.esquerda = remove(raiz.esquerda, valor);
            }

            else{
                // se ele nao eh igual e nem menor, ele eh maior que o valor do meu no atual
                raiz.direita = remove(raiz.direita, valor);
            }
        }

        return raiz;  // retornando o no atualizado
    }

    // metodo para encontrar o menor valor de uma ABB
    public T minimo(){
        if(this.raiz == null){
            return null;
        }else{
            return minimo(raiz);
        }
    }

    public T minimo(TipoABB<T> no_atual){
        if(no_atual.esquerda == null){
            return no_atual.valor;
        }else{
            return minimo(no_atual.esquerda);
        }
    }

    // exercicio 4  (ta errado atualmente)
    public boolean estaCompleta() {
        if (raiz == null) {
            return true;  // arvore vazia eh considerada completa
        }
    
        // fila para realizar a travessia em largura
        Queue<TipoABB<T>> fila = new LinkedList<>();
        fila.add(raiz);  // adicionando a raiz na fila
    
        boolean flag = false;  // flag para indicar se encontramos um no incompleto
    
        // enquanto a fila não estiver vazia
        while (!fila.isEmpty()) {
            TipoABB<T> no_corrente = fila.poll();  // pega o primeiro elemento da fila
    
            // Verifica o filho à esquerda
            if (no_corrente.esquerda != null) {
                if (flag) {
                    // se já encontramos um nó incompleto e ainda temos filho a esquerda, a arvore nao eh completa
                    return false;
                }
                fila.add(no_corrente.esquerda);  // coloca o filho a esquerda na fila
            } else {
                // se não tem filho a esquerda, marcamos a flag como verdadeiro
                flag = true;
            }
    
            // verifica o filho a direita
            if (no_corrente.direita != null) {
                if (flag) {
                    // se ja encontramos um nó incompleto e ainda temos filho a direita, a arvore n eh completa
                    return false;
                }
                fila.add(no_corrente.direita);  // coloca o filho a direita na fila
            } else {
                // Se não tem filho a direita, marcamos a flag como verdadeiro
                flag = true;
            }
        }
    
        // Se percorremos a árvore sem violar as regras de completude, retornamos true
        return true;
    }

    // exercicio 5
    public boolean temsomaRaiz(int soma){
        return temsomaRaiz(raiz, soma);
    }

    public boolean temsomaRaiz(TipoABB<T> no_atual, int soma){
        // se o no atual for nulo, nao existe essa soma
        if(no_atual == null){
            return false;
        }

        // verificando se o no_atual eh uma folha E se o seu valor eh igual a soma
        // se sim, eu achei um caminho
        if(no_atual.direita == null && no_atual.esquerda == null && (int) no_atual.valor == soma){
            return true;
        }else{

            // senao, eu verifico se existe um caminho pra esquerda OU para a direita
            // mas antes, eu preciso subtrair o valor da soma do no_atual a cada chamada
            // ou seja, a cada no que eu passo
            soma = soma - (int) no_atual.valor;

            return temsomaRaiz(no_atual.esquerda, soma) || temsomaRaiz(no_atual.direita, soma);
        }
    }

    public boolean busca(T valor){
        return busca(raiz, valor);
    }

    public boolean busca(TipoABB<T> no_atual, T valor){
        // se a minha raiz for nula, o elemento nao se encontra na arvore
        if(no_atual == null){
            return false;
        }

        // verificando se o valor eh igual ao no atual
        if(valor.compareTo(no_atual.valor) == 0){
            // se sim, retorne verdadeiro
            return true;
        }
        // verificando se o valor eh menor que o valor do no_atual
        else if(valor.compareTo(no_atual.valor) < 0){
            // se sim, eu faço a busca na esquerda
            return busca(no_atual.esquerda, valor);
        }
        // se ele nao eh igual, nem menor, ele eh maior que o meu no_atual
        else{

            // sendo, eu busca na subarvore a direita
            return busca(no_atual.direita, valor);
        }
    }

    // exercicio 6
    int profundidade(T valor){
        return profundidade(raiz, valor);
    }

    int profundidade(TipoABB<T> no_atual, T valor){

        // primeiro eu verifico se o valor se encontra na arvore
        if(busca(valor) == false){
            // se ele nao se encontra, sua profundidade eh -1
            return -1;
        }else{

            // se ele eh menor que o valor do no_atual, eu vou para a esquerda
            if(valor.compareTo(no_atual.valor) < 0){
                // 1 + a sua profundidade a esquerda
                return 1 + profundidade(no_atual.esquerda, valor);
            }

            // se ele eh maior que o valor do no_atual, eu vou para a direita
            else if(valor.compareTo(no_atual.valor) > 0){
                // 1 + a sua profundidade a direita
                return 1 + profundidade(no_atual.direita, valor);
            }
            // se ele nao eh menor nem maior, ele eh igual ao valor do no_atual
            else{
                // neste caso, a sua profundidade eh 0, porque eu NAO vou descer
                // um no para a esquerda ou para a direita
                return 0;
            }
        }
    }

    // exercicio 7
    public boolean AVL(){
        return AVL(raiz);
    }

    public boolean AVL(TipoABB<T> no_atual){

        // se a arvore estiver vazia, ela ja atende trivialmente a propriedade da AVL
        if(no_atual == null){
            return true;
        }else{

            int alturaD = altura(no_atual.direita);
            int alturaE = altura(no_atual.esquerda);

            // se a diferenca entre a altura a direita e a altura a esquerda for maior que 1
            // a arvore em questao NAO antende a propriedade da AVL
            if(Math.abs(alturaD - alturaE) > 1){
                return false;
            }
            // se o no atual atende a propriedade, eu vefico se o filho a direita E a esquerda
            // do no atual atende
            else{

                return AVL(no_atual.direita) && AVL(no_atual.esquerda);

            }
        }
    }

    // exercicio 8
    public int quantFolhas(){
        return quantFolhas(raiz);
    }

    public int quantFolhas(TipoABB<T> no_atual){

        // se a raiz for nula, a arvore NAO possui follha
        if(no_atual == null){
            return 0;
        }else{

            // se o no atual eh uma folha, eu retorno 1
            if(no_atual.direita == null && no_atual.esquerda == null){
                return 1;
            }else{

                // senao,  eu conto quantas folhas tem a direita e a esquerda
                return 0 + quantFolhas(no_atual.direita) + quantFolhas(no_atual.esquerda);
            }
        }
    }

    public int quantNos(){
        return quantNos(raiz);
    }

    public int quantNos(TipoABB<T> raiz){
        
        // se a raiz eh nula, a quantidade eh nos eh 0
        if(raiz == null){
            return 0;
        }else{

            return 1 + quantNos(raiz.direita) + quantNos(raiz.esquerda);
        }
    }
    
}