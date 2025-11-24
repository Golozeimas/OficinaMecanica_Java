package DB;

import Model.Pagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DecimalFormat;

public class PagamentoDAO {

    // Registrar pagamento
    public static boolean registrarPagamento(String idOrdem, double valorTotal, String formaPagamento) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement(
                    "INSERT INTO pagamento (id_ordem, valor_total, forma_pagamento, status_pagamento, data_pagamento) " +
                            "VALUES (?, ?, ?, 'Pago', NOW())"
            );
            stmt.setInt(1, Integer.parseInt(idOrdem));
            stmt.setDouble(2, valorTotal);
            stmt.setString(3, formaPagamento);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao registrar pagamento: " + e.getMessage());
            return false;
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt);
        }
    }

    public static Pagamento buscarPagamentoPorOrdem(String idOrdem) {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT * FROM pagamento WHERE id_ordem = ? ORDER BY data_pagamento DESC LIMIT 1"
            );
            stmt.setInt(1, Integer.parseInt(idOrdem));
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            if (rs.next()) {
                String dataPgto = rs.getString("data_pagamento");
                if (dataPgto == null) dataPgto = "-";

                return new Pagamento(
                        rs.getString("id_pagamento"),
                        rs.getString("id_ordem"),
                        df.format(rs.getDouble("valor_total")),
                        rs.getString("forma_pagamento"),
                        rs.getString("status_pagamento"),
                        dataPgto
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pagamento: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return null;
    }

    // Verificar se ordem já foi paga
    public static boolean ordemJaPaga(String idOrdem) {
        Pagamento pagamento = buscarPagamentoPorOrdem(idOrdem);
        return pagamento != null && pagamento.getStatusPagamento().equals("Pago");
    }

    public static ObservableList<Pagamento> listarPagamentos() {
        ObservableList<Pagamento> listaPagamentos = FXCollections.observableArrayList();
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM pagamento ORDER BY data_pagamento DESC");
            rs = stmt.executeQuery();

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            while (rs.next()) {
                String dataPgto = rs.getString("data_pagamento");
                if (dataPgto == null) dataPgto = "-";

                Pagamento pagamento = new Pagamento(
                        rs.getString("id_pagamento"),
                        rs.getString("id_ordem"),
                        df.format(rs.getDouble("valor_total")),
                        rs.getString("forma_pagamento"),
                        rs.getString("status_pagamento"),
                        dataPgto
                );
                listaPagamentos.add(pagamento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return listaPagamentos;
    }

    public static double calcularTotalReceitas() {
        Connection conexao = ConexaoComBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double total = 0;

        try {
            stmt = conexao.prepareStatement(
                    "SELECT SUM(valor_total) as total FROM pagamento WHERE status_pagamento = 'Pago'"
            );
            rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular receitas: " + e.getMessage());
        } finally {
            ConexaoComBanco.fechaConexao(conexao, stmt, rs);
        }

        return total;
    }
}