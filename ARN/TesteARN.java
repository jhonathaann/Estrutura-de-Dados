package ARN;

public class TesteARN {
    public static void main(String[] args) {
        ArvoreRN<Integer> arvore = new ArvoreRN<Integer>();
        int valor = 5;

        arvore.insercao(10);
        arvore.insercao(5);
        arvore.insercao(1);
        arvore.insercao(12);
        arvore.insercao(122);
        //arvore.insercao(13);
        //arvore.insercao(14);
        //arvore.insercao(50);

        //arvore.raiz.direita.corVermelha = true;
        arvore.raiz.esquerda.direita.corVermelha = true;
        
        
        System.out.println("Valor " + valor + " se encontra na arovere? " + arvore.busca(valor));
        System.out.println("Quantidade de Nós vermelhos que a arvore possui é: " +  arvore.quantVermelhos());
        System.out.println("A arvore possui algum vertice vermelho com PELO MENOS 1 filho vermelho? " + arvore.FilhosVerm());
        //System.out.println(arvore.alturaPreta());
        System.out.println("A arvore atende as propriedade de um arvore rubro negra? " + arvore.verificaPropriedades());
        System.out.println("Profundidade do Nó " + valor + " na arvore: " + arvore.profundidade(valor));
        //arvore.inOrdem();

        arvore.imprimir();
    }
}
