import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InterfaceMenu{
    private GerenciadorTarefas gerenciadorTarefas;
    private Scanner scanner;

    public InterfaceMenu(){
        this.gerenciadorTarefas = new GerenciadorTarefas();
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
        exibirTarefas(tarefas, ""📋 TODAS AS TAREFAS"");
    }

    private void listarTarefasPendentes(){
        List<Tarefa> tarefas = gerenciadorTarefas.obterTarefasPendentes();
        exibirTarefas(tarefas, "⏳ TAREFAS PENDENTES");
    }

    private void listarTarefasConcluidas(){
        List<Tarefa> tarefas = gerenciadorTarefas.obterTarefasConcluidas(;
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
            System.out.println("%d. %s%n", i + 1, tarefas.get(i));
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
}
