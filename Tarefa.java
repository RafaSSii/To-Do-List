import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa{
    private String descricao;
    private boolean concluida;
    private LocalDateTime criadaEm;
    private int prioridade;

    public Tarefa(String descricao){
        this.descricao = descricao;
        this.concluida = false;
        this.criadaEm = LocalDateTime.now();
        this.prioridade = 2;
    }

    public Tarefa(String descricao, int prioridade){
        this(descricao);
        this.prioridade = prioridade;
    }

    public String getDescricao(){
        return descricao;
    }

    public boolean estaConcluida(){
        return concluida;
    }

    public LocalDateTime getCriadaEm(){
        return criadaEm;
    }

    public int getPrioridade(){
        return prioridade;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setConcluida(boolean concluida){
        this.concluida = concluida;
    }

    public void setPrioridade(int prioridade){
        if (prioridade >= 1 && prioridade <= 3){
            this.prioridade = prioridade;
        }
    }

    public String getTextoPrioridade(){
        switch (prioridade){
            case 1: return "Alta";
            case 2: return "Média";
            case 3: return "Baixa";
            default: return "Indefinida";
        }
    }

    public String getIconePrioridade(){
        switch (prioridade){
            case 1: return "🔴";
            case 2: return "🟡";
            case 3: return "🟢";
            default: return "⚪";
        }
    }

    @Override
    public String toString(){
        String status = concluida ? "✅" : "⏳";
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return String.format("[%s] %s %s - %s (Criada: %s)", status, getIconePrioridade(), descricao, getTextoPrioridade(), criadaEm.format(formatador));
    }
}