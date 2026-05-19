// Exercício 09 - wait / notify / notifyAll (Produtor-Consumidor)
// Resumo:
//  - wait()      libera o monitor e bloqueia até ser notificado
//  - notify()    acorda UMA thread esperando no monitor
//  - notifyAll() acorda TODAS
//  - Sempre chamar dentro de synchronized e dentro de um while (não if): "spurious wakeups".

import java.util.ArrayDeque;
import java.util.Queue;

public class Ex09_WaitNotify {

    private static final int CAPACIDADE = 3;
    private static final Queue<Integer> fila = new ArrayDeque<>();
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread produtor = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    synchronized (lock) {
                        while (fila.size() == CAPACIDADE) lock.wait();
                        fila.add(i);
                        System.out.println("produziu " + i + " | fila=" + fila);
                        lock.notifyAll();
                    }
                    Thread.sleep(50);
                }
            } catch (InterruptedException ignored) {}
        }, "produtor");

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    synchronized (lock) {
                        while (fila.isEmpty()) lock.wait();
                        int v = fila.poll();
                        System.out.println("consumiu " + v + " | fila=" + fila);
                        lock.notifyAll();
                    }
                    Thread.sleep(120);
                }
            } catch (InterruptedException ignored) {}
        }, "consumidor");

        produtor.start();
        consumidor.start();
        produtor.join();
        consumidor.join();
    }
}
