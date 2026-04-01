import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DBConnection;
import java.sql.Connection;
import java.util.List;

public class GerenciadorTarefas {

    ArrayList<Tarefa> tarefas = new ArrayList<>();
    ArrayList<Tarefa> tarefasConcluidas = new ArrayList<>();
    Tarefa ultimaConcluida;

    File arquivo;

    public File criarArquivo(String path, String nomeArquivo){
        try{
            arquivo = new File(path, nomeArquivo);

            if(arquivo.createNewFile()){
                System.out.println("Arquivo criado: " + arquivo.getName());
            }else{
                System.out.println("O arquivo já existe.");
            }
            return arquivo;
        }catch(IOException e){
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
        return null;
    }
    public Tarefa addTarefa(String nome, String descricao){
        String sql = "INSERT INTO tb_tarefa (nome, descricao) VALUES (?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    public List<Tarefa> listarTarefas(){

        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tb_tarefa";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setConcluida(rs.getBoolean("concluida"));

                tarefas.add(tarefa);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return tarefas;
    }

    public boolean marcarConcluida(int input){
       if(input < 0 || input >= tarefas.size()){
           return false;
       }else {
           Tarefa tarefaConcluida = tarefas.remove(input);
           tarefaConcluida.setConcluida(true);
           tarefasConcluidas.add(tarefaConcluida);
           salvarArquivo();

           ultimaConcluida = tarefaConcluida;

           return true;
       }
    }


    public String mostrarOpecoesDaLista(){
        return "O que deseja fazer em sua lista de tarefas? \n" +
                "[1] - Criar tarefas \n" +
                "[2] - Listar tarefas \n" +
                "[3] - Marcar tarefas como concluída \n" +
                "[4] - Deletar tarefas \n" +
                "[5] - Sair";
    }

    public void salvarArquivo(){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {

            bw.write("===PENDENTES===");
            bw.newLine();
            if(tarefas.isEmpty()){
                bw.write("Todas as tarefas foram concluídas.");
                bw.newLine();
            }
            for(Tarefa tarefa : tarefas){
                bw.write("Tarefa: " + tarefa.getNome());
                bw.newLine();
                bw.write("Descrição: " + tarefa.getDescricao());
                bw.newLine();
            }
            bw.write("===CONCLUÍDAS===");
            bw.newLine();
            if(tarefasConcluidas.isEmpty()){
                bw.write("Nenhum tarefa foi concluída.");
            }
            for(Tarefa tarefasConcluidas : tarefasConcluidas){
                bw.write("Tarefa: " + tarefasConcluidas.getNome());
                bw.newLine();
                bw.write("Descrição: " + tarefasConcluidas.getDescricao());
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao criar a tarefa " + e.getMessage());
        }
    }

    public void deletarTarefa(int id){
        String sql = "DELETE FROM tb_tarefa WHERE id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Tarefa> pegarListaDePendentes(){
        return tarefas;
    }
    public ArrayList<Tarefa> pegarListaDeConcluidas(){
        return tarefasConcluidas;
    }


    public void sair(){
        salvarArquivo();
        System.exit(0);
    }
}
