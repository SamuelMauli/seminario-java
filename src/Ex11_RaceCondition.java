// Exercício 11 - Race Condition
// Resumo:
//  Race condition acontece quando o resultado depende da ordem de execução
//  de threads concorrentes sobre dados compartilhados sem sincronização.
//  Aqui i++ não é atômico (load-add-store): perdemos atualizações.

public class Ex11_RaceCondition {

    static int inseguro = 0;       // sem proteção -> race condition
    static int seguro = 0;         // com synchronized

    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 100_000; i++) {
                inseguro++;                                  // BUG: race
                synchronized (lock) { seguro++; }            // OK
            }
        };

        Thread t1 = new Thread(r), t2 = new Thread(r);
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("inseguro = " + inseguro + " (esperado 200000)");
        System.out.println("seguro   = " + seguro   + " (esperado 200000)");
    }
}
