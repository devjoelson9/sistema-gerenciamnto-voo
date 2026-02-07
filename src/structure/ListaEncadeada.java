package structure;

import java.util.ArrayList;
import java.util.List;

public class ListaEncadeada<T> {

    private No<T> inicio;
    private int tamanho;

    private static class No<T> {
        T valor;
        No<T> proximo;

        No(T valor) {
            this.valor = valor;
        }
    }

    public void inserir(T valor) {
        No<T> novo = new No<>(valor);

        if (inicio == null) {
            inicio = novo;
        } else {
            No<T> atual = inicio;

            while (atual.proximo != null) {
                atual = atual.proximo;
            }

            atual.proximo = novo;
        }

        tamanho++;
    }

    public boolean remover(T valor) {

        if (inicio == null) return false;

        if (inicio.valor.equals(valor)) {
            inicio = inicio.proximo;
            tamanho--;
            return true;
        }

        No<T> atual = inicio;

        while (atual.proximo != null) {
            if (atual.proximo.valor.equals(valor)) {
                atual.proximo = atual.proximo.proximo;
                tamanho--;
                return true;
            }
            atual = atual.proximo;
        }

        return false;
    }

    public T buscar(T valor) {

        No<T> atual = inicio;

        while (atual != null) {
            if (atual.valor.equals(valor)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }

        return null;
    }

    public List<T> listar() {

        List<T> lista = new ArrayList<>();

        No<T> atual = inicio;

        while (atual != null) {
            lista.add(atual.valor);
            atual = atual.proximo;
        }

        return lista;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }
}
