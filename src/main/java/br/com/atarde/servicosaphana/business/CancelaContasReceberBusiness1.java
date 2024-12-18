/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosaphana.business;


/**
 *
 * @author mroland
 */
public class CancelaContasReceberBusiness1 {

/*	
    public CancelaContasReceberBusiness1() {
    }

    public void cancelarSAP() throws TSApplicationException {

        List<CancelaContasReceber> cancelaContasReceberList = new ArrayList<CancelaContasReceber>();

        try {

            for (CancelaContasReceber item : new CancelaContasReceberDAO().pesquisar(new CancelaContasReceber(new Status(0L)))) {

                item.getStatus().setId(2L);

                this.alterar(item);

                cancelaContasReceberList.add(item);

            }

            for (CancelaContasReceber cancelaContasReceberModel : cancelaContasReceberList) {

                this.cancelarSAP(cancelaContasReceberModel);

            }


        } catch (TSApplicationException e) {

            e.printStackTrace();
        }

    }

    private void alterar(CancelaContasReceber model) throws TSApplicationException {

        new CancelaContasReceberDAO().alterar(model);

    }

    private void cancelarSAP(CancelaContasReceber model) {

        try {

            if (model.getContasReceber().getTipoModalidade().equals("B")) {

               // model.getContasReceber().setModalidadePagamentoBoleto(new ModalidadePagamentoBoletoDAO().obterporcontasreceberInterface(new ModalidadePagamentoBoleto(model.getContasReceber())));

                new ContasReceberSapDiApiDAO().cancelarBoleto(model.getContasReceber());

            } else {

                if (model.getContasReceber().getTipoModalidade().equals("T")) {

                    new ContasReceberSapDiApiDAO().cancelar(model.getContasReceber());

                }
            }

            model.getStatus().setId(1L);

            model.setMensagemErro(null);

            model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

            this.alterar(model);

        } catch (TSApplicationException e) {

            model.getStatus().setId(0L);

            model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

            try {

                this.alterar(model);

            } catch (TSApplicationException e1) {

                e1.printStackTrace();
            }


        }

    }
    
*/    
}
