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
}
