package service;

import model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido) {
        System.out.println("======================================");
        System.out.println("= Gerando nota fiscal para pedido " + pedido.getId() + ". =");
        System.out.println("======================================");
    }
}
