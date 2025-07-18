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
        return tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == prioridade).collect(Collectors.toList());
    }

    public boolean concluirTarefa(int indice){
        if (indiceValido(indice)){
            tarefas.get(indice).setConcluida(true);
            return true;
        }
        return false;
    }

    public boolean removerTarefa(int indice){
        if (indiceValido(indice)){
            tarefas.remove(indice);
            return true;
        }
        return false;
    }

    public boolean editarTarefa(int indice, String novaDescricao){
        if (indiceValido(indice)){
            tarefas.get(indice).setDescricao(novaDescricao);
            return true;
        }
        return false;
    }

    public boolean alternarPrioridade(int indice, int novaPrioridade){
        if (indiceValido(indice)){
            tarefas.get(indice).setPrioridade(novaPrioridade);
            return true;
        }
        return false;
    }

    public Tarefa obterTarefa(int indice){
        if (indiceValido(indice)){
            return tarefas.get(indice);
        }
        return null;
    }

    public boolean indiceValido(int indice){
        return indice >= 0 && indice < tarefas.size();
    }

    public int getTotalTarefas(){
        return tarefas.size();
    }

    public EstatisticasTarefas obterEstatisticas(){
        int total = tarefas.size();
        int concluidas = (int) tarefas.stream().filter(Tarefa::estaConcluida).count();
        int pendentes = total - concluidas;

        return new EstatisticasTarefas(total, concluidas, pendentes);
    }

    public void ordenarPrioridade(){
        tarefas.sort(Comparator.comparingInt(Tarefa::getPrioridade));
    }

    public int limparTarefasConcluidas(){
        int removidas = (int) tarefas.stream().filter(Tarefa::estaConcluida).count();
        tarefas.removeIf(Tarefa::estaConcluida);
        return removidas;
    }
}
