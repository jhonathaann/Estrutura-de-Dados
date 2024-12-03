package Testes;

public class TesteABB {
    public static void main(String[] args) {
        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
        int valor;
        
        // inserindo valores na arvore
        abb.inserir(50);
        abb.inserir(30);
        abb.inserir(70);
        abb.inserir(20);
        abb.inserir(40);
        abb.inserir(60);
        abb.inserir(80);

        System.out.println("Arvore na ordem em que os elementos foram inseridos:");
        abb.imprimir(abb.inicio);
        System.out.println("\nArvore Ordenada:");
        abb.imprimirOrdenado(abb.inicio);


        // testar a busca
        valor = 41;
        if (abb.busca(abb.inicio, valor) == true) {  
            System.out.println("\nValor " + valor + " encontrado na árvore.");
        } else {
            System.out.println("\nValor " + valor + " não encontrado na árvore.");
        }
    }
}
