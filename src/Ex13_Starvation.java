// Exercício 13 - Starvation (inanição)
// Resumo:
//  Uma thread "passa fome" quando outras de maior prioridade ou mais agressivas
//  monopolizam o recurso (CPU, lock) e ela quase nunca executa.
//  Aqui simulamos com prioridades extremas + monopolizando o lock.

public class Ex13_Starvation {

    private static final Object lock = new Object();
    private static volatile boolean rodar = true;

    public static void main(String[] args) throws InterruptedException {
        Runnable guloso = () -> {
            while (rodar) {
                synchronized (lock) {
                    // segura o lock por um bom tempo
                    long fim = System.currentTimeMillis() + 50;
                    while (System.currentTimeMillis() < fim) { /* busy */ }
                    System.out.println(Thread.currentThread().getName() + " trabalhou");
                }
            }
        };

        Runnable faminto = () -> {
            int trabalhei = 0;
            while (rodar) {
                synchronized (lock) {
                    trabalhei++;
                }
            }
            System.out.println("faminto executou só " + trabalhei + " vezes");
        };

        Thread g1 = new Thread(guloso, "guloso-1");
        Thread g2 = new Thread(guloso, "guloso-2");
        Thread f  = new Thread(faminto, "faminto");

        g1.setPriority(Thread.MAX_PRIORITY);
        g2.setPriority(Thread.MAX_PRIORITY);
        f.setPriority(Thread.MIN_PRIORITY);

        g1.start(); g2.start(); f.start();
        Thread.sleep(1000);
        rodar = false;

        g1.join(); g2.join(); f.join();
    }
}
