// Exercício 01 - Criação de Threads: Thread vs Runnable
// Resumo: Há duas formas básicas de criar threads em Java:
//  1) Estender java.lang.Thread e sobrescrever run()
//  2) Implementar java.lang.Runnable e passar para new Thread(runnable)
// A forma com Runnable é preferida (composição > herança).

public class Ex01_ThreadVsRunnable {

    // Forma 1: estendendo Thread
    static class MinhaThread extends Thread {
        @Override
        public void run() {
            System.out.println("[Thread] rodando em: " + Thread.currentThread().getName());
        }
    }

    // Forma 2: implementando Runnable
    static class MeuRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("[Runnable] rodando em: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MinhaThread();
        Thread t2 = new Thread(new MeuRunnable(), "worker-runnable");
        Thread t3 = new Thread(() -> System.out.println("[Lambda] rodando em: "
                + Thread.currentThread().getName()), "worker-lambda");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("main terminou");
    }
}
