package model.DAO;

import java.util.List;
import model.bo.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.bo.ContaAReceber;
import model.bo.Estoque;
import model.bo.ItemDeVenda;
import model.bo.PessoaFisica;

public class VendaDAO implements InterfaceDAO<Venda> {

    @Override
    public void Create(Venda objeto) {

        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.VENDA_CREATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());

            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setFloat(5, objeto.getValorDoDesconto());
            pstm.setFloat(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getPessoaFisica().getId());
            pstm.setString(9, objeto.getUserCaixa());
            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public Venda Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conexao.prepareStatement(SQL.VENDA_RETRIVE_ONE_ID);
            rs = pstm.executeQuery();
            Venda venda = new Venda.VendaBuilder().createVenda();

            while (rs.next()) {
                venda.setId(rs.getInt("id"));
                venda.setData(rs.getString("datavenda"));
                venda.setHora(rs.getString("hora"));
                venda.setDataDeVencimento(rs.getString("datavencimento"));
                venda.setObservacao(rs.getString("observacao"));
                venda.setValorDoDesconto(rs.getFloat("valordesconto"));
                venda.setValorTotal(rs.getFloat("valortotal"));
                venda.setStatus(rs.getBoolean("status"));
                venda.setPessoaFisica(
                        service.ServicePessoaFisica.Buscar(rs.getInt("pessoafisicaid"))
                );
                venda.setUserCaixa(rs.getString("usercaixa"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return venda;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public List<Venda> Retrieve() {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Venda> vendas = new ArrayList();
        try {
            pstm = conexao.prepareStatement(SQL.VENDA_RETRIVE_ALL);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda.VendaBuilder()
                        .setId(rs.getInt("id"))
                        .setData(rs.getString("datavenda"))
                        .setHora(rs.getString("hora"))
                        .setDataDeVencimento(rs.getString("datavencimento"))
                        .setObservacao(rs.getString("observacao"))
                        .setValorDoDesconto(rs.getFloat("valordesconto"))
                        .setValorTotal(rs.getFloat("valortotal"))
                        .setStatus(rs.getBoolean("status"))
                        .setPessoaFisica(
                                service.ServicePessoaFisica.Buscar(rs.getInt("pessoafisicaid"))
                        )
                        .setUserCaixa(rs.getString("usercaixa"))
                        .createVenda();
                vendas.add(venda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return vendas;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    public List<Venda> RetrieveBuscaVendaDeUmCliente(PessoaFisica pessoaFisica) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.VENDA_RETRIVE_ONE_ID_PESSOA_FISICA_ID);
            pstm.setInt(1, pessoaFisica.getId());
            rs = pstm.executeQuery();
            List<Venda> vendas = new ArrayList();

            while (rs.next()) {
                Venda venda = new Venda.VendaBuilder()
                        .setId(rs.getInt("id"))
                        .setData(rs.getString("datavenda"))
                        .setHora(rs.getString("hora"))
                        .setDataDeVencimento(rs.getString("datavencimento"))
                        .setObservacao(rs.getString("observacao"))
                        .setValorDoDesconto(rs.getFloat("valordesconto"))
                        .setValorTotal(rs.getFloat("valortotal"))
                        .setStatus(rs.getBoolean("status"))
                        .setPessoaFisica(
                                service.ServicePessoaFisica.Buscar(rs.getInt("pessoafisicaid"))
                        )
                        .setUserCaixa(rs.getString("usercaixa"))
                        .setItensDeVenda(
                                service.ServiceItemDeVenda.BuscarListaDeUmaVenda(
                                        rs.getInt("id")
                                )
                        )
                        .createVenda();
                vendas.add(venda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return vendas;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Venda objeto) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.VENDA_UPDATE);

            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setDouble(5, objeto.getValorDoDesconto());
            pstm.setDouble(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getPessoaFisica().getId());
            pstm.setString(9, objeto.getUserCaixa());
            pstm.setInt(10, objeto.getId());
            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Venda objeto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {

            for (ItemDeVenda i : objeto.getItensDeVenda()) {
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(i.getProduto().getId());
                estoque.setQuantidade(estoque.getQuantidade() + i.getQuantidade());
                service.ServiceEstoque.Atualizar(estoque);
                service.ServiceItemDeVenda.Deletar(i);
            }

            ContaAReceber contaaReceber = service.ServiceContaAReceber.BuscarIdDaContaAReceberPeloIdDaVenda(objeto.getId());
            service.ServiceContaAReceber.Deletar(contaaReceber);

            pstm = conexao.prepareStatement(SQL.VENDA_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public void Delete(int idVenda) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {
            Venda venda = service.ServiceVenda.Buscar(idVenda);
            for (ItemDeVenda i : venda.getItensDeVenda()) {
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(i.getProduto().getId());
                estoque.setQuantidade(estoque.getQuantidade() + i.getQuantidade());
                service.ServiceEstoque.Atualizar(estoque);
                service.ServiceItemDeVenda.Deletar(i);
            }

            ContaAReceber contaaReceber = service.ServiceContaAReceber.BuscarIdDaContaAReceberPeloIdDaVenda(idVenda);
            service.ServiceContaAReceber.Deletar(contaaReceber);

            pstm = conexao.prepareStatement(SQL.VENDA_DELETE);
            pstm.setInt(1, venda.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public int Retrieve(Venda venda) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.VENDA_RETRIVE_VENDA_OBJ);
            pstm.setFloat(1, venda.getValorTotal());
            pstm.setInt(2, venda.getPessoaFisica().getId());
            pstm.setString(3, venda.getData());
            rs = pstm.executeQuery();

            while (rs.next()) {
                venda.setId(rs.getInt("id"));//1
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return venda.getId();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: VendaDAO->Retrive\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

}
