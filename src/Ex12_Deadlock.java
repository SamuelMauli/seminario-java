// Exercício 12 - Deadlock
// Resumo:
//  Deadlock = duas (ou mais) threads se bloqueiam mutuamente esperando locks.
//  Causa clássica: aquisição de locks em ORDEM DIFERENTE.
//  Solução: sempre adquirir locks na MESMA ORDEM, ou usar tryLock com timeout.

public class Ex12_Deadlock {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("t1 pegou A, tentando B...");
                try { Thread.sleep(100); } catch (InterruptedException e) { return; }
                synchronized (lockB) {
                    System.out.println("t1 pegou B");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {            // ordem invertida -> deadlock
                System.out.println("t2 pegou B, tentando A...");
                try { Thread.sleep(100); } catch (InterruptedException e) { return; }
                synchronized (lockA) {
                    System.out.println("t2 pegou A");
                }
            }
        }, "t2");

        t1.start(); t2.start();

        // espera limitada; se travar, encerramos forçado
        t1.join(2000);
        t2.join(2000);
        if (t1.isAlive() || t2.isAlive()) {
            System.out.println(">>> DEADLOCK detectado, encerrando demonstração <<<");
            System.exit(0);
        }
    }
}
