
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        GerenciadorTarefas gerenciadorTarefas = new GerenciadorTarefas();

        System.out.println("O que deseja fazer na sua lista de tarefas?");


        while(true) {

            System.out.println(gerenciadorTarefas.mostrarOpecoesDaLista());

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    while (true) {
                        System.out.println("Digite o nome da tarefa a ser adicionada: ");
                        String tarefaNome = sc.nextLine();
                        System.out.println("Digite a descrição da tarefa a ser adicionada: ");
                        String tarefaDescricao = sc.nextLine();

                        gerenciadorTarefas.addTarefa(tarefaNome, tarefaDescricao);

                        System.out.println("Deseja adicionar outra tarefa? [s/n]");
                        String resposta = sc.nextLine();

                        if (resposta.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println(gerenciadorTarefas.listarTarefas());
                    continue;
                case 3:
                    gerenciadorTarefas.listarTarefas();
                    System.out.print("Digite o ID da tarefa que deseja atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o novo nome da tarefa: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Digite a nova descrição da tarefa: ");
                    String novaDescricao = sc.nextLine();
                    gerenciadorTarefas.atualizarTarefa(idAtualizar, novoNome, novaDescricao);
                break;
                case 4:
                    System.out.print("Digite o id da tarefa que deseja deletar: ");
                    gerenciadorTarefas.listarTarefas();
                    int idDeletar = sc.nextInt();
                    gerenciadorTarefas.deletarTarefa(idDeletar);
                    break;
                case 5:
                    gerenciadorTarefas.sair();
            }
        }
    }
}
