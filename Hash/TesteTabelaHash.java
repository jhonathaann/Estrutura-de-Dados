package Hash;
import java.util.LinkedList;
public class TesteTabelaHash {
    public static void main(String[] args) {
        HashTable<Integer, String> testHash = new HashTable<Integer, String>(10);
        testHash.insercao(1, "bruno");
        testHash.insercao(2, "bruno");
        testHash.insercao(1, "lucas");
        testHash.insercao(3, "joao");
        testHash.insercao(3, "jose");
        testHash.insercao(3, "luis");
        testHash.insercao(1, "luis");

        testHash.print();

        LinkedList<String> valoresRetornados = testHash.get(1);
        for(int i = 0; i < valoresRetornados.size(); i++){
            System.out.println(valoresRetornados.get(i));
        }

        System.out.println(testHash.elementosPosicao(3));
        System.out.println(testHash.remover(1, "luis"));

       testHash.print();
    }
    
}
