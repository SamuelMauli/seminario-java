// Exercício 10 - Java Memory Model: happens-before
// Resumo:
//  O JMM define a relação "happens-before": se A happens-before B,
//  então os efeitos de A são visíveis para B.
//  Regras comuns:
//   - Tudo antes de start() happens-before o início da thread
//   - Tudo dentro de uma thread happens-before join() retornar
//   - unlock happens-before lock seguinte do mesmo monitor
//   - write em volatile happens-before read seguinte daquele volatile

public class Ex10_HappensBefore {

    private static int dado = 0;             // não-volatile de propósito
    private static volatile boolean pronto;  // barreira de memória

    public static void main(String[] args) throws InterruptedException {
        Thread leitor = new Thread(() -> {
            while (!pronto) { /* spin */ }
            // graças ao happens-before do volatile, vemos dado=42
            System.out.println("leitor viu dado = " + dado);
        }, "leitor");

        leitor.start();

        dado = 42;        // 1) escrita normal
        pronto = true;    // 2) escrita volatile -> publica (1) para o leitor

        leitor.join();
        System.out.println("fim");
    }
}
