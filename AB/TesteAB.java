package AB;

public class TesteAB {
    public static void main(String[] args) {
        ArvoreBinaria<String> arvore = new ArvoreBinaria<String>();
        String[] lista = {"abc", "aaaaa"};

        arvore.insercaoAleatoria("gustavo");
        arvore.insercaoAleatoria("pedro");
        arvore.insercaoAleatoria("luis");
        arvore.insercaoAleatoria("ana");

        arvore.insereMultiplos(lista);

        System.out.println(arvore.toString());

        System.out.printf("Altura da arvore: %d\n", arvore.altura());
        System.out.println("A arvore esta balandeada? " + arvore.balanceada());
        System.out.println("O menor valor da arvore eh: " + arvore.buscaMenor());
        System.out.println("O maior valor da arvore eh: " + arvore.buscaMaior());


        arvore.remove("gustavo");
        System.out.println("Arvore após a remoção do elemento gustavo\n" + arvore.toString());

    }
}
