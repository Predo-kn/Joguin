import entities.itens.Raridade;
import entities.itens.Teste;
import entities.player.Player;

public class Main {
    public static void main(String[] args) {
        Teste teste = new Teste("teste", "aaaa", Raridade.COMMON);
        CoracaoPradaria coracaoPradaria = new CoracaoPradaria("coracao", "corassaum", Raridade.COMMON);
        Warrior warrior = new Warrior(20, 20, 20, 20, 20);
        Player player = new Player("Pedro", warrior);

        player.getBag().addItem(coracaoPradaria);
        player.getBag().addItem(teste);
    }
}