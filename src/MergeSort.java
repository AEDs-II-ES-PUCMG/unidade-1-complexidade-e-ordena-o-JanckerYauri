import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements IOrdenador<T> {

    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;
    private double inicio;
    private double nanoToMilli = 1.0 / 1_000_000;

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }

    private void iniciar() {
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.inicio = System.nanoTime();
    }

    private void terminar() {
        this.tempoOrdenacao = (System.nanoTime() - this.inicio) * nanoToMilli;
    }

    private void mergeSort(T[] vetor, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(vetor, esq, meio);
            mergeSort(vetor, meio + 1, dir);
            intercalar(vetor, esq, meio, dir);
        }
    }

    private void intercalar(T[] vetor, int esq, int meio, int dir) {
        T[] ladoEsquerdo = Arrays.copyOfRange(vetor, esq, meio + 1);
        T[] ladoDireito = Arrays.copyOfRange(vetor, meio + 1, dir + 1);

        this.movimentacoes += ladoEsquerdo.length + ladoDireito.length;

        int i = 0, j = 0, k = esq;

        while (i < ladoEsquerdo.length && j < ladoDireito.length) {
            this.comparacoes++;
            if (ladoEsquerdo[i].compareTo(ladoDireito[j]) <= 0) {
                vetor[k] = ladoEsquerdo[i];
                i++;
            } else {
                vetor[k] = ladoDireito[j];
                j++;
            }
            this.movimentacoes++;
            k++;
        }

        while (i < ladoEsquerdo.length) {
            vetor[k] = ladoEsquerdo[i];
            i++;
            k++;
            this.movimentacoes++;
        }

        while (j < ladoDireito.length) {
            vetor[k] = ladoDireito[j];
            j++;
            k++;
            this.movimentacoes++;
        }
    }

    @Override
    public T[] ordenar(T[] dados) {
        T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
        iniciar();
        mergeSort(dadosOrdenados, 0, dadosOrdenados.length - 1);
        terminar();
        return dadosOrdenados;
    }
}