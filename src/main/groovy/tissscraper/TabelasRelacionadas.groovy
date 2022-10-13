package tissscraper

import groovyx.net.http.OkHttpBuilder
import groovyx.net.http.optional.Download
import org.jsoup.Jsoup

class TabelasRelacionadas {

    static void downloadErrosDeEnviosParaANS(String url, String fileOutName){

        def doc = Jsoup.connect(url).get()

        // Clique aqui para baixar a tabela de erros no envio para a ANS (.xlsx): #parent-fieldname-text > p:nth-child(2) > a
        def urlDownload = doc.select("#parent-fieldname-text > p:nth-child(2) > a").attr("href")

        def arquivo = OkHttpBuilder.configure {
            request.uri = urlDownload
        }.get {
            Download.toFile(delegate, new File("./Downloads/${fileOutName}.xlsx"))
        }
    }
}
