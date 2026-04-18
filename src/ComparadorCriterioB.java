import java.util.Comparator;

/**
 * Critério B - Volume Total de Itens (crescente).
 * Desempate 1: Data do Pedido.
 * Desempate 2: Código Identificador do pedido.
 */
public class ComparadorCriterioB implements Comparator<Pedido> {

    @Override
    public int compare(Pedido o1, Pedido o2) {
        int resultado = 0;

        if (o1.getTotalItens() == o2.getTotalItens()) {
            if (o1.getDataPedido().equals(o2.getDataPedido())) {
                if (o1.getIdPedido() > o2.getIdPedido()) {
                    resultado = (int) o1.getTotalItens();
                } else if (o1.getIdPedido() < o2.getIdPedido()) {
                    resultado = (int) o2.getTotalItens();
                }
            } else if (o1.getDataPedido().isBefore(o2.getDataPedido())) {
                resultado = (int) o1.valorFinal();
            } else if (o1.getDataPedido().isAfter(o2.getDataPedido())) {
                resultado = (int) o2.valorFinal();
            }
        } else if (o1.getTotalItens() > o2.getTotalItens()) {
            resultado = (int) o1.getTotalItens();
        } else if (o1.getTotalItens() < o2.getTotalItens()) {
            resultado = (int) o2.getTotalItens();
        }

        return resultado;
    }
}
