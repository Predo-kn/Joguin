SISTEMA DE DROPS

O sistema de drops permite que inimigos soltem itens quando derrotados.

COMO USAR:

1. Adicionar itens à drop table de um inimigo:

    Inimigo enemy = new Aurelion();
    
    Item item1 = new CoracaoDoAurelion();
    Item item2 = new Mascara();
    
    enemy.adicionarDrop(item1);
    enemy.adicionarDrop(item2);

2. Ou adicionar múltiplos itens de uma vez:

    enemy.adicionarDrops(item1, item2, item3);

3. Iniciar uma batalha e receber drops:

    Battle battle = new Battle(player, enemy);
    Item drop = battle.executeBattleAndGetDrop();

MÉTODOS DISPONÍVEIS:

Em Inimigo:
- adicionarDrop(Item item) - Adiciona um item à tabela de drops
- adicionarDrops(Item... itens) - Adiciona múltiplos itens
- soltarDrop() - Retorna um item aleatório da tabela (null se vazio)
- getDropTable() - Retorna a lista completa de drops

Em Battle:
- executeBattleAndGetDrop() - Executa batalha e retorna drop se ganhar

EXEMPLO COMPLETO:

    Knight player = new Knight(55, 12, 4, 2, 2);
    player.setNome("Arthur");
    
    Aurelion boss = new Aurelion();
    
    CoracaoDoAurelion coracao = new CoracaoDoAurelion();
    Mascara mascara = new Mascara();
    OlhoVigilante olho = new OlhoVigilante();
    
    boss.adicionarDrops(coracao, mascara, olho);
    
    Battle battle = new Battle(player, boss);
    Item itemObtido = battle.executeBattleAndGetDrop();
    
    if (itemObtido != null) {
        System.out.println("Obteve: " + itemObtido.getNome());
    }

NOTAS:

- Qualquer classe que herda de Item pode ser adicionada como drop
- Os itens são selecionados aleatoriamente quando soltarDrop() é chamado
- Se o inimigo não tem itens na drop table, soltarDrop() retorna null
- A batalha funciona normalmente, o sistema de drops é apenas adicionado ao final
