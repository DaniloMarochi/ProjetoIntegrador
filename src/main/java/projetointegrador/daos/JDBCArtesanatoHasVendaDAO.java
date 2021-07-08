package projetointegrador.daos;

import projetointegrador.controls.JanelaBase;
import projetointegrador.daos.interfaces.ArtesanatoHasVendaDAO;
import projetointegrador.db.FabricaConexoes;
import projetointegrador.models.ArtesanatoHasVenda;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCArtesanatoHasVendaDAO implements ArtesanatoHasVendaDAO {

    private static String INSERE = "INSERT INTO artesanato_has_venda(artesanato_id_artesanato, venda_id_venda) VALUES (?,?)";
    private static String BUSCAID = "SELECT * FROM artesanato_has_venda WHERE id=?";
    private static String LISTA = "SELECT * FROM artesanato_has_venda";

    //private static String TOTALVENDAS = "CALL total_vendas(?)";

    @Override
    public boolean inserir(ArtesanatoHasVenda artesanatoHasVenda) throws SQLException {

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERE);

        pstmt.setInt(1,artesanatoHasVenda.getId_artesanato());
        pstmt.setInt(2,artesanatoHasVenda.getId_venda());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public ArtesanatoHasVenda buscaId(int id) throws SQLException {

        ArtesanatoHasVenda artesanatoHasVenda = null;

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(BUSCAID);

        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            int pk = rs.getInt("id");
            int id_venda = rs.getInt("venda_id_venda");
            int id_artesanato = rs.getInt("artesanato_id_artesanato");

            artesanatoHasVenda = new ArtesanatoHasVenda(pk, id_artesanato,id_venda);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return artesanatoHasVenda;
    }

    @Override
    public List<ArtesanatoHasVenda> lista() throws SQLException {

        List<ArtesanatoHasVenda> lista = new ArrayList<>();

        Connection conn = FabricaConexoes.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(LISTA);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){

            int id = rs.getInt("id");
            int id_venda = rs.getInt("venda_id_venda");
            int id_artesanato = rs.getInt("artesanato_id_artesanato");

            ArtesanatoHasVenda artesanatoHasVenda = new ArtesanatoHasVenda(id, id_artesanato, id_venda);

            lista.add(artesanatoHasVenda);

        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }




}
