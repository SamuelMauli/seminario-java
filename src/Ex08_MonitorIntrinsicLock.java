// Exercício 08 - Monitores e Intrinsic Lock
// Resumo:
//  - Todo objeto em Java tem um "monitor" (intrinsic/monitor lock).
//  - synchronized(obj) adquire o monitor de obj; só uma thread por vez entra.
//  - O lock é REENTRANTE: a mesma thread pode adquirir o mesmo lock várias vezes.

public class Ex08_MonitorIntrinsicLock {

    private final Object monitor = new Object();
    private int chamadasA = 0;
    private int chamadasB = 0;

    public void metodoA() {
        synchronized (monitor) {
            chamadasA++;
            metodoB(); // reentrante: mesma thread re-adquire o mesmo monitor
        }
    }

    public void metodoB() {
        synchronized (monitor) {
            chamadasB++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Ex08_MonitorIntrinsicLock app = new Ex08_MonitorIntrinsicLock();

        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) app.metodoA();
        };

        Thread t1 = new Thread(r), t2 = new Thread(r);
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("chamadasA = " + app.chamadasA);
        System.out.println("chamadasB = " + app.chamadasB);
    }
}
