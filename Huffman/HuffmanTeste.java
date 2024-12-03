package Huffman;

public class HuffmanTeste {
    public static void main(String[] args) {
        String texto = "ola";
        HuffmanTrie huffman = new HuffmanTrie(texto);
        String comprimida = huffman.comprime(texto);
        String descomprimida = huffman.descomprime(comprimida);

        System.out.println("Mensagem Comprimida: " + comprimida);
        System.out.println("Mensagem Descomprimida: " + descomprimida);
        System.out.println(huffman.getHuffmanTable());

    }
}
