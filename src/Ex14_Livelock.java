// Exercício 14 - Livelock
// Resumo:
//  As threads NÃO estão bloqueadas, mas ficam reagindo umas às outras
//  e nunca progridem. Exemplo clássico: dois processos cedendo a vez
//  educadamente para sempre.

public class Ex14_Livelock {

    static class Pessoa {
        final String nome;
        boolean comFome = true;
        Pessoa(String nome) { this.nome = nome; }
    }

    static class Colher {
        Pessoa dono;
        Colher(Pessoa dono) { this.dono = dono; }

        // ambos tentam ceder a colher se o outro estiver com fome -> livelock
        synchronized void usar(Pessoa quem, Pessoa outro) {
            while (quem.comFome) {
                if (dono != quem) {
                    try { Thread.sleep(10); } catch (InterruptedException e) { return; }
                    continue;
                }
                if (outro.comFome) {
                    System.out.println(quem.nome + ": " + outro.nome + " parece com fome, fica com a colher");
                    dono = outro;     // cede educadamente
                    continue;
                }
                // (nunca chega aqui no livelock)
                quem.comFome = false;
                System.out.println(quem.nome + " comeu!");
                dono = outro;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Pessoa a = new Pessoa("Ana");
        Pessoa b = new Pessoa("Bia");
        Colher colher = new Colher(a);

        Thread t1 = new Thread(() -> colher.usar(a, b), "Ana");
        Thread t2 = new Thread(() -> colher.usar(b, a), "Bia");

        t1.start(); t2.start();

        Thread.sleep(800);
        System.out.println(">>> LIVELOCK: ambos cedem para sempre, encerrando demo <<<");
        System.exit(0);
    }
}
