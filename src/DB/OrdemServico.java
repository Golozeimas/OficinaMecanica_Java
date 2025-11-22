package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdemServico {

    public static boolean deletarOrdem(String idOrdem) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM ordem_servico WHERE id_ordem=?");
            stmt.setInt(1, Integer.parseInt(idOrdem));

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar ordem: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }
}
