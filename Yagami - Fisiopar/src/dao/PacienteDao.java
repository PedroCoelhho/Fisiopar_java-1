/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Paciente;
import engine.ConnectionFactory;

/**
 *
 * @author Juan
 */
public class PacienteDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Paciente> listar() {
        String sql = "SELECT * FROM paciente";

        List<Paciente> pacientes = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Paciente paciente = new Paciente();

                //Recupera o id do banco e atribui ele ao objeto
                paciente.setId(rset.getInt("id"));
                paciente.setNome(rset.getString("nome"));
                paciente.setData_nasc(rset.getDate("data_nasc"));
                paciente.setSexo(rset.getString("sexo"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                pacientes.add(paciente);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pacientes;
    }

    public boolean salvar(Paciente paciente) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO paciente\n" +
        "(nome, data_nasc, sexo, cpf, rg, est_civ,\n" +
        "etnia, nome_resp, nome_mae, tel_prim,\n" +
        "tel_sec, email, fk_endereco, convenio, cns,\n" +
        "valid_cart, dat_hr)\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
        "?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  paciente.getNome());
            pstm.setDate  (2,  paciente.getData_nasc());
            pstm.setString(3,  paciente.getSexo());
            pstm.setString(4,  paciente.getCpf());
            pstm.setString(5,  paciente.getRg());
            pstm.setString(6,  paciente.getEst_civ());
            pstm.setString(7,  paciente.getEtnia());
            pstm.setString(8,  paciente.getNome_resp());
            pstm.setString(9,  paciente.getNome_mae());
            pstm.setString(10, paciente.getTel_prim());
            pstm.setString(11, paciente.getTel_sec());
            pstm.setString(12, paciente.getEmail());
            pstm.setInt   (13, paciente.getFk_endereco());
            pstm.setString(14, paciente.getConvenio());
            pstm.setString(15, paciente.getCns());
            pstm.setDate  (16, paciente.getValid_cart());
            pstm.setTime  (17, paciente.getData_hr());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Algo deu errado...\n" + e,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(Exception e){
                e.printStackTrace();
            }
            return retorno;
        }
    }

    public boolean alterar(Paciente paciente) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = String.format("UPDATE paciente "
                   + "SET nome = '%s', data_nasc = '%s', sexo = '%s' "
                   + "WHERE id = %s", paciente.getNome(), paciente.getData_nasc(), paciente.getSexo(), paciente.getId());

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            /*
            pstm.setString(1, paciente.getNome());
            pstm.setDate(2, paciente.getData_nasc());
            pstm.setString(3, paciente.getSexo());
            pstm.setInt(4, paciente.getId());
            */
            //Executa a sql para inserção dos dados
            
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Algo deu errado...\n" + e,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(Exception e){
                e.printStackTrace();
            }
            return retorno;
        }
    }

    public boolean deletar(int id) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "DELETE FROM paciente "
                   + "WHERE id = " + id;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Algo deu errado...\n" + e,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(Exception e){
                e.printStackTrace();
            }
            return retorno;
        }
    }

}