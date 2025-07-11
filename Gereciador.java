import java.util.*;
import java.util.stream.Collectors;

public class Gereciador{
    private List<Tarefa> tarefas;

    public Gereciador(){
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String descricao){
        tarefas.add(new Tarefas(descricao));
    }

    public void adicionarTarefa(String descricao, int prioridade){
        tarefas.add(new Tarefa(descricao, prioridade));
    }

    public List<Tarefas> pegarTodasTarefas(){
        return new ArrayList<>(tarefas);
    }
}
