package structure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FilaPrioridade<T> {

    private final PriorityQueue<T> heap;

    public FilaPrioridade(Comparator<T> comparator) {
        this.heap = new PriorityQueue<>(comparator);
    }

    // =========================
    // ADD
    // =========================
    public void add(T valor) {
        heap.add(valor);
    }

    // =========================
    // POLL (remove maior prioridade)
    // =========================
    public T poll() {
        return heap.poll();
    }

    // =========================
    // PEEK
    // =========================
    public T peek() {
        return heap.peek();
    }

    // =========================
    // TAMANHO
    // =========================
    public int size() {
        return heap.size();
    }

    // =========================
    // VAZIA
    // =========================
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
