package projetointegrador.daos;

import projetointegrador.daos.interfaces.ArtesanatoDAO;
import projetointegrador.db.FabricaConexoes;
import projetointegrador.models.Artesanato;
import projetointegrador.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCArtesanatoDAO implements ArtesanatoDAO {
    private static final String INSERT = "INSERT INTO artesanato(tamanho, material, categoria, valor, estoque) VALUES (?,?,?,?,?)";
    private static final String LISTA = "SELECT * FROM artesanato";
    private static final String UPDATE = "UPDATE artesanato SET tamanho=?, material=?, categoria=?, valor=?, estoque=? WHERE id_artesanato=?";
    private static final String BUSCAID = "SELECT * FROM artesanato WHERE id_artesanato=?";
    private static final String EXCLUIR = "DELETE FROM artesanato WHERE id_artesanato=?";

    @Override
    public boolean inserir(Artesanato artesanato) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT);

        pstmt.setString(1, artesanato.getTamanho());
        pstmt.setString(2, artesanato.getMaterial());
        pstmt.setString(3, artesanato.getCategoria());
        pstmt.setFloat(4, artesanato.getValor());
        pstmt.setInt(5, artesanato.getEstoque());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public boolean atualizar(int id, Artesanato artesanato) throws SQLException {
        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setString(1, artesanato.getTamanho());
        pstmt.setString(2, artesanato.getMaterial());
        pstmt.setString(3, artesanato.getCategoria());
        pstmt.setFloat(4, artesanato.getValor());
        pstmt.setInt(5, artesanato.getEstoque());

        pstmt.setInt(6,id);

        int ret = pstmt.executeUpdate();

        return ret==1;
    }

    @Override
    public List<Artesanato> lista() throws SQLException {
        ArrayList<Artesanato> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id_artesanato");
            String tamanho = rs.getString("tamanho");
            String material = rs.getString("material");
            String categoria = rs.getString("categoria");
            Float valor = rs.getFloat("valor");
            int estoque = rs.getInt("estoque");

            Artesanato artesanato = new Artesanato(id, tamanho, material, categoria, valor, estoque);
            lista.add(artesanato);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }

    @Override
    public Artesanato buscaId(int id) throws SQLException {
        Artesanato artesanato=null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            //int id = rs.getInt("id_artesanato");
            String tamanho = rs.getString("tamanho");
            String material = rs.getString("material");
            String categoria = rs.getString("categoria");
            Float valor = rs.getFloat("valor");
            Integer estoque = rs.getInt("estoque");

            artesanato = new Artesanato(tamanho, material, categoria, valor, estoque);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return artesanato;
    }


    @Override
    public boolean excluir(int id) throws SQLException {

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(EXCLUIR);

        pstmt.setInt(1,id);

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }
}
