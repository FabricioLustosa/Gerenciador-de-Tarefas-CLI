import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorTarefas gerenciadorTarefas = new GerenciadorTarefas();

        System.out.print("Digite o caminho para a criação do arquivo:");
        String path = sc.nextLine();
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = sc.nextLine();

        if(!nomeArquivo.toLowerCase().endsWith(".txt")){
            nomeArquivo += ".txt";
        }
        gerenciadorTarefas.criarArquivo(path, nomeArquivo);
        gerenciadorTarefas.carregarTarefas();


        while(true) {

            gerenciadorTarefas.mostrarOpecoesDaLista();

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    while (true) {
                        System.out.println("Digite o nome da tarefa a ser adicionada: ");
                        String tarefaNome = sc.nextLine();
                        System.out.println("Digite a descrição da tarefa a ser adicionada: ");
                        String tarefaDescricao = sc.nextLine();

                        gerenciadorTarefas.addtarefa(tarefaNome, tarefaDescricao);

                        System.out.println("Deseja adicionar outra tarefa? [s/n]");
                        String resposta = sc.nextLine();

                        if (resposta.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    gerenciadorTarefas.listarTarefas();
                    continue;
                case 3:
                    gerenciadorTarefas.marcarConluida();
                    break;
                case 5:
                    gerenciadorTarefas.sair();
            }
        }
    }
}
