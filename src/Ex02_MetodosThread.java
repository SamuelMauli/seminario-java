// Exercício 02 - Métodos importantes de Thread
// Resumo:
//  start()         -> inicia a thread (chama run() em paralelo)
//  run()           -> corpo da thread (NUNCA chame direto, isso roda na main)
//  sleep(ms)       -> pausa a thread atual sem liberar locks
//  join()          -> espera outra thread terminar
//  yield()         -> dica ao scheduler para ceder o processador
//  interrupt()     -> sinaliza pedido de interrupção
//  isInterrupted() -> consulta a flag de interrupção

public class Ex02_MetodosThread {

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("worker: interrompido, saindo");
                    return;
                }
                System.out.println("worker: passo " + i);
                try {
                    Thread.sleep(200); // pausa 200ms
                } catch (InterruptedException e) {
                    // sleep limpa a flag, então restauramos
                    Thread.currentThread().interrupt();
                }
                Thread.yield(); // sugere ao scheduler trocar
            }
        }, "worker");

        worker.start();
        Thread.sleep(500);
        worker.interrupt();   // pede para parar
        worker.join();        // espera terminar
        System.out.println("main: worker finalizou");
    }
}
