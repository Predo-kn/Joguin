package entities.itens;

public class Mascara extends Item {

    // Máscara Sem Identidade — (Épico)
    // Quando a vida cai abaixo de 30%,
    // ganha +4 Dano até o fim do combate

    public Mascara() {
        super(
                "Máscara Sem Identidade",
                "Uma máscara lisa que toma a expressão do portador. " +
                        "Quando a vida cai abaixo de 30%, concede +4 de Dano até o fim do combate.",
                Raridade.EPIC
        );
        addBuff(Buff.VIDA, 10);
    }
}
