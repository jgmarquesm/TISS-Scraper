package tissscraper

import org.jsoup.Jsoup

import static tissscraper.HistoricoDeVersoes.extrairDadosDaTabela
import static tissscraper.PadraoDoMes.downloadComponeteDeComunicacao
import static tissscraper.TabelasRelacionadas.downloadErrosDeEnviosParaANS

class TissScraper {
    static void scrape(String url) {

        def doc = Jsoup.connect(url).get()

        // Selector Padrão TISS - Ms/Ano: #parent-fieldname-text > p:nth-child(4) > a
        String urlPadraoDoMes = doc.select("#parent-fieldname-text > p:nth-child(4) > a").attr("href")
        String nameOutPadraoDoMes = "Componentes de comunicação Set2022"

        // Selector Padrão TISS - Histórico das versões dos Componentes do Padrão TISS: #parent-fieldname-text > p:nth-child(6) > a
        String urlVersoesAnteriores = doc.select("#parent-fieldname-text > p:nth-child(6) > a").attr("href")
        String nameOutVersoesAnteriores = "Informações sobre versões do Padrão TISS Jan2016-Set2022"
        String limiteDeDataInferior = "dez/2015"

        // Selector Padrão TISS - Tabelas Relacionadas: #parent-fieldname-text > p:nth-child(8) > a
        String urlTabelasRelacionadas = doc.select("#parent-fieldname-text > p:nth-child(8) > a").attr("href")
        String nameOutErrosDeEnvio = "Tabela Erros de envio para ANS"

        downloadComponeteDeComunicacao(urlPadraoDoMes, nameOutPadraoDoMes)
        extrairDadosDaTabela(urlVersoesAnteriores, nameOutVersoesAnteriores, limiteDeDataInferior)
        downloadErrosDeEnviosParaANS(urlTabelasRelacionadas, nameOutErrosDeEnvio)
    }
}
