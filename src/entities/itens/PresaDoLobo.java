package entities.itens;

public class PresaDoLobo extends Item {

    // 1. Presa do Lobo — (Comum)
    // Drop de: Lobo Silvestre
    // Descrição: Um dente afiado que vibra com energia primal.
    // Efeito:
    // +2 de Dano
    // +1 de Velocidade de Movimento

    public PresaDoLobo() {
        super(
                "Presa do Lobo",
                "Um dente afiado que vibra com energia primal.",
                Raridade.COMMON
        );

        addBuff(Buff.DANO, 2);
        addBuff(Buff.MOVESPEED, 1);
    }
}
