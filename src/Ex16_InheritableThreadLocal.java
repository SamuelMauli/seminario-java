// Exercício 16 - InheritableThreadLocal
// Resumo:
//  Igual ao ThreadLocal, mas o valor da thread-pai é HERDADO pela thread-filha
//  no momento em que ela é criada. Útil para propagar contexto (ex.: requestId).

public class Ex16_InheritableThreadLocal {

    private static final InheritableThreadLocal<String> contexto =
            new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        contexto.set("requestId=42");
        System.out.println("main: " + contexto.get());

        Thread filha = new Thread(() -> {
            // herda o valor da main
            System.out.println("filha (herdado): " + contexto.get());

            Thread neta = new Thread(() -> {
                System.out.println("neta  (herdado): " + contexto.get());
            }, "neta");
            neta.start();
            try { neta.join(); } catch (InterruptedException ignored) {}
        }, "filha");

        filha.start();
        filha.join();

        // ThreadLocal comum NÃO faria essa herança.
    }
}
