import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> matriz1 = gerarM1();
        System.out.println();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "1º matriz finalizada com sucesso!" + ConsoleColors.RESET);
        Main.printMatriz(matriz1);
        System.out.println();

        ArrayList<ArrayList<Integer>> matriz2 = gerarM2(matriz1);
        System.out.println();
        System.out.println(ConsoleColors.GREEN_BRIGHT + "2º matriz finalizada com sucesso!" + ConsoleColors.RESET);
        Main.printMatriz(matriz2);
        System.out.println();

        System.out.println(ConsoleColors.GREEN_BRIGHT + "3º matriz gerada!" + ConsoleColors.RESET);
        ArrayList<ArrayList<Integer>> matriz3 = gerarM3(matriz1, matriz2);

        System.out.println();
        System.out.println(ConsoleColors.WHITE_BRIGHT + "3º matriz resultado:" + ConsoleColors.RESET);
        Main.printMatriz(matriz3);
    }

    private static void printMatriz(ArrayList<ArrayList<Integer>> matriz) {
        for (ArrayList<Integer> linha : matriz) {
            for (Integer coluna : linha) {
                int pad = 5 - String.valueOf(coluna).length();

                System.out.print(coluna);
                for (int i = 0; i < pad; i++) {
                    System.out.print(' ');
                }

                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static ArrayList<ArrayList<Integer>> gerarM1() {
        Scanner sc = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> matriz1 = new ArrayList<>();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Declare a primeira matriz:");

        int linhaM1 = 0;
        int numeroColunasM1 = 0;

        // 1º Matriz
        while (true) {
            System.out.print(
                    ConsoleColors.YELLOW + "[Separe a(s) coluna(s) com" + ConsoleColors.YELLOW_BOLD + " ESPAÇO " + ConsoleColors.YELLOW + "ou pressione" + ConsoleColors.YELLOW_BOLD + " ENTER " + ConsoleColors.YELLOW + "para finalizar a 1ª matriz] " + ConsoleColors.WHITE_BOLD + " 1º matriz, " + (linhaM1 + 1) + "ª linha: " + ConsoleColors.RESET
            );

            String input = sc.nextLine();
            if (Objects.equals(input, "")) {
                System.out.print(String.format("\033[%dA", 2)); // Move up
                System.out.print("\033[2K"); // Erase line content
                break;
            }

            try {
                String[] colunas = input.split(" ");


                if ((numeroColunasM1 != 0) && (colunas.length != numeroColunasM1)) {
                    System.out.println(ConsoleColors.RED + "Você deve inserir " + numeroColunasM1 + " coluna(s) e você inseriu " + colunas.length + " coluna(s). Tente novamente!" + ConsoleColors.RESET);
                    continue;
                } else {
                    numeroColunasM1 = colunas.length;
                }

                matriz1.add(new ArrayList<>()); // array de colunas

                for (String coluna : colunas) {
                    matriz1.get(linhaM1).add(Integer.parseInt(coluna));
                }
            } catch (Exception error) {
                System.out.println(ConsoleColors.RED + "Houve um erro, inseriu os valores corretamente? Tente novamente!" + ConsoleColors.RESET);

            }

            linhaM1++;
        }

        return matriz1;
    }

    private static ArrayList<ArrayList<Integer>> gerarM2(ArrayList<ArrayList<Integer>> m1) {
        Scanner sc = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Declare a segunda matriz:");
        int linhaM2 = 0;

        int numeroColunasM1 = m1.get(0).size();
        int numeroLinhasM1 = m1.size();

        while (numeroColunasM1 > linhaM2) {
            System.out.print(
                    ConsoleColors.YELLOW + "[Separe a(s) coluna(s) com" + ConsoleColors.YELLOW_BOLD + " ESPAÇO" + ConsoleColors.YELLOW + "] " + ConsoleColors.WHITE_BOLD + " 2º matriz, " + (linhaM2 + 1) + "ª linha: " + ConsoleColors.RESET
            );

            String input = sc.nextLine();

            String[] colunas = input.split(" ");

            if ((colunas.length != numeroLinhasM1)) {
                System.out.println(ConsoleColors.RED + "Você deve inserir " + numeroLinhasM1 + " coluna(s) e você inseriu " + colunas.length + " coluna(s). Tente novamente!" + ConsoleColors.RESET);
                continue;
            }

            matriz.add(new ArrayList<>()); // array de colunas

            for (String coluna : colunas) {
                matriz.get(linhaM2).add(Integer.parseInt(coluna));
            }

            linhaM2++;
        }
        return matriz;
    }

    private static ArrayList<ArrayList<Integer>> gerarM3(ArrayList<ArrayList<Integer>> m1, ArrayList<ArrayList<Integer>> m2) {
        ArrayList<ArrayList<Integer>> matriz3 = new ArrayList<>();
        int colunaM2 = 0;
        int linhaM2 = 0;
        int linhaM3 = 0;

        //loop da linha M1
        for (ArrayList<Integer> linha : m1) {
            colunaM2 = 0;
            matriz3.add(new ArrayList<>());

            // loop da coluna M2
            for (Integer _i : m2.get(0)) { //percorre cada coluna do m2
                linhaM2 = 0;

                int i = 0;
                // loop da coluna M1
                for (Integer coluna : linha) {
                    System.out.print(coluna + " * " + m2.get(linhaM2).get(colunaM2) + " ");
                    if (m1.get(0).size() != ++i) System.out.print("+ ");

                    matriz3.get(linhaM3).add(coluna * m2.get(linhaM2).get(colunaM2));
                    linhaM2++;
                }
                System.out.print("   ");
                colunaM2++;
            }

            System.out.println();
            linhaM3++;
        }

        return matriz3;
    }
}