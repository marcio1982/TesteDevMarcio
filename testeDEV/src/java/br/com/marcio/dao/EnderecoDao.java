/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.dao;

import br.com.marcio.entity.Endereco;
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
public class EnderecoDao extends BaseDao{
    PreparedStatement ps;
    ResultSet rs;
 
    
    public void inserir(Endereco endereco) {
       // TODO Auto-generated method stub
       Connection conexao = getConexao();
       try {
			
           PreparedStatement ps = conexao.prepareStatement("insert into tbendereco (cpfCnpj, logradouro, bairro, cidade, uf, cep) values (?,?,?,?,?,?)");
           //pstm = con.prepareStatement(insert);
           ps.setString(1, endereco.getCpfCnpj());
           ps.setString(2, endereco.getLogradouro());
           ps.setString(3, endereco.getBairro());
           ps.setString(4, endereco.getCidade());
           ps.setString(5, endereco.getUf());
           ps.setString(6, endereco.getCep());
           ps.execute();
           ps.close();
           conexao.close();

       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }

    public void atualizar(Endereco endereco) {
    // TODO Auto-generated method stub
        String atualiza = "update tbendereco set logradouro = ?, bairro = ?, cidade = ?, uf = ?, cep = ? where id = ?";
        Connection conexao = getConexao();
        try {
            ps = conexao.prepareStatement(atualiza);
            ps.setString(1,endereco.getLogradouro());
            ps.setString(2, endereco.getBairro());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getUf());
            ps.setString(5, endereco.getCep());
            ps.setLong(6, endereco.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

   }

    public void remover(Endereco endereco) {
       // TODO Auto-generated method stub
       String remove = "delete from tbendereco where id = ?";
       Connection conexao = getConexao();
       try {
           ps = conexao.prepareStatement(remove);
           ps.setLong(1,endereco.getId());
           ps.executeUpdate();
           ps.close();
           conexao.close();
       } catch (Exception e) {
           // TODO: handle exception
       }
    }

    public Endereco pesquizarId(long Id) {
       // TODO Auto-generated method stub
       Connection conexao = getConexao();
       Endereco endereco = new Endereco();
       String pesquisa = "select * from tbendereco where id = ?";
       try {
           ps = conexao.prepareStatement(pesquisa);
           ps.setLong(1,Id);
           rs = ps.executeQuery();
           while (rs.next()){
               endereco.setId(rs.getLong("id"));
               endereco.setLogradouro(rs.getString("logradouro"));
               endereco.setBairro(rs.getString("bairro"));
               endereco.setCidade(rs.getString("cidade"));
               endereco.setUf(rs.getString("uf"));
               endereco.setCep(rs.getString("cep"));
           }
           ps.close();
           rs.close();
           return endereco;
       } catch (Exception e) {
           // TODO: handle exception
       }
       return null;

    }
    
    public List<Endereco> consultar(String texto) {
        List<Endereco> lista = new ArrayList<>();
        try {
            Connection conexao = getConexao();
            PreparedStatement pstm = conexao.prepareStatement("Select * from tbendereco where logradouro like ? or bairro like ? or cidade like ? or uf like ? or cep like ? ");
            pstm.setString(1, '%'+texto+'%');
            pstm.setString(2, '%'+texto+'%');
            pstm.setString(3, '%'+texto+'%');
            pstm.setString(4, '%'+texto+'%');
            pstm.setString(5, '%'+texto+'%');
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getLong("id"));
                endereco.setCpfCnpj(rs.getString("cpfCnpj"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setCep(rs.getString("cep"));
                endereco.setUf(rs.getString("uf"));
                lista.add(endereco);
            }
            pstm.close();
            conexao.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return lista;
    }
    public List<Endereco> todos() {
        // TODO Auto-generated method stub
        List<Endereco> lista = new ArrayList<>();
        String pesqLista = ("select * from tbendereco");
        Connection conexao = getConexao();
        try {
            ps = conexao.prepareStatement(pesqLista);
            rs = ps.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getLong("id"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setCep(rs.getString("cep"));
                lista.add(endereco);
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
