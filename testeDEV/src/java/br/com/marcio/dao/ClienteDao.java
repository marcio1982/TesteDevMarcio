/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.dao;

import br.com.marcio.entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author adm
 */
public class ClienteDao extends BaseDao {
    public void alterar(Cliente cliente) {
        try {
            Connection conexao = getConexao();

            PreparedStatement pstmt = conexao
                            .prepareStatement("update tbcliente SET nome = ?, telefone = ?, celular = ?, email = ?, datacadastro = ?"
                                            + " WHERE cpfCnpj = ? ");
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getTelefone());
            pstmt.setString(3, cliente.getCelular());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setDate(5, new java.sql.Date(cliente.getDataCadastro().getTime()));
            pstmt.setString(6, cliente.getCpfCnpj());
            pstmt.execute();
            pstmt.close();
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public void excluir(Cliente cliente) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                            .prepareStatement("delete from	tbcliente where cpfCnpj = ? ");
            pstm.setString(1, cliente.getCpfCnpj());
            pstm.execute();
            pstm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public boolean validaIdade(Cliente cliente) {
        Date data_hoje;
	Date data_nasc;
        data_nasc = (Date) cliente.getDataCadastro();
        data_hoje = new Date();

        boolean menor = false;
        try {
            int idade = data_hoje.getYear() - data_nasc.getYear();
            if (idade <= 18 && cliente.getCpfCnpj().length() <= 11) {
                menor = true;
            }else if (idade < 3 && cliente.getCpfCnpj().length() > 11) {
                menor = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return menor;
    }
    
    public boolean existe(Cliente cliente) {
        boolean achou = false;
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao
                            .prepareStatement("Select * from tbcliente where cpfCnpj =	?");
            pstm.setString(1, cliente.getCpfCnpj());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                    achou = true;
            }
            pstm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return achou;
    }
 
    public void inserir(Cliente cliente) {
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao.prepareStatement("Insert into	tbcliente (cpfCnpj, nome, telefone, celular, email, datacadastro) values(?,?,?,?,?,?)");
            pstm.setString(1, cliente.getCpfCnpj());
            pstm.setString(2, cliente.getNome());
            pstm.setString(3, cliente.getTelefone());
            pstm.setString(4, cliente.getCelular());
            pstm.setString(5, cliente.getEmail());
            pstm.setDate(6, new java.sql.Date(cliente.getDataCadastro()
                            .getTime()));
            pstm.execute();
            pstm.close();
                conexao.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
 
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery("select * from tbcliente");
            while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCpfCnpj(rs.getString("cpfCnpj"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setCelular(rs.getString("celular"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setDataCadastro(new java.util.Date(rs.getDate("datacadastro").getTime()));
                    lista.add(cliente);
            }
            stm.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
 
    public List<Cliente> consultar(String texto) {
        List<Cliente> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao.prepareStatement("Select * from tbcliente where cpfcnpj like ? or nome like ? or telefone like ? or celular like ? or email like ? ");
            pstm.setString(1, '%'+texto+'%');
            pstm.setString(2, '%'+texto+'%');
            pstm.setString(3, '%'+texto+'%');
            pstm.setString(4, '%'+texto+'%');
            pstm.setString(5, '%'+texto+'%');
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpfCnpj(rs.getString("cpfCnpj"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataCadastro(new java.util.Date(rs.getDate("datacadastro").getTime()));
                lista.add(cliente);
            }
            pstm.close();
            conexao.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return lista;
    }
    public Cliente pesquisarId(String cpfCnpj) {
        // TODO Auto-generated method stub
        Connection conexao = getConexao();
        Cliente cliente = new Cliente();
        String pesquisa = "select * from tbcliente where cpfCnpj = ?";
        try {
            PreparedStatement ps  = conexao.prepareStatement(pesquisa);
            ps.setString(1,cpfCnpj);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                cliente.setCpfCnpj(rs.getString("cpfCnpj"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setDataCadastro(new java.util.Date(rs.getDate("datacadastro").getTime()));
            }
            ps.close();
            rs.close();
            return cliente;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }    
}
