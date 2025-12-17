package entities.itens;

public class VozDoVazio extends Item{

    public VozDoVazio() {
        super(
                "Olho Vigilante",
                "Um olho preservado que nunca pisca. " +
                        "Concede +1 Attack Speed e +3 de dano no primeiro ataque do combate.",
                Raridade.RARE
        );

        addBuff(Buff.DANO, 2);
        addBuff(Buff.ATKSPEED, 2);
    }

    //5. Voz do Vazio — (Épico)
    //Drop de: Eco do Abismo
    //Descrição: Um fragmento do eco que murmura possibilidades impossíveis.
    //Efeito:
    //+2 de Dano
    //+2 de Attack Speed
    //A cada 3 rodadas, causa 5 Dano Verdadeiro no inimigo automaticamente
    //Design: Oferece poder ofensivo sem inflar vida/defesa demais.
}
