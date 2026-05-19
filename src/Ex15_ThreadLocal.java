// Exercício 15 - ThreadLocal
// Resumo:
//  ThreadLocal<T> dá a cada thread sua PRÓPRIA cópia do valor.
//  Útil para: SimpleDateFormat por thread, contexto de request, IDs etc.
//  Lembre de remove() em pools de threads para evitar leaks.

public class Ex15_ThreadLocal {

    private static final ThreadLocal<Integer> contador =
            ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 3; i++) {
                contador.set(contador.get() + 1);
            }
            System.out.println(Thread.currentThread().getName()
                    + " contador local = " + contador.get());
            contador.remove(); // boa prática
        };

        Thread t1 = new Thread(r, "A");
        Thread t2 = new Thread(r, "B");
        Thread t3 = new Thread(r, "C");

        t1.start(); t2.start(); t3.start();
        t1.join();  t2.join();  t3.join();
        // cada thread terminou com seu próprio "3" — não há compartilhamento
    }
}
