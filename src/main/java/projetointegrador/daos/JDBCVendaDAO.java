package projetointegrador.daos;

import projetointegrador.daos.interfaces.VendaDAO;
import projetointegrador.db.FabricaConexoes;
import projetointegrador.models.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCVendaDAO implements VendaDAO {
    private static final String INSERT = "INSERT INTO venda(qtd_artesanato,valor_total,id_cliente,id_artesanato) VALUES (?,?,?,?)";
    private static final String LISTA = "SELECT * FROM venda";
    private static final String UPDATE = "UPDATE venda SET qtd_artesanato=?, valor_total=?, id_cliente=?, id_artesanato=? WHERE id_venda=?";
    private static final String BUSCAID = "SELECT * FROM venda WHERE id_venda=?";
    private static final String ULTIMOID = "SELECT * FROM venda ORDER BY id_venda DESC LIMIT 1";


    @Override
    public boolean inserir(Venda venda) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT);

        pstmt.setInt(1, venda.getQtd_artesanato());
        pstmt.setFloat(2, venda.getValor_total());
        pstmt.setInt(3, venda.getId_cliente());
        pstmt.setInt(4, venda.getId_artesanato());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public boolean atualizar(int id, Venda venda) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setInt(1, venda.getQtd_artesanato());
        pstmt.setFloat(2, venda.getValor_total());
        pstmt.setInt(3, venda.getId_cliente());
        pstmt.setInt(4, venda.getId_artesanato());

        pstmt.setInt(5,id);

        int ret = pstmt.executeUpdate();

        return ret==1;
    }

    @Override
    public List<Venda> lista() throws SQLException {
        ArrayList<Venda> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id_venda");
            int qtd_artesanato = rs.getInt("qtd_artesanato");
            float valor_total = rs.getFloat("valor_total");
            int id_cliente = rs.getInt("id_cliente");
            int id_artesanato = rs.getInt("id_artesanato");

            Venda venda = new Venda(id, qtd_artesanato, valor_total, id_cliente, id_artesanato);
            System.out.println(id);
            lista.add(venda);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }

    @Override
    public int ultimoId() throws SQLException {
        int id = 0;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(ULTIMOID);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            id = rs.getInt("id_venda");
        }

        rs.close();
        pstmt.close();
        conn.close();

        return id;
    }

    @Override
    public Venda buscaId(int id) throws SQLException {

        Venda venda=null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            int id_venda = rs.getInt("id_venda");
            int qtd_artesanato = rs.getInt("qtd_artesanato");
            float valor_total = rs.getFloat("valor_total");
            int id_cliente = rs.getInt("id_cliente");
            int id_artesanato = rs.getInt("id_artesanato");

            venda = new Venda(id_venda, qtd_artesanato, valor_total, id_cliente, id_artesanato);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return venda;
    }


    @Override
    public boolean delete(Venda venda) throws SQLException {
        return false;
    }
}