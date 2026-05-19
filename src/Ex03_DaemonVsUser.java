// Exercício 03 - Daemon Threads vs User Threads
// Resumo:
//  - User thread: a JVM espera ela terminar antes de encerrar.
//  - Daemon thread: thread "de fundo" (ex.: GC). Quando só restam daemons, a JVM encerra.
//  - setDaemon(true) precisa ser chamado ANTES de start().

public class Ex03_DaemonVsUser {

    public static void main(String[] args) throws InterruptedException {
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("daemon trabalhando...");
                try { Thread.sleep(200); } catch (InterruptedException e) { return; }
            }
        }, "daemon-worker");
        daemon.setDaemon(true); // marca como daemon
        daemon.start();

        Thread user = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("user: passo " + i);
                try { Thread.sleep(150); } catch (InterruptedException e) { return; }
            }
        }, "user-worker");
        user.start();

        user.join(); // espera só a user
        System.out.println("main termina; daemon morre junto com a JVM");
    }
}
