import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random();
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;
    

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;        
    }

    /**
     * Gerador de vetores de objetos do tipo Integer aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor de Objetos Integer com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static Integer[] gerarVetorObjetos(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, 10 * tamanho);
        }
        return vetor;
    }


    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("--- Oficina: Ordenação PUC Minas ---");
        System.out.print("Digite o tamanho do vetor para teste: ");
        int tam = teclado.nextInt();
        
        Integer[] vetor = gerarVetorObjetos(tam);
        
        System.out.println("\nEscolha o método de ordenação:");
        System.out.println("1 - BubbleSort");
        System.out.println("2 - InsertionSort");
        System.out.println("3 - MergeSort");
        System.out.print("Opção: ");
        int opcao = teclado.nextInt();

        // Interface polimórfica para os ordenadores 
        IOrdenador<Integer> ordenador = null;

        switch (opcao) {
            case 1:
                ordenador = new BubbleSort<>();
                break;
            case 2:
                ordenador = new InsertionSort<>();
                break;
            case 3:
                ordenador = new MergeSort<>();
                break;
            default:
                System.out.println("Opção inválida!");
        }

        if (ordenador != null) {
            Integer[] vetorOrdenado = ordenador.ordenar(vetor);

            System.out.println("\n--- Resultados da Ordenação ---");
            System.out.println("Método: " + ordenador.getClass().getSimpleName());
            System.out.println("Comparações: " + ordenador.getComparacoes());
            System.out.println("Movimentações: " + ordenador.getMovimentacoes());
            System.out.printf("Tempo de ordenação: %.4f ms\n", ordenador.getTempoOrdenacao());
            
            if (tam <= 20) {
                System.out.println("Vetor Resultante: " + Arrays.toString(vetorOrdenado));
            }
        }

        teclado.close();
    }
}
