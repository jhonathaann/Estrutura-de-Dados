package FilaDePrioridade;

public class TesteListaPrioridade {

    public static void main(String[] args) {
        ListaPrioridade<String> lista = new ListaPrioridade<>();


        // inserindo os valores na lista
        lista.insercao("a", 10);
        lista.insercao("b", 1);
        lista.insercao("c", 11);
        lista.insercao("z", 10);

        if(lista.estaVazia() == true){
            System.out.println("\nA lista esta vazia");
        }else{
            System.out.println("\nA lista NÃO esta vazia");
        }
        
        System.out.println("\nTamanho da lista: " + lista.tamanho);
        lista.printList();

        lista.removeMax();

        System.out.println("\nTamanho da lista: " + lista.size());
        lista.printList();

        
        if(lista.busca("b") == true){
            System.out.println("Valor encontardo na lista!");
        }else{
            System.out.println("Valo NAO encontrado na lista");
        }
        
    }
   
}
