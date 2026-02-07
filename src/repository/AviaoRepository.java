package repository;

import entity.AviaoEntity;
import structure.ListaEncadeada;

import java.util.List;

public class AviaoRepository {

    private final ListaEncadeada<AviaoEntity> lista = new ListaEncadeada<>();

    public void salvar(AviaoEntity aviao) {
        lista.inserir(aviao);
    }

    public boolean removerPorCodigo(String codigo) {

        AviaoEntity aviao = buscarPorCodigo(codigo);

        if (aviao == null) return false;

        return lista.remover(aviao);
    }

    public AviaoEntity buscarPorCodigo(String codigo) {

        for (AviaoEntity a : lista.listar()) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }

        return null;
    }

    public List<AviaoEntity> listar() {
        return lista.listar();
    }

    public boolean existe(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    public int quantidade() {
        return lista.size();
    }
}
