/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosaphana.sap.dao;

import br.com.atarde.servicosaphana.sap.model.ParceiroNegocioContaBanco;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioContaBancoDAO {

    public ParceiroNegocioContaBancoDAO() {
    }

    public ParceiroNegocioContaBanco obterPorContasReceber(ParceiroNegocioContaBanco model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("parceironegociocontabancodao.obterPorContasReceber", model.getParceiroNegocio().getContasReceber().getId());

        return (ParceiroNegocioContaBanco) broker.getObjectBean(ParceiroNegocioContaBanco.class, "id", "parceiroNegocioModel.contasReceberModel.id", "parceiroNegocioModel.id",
                                                                                                           "codigoBanco","numeroConta","digitoConta", "numeroAgencia", "digitoAgencia", "tipoConta");
        
    }

    public ParceiroNegocioContaBanco obterPorParceiroNegocio(ParceiroNegocioContaBanco model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("parceironegociocontabancodao.obterPorParceiroNegocio", model.getParceiroNegocio().getId());

        return (ParceiroNegocioContaBanco) broker.getObjectBean(ParceiroNegocioContaBanco.class, "id");

    }

}
