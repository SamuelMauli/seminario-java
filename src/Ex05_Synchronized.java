// Exercício 05 - synchronized
// Resumo:
//  - synchronized garante exclusão mútua (só uma thread por vez no bloco)
//  - Também garante visibilidade (publica escritas para outras threads)
//  - Pode ser em método (lock = this ou Class) ou em bloco (lock = objeto escolhido)

public class Ex05_Synchronized {

    static class Contador {
        private int valor = 0;

        // método sincronizado: lock implícito = this
        public synchronized void incrementar() {
            valor++;
        }

        // bloco sincronizado: lock explícito
        public int lerComBloco() {
            synchronized (this) {
                return valor;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Contador c = new Contador();
        Runnable tarefa = () -> {
            for (int i = 0; i < 10_000; i++) c.incrementar();
        };

        Thread t1 = new Thread(tarefa);
        Thread t2 = new Thread(tarefa);
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("valor final = " + c.lerComBloco()); // 20000 garantido
    }
}
