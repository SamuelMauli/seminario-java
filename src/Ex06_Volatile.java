// Exercício 06 - volatile
// Resumo:
//  - volatile garante VISIBILIDADE entre threads (sem cache local desatualizado)
//  - NÃO garante atomicidade (ex.: i++ continua quebrado)
//  - Caso clássico: flag de parada de uma thread.

public class Ex06_Volatile {

    private static volatile boolean rodando = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            long i = 0;
            while (rodando) {        // sem volatile, poderia rodar para sempre
                i++;
            }
            System.out.println("worker parou, iterou " + i + " vezes");
        }, "worker");
        t.start();

        Thread.sleep(300);
        rodando = false;             // outra thread enxerga rápido
        t.join();
        System.out.println("main terminou");
    }
}
