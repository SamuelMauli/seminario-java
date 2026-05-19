// Exercício 07 - final, Imutabilidade e Visibilidade Segura
// Resumo:
//  - Campos final só podem ser atribuídos uma vez (no construtor).
//  - O JMM garante que, após o construtor terminar, outras threads enxergam
//    os campos final corretamente inicializados (safe publication).
//  - Objetos imutáveis (todos os campos final, sem setters) são thread-safe por natureza.

public final class Ex07_FinalImutabilidade {

    // Classe imutável: pode ser compartilhada entre threads sem sincronização
    static final class Ponto {
        private final int x;
        private final int y;

        Ponto(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }

        // operações retornam novo objeto (estilo "copy on write")
        public Ponto deslocar(int dx, int dy) {
            return new Ponto(x + dx, y + dy);
        }

        @Override public String toString() { return "(" + x + "," + y + ")"; }
    }

    public static void main(String[] args) throws InterruptedException {
        final Ponto origem = new Ponto(0, 0); // compartilhado entre threads

        Thread t1 = new Thread(() -> System.out.println("t1 vê: " + origem));
        Thread t2 = new Thread(() -> System.out.println("t2 vê: " + origem.deslocar(3, 4)));

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("origem permanece: " + origem);
    }
}
