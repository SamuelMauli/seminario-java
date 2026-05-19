# Seminário — Concorrência em Java

Material do seminário sobre **Fundamentos de Threads, Memória e Coordenação Básica** em Java.
Cada tópico tem um arquivo `.java` independente, simples, comentado e que compila por si só.

## 👥 Equipe (ordem alfabética)

- Lucas Gomes
- Luiz Eduardo
- Luiz Gabriel
- Pedro Rossi
- Renan Souza
- Samuel Mauli
- William Ferraz

## 📁 Estrutura

```
seminario-java/
├── src/                                  # 16 exercícios independentes
│   ├── Ex01_ThreadVsRunnable.java
│   ├── Ex02_MetodosThread.java
│   ├── ...
│   └── Ex16_InheritableThreadLocal.java
├── slides/
│   ├── Seminario_Concorrencia_Java.pptx  # apresentação pronta
│   └── img/                              # gráficos usados nos slides
├── gerar_slides.py                       # script que gera o .pptx
└── README.md
```

## 📚 Tópicos cobertos

| # | Arquivo | Tópico |
|---|---------|--------|
| 01 | [Ex01_ThreadVsRunnable.java](src/Ex01_ThreadVsRunnable.java) | Thread, Runnable e lambdas |
| 02 | [Ex02_MetodosThread.java](src/Ex02_MetodosThread.java) | `start / sleep / join / yield / interrupt / isInterrupted` |
| 03 | [Ex03_DaemonVsUser.java](src/Ex03_DaemonVsUser.java) | Daemon threads vs user threads |
| 04 | [Ex04_CicloDeVida.java](src/Ex04_CicloDeVida.java) | Estados: `NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED` |
| 05 | [Ex05_Synchronized.java](src/Ex05_Synchronized.java) | `synchronized` (método e bloco) |
| 06 | [Ex06_Volatile.java](src/Ex06_Volatile.java) | `volatile` e visibilidade |
| 07 | [Ex07_FinalImutabilidade.java](src/Ex07_FinalImutabilidade.java) | `final`, imutabilidade, *safe publication* |
| 08 | [Ex08_MonitorIntrinsicLock.java](src/Ex08_MonitorIntrinsicLock.java) | Monitores e reentrância |
| 09 | [Ex09_WaitNotify.java](src/Ex09_WaitNotify.java) | `wait / notify / notifyAll` (produtor-consumidor) |
| 10 | [Ex10_HappensBefore.java](src/Ex10_HappensBefore.java) | Java Memory Model — *happens-before* |
| 11 | [Ex11_RaceCondition.java](src/Ex11_RaceCondition.java) | Race condition |
| 12 | [Ex12_Deadlock.java](src/Ex12_Deadlock.java) | Deadlock |
| 13 | [Ex13_Starvation.java](src/Ex13_Starvation.java) | Starvation |
| 14 | [Ex14_Livelock.java](src/Ex14_Livelock.java) | Livelock |
| 15 | [Ex15_ThreadLocal.java](src/Ex15_ThreadLocal.java) | `ThreadLocal` |
| 16 | [Ex16_InheritableThreadLocal.java](src/Ex16_InheritableThreadLocal.java) | `InheritableThreadLocal` |

## ▶️ Como compilar e rodar

Requisito: **JDK 8+**.

```bash
# compilar todos os exercícios
javac -d out src/*.java

# rodar um exercício
java -cp out Ex01_ThreadVsRunnable
java -cp out Ex11_RaceCondition
java -cp out Ex12_Deadlock
```

Cada arquivo é independente: tem o próprio `main` e um comentário-resumo no topo explicando o conceito.

## 🧠 Resumo dos conceitos

- **Thread / Runnable** — unidade de execução vs tarefa a executar
- **synchronized** — exclusão mútua + visibilidade
- **volatile** — visibilidade (sem atomicidade)
- **final / imutabilidade** — thread-safety por construção
- **wait/notify** — coordenação clássica via monitor
- **JMM (happens-before)** — regras que garantem visibilidade
- **Race, Deadlock, Starvation, Livelock** — os 4 problemas clássicos
- **ThreadLocal** — estado isolado por thread
