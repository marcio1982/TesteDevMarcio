/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.dao;

import br.com.marcio.entity.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adm
 */
public class ContatoDao extends BaseDao {
    PreparedStatement ps;
    ResultSet rs;
 
    
    public void inserir(Contato contato) {
       // TODO Auto-generated method stub
       Connection conexao = getConexao();
       try {
			
           PreparedStatement ps = conexao.prepareStatement("insert into tbcontato (cpfCnpj, nome, telefone) values (?,?,?)");
           //pstm = con.prepareStatement(insert);
           ps.setString(1, contato.getCpfCnpj());
           ps.setString(2, contato.getNome());
           ps.setString(3, contato.getTelefone());
           ps.execute();
           ps.close();
           conexao.close();

       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }

    public void atualizar(Contato contato) {
    // TODO Auto-generated method stub
        String atualiza = "update tbcontato set nome = ?, telefone = ?, cpfCnpj = ? where id = ?";
        Connection conexao = getConexao();
        try {
            ps = conexao.prepareStatement(atualiza);
            ps.setString(1,contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getCpfCnpj());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

   }

    public void remover(Contato contato) {
       // TODO Auto-generated method stub
       String remove = "delete from tbcontato where id = ?";
       Connection conexao = getConexao();
       try {
           ps = conexao.prepareStatement(remove);
           ps.setLong(1,contato.getId());
           ps.executeUpdate();
           ps.close();
           conexao.close();
       } catch (Exception e) {
           // TODO: handle exception
       }
   }

    public Contato pesquizarId(long Id) {
       // TODO Auto-generated method stub
       Connection conexao = getConexao();
       Contato contato = new Contato();
       String pesquisa = "select * from tbcontato where id = ?";
       try {
           ps = conexao.prepareStatement(pesquisa);
           ps.setLong(1,Id);
           rs = ps.executeQuery();
           while (rs.next()){
               contato.setId(rs.getLong("id"));
               contato.setNome(rs.getString("nome"));
               contato.setTelefone(rs.getString("telefone"));
               contato.setCpfCnpj(rs.getString("cpfCnpj"));
           }
           ps.close();
           rs.close();
           return contato;
       } catch (Exception e) {
           // TODO: handle exception
       }
       return null;

    }
    
    public List<Contato> consultar(String texto) {
        List<Contato> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao.prepareStatement("Select * from tbcontato where cpfCnpj like ? or nome like ? or telefone like ?");
            pstm.setString(1, '%'+texto+'%');
            pstm.setString(2, '%'+texto+'%');
            pstm.setString(3, '%'+texto+'%');
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setCpfCnpj(rs.getString("cpfCnpj"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                lista.add(contato);
            }
            pstm.close();
            conexao.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return lista;
    }

    public List<Contato> todos() {
        // TODO Auto-generated method stub
        List<Contato> lista = new ArrayList<>();
        String pesqLista = ("select * from tbcontato");
        Connection conexao = getConexao();
        try {
            ps = conexao.prepareStatement(pesqLista);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setCpfCnpj(rs.getString("cpfCnpj"));
                lista.add(contato);
            }
            ps.close();
            conexao.close();
            rs.close();
            return lista;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    } 
}
