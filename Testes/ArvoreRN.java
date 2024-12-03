package Testes;

public class ArvoreRN<T extends Comparable<T>> {
    NoRBN<T> raiz;
    NoRBN<T> nulo;  // referencia para as folhas, que sao nós ficticios e que sao sempre pretos

    public ArvoreRN(){
        nulo = new NoRBN<T>();

        // agora, no inicio a raiz aponta para esse no nulo
        raiz = nulo;
    }

    // fazendo a rotação para a esquerda
    public void rotacaoEsq(NoRBN<T> x){
        NoRBN<T> y = x.direita;  // y,  antes de rotaçao, eh o filho a direita de x

        // agora, o filho a direita de x precisa receber o filho a esquerda do y, que naquela
        // figura eh a subarvore beta. se essa subarvore for != nulo, eu preciso fazer o pai dela
        // referenciar o x.
        // mas quem eh beta? beta eh filho a esquerda de y

        x.direita = y.esquerda;
        if(y.esquerda != nulo){
            y.esquerda.pai = x;
        }

        // agora eu preciso fazer o pai de y apontar para o pai de x (porque x e y vao trocar de lugar)
        // mas pra isso, eu preciso descobrir se x eh a raiz ou se x tem algum pai
        // porque se x tem algum pai, ele poder ser filho a direita ou filho a esquerda de seu pai

        y.pai = x.pai;

        if(x.pai == nulo){
            raiz = y;
        }else{

            // verificando se x eh o filho a esquerda do pai dele
            if(x == x.pai.esquerda){
                x.pai.esquerda = y;
            }else{
                // senao, x eh filho a direta do pai dele
                x.pai.direita = y;

            }
        }

        // agora falta cortar a ligaçao do y com a subarvore beta, e fazer y apontar para x
        // e fazer o pai de x ser agora o y

        // filho esquerda de y passa a ser o x
        y.esquerda = x;

        // pai de x passa a ser o y
        x.pai = y;
    }

    // fazendo a rotação para a direita
    public void rotacaoDir(NoRBN<T> y){
        NoRBN<T> x = y.esquerda; // antes da rotação, x eh filho a esquerda de y

        // agora, o filho a esqurda de y tem que apontar para a subarvore beta
        // mas quem eh essa subarvore beta? antes da rotação beta = filho a direita de x

        y.esquerda = x.direita;

        // agora eu preciso ver se a subarvore beta eh diferente de nula
        // se sim, o pai o de beta aponta para y

        if(x.direita != nulo){
            x.direita.pai = y;
        }

        // agora eu preciso fazer o pai de x apontar para o pai de y (porque x e y vao trocar de lugar)
        // e depois verificar se y era uma raiz, porque se sim, x vai passar a ser a raiz
        // senao, precisamos descobrir se y eh filho a direita ou a esquerda de seu pai

        x.pai = y.pai;

        if(y.pai == nulo){
            // se sim, y era raiz
            raiz = x;
        }else{

            if(y == y.pai.esquerda){
                // y eh filho a esquerda de seu pai
                // logo, na troca, o filho a esqurda do pai de y passa a ser o x
                y.pai.esquerda = x;
            }else{

                // y eh filho a direita de seu pai
                y.pai.direita = x;
            }
        }

        // nesse processo de subir o x e descer o y eu preciso fazer
        // o y pai apontar para x, e o filho a direita de x apontar para y

        x.direita = y;
        y.pai = x;
    }

    // fazendo metodo de insercao
    // fazendo uma insercao iterativa
    void inserir(T valor){

        // primeira coisa: faça a inserção como se fosse uma arvore binaria normal
        // depois restaura as propriedades de arvore rubro negra

        NoRBN<T> anterior, aux;
        anterior = nulo;
        aux = raiz;

        // neste metodo aqui, primeiro vamos verificar se o valor ja esta na arvore. se ele estiver, nao vamos inseri-lo
        while(aux != nulo){
            System.out.println("teste1");
            // salva o no atual antes de tudo
            anterior = aux;

            if(valor.compareTo(aux.valor) < 0){
                aux = aux.esquerda;
            }else if(valor.compareTo(aux.valor) > 0){
                aux = aux.direita;
            }else if(valor.compareTo(aux.valor) == 0){
                return;  // se ele ja estiver, eu n vou inserir ele denovo
            }
        }

        NoRBN<T> novo_no = new NoRBN<>(valor);
        // o pai do novo no eh justamente o antetior, que no caso antes da insercao deste novo_no, era uma folha
        novo_no.pai = anterior;

        // esquerdo e direito deste novo_no vao receber nulo
        novo_no.direita = novo_no.esquerda = nulo;
        novo_no.corVermelho = true;  // todo no inserido eh vermelho

        // nas linhas 127 a 133 eu fiz o novo_no apontar para a arvore. agora eu preciso fazer a arvore
        // apontar para o novo_no
        if(raiz == nulo){
                raiz = novo_no;
        }else if(valor.compareTo(anterior.valor) < 0){
            anterior.esquerda = novo_no;
        }else{
            anterior.direita = novo_no;
        }

        // agora que fizemos a inserção, precisamos restaurar as propriedades da ABR
        restauraPropriedadesRN(novo_no);
}
    

    // ha tres casos a considerar quando o x e o pai de x sao vermelhos
    // o x, que o novo no inserido, eh sempre vermelho
    public void restauraPropriedadesRN(NoRBN<T> x){
        NoRBN<T> y;  // tio de x
        
        System.out.println("teste2");
        // nos temos que avaliar isso enquanto a cor do pai de x for vermelha
        while(x.pai.corVermelho == true){
            // aqui dentro existem dois casos: quando o pai de x eh filho esquerdo do avo de x (1)
            // ou quando o pai de x eh filho direito do avo de x

            System.out.println("teste3");
            // caso 1
            if(x.pai == x.pai.pai.esquerda){
                System.out.println("x eh filho a esquerda de seu pai");
                // precisamos descobrir quem eh o tio de x
                // neste caso, o tio de x eh o filho direito do avo de x
                y = x.pai.pai.direita;

                if(y.corVermelho == true){  // caso 1
                    // tenho que recolorir alguns vertices
                    y.corVermelho = false;  // tio de x eh pintado de preto
                    x.pai.corVermelho = false;  // pai de x eh pintado de preto
                    x.pai.pai.corVermelho = true;  // avo de x eh pintado de 
                    
                    x = x.pai.pai;  // ????

                    System.out.println("teste4");
                }
                // Caso 2: tio do x eh preto e x eh filho direito OU filho esquerdo
                else{

                    System.out.println("x eh filho a esquerda de seu pai");
                    // x eh filho direito do seu pai
                    if(x == x.pai.direita){
                        // faço o x apontar para o pai dele. depois aplico uma rotaçaoEsq em x
                        x = x.pai;
                        rotacaoEsq(x);
                        System.out.println("Rotacao para esquerda realizada com o " + x);
                    }

                    // saindo do Caso 2 eh a entrada do caso 3
                    // aplicando o Caso 3 (x tem um tio y preto e x eh filho esquerdo)

                    x.pai.corVermelho = false;  // pai do x agora eh preto
                    x.pai.pai.corVermelho = true;   // avo de x agr eh vermelho
                    rotacaoDir(x.pai.pai);   // aplico uma rotação para a direita no avo de x
                    System.out.println("Rotacao para direita realizada com o " + x.pai.pai.valor);

                }

            }else{
                // Caso do espelhamento
                y = x.pai.pai.esquerda;

                // aqui sao os casos de espelhamento

                if(y.corVermelho == true){  // Caso 1
                    // tenho que recolorir alguns vertices
                    y.corVermelho = false;
                    x.pai.corVermelho = false;  
                    x.pai.pai.corVermelho = true; 
                    x = x.pai.pai;  // ????
                }
                // Caso 2: tio de x ser preto E ser eh filho esquerdo OU direito
                else{

                    if(x == x.pai.esquerda){  // caso 2 espelhado
                        x = x.pai;
                        rotacaoDir(x);

                    }

                    // saida do caso 2 eh a entrada do caso 3 (agora espelhado)
                    x.pai.corVermelho  = false;
                    x.pai.pai.corVermelho = true;
                    rotacaoEsq(x.pai.pai);
                }

            }
        } // fim do while

        // antes de trocar o metodo eu troco a cor da raiz
        raiz.corVermelho = false;
    }

    void imprimir(){
        inOrdem(raiz, "            ");
    }

    public void inOrdem(NoRBN<T> raiz, String espaco){

        if(raiz != nulo){
            inOrdem(raiz.esquerda, "            " + espaco);
            System.out.println(espaco + raiz.valor +"," +raiz.corVermelho);
            inOrdem(raiz.direita, "            "+ espaco);
        }
    }
}
