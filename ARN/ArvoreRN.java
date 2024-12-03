package ARN;

public class ArvoreRN<T extends Comparable<T>> {
    TipoARN<T> raiz;
    int tamanho;

    public ArvoreRN(){
        this.raiz = null;
        tamanho = 0;
    }

    // metodo para realizar rotacao para esquerda
    public TipoARN<T> rotEsq(TipoARN<T> A){
        // inicialemte A era a raiz da subarvore
        TipoARN<T> B = A.direita;  // o filho a direita do A vai passar a ser a nova raiz da subarvore
        A.direita = B.esquerda;  // o filho a esquerda do B passa a ser o filho a direita do A
        B.esquerda = A;   // A se torna filho a direta do B (nova raiz)
        B.corVermelha = A.corVermelha;   // B recece a cor do A
        A.corVermelha = true;    // A fica vermelho

        return B;   // retorna a nova raiz da subarvore
    }

    

    // metodo que realiza a rotacao para a direita
    public TipoARN<T> rotDir(TipoARN<T> A){
        // inicialmente o A era a raiz da subarvore
        TipoARN<T> B = A.esquerda;   // o filho a esquerda do A vai passar a ser a nova raiz da subarvore
        A.esquerda = B.direita;  // o filho a direita do B vai passar a ser o filho a esquerda do A
        B.direita = A;     // A se torna filho a direita do B
        B.corVermelha = A.corVermelha;    // B recebe a cor do A
        A.corVermelha = true;    // pinta o A de vermelho

        return B;   // retorna a nova raiz da subarvore
    }

    // metodo para inverter a cor de um vertice e de seus filhos
    public void inverteCores(TipoARN<T> raiz){
        raiz.corVermelha = !raiz.corVermelha;   // inverte a cor do vertice pai
        raiz.direita.corVermelha = !raiz.direita.corVermelha;    // inverte a cor do filho a direita
        raiz.esquerda.corVermelha = !raiz.esquerda.corVermelha;   // inverte a cor do filho a esquerda
    }

    // método para verificar se um nó eh vermelho
    public boolean ehVermelho(TipoARN<T> no){
        if(no == null){
            return false;  // todo folha eh preta
        }

        return no.corVermelha;   // esse campo indica a cor. true = vermelho; false = preto
    }


    public int altura(TipoARN<T> no){
        if(no == null){
            return -1;   // arvore vazia tem altura menos 1
        }

        // a altura eh 1 + a maior altura entre a subarvore a esquerda e subarvore a direita
        return 1 + Math.max(altura(no.direita), altura(no.esquerda));

    }

    public void insercao(T valor){
        raiz = insercao(raiz, valor);
        raiz.corVermelha = false;   // raiz eh sempre preta
    }

    public TipoARN<T> insercao(TipoARN<T> no_atual, T valor){
        // se a arvore se encontra vazia, eu insiro logo na raiz
        if(no_atual == null){
            tamanho++;
            return new TipoARN<T>(valor);
        }

        int resultado = valor.compareTo(no_atual.valor);   // faça a comparacao entre os dois valores
        if(resultado == 0){
            // o valor que eu quero inserir eh igual ao valor do meu vertice atual. nesse caso, eu sobrescrevo
            no_atual.valor = valor;
        }
        // se o valor eh menor que o valor do meu vertice atual, eu vou para a esquerda
        else if(resultado < 0){
            no_atual.esquerda = insercao(no_atual.esquerda, valor);
        }
        // se ele nao eh igual nem menor, ele eh maior
        else{
            // neste caso, eu vou para a direita
            no_atual.direita = insercao(no_atual.direita, valor);
        }

        //  quando chegar nessa parte do codigo, a insercao ja foi feita
        // e no_atual.direita OU no_atual.esquerda aponta para o novo no

        // agora eh preciso balancear a arvore com as rotacoes e com o recolorimento
        
        // corrigindo o caso de filho direito vermelho E filho esquerdo preto
        if(ehVermelho(no_atual.direita) && !ehVermelho(no_atual.esquerda)){
            // faz apenas uma rotacao para esquerda
            no_atual = rotEsq(no_atual);
        }

        // corrigindo o caso de dois filhos esquerdos vermelho
        if(ehVermelho(no_atual.esquerda) && ehVermelho(no_atual.esquerda.esquerda)){
            // faz apenas uma rotacao para direita
            no_atual = rotDir(no_atual);
        }

        // corrigindo o caso de dois filhos vermelhos
        if(ehVermelho(no_atual.esquerda) && ehVermelho(no_atual.direita)){
            // apenas faz uma recoloracao
            inverteCores(no_atual);
        }

        return no_atual;   // retorna o no ajustado
    }


    void imprimir(){
        inOrdem(raiz, "            ");
    }

    public void inOrdem(TipoARN<T> raiz, String espaco){

        if(raiz != null){
            inOrdem(raiz.esquerda, "            " + espaco);
            System.out.println(espaco + raiz.valor +"," +raiz.corVermelha);
            inOrdem(raiz.direita, "            "+ espaco);
        }
    }

    // exercicio 4 (inOrdem, que eh o ordenado)
    public void inOrdem(){
        inOrdem(raiz);
    }
    
    public void inOrdem(TipoARN<T> raiz){
        if(raiz == null){
            return;
        }
    
        inOrdem(raiz.esquerda);
        System.out.printf("%d ", raiz.valor);
           inOrdem(raiz.direita);
    }


     // exercicio 2
     public boolean busca(T valor){
        return busca(raiz, valor);
    }

    public boolean busca(TipoARN<T> no_atual, T valor){

        // se a raiz for nula, a arvore esta vazia, logo o elemento n se encontra na arvore
        if(no_atual == null){
            return false;
        }else{

            // verifica se o valor eh igual ao valor que se encontra no meu no atual
            if(valor.compareTo(no_atual.valor) == 0){
                // se sim, eu encontrei o elemento
                return true;
            }else if(valor.compareTo(no_atual.valor) < 0){  // valor menor que o valor que se encontra no meu no atual
                // busco na subarvore a esquerda
                return busca(no_atual.esquerda, valor);
                
            }else{
                // valor maior que o valor que se encontra no meu no atual. irei buscar na subarvore a direita
                return busca(no_atual.direita, valor);  
            }
        }
        
    }

    // exercicio 5 (profundidade de um nó = sua distancia ate a raiz)
    public int profundidade(T valor){
        // primeiro, eu verifico se ele se encontra na arvore
        if(busca(valor)  == false){
            // se ele nao se encontra, sua profundidade eh -1
            return -1;
        }

        return profundidade(raiz, valor);
    }

    public int profundidade(TipoARN<T> raiz, T valor){
        // se a arvore se encontra vazia, a profundidade desse nó eh -1 tbm
        if(raiz == null){
            return -1;
        }

        // se ele eh igual ao valor da raiz, ent sua profundidade eh 0 (lembrando que na arvore nos nao temos elementos repetidos)
        if(valor.compareTo(raiz.valor) == 0){
            return 0;
        }
        // se ele eh menor que o valor do meu no atual, eu vou para a esquerda da minha raiz atual
        else if(valor.compareTo(raiz.valor) < 0){
            return 1 + profundidade(raiz.esquerda, valor);
        }

        // se ele nao eh igual e nem menor, ele eh maior
        else{
            return 1 + profundidade(raiz.direita, valor);
        }
    }

    // metodo que retorna a quant de nos  (ou arestas tbm. pq nessa implementacao os dois vao estar em mesmas quant) vermelhos que a arvore possui
    public int quantVermelhos(){
        return quantVermelhos(raiz);
    }

    public int quantVermelhos(TipoARN<T> raiz){
        if(raiz == null){
            return 0;
        }

        // verifica se o no atual eh vermelho
        if(raiz.corVermelha == true){
            return 1 + quantVermelhos(raiz.esquerda) + quantVermelhos(raiz.direita);
        }else{
            return 0 + quantVermelhos(raiz.esquerda) + quantVermelhos(raiz.direita);
        }
    }
 

    // metodo que retorna a quant de nos pretos que a arvore possui
    public int quantPretos(){
        return quantPretos(raiz);
    }

    public int quantPretos(TipoARN<T> raiz){
        if(raiz == null){
            return 0;
        }else{

            //  verifica se o no atual eh ptrto
            if(raiz.corVermelha == false){
                return 1 + quantPretos(raiz.direita) + quantPretos(raiz.esquerda);
            }else{

                return 0 + quantPretos(raiz.direita) + quantPretos(raiz.esquerda);
            }
        }
    }


    // exercicio 3
    public boolean verificaPropriedades(){
        // verificando literalmente so a cor da raiz da minha arvore
        if(raiz.corVermelha == true){
            // raiz eh vermelha. oq nao pode
            return false;

        }
        // vericicando se um nó vermelho possui ao menos 1 filho vermelho
        // essa ideia eh a mesma de verificar o seguinte: cada no pode ter no maximo
        // uma aresta vermelha incidente nele (e isso inclui a aresta do pai)
        if(FilhosVerm() == true){  
            // se sim, a arvore NAO esta balanceada com a prop rubro negras
            return false;
        }

        // verificando se a arvore possui ao menos um vertice com filho vermelho
        if(filhoDirVermellho() == true){
            // se sim, a arvore NAO esta balenceada
            return false;
        }
        else{
            return verificaPropriedades(raiz);
        }
    }

    public boolean verificaPropriedades(TipoARN<T> raiz){
        // se arvore esta vazia, ela atende trivialmente as propriedades
        if(raiz == null){
            return true;
        }

        // calcule a altura preta direita e a altura preta esquerda do no atual
        int alturaDir = alturaPreta(raiz.direita);
        int alturaEsq = alturaPreta(raiz.esquerda);
        
        if(alturaDir != alturaEsq){
            // se elas forem diferentes, a arovore NAO esta balenceada
            return false;
        }else{
            // se o no atual atende essa propriedade, eu vou para os filhos dele testar essa prop
            verificaPropriedades(raiz.direita);
            verificaPropriedades(raiz.esquerda);
        }
        return true;
    }


     // metodo que retorna verdadeiro se um nó VERMELHO possui PELO MENOS UM FILHO VERMELHO
     public boolean FilhosVerm(){
        return FilhosVerm(raiz);
    }

    public boolean FilhosVerm(TipoARN<T> raiz){

        if(raiz == null){
            // se a raiz eh nula ele (no atual) NAO tem dois filhos vermelhos
            return false;
        }
        
        if(raiz.corVermelha == true){
            // se o no eh vermelho, ele nao pode ter filhos vermelhos
            // OBS: primeiro eu verifico se o no em questao tem filho pra esquerda ou pra direita
            if((raiz.direita != null && raiz.direita.corVermelha == true) 
                        || (raiz.esquerda != null && raiz.esquerda.corVermelha == true)){
                return true;
            }

            return false;  //  o no em questao, NAO possui dois filhos vermelhos

        }else{

            boolean arvoreDir = FilhosVerm(raiz.direita);
            boolean arvoreEsq = FilhosVerm(raiz.esquerda);

            if(arvoreDir == true || arvoreEsq == true){
                // se um vertive da subarvore a esq OU um vertice da subarvore a direita eh VERMELHO
                // E tem pelo menos um filho vermelho, eu achei um vertice que quebra esta propri. da arvore
                return true;
            }else{
                return false;
            }

        }
    }
    
    // metodo que verifica a quantidade de nós pretos que um vertice possui
    // tanto pela esquerda dele quanto pela direita dele
    public int alturaPreta(){
        return alturaPreta(raiz);
    }

    public int alturaPreta(TipoARN<T> raiz){
        // se eu NAO tenho nenhum no, a altura negra eh -1
        if(raiz == null){
            return -1;
        }

        // calculando a altura preta dos filhos a esquerda e a direita
        int alturaDir = alturaPreta(raiz.direita);
        int alturaEsq = alturaPreta(raiz.esquerda);

        int alturaPretaAtual = 0;

        // se o no atual for preto, alturaPretaAtual recebe 1
        if(raiz.corVermelha == false){
            alturaPretaAtual = 1;
        }

        return alturaPretaAtual + Math.max(alturaEsq, alturaDir);
    }

    // metodo que verifica se a arvore possui ao menos um vertice x que tem um filho direito vermelho
    // ou seja, a aresta do filho a direta de x eh vermelha. e neste caso, isso NAO pode. a aresta 
    // vermelha eh so para o filho a esquerda de qualquer vertice da arvore
    public boolean filhoDirVermellho(){
        return filhoDirVermellho(raiz);
    }

    public boolean filhoDirVermellho(TipoARN<T> raiz){
        // se a arvore esta vazia, ela NAO tem um vertice com filho vermelho
        if(raiz == null){
            return false;
        }

        if( raiz.direita != null && raiz.direita.corVermelha == true){
            return true;
        }else{

            // basta encontrar um vertice na subarovre a esquerda que tem um filho direito vermelho
            // OU um vertice na subarvore a direita que tem um filho direito vermelho
            return filhoDirVermellho(raiz.direita) || filhoDirVermellho(raiz.esquerda);
        }
    }


}
