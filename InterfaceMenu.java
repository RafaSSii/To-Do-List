import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InterfaceMenu{
    private final Gerenciador gerenciadorTarefas;
    private final Scanner scanner;

    public InterfaceMenu(){
        this.gerenciadorTarefas = new Gerenciador();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar(){
        System.out.println("🎯 Bem-vindo à sua Lista de Tarefas Avançada!");

        while (true){
            mostrarMenu();

            try{
                int escolha = scanner.nextInt();
                scanner.nextLine();

                if (!tratarEscolhaMenu(escolha)){
                    break;
                }
            } catch(InputMismatchException e){
                System.out.println("❌ Entrada inválida. Digite um número.");
                scanner.nextLine();
            }
        }
    }

    private void mostrarMenu(){
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🎯 LISTA DE TAREFAS - MENU PRINCIPAL");
        System.out.println("=".repeat(40));
        System.out.println("1.  📝 Adicionar tarefa");
        System.out.println("2.  📋 Listar todas as tarefas");
        System.out.println("3.  ⏳ Listar tarefas pendentes");
        System.out.println("4.  ✅ Listar tarefas concluídas");
        System.out.println("5.  🎯 Marcar tarefa como concluída");
        System.out.println("6.  🗑️  Remover tarefa");
        System.out.println("7.  ✏️  Editar tarefa");
        System.out.println("8.  🔢 Alterar prioridade");
        System.out.println("9.  🔍 Filtrar por prioridade");
        System.out.println("10. 📊 Ver estatísticas");
        System.out.println("11. 🔄 Ordenar por prioridade");
        System.out.println("12. 🧹 Limpar tarefas concluídas");
        System.out.println("13. 🚪 Sair");
        System.out.println("=".repeat(40));
        System.out.print("Escolha uma opção (1-13): ");
    }

    private boolean tratarEscolhaMenu(int escolha){
        switch (escolha){
            case 1: menuAdicionarTarefa();
            break;

            case 2: listarTodasTarefas();
            break;

            case 3: listarTarefasPendentes();
            break;

            case 4: listarTarefasConcluidas();
            break;

            case 5: menuConcluirTarefa();
            break;

            case 6: menuRemoverTarefa();
            break;

            case 7: menuEditarTarefa();
            break;

            case 8: menuAlterarPrioridade();
            break;

            case 9: menuFiltrarPorPrioridade();
            break;

            case 10: mostrarEstatisticas();
            break;

            case 11: ordenarPorPrioridade();
            break;

            case 12: limparTarefasConcluidas();
            break;

            case 13: 
                System.out.println("👋 Obrigado por usar a Lista de Tarefas!");
                return false;
            default:
                System.out.println("❌ Opção inválida. Tente novamente.");     
        }
        return true;
    }

    private void menuAdicionarTarefa(){
        System.out.println("📝 Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();

        System.out.println("🎯 Selecione a prioridade:");
        System.out.println("1. 🔴 Alta");
        System.out.println("2. 🟡 Média");
        System.out.println("3. 🟢 Baixa");
        System.out.print("Prioridade (1-3, ou Enter para média): ");

        String entradaPrioridade = scanner.nextLine();
        int prioridade = 2;

        if (!entradaPrioridade.isEmpty()){
            try {
                prioridade = Integer.parseInt(entradaPrioridade);
                if (prioridade < 1 || prioridade > 3){
                    prioridade = 2;
                }
            }catch (NumberFormatException e){
                prioridade = 2;
            }
        }

        gerenciadorTarefas.adicionarTarefa(descricao, prioridade);
        System.out.println("✅ Tarefa adicionada com sucesso!");
    }

    private void listarTodasTarefas(){
        List<Tarefa> tarefas = gerenciadorTarefas.obterTodasTarefas();
        exibirTarefas(tarefas, "📋TODAS AS TAREFAS");
    }

    private void listarTarefasPendentes(){
        List<Tarefa> tarefas = gerenciadorTarefas.obterTarefasPendentes();
        exibirTarefas(tarefas, "⏳ TAREFAS PENDENTES");
    }

    private void listarTarefasConcluidas(){
        List<Tarefa> tarefas = gerenciadorTarefas.obterTarefasConcluidas();
        exibirTarefas(tarefas, "✅ TAREFAS CONCLUÍDAS");
    }

    private void exibirTarefas(List<Tarefa> tarefas, String titulo){
        System.out.println("\n" + "=".repeat(50));
        System.out.println(titulo);
        System.out.println("=".repeat(50));

        if (tarefas.isEmpty()){
            System.out.println("📭 Nenhuma tarefa encontrada.");
            return;
        }

        for (int i = 0; i < tarefas.size(); i++){
            System.out.printf("%d. %s%n", i + 1, tarefas.get(i));
        }
    }

    private void menuConcluirTarefa(){
        List<Tarefa> tarefasPendentes = gerenciadorTarefas.obterTarefasPendentes();
        if (tarefasPendentes.isEmpty()){
            System.out.println("🎉 Todas as tarefas foram concluídas!");
            return;
        }

        listarTarefasPendentes();
        System.out.println("🎯 Digite o número da tarefa para marcar como concluída: ");

        try {
            int indice = scanner.nextInt() - 1;
            if (gerenciadorTarefas.concluirTarefa(indice)){
                System.out.println("✅ Tarefa marcada como concluída!");
            } else {
                System.out.println("❌ Índice inválido.");
            }
        } catch (InputMismatchException e){
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }

    private void menuRemoverTarefa(){
        if (gerenciadorTarefas.getTotalTarefas() == 0){
            System.out.println("📭 Nenhuma tarefa para remover.");
            return;
        }

        listarTodasTarefas();
        System.out.println("🗑️ Digite o número da tarefa para remover: ");

        try {
            int indice = scanner.nextInt() - 1;
            if (gerenciadorTarefas.removerTarefas(indice)){
                System.out.println("✅ Tarefa removida com sucesso!");
            } else {
                System.out.println("❌ Índice inválida.");
            }
        } catch (InputMismatchException e){
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }

    private  void menuEditarTarefa(){
        if (gerenciadorTarefas.getTotalTarefas() == 0){
            System.out.println("📭 Nenhuma tarefa para editar.");
            return;
        }

        listarTodasTarefas();
        System.out.println("✏️ Digite o número da tarefa para editar: ");

        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine();

            Tarefa tarefa = gerenciadorTarefas.obterTarefa(indice);
            if (tarefa != null){
                System.out.printf("📝 Descrição atual: %s%n", tarefa.getDescricao());
                System.out.print("✏️ Nova descrição: ");
                String novaDescricao = scanner.nextLine();

                if (gerenciadorTarefas.editarTarefas(indice, novaDescricao)){
                    System.out.println("✅ Tarefa editada com sucesso!");
                }
            } else {
                System.out.println("❌ Índice inválido.");
            }
        } catch (InputMismatchException e){
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }

    private void menuAlterarPrioridade(){
        if (gerenciadorTarefas.getTotalTarefas() == 0){
            System.out.println("📭 Nenhuma tarefa disponível.");
            return;
        }

        listarTodasTarefas();
        System.out.println("📝 Digite o número da tarefa para alterar prioridade: ");

        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine();

            Tarefa tarefa = gerenciadorTarefas.obterTarefa(indice);
            if (tarefa != null){
                System.out.printf("🎯 Prioridade atual: %s%n", tarefa.getTextoPrioridade());
                System.out.println("🎯 Nova prioridade:");
                System.out.println("1. 🔴 Alta");
                System.out.println("2. 🟡 Média");
                System.out.println("3. 🟢 Baixa");
                System.out.print("Escolha (1-3): ");

                int novaPrioridade = scanner.nextInt();
                if (gerenciadorTarefas.alterarPrioridade(indice, novaPrioridade)){
                    System.out.println("✅ Prioridade alterada com sucesso!!!");
                } else {
                    System.out.println("❌ Prioridade inválida.");
                }
            } else {
                System.out.println("❌ Índice inválido.");
            }
        } catch (InputMismatchException e){
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }

    private void menuFiltrarPorPrioridade(){
        System.out.println("🔍 Filtrar por prioridade:");
        System.out.println("1. 🔴 Alta");
        System.out.println("2. 🟡 Média");
        System.out.println("3. 🟢 Baixa");
        System.out.print("Escolha (1-3): ");

        try {
            int prioridade = scanner.nextInt();
            List<Tarefa> tarefasFiltradas = gerenciadorTarefas.obterTarefasPorPrioridade(prioridade);

            String textoPrioridade = "";
            switch (prioridade){
                case 1: textoPrioridade = "🔴 ALTA";
                break;
                case 2: textoPrioridade = "🟡 MÉDIA";
                break;
                case 3: textoPrioridade = "🟢 BAIXA";
                break;
            }

            exibirTarefas(tarefasFiltradas, "🔍 TAREFAS COM PRIORIDADE " + textoPrioridade);
        } catch (InputMismatchException e){
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
        }
    }

    private void mostrarEstatisticas(){
        EstatisticasTarefas estatisticas = gerenciadorTarefas.obterEstatisticas();
        System.out.println("\n" + estatisticas);
    }

    private void ordenarPorPrioridade(){
        gerenciadorTarefas.ordenarPorPrioridade();
        System.out.println("✅ Tarefas ordenadas por prioridade!");
    }

    private void limparTarefasConcluidas(){
        int removidas = gerenciadorTarefas.limparTarefasConcluidas;
        System.out.printf("%d tarefa(s) concluída(s) removida(s)!%n", removidas);
    }
}
