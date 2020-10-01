package listener;

import javax.persistence.PostLoad;

public class GenericListener {

    @PostLoad
    public void logInicializacao(Object obj) {
        System.out.println("==========================================");
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada.");
        System.out.println("==========================================");
    }
}
