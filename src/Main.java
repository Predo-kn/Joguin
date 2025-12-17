import game.Menus;

public class Main {

    public static void main(String[] args) {
        try {
            new Menus().exibirMenuPrincipal();
        } catch (Exception e) {
            System.err.println("Erro fatal ao iniciar o jogo:");
            e.printStackTrace();
        }
    }
}
