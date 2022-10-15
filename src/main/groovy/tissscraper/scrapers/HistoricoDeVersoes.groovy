package tissscraper.scrapers

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class HistoricoDeVersoes {

    static void scraperHistoricoDeVersoes(String url){

        def doc = Jsoup.connect(url).get()

        // Selector Padrão TISS - Histórico das versões dos Componentes do Padrão TISS: #parent-fieldname-text > p:nth-child(6) > a
        String urlVersoesAnteriores = doc.select("#parent-fieldname-text > p:nth-child(6) > a").attr("href")

        String nameOutVersoesAnteriores = "Informações sobre versões do Padrão TISS Jan2016-Set2022"
        String limiteDeDataInferior = "dez/2015"

        extrairDadosDaTabela(urlVersoesAnteriores, nameOutVersoesAnteriores, limiteDeDataInferior)
    }

    static void extrairDadosDaTabela(String url, String fileNameOut, String limitMesInferior){
        def doc = Jsoup.connect(url).get()
        Element table = doc.getElementsByTag("table").first()
        Element tbody = table.getElementsByTag("tbody").first()
        List<Element> meses = tbody.getElementsByTag("tr")

        String historico = "Competência, Publicação, Início da Vigencia\n"

        for (Element el: meses){
            List<Element> colunas = el.getElementsByTag("td")
            if (colunas.get(0).text().equalsIgnoreCase(limitMesInferior)) {
                break
            } else {
                historico += colunas.get(0).text() + ", " + colunas.get(1).text() + ", " + colunas.get(2).text() + "\n"
            }
        }
        salvarCsv(historico, fileNameOut)
    }

    static void salvarCsv(String textoDoArquivo, String nomeDoArquivo){
        String path = "./Downloads/${nomeDoArquivo}.csv"
        try {
            File file = new File(path)
            FileWriter myWriter = new FileWriter(file.getPath())
            myWriter.write(textoDoArquivo)
            myWriter.close()
            System.out.println("Arquivo escrito com sucesso.")
        } catch (IOException e) {
            System.out.println("Ocorreu um erro!")
            e.printStackTrace()
        }
    }
}
