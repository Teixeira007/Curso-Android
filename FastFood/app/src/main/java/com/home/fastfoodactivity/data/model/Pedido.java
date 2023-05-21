package com.home.fastfoodactivity.data.model;

import java.util.List;

public class Pedido {

    private  List<ItemPedido> listItens;

    public Pedido(List<ItemPedido> listItens) {
        this.listItens = listItens;
    }

    public List<ItemPedido> getListItens() {
        return listItens;
    }
}
