package com.home.fastfoodactivity.data.model;

import java.util.List;

public class Pedido {

    private  List<ItemPedido> listItens;
    private double total;

    public Pedido(List<ItemPedido> listItens, double total) {
        this.listItens = listItens;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public Pedido(List<ItemPedido> listItens) {
        this.listItens = listItens;
    }

    public List<ItemPedido> getListItens() {
        return listItens;
    }
}
