package BatalhaNaval;

import java.util.Random;
import java.util.Scanner;

public class naval {

    public static void alocarNavio(char[][] tabuleiro, int tamanho, Random aleatorio, char letra) {
        int coluna, linha;
        do {
            coluna = aleatorio.nextInt(10);
            linha = aleatorio.nextInt(10);
        } while (!posicaoValida(tabuleiro, linha, coluna, tamanho));

        for (int i = 0; i < tamanho; i++) {
            tabuleiro[linha][coluna + i] = letra;
        }
    }

    public static boolean posicaoValida(char[][] tabuleiro, int linha, int coluna, int tamanho) {
        if (coluna + tamanho > 10) {
            return false;
        }

        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[linha][coluna + i] != '~') {
                return false;
            }
        }
        return true;
    }

    public static void alocarNaviosAutomaticamente(char[][] tabuleiro) {
        Random random = new Random();

        alocarNavio(tabuleiro, 4, random, 'w');

        for (int i = 0; i < 2; i++) {
            alocarNavio(tabuleiro, 3, random, 'x');
        }

        for (int i = 0; i < 3; i++) {
            alocarNavio(tabuleiro, 2, random, 'y');
        }

        for (int i = 0; i < 4; i++) {
            alocarNavio(tabuleiro, 1, random, 'z');
        }
    }

    public static void alocarNavio1(char[][] tabuleiro, int tamanho, Scanner ler, char letra) {
        int linha, coluna;
        char orientacao;

        do {
            System.out.println("Digite a coluna do navio (A-J):");
            String colunas = ler.next().toUpperCase();

            System.out.println("Digite a linha do navio (0-9):");
            linha = ler.nextInt();

            System.out.println("Digite a orientação do navio (H - horizontal, V - vertical):");
            orientacao = ler.next().toUpperCase().charAt(0);

            String alfabeto = "ABCDEFGHIJ";
            coluna = alfabeto.indexOf(colunas);

            if (orientacao == 'H' && posicaoValidaHorizontal(tabuleiro, linha, coluna, tamanho)) {
                for (int j = 0; j < tamanho; j++) {
                    tabuleiro[linha][coluna + j] = letra;
                }
                break;
            } else if (orientacao == 'V' && posicaoValidaVertical(tabuleiro, linha, coluna, tamanho)) {
                for (int i = 0; i < tamanho; i++) {
                    tabuleiro[linha + i][coluna] = letra;
                }
                break;
            } else {
                System.out.println("Jogada inválida. Tente novamente.");
            }
        } while (true);

        exibirTabuleiro(tabuleiro);
    }

    public static void alocarNaviosManualmente(char[][] tabuleiro) {
        Scanner ler = new Scanner(System.in);

        alocarNavio1(tabuleiro, 4, ler, 'w');

        for (int i = 0; i < 2; i++) {
            alocarNavio1(tabuleiro, 3, ler, 'x');
        }

        for (int i = 0; i < 3; i++) {
            alocarNavio1(tabuleiro, 2, ler, 'y');
        }

        for (int i = 0; i < 4; i++) {
            alocarNavio1(tabuleiro, 1, ler, 'z');
        }
    }

    public static boolean posicaoValidaVertical(char[][] tabuleiro, int linha, int coluna, int tamanho) {
        if (linha + tamanho > 10) {
            return false;
        }

        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[linha + i][coluna] != '~') {
                return false;
            }
        }

        return true;
    }

    public static boolean posicaoValidaHorizontal(char[][] tabuleiro, int linha, int coluna, int tamanho) {
        if (coluna + tamanho > 10) {
            return false;
        }
        for (int j = 0; j < tamanho; j++) {
            if (tabuleiro[linha][coluna + j] != '~') {
                return false;
            }
        }
        return true;
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void exibirTabuleiroSemBarcos(char[][] tabuleiro) {
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == 'w' || tabuleiro[i][j] == 'x' || tabuleiro[i][j] == 'y'
                        || tabuleiro[i][j] == 'z') {
                    System.out.print("~ ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void jogada(char[][] tabuleiro, char simbolo) {
        Scanner ler = new Scanner(System.in);
        boolean acertouNavio = false;

        while (true) {

            System.out.println("Digite a coluna do tiro (A-J):");
            String colunas = ler.next().toUpperCase();

            System.out.println("Digite a linha do tiro (0-9):");
            int linha = ler.nextInt();

            String alfabeto = "ABCDEFGHIJ";
            int coluna = alfabeto.indexOf(colunas);


            if (coluna < 0 || coluna >= 10 || linha < 0 || linha >= 10) {
                System.out.println("Posição inválida. Tente novamente.");
                break;
            }

            if (tabuleiro[linha][coluna] == '~') {
                System.out.println("Água!");
                tabuleiro[linha][coluna] = '.';
                break;
            } else if (tabuleiro[linha][coluna] == 'w' || tabuleiro[linha][coluna] == 'x'|| tabuleiro[linha][coluna] == 'y' || tabuleiro[linha][coluna] == 'z') {
                System.out.println("Acertou um navio!");
                tabuleiro[linha][coluna] = '-';
                acertouNavio = true;
                break;
            } else {
                System.out.println("Posição inválida. Tente novamente.");
            }
        }

        if (acertouNavio && !verificarVitoria(tabuleiro)) {
            jogada(tabuleiro, simbolo);
        } else if (verificarVitoria(tabuleiro)) {
            System.out.println("========PARABENS==========");
        }
    }

    public static void jogadaComputador(char[][] tabuleiro, char simbolo) {
        Random posicao = new Random();
        boolean acertouNavio = false;

        while (true) { //criar um loop infinito
            int coluna = posicao.nextInt(10);
            int linha = posicao.nextInt(10);

            String alfabeto = "ABCDEFGHIJ";
            char letraColuna = alfabeto.charAt(coluna);
            int numeroLinha = linha;

            System.out.println("O computador jogou em: " + letraColuna + numeroLinha);

            if (tabuleiro[linha][coluna] == '~') {
                System.out.println("O computador errou o tiro!");
                tabuleiro[linha][coluna] = '.';
                break;
            } else if (tabuleiro[linha][coluna] == 'w' || tabuleiro[linha][coluna] == 'x'|| tabuleiro[linha][coluna] == 'y' || tabuleiro[linha][coluna] == 'z') {
                System.out.println("O computador acertou um navio!");
                tabuleiro[linha][coluna] = '-';
                acertouNavio = true;
                break;
            }
        }
        if (acertouNavio && !verificarVitoria(tabuleiro)) {
            jogadaComputador(tabuleiro, simbolo);
        } else if (verificarVitoria(tabuleiro)) {
            System.out.println("========PARABENS==========");
        }
    }

    public static boolean verificarVitoria(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == 'w' || tabuleiro[i][j] == 'x' || tabuleiro[i][j] == 'y'|| tabuleiro[i][j] == 'z') {
                    if (tabuleiro[i][j] != '-') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}