package structure;

public class Fila<T> {

    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    private static class No<T> {
        T valor;
        No<T> proximo;

        No(T valor) {
            this.valor = valor;
        }
    }

    // =========================
    // ENQUEUE (entra no final)
    // =========================
    public void enqueue(T valor) {

        No<T> novo = new No<>(valor);

        if (isEmpty()) {
            inicio = fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }

        tamanho++;
    }

    // =========================
    // DEQUEUE (remove do início)
    // =========================
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T valor = inicio.valor;
        inicio = inicio.proximo;

        if (inicio == null) {
            fim = null;
        }

        tamanho--;
        return valor;
    }

    // =========================
    // PEEK (ver primeiro)
    // =========================
    public T peek() {
        return isEmpty() ? null : inicio.valor;
    }

    // =========================
    // TAMANHO
    // =========================
    public int size() {
        return tamanho;
    }

    // =========================
    // VAZIA
    // =========================
    public boolean isEmpty() {
        return tamanho == 0;
    }

    // =========================
    // DEBUG
    // =========================
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");
        No<T> atual = inicio;

        while (atual != null) {
            sb.append(atual.valor);
            if (atual.proximo != null) sb.append(", ");
            atual = atual.proximo;
        }

        sb.append("]");
        return sb.toString();
    }
}
