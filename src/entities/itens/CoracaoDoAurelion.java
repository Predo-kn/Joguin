package entities.itens;

public class CoracaoDoAurelion extends Item{

    private String nome = "Coração Do Aurelion";
    private String info = "Uma máscara lisa que toma a expressão do portador.";
    private Raridade raridade = Raridade.MITHIC;


    public CoracaoDoAurelion() {
        super("Coração Do Aurelion", "", Raridade.MITHIC);
    }
}
