package projetointegrador.daos;

import projetointegrador.daos.interfaces.ClienteDAO;
import projetointegrador.db.FabricaConexoes;
import projetointegrador.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCClienteDAO implements ClienteDAO {
    private static final String INSERT = "INSERT INTO cliente(nome,endereco,email,telefone) VALUES (?,?,?,?)";
    private static final String LISTA = "SELECT * FROM cliente";
    private static final String UPDATE = "UPDATE cliente SET nome=?, endereco=?, email=?, telefone=? WHERE id_cliente=?";
    private static final String BUSCAID = "SELECT * FROM cliente WHERE id_cliente=?";
    private static final String CLIENTEVENDA = "SELECT venda_id_venda FROM artesanato_has_venda WHERE venda_id_cliente=?";

    @Override
    public boolean inserir(Cliente cliente) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT);

        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getEndereco());
        pstmt.setString(3, cliente.getEmail());
        pstmt.setString(4, cliente.getTelefone());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public boolean atualizar(int id, Cliente cliente) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getEndereco());
        pstmt.setString(3, cliente.getEmail());
        pstmt.setString(4, cliente.getTelefone());

        pstmt.setInt(5,id);

        int ret = pstmt.executeUpdate();

        return ret==1;
    }

    @Override
    public List<Cliente> lista() throws SQLException {
        ArrayList<Cliente> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id_cliente");
            String nome = rs.getString("nome");
            String endereco = rs.getString("endereco");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");

            Cliente cliente = new Cliente(id, nome, email, telefone, endereco);

            lista.add(cliente);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }

    @Override
    public Cliente buscaId(int id) throws SQLException {
        Cliente cliente=null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            //int pk = rs.getInt("id");
            String nome = rs.getString("nome");
            String endereco = rs.getString("endereco");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");

            cliente = new Cliente(nome, email, telefone, endereco);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return cliente;
    }

    @Override
    public Cliente buscaClienteDaVenda(int venda_id_cliente) throws SQLException {
        Cliente cliente = null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(CLIENTEVENDA);
        pstmt.setInt(1,venda_id_cliente);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            int id_cliente = rs.getInt("id_cliente");

            cliente = buscaId(id_cliente);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return cliente;
    }


    @Override
    public boolean delete(Cliente Cliente) throws SQLException {
        return false;
    }
}
