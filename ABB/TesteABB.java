package ABB;

public class TesteABB {
    public static void main(String[] args) {
        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<Integer>();

        arvore.insercao(50);
        arvore.insercao(30);
        arvore.insercao(70);
        arvore.insercao(20);
        arvore.insercao(40);
        arvore.insercao(60);
        arvore.insercao(80);
        arvore.insercao(10);
        
       arvore.remove(30);
        

        //arvore.imprimir(arvore.raiz);
        //System.out.println();
        arvore.imprimir();
        System.out.println();
        //arvore.imprimirPosOrdem(arvore.raiz);

        System.out.println("\nA arvore tem altura: " + arvore.altura());
        System.out.println("Existe um caminho da raiz a folha, cuja soma destes nós seja igual a 121? " + arvore.temsomaRaiz(121));
        System.out.println("O valor 72 se encontra da arvore? " + arvore.busca(72));
        System.out.println("Qual a profundidade do no 10? " + arvore.profundidade(10));
        System.out.println("A arvore atende a propriedade AVL? " + arvore.AVL());
        System.out.println("Quantidade de Nós folha na arvore: " + arvore.quantFolhas());
        System.out.println("Quantidade de Nós que a arvore possui: " + arvore.quantNos());
    }
    
}
    
