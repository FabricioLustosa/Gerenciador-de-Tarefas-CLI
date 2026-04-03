import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import db.DBConnection;

import java.util.List;

public class GerenciadorTarefas {

    Tarefa ultimaConcluida;


    public Tarefa addTarefa(String nome, String descricao){
        String sql = "INSERT INTO tb_tarefa (nome, descricao) VALUES (?, ?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setNome(nome);
                    tarefa.setDescricao(descricao);

                    return tarefa;
                }
            }
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
                tarefa.setStatus(TarefaStatus.fromCodigo(rs.getInt("status")));
                tarefas.add(tarefa);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return tarefas;
    }

    public void atualizarTarefa(int id, String novoNome, String novaDescricao){
        String sql = "UPDATE tb_tarefa SET nome = ?, descricao = ? WHERE id = " + id;

        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, novoNome);
            stmt.setString(2, novaDescricao);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
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
    public String mostrarOpecoesDaLista(){
        return "O que deseja fazer em sua lista de tarefas? \n" +
                "[1] - Adicionar tarefa \n" +
                "[2] - Listar tarefas \n" +
                "[3] - Atualizar tarefa \n" +
                "[4] - Deletar tarefa \n" +
                "[5] - Sair";
    }

    public void sair(){
        System.exit(0);
    }
}
