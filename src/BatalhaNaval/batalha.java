package BatalhaNaval;

import java.util.Scanner;

public class batalha {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        char[][] tabuleiro = new char[10][10];
        char[][] tabuleiro2 = new char[10][10];
        naval n1 = new naval();

        System.out.println("Seja Bem Vindo na batalha naval");
        System.out.println("\n" +
                "╔══════════════════╗\n" +
                "║  BATALHA NAVAL   ║\n" +
                "╚══════════════════╝\n" +
                "");

        // Inicializando o tabuleiro
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = '~';
                tabuleiro2[i][j] = '~';
            }
        }

        int modoJogo = 0;
        boolean modoValido = false;

        while (!modoValido) {
            System.out.println("Escolha o modo de jogo:");
            System.out.println("1 - Jogador vs. Computador");
            System.out.println("2 - Jogador vs. Jogador");
            modoJogo = ler.nextInt();

            if (modoJogo == 1 || modoJogo == 2) {
                modoValido = true;
            } else {
                System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }

        switch (modoJogo) {
            case 1:
                Scanner ler3 = new Scanner(System.in);
                boolean jogarNovamente = true;

                System.out.println("\nModo de jogo selecionado: Jogador vs Máquina");
                System.out.println();

                while (jogarNovamente) {

                    System.out.println("deseja alocar os navios automaticamente (1) ou manualmente (2)?");

                    String opcao3 = ler3.next();

                    switch (opcao3) {
                        case "1":
                            n1.alocarNaviosAutomaticamente(tabuleiro);
                            n1.alocarNaviosAutomaticamente(tabuleiro2);
                            jogarNovamente = false;
                            break;
                        case "2":
                            n1.alocarNaviosManualmente(tabuleiro);
                            n1.alocarNaviosAutomaticamente(tabuleiro2);
                            jogarNovamente = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Repetindo a jogada.");
                            break;
                    }
                }

                System.out.println();

                System.out.println("Tabuleiro do jogador 1");
                n1.exibirTabuleiro(tabuleiro);

                System.out.println();

                System.out.println("Tabuleiro da maquina");
                n1.exibirTabuleiro(tabuleiro2);

                System.out.println();

                boolean jogos = true;

                while (jogos) {

                    System.out.println();

                    System.out.println("Tabuleiro da Maquina");
                    n1.exibirTabuleiroSemBarcos(tabuleiro2);
                    System.out.println("Sua vez de jogar jogador 1:");
                    n1.jogada(tabuleiro2, 'X');

                    if (n1.verificarVitoria(tabuleiro2)) {
                        System.out.println("Parabéns! Você afundou todos os navios do computador.");
                        break;
                    }

                    System.out.println();

                    System.out.println("Tabuleiro do jogador");
                    n1.exibirTabuleiroSemBarcos(tabuleiro);
                    System.out.println("\nVez do computador:");
                    n1.jogadaComputador(tabuleiro, 'O');


                    if (n1.verificarVitoria(tabuleiro)) {
                        System.out.println("O computador afundou todos os seus navios. Fim de jogo.");
                        break;
                    }
                }

                break;
            case 2:
                System.out.println("\nModo de jogo selecionado: Jogador vs. Jogador");

                System.out.println("Digite o nome do primeiro jogador:");
                String jogador1 = ler.next();

                System.out.println("Digite o nome do segundo jogador:");
                String jogador2 = ler.next();

                Scanner ler1 = new Scanner(System.in);
                Scanner ler2 = new Scanner(System.in);

                boolean jogarNovamente1 = true;
                boolean jogarNovamente2 = true;

                while (jogarNovamente1) {
                    System.out.println(jogador1 + " deseja alocar os navios automaticamente (1) ou manualmente (2)?");
                    String opcao1 = ler1.next();

                    switch (opcao1) {
                        case "1":
                            n1.alocarNaviosAutomaticamente(tabuleiro);
                            jogarNovamente1 = false;
                            break;
                        case "2":
                            n1.alocarNaviosManualmente(tabuleiro);
                            jogarNovamente1 = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Repetindo a jogada.");
                            break;
                    }
                }

                n1.exibirTabuleiro(tabuleiro);

                while (jogarNovamente2) {
                    System.out.println(jogador2 + " deseja alocar os navios automaticamente (1) ou manualmente (2)?");
                    String opcao1 = ler2.next();

                    switch (opcao1) {
                        case "1":
                            n1.alocarNaviosAutomaticamente(tabuleiro2);
                            jogarNovamente2 = false;
                            break;
                        case "2":
                            n1.alocarNaviosManualmente(tabuleiro2);
                            jogarNovamente2 = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Repetindo a jogada.");
                            break;
                    }
                }

                n1.exibirTabuleiro(tabuleiro2);

                boolean jogo = true;

                while (jogo) {
                    // Jogada do jogador 1
                    System.out.println("Atire no campo " + jogador2);
                    System.out.println();

                    System.out.println("Tabuleiro - " + jogador2);

                    n1.exibirTabuleiroSemBarcos(tabuleiro2);
                    System.out.println("Sua vez de jogar: " + jogador1);
                    n1.jogada(tabuleiro2, 'X');

                    if (n1.verificarVitoria(tabuleiro2)) {
                        System.out.println("Parabéns! " + jogador1 + " afundou todos os navios"+ jogador2 + "");
                        jogo = false;
                        break;
                    }

                    System.out.println("Atire no campo " + jogador1);
                    System.out.println("Tabuleiro do  - " + jogador1);
                    n1.exibirTabuleiroSemBarcos(tabuleiro);
                    System.out.println("\nSua vez de jogar: " + jogador2);
                    n1.jogada(tabuleiro, 'O');


                    if (n1.verificarVitoria(tabuleiro)) {
                        System.out.println("Parabéns! " + jogador2 + " afundou todos os navios "+ jogador1 +"");
                        jogo = false;
                        break;
                    }
                }
        }
    }
}