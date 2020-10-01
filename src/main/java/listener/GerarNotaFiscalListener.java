package listener;

import model.Pedido;
import service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService service = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
        if(pedido.isPago() && pedido.getNotaFiscal() == null) {
            service.gerar(pedido);
        }
    }
}
