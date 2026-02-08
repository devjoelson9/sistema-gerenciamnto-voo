package structure;

import java.util.ArrayList;
import java.util.List;

public class Pilha<T> {

    private No<T> topo;
    private int tamanho;

    private static class No<T> {
        T valor;
        No<T> proximo;

        No(T valor, No<T> proximo) {
            this.valor = valor;
            this.proximo = proximo;
        }
    }

    public void push(T valor) {
        topo = new No<>(valor, topo);
        tamanho++;
    }

    public List<T> listar() {

        List<T> lista = new ArrayList<>();

        No<T> atual = topo;

        while (atual != null) {
            lista.add(atual.valor);
            atual = atual.proximo;
        }

        return lista;
    }


    public T pop() {

        if (isEmpty()) {
            return null;
        }

        T valor = topo.valor;
        topo = topo.proximo;
        tamanho--;

        return valor;
    }

    public T peek() {
        return isEmpty() ? null : topo.valor;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }
}
