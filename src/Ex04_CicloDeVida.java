// Exercício 04 - Ciclo de Vida de uma Thread
// Resumo dos estados (enum Thread.State):
//  NEW           -> criada mas ainda não iniciada
//  RUNNABLE      -> pronta/executando (depende do scheduler)
//  BLOCKED       -> aguardando monitor (synchronized)
//  WAITING       -> aguardando outra thread sem timeout (wait, join)
//  TIMED_WAITING -> aguardando com timeout (sleep, join(ms), wait(ms))
//  TERMINATED    -> terminou

public class Ex04_CicloDeVida {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(300);            // TIMED_WAITING
                synchronized (lock) {         // pode passar por BLOCKED
                    lock.wait(200);           // TIMED_WAITING
                }
            } catch (InterruptedException ignored) {}
        }, "ciclo");

        System.out.println("antes do start: " + t.getState()); // NEW
        t.start();

        Thread.sleep(50);
        System.out.println("durante sleep:  " + t.getState()); // TIMED_WAITING

        Thread.sleep(400);
        System.out.println("durante wait:   " + t.getState()); // TIMED_WAITING (ou RUNNABLE)

        t.join();
        System.out.println("depois do join: " + t.getState()); // TERMINATED
    }
}
