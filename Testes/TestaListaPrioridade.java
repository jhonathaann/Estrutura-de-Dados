package Testes;

public class TestaListaPrioridade {
    public static void main(String[] args) {
        ListaPrioridade<String> lista = new ListaPrioridade<>();

        lista.inserir("A", 100);
        lista.inserir("B", 30);
        lista.inserir("C", 20);
        lista.inserir("D", 51);
        lista.inserir("E", 41);
        lista.inserir("E", 200);

        System.out.printf("\nTamanho: %d\n", lista.tamanho);

        System.out.println("Lista após inserções:");
        lista.printList();

        System.out.println("\nRemovendo o elemento de maior prioridade: " + lista.removeMax());
        System.out.println("Lista após remover o elemento de maior prioridade:");
        lista.printList();

        System.out.println("\nRemovendo o elemento de maior prioridade: " + lista.removeMax());
        System.out.println("Lista após remover o elemento de maior prioridade:");
        lista.printList();
    }
}
