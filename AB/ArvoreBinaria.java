package AB;
import java.util.Random;

public class ArvoreBinaria<T extends Comparable<T>>{
    NoAB<T> raiz;  // raiz da arvore
    private static Random rand = new Random();
    
    // inicializar a arvore
    public ArvoreBinaria(){
        this.raiz  = null;
    }

    public void insercaoAleatoria(T valor){

        boolean insercao = false;

        // se a arvore esta vazia, a insercao ira ocorrer na raiz
        if(raiz == null){
            raiz = new NoAB<T>(valor);
        }else{

            // preciso de uma variavel auxiliar para percorrer a arvore
            NoAB<T> aux = raiz;

            while(insercao == false){

                // se for true, vamos para a esquerda
                if(rand.nextBoolean() == true){

                    // se o no a esquerda do meu no atual for nulo, insere
                    if(aux.esquerda == null){
                        aux.esquerda = new NoAB<T>(valor);
                        insercao = true;
                    }else{
                        // senao, caminhe para a esquerda;
                        aux = aux.esquerda;
                    }

                    
                }else{
                    // se for false, vamos para a direita

                    // se a dereita do meu no atual for nullo, insere
                    if(aux.direita == null){
                        aux.direita = new NoAB<T>(valor);
                        insercao = true;
                    }else{
                        // senao, caminhe para a direita
                        aux = aux.direita;
                    }
                }

               
            }
        }
    }


    public boolean busca(T valor){
        return busca(raiz, valor);
    }

    public boolean busca(NoAB<T> raiz, T valor){
        
        if(raiz == null){
            return false;
        }

        if(raiz.valor == valor){
            return true;
        }

        if(busca(raiz.direita, valor) == true){
            return true;
        }

        if(busca(raiz.esquerda, valor) == true){
            return true;
        }

        return false;
    }


    public void insereMultiplos(T[] lista){

        for(int i = 0; i < lista.length; i++){
            insercaoAleatoria(lista[i]);
        }
    }


    public int altura(){
        return altura(raiz);
    }

    public int altura(NoAB<T> raiz){
        
        if(raiz == null){
            return -1;
        }else{
            
            return 1 + Math.max(altura(raiz.direita), altura(raiz.esquerda));
        }
    }


    public boolean balanceada(){
        return balanceada(raiz);
    }

    public boolean balanceada(NoAB<T> raiz){
        if(raiz == null){
            return false;
        }else{
            int alturaD = altura(raiz.direita);
            int alturaE = altura(raiz.esquerda);

            alturaD = alturaD - alturaE;

            if(Math.abs(alturaD) > 1){
                return false; // nao esta balanceada
            }else{
                return true;
            }
        }
    }

    public T buscaMenor(){
        return buscaMenor(raiz);
    }

    public T buscaMenor(NoAB<T> raiz){

        if(raiz == null){
            return null;
        }else{

            T menorValor = raiz.valor;
            T menorEsquerda = buscaMenor(raiz.esquerda);
            T menorDireita = buscaMenor(raiz.direita);

            // se o no a esquerda eh menor que o nosso menor atual, trocamos (atualizamos o menor valor)
            if(menorEsquerda != null && menorEsquerda.compareTo(menorValor) < 0){
                menorValor = menorEsquerda;
            }

            // se o no a direita eh menor que o no atual, trocamos (atualizamos o menor valor)
            if(menorDireita != null && menorDireita.compareTo(menorValor) < 0){
                menorValor = menorDireita;
            }


            return menorValor;
        }
        
    }

    public T buscaMaior(){
        return buscaMaior(raiz);
    }

    public T buscaMaior(NoAB<T> raiz){

        if(raiz == null){
            return null;
        }else{

            T maiorValor = raiz.valor;
            T maiorEsquerda = buscaMaior(raiz.esquerda);
            T maiorDireita = buscaMaior(raiz.direita);


            // se o no da esquerda for maior que o no atual, trocamos (atualzimos o maior valor)
            if(maiorEsquerda != null && maiorEsquerda.compareTo(maiorValor) > 0){
                maiorValor = maiorEsquerda;
            }
            
            // se o no da direita for maior que o no atual, trocamos (atualzimos o maior valor)
            if(maiorDireita != null && maiorDireita.compareTo(maiorValor) > 0){
                maiorValor = maiorDireita;
            }

            return maiorValor;
        }
    }

    public void remove(T valor){
        raiz = remove(raiz, valor);
    }

    private NoAB<T> remove(NoAB<T> raiz, T valor){
        
        if(raiz == null){
            return null;
        }else{

            // achei o no a ser removido
            if(valor.compareTo(raiz.valor) == 0){
                // verificando se o no a ser removido eh uma folha, ou seja, não tem filhos
                if(raiz.direita == null && raiz.esquerda == null){
                    // se sim, retornamos nulo
                    return null;

                }else if(raiz.esquerda == null){   // no so tem um filho(a direita)
                    return raiz.direita;   // retorno o no a direita para substituir o no a ser removido

                }else if(raiz.direita == null){   // no a ser removido so tem o filho a esquerda
                    return raiz.esquerda;


                }else{  // aqui, o no a ser removido possui dois filhos

                    // neste caso da AB, eu posso escolher qual no eu vou subir
                    // ja que a ordenação nesta arvore nao importa. eu vou subir o no da direita
                    // mas antes disso, eu preciso atualizar o no da esquerda do direita(que vai subir)
                    // para nao perder o no da esquerda do no que vai ser removido
                    raiz.direita.esquerda = raiz.esquerda;
                    return raiz.direita;
                }       
            }else{
                // busco pela esquerda e pela direibta da raiz atual o valor a ser removido
                remove(raiz.direita, valor);
                remove(raiz.esquerda, valor);
            }
        }

        return raiz;  // retorno a raiz atualizada
    }



    public String toString() {

        StringBuilder out = new StringBuilder();

        if (raiz == null) {

            out.append("<null>\n");

        }

        else {

            toString(raiz.direita, "", false, out);

            out.append(raiz.valor.toString() + "\n");

            toString(raiz.esquerda, "", true, out);

            out.append("\n");

        }

        return out.toString();

    }

    

    private void toString(NoAB<T> node, String prefix, boolean leftChild, StringBuilder out) {

        if (node == null)

            return;

        if (leftChild) {

            toString(node.direita, prefix + "| ", false, out);

            out.append(prefix + "|>" + node.valor.toString() + "\n");

            toString(node.esquerda, prefix + " ", true, out);

        }

        else {

            toString(node.direita, prefix + " ", false, out);

            out.append(prefix + "|>" + node.valor.toString() + "\n");

            toString(node.esquerda, prefix + "| ", true, out);

        }

    }
}