import java.util.*;
import java.util.stream.Collectors;

public class Gereciador{
    private List<Tarefa> tarefas;

    public Gereciador(){
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String descricao){
        tarefas.add(new Tarefa(descricao));
    }

    public void adicionarTarefa(String descricao, int prioridade){
        tarefas.add(new Tarefa(descricao, prioridade));
    }

    public List<Tarefa> pegarTodasTarefas(){
        return new ArrayList<>(tarefas);
    }

    public List<Tarefa> pegarTarefasPendentes(){
        return tarefas.stream().filter(tarefa -> !tarefa.estaConcluida()).collect(Collectors.toList());
    }

    public List<Tarefa> obterTarefasConcluidas(){
        return tarefas.stream().filter(Tarefa::estaConcluida).collect(Collectors.toList());
    }

    public List<Tarefa> obterTarefasPorPrioridade(int prioridade){
        return tarefas.stream().filter(tarefa -> tarefa.getPrioridade() = prioridade).collect(Collectors.toList());
    }
}
