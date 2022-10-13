package tissscraper

import groovyx.net.http.OkHttpBuilder
import groovyx.net.http.optional.Download
import org.jsoup.Jsoup

class PadraoDoMes {

    static void downloadComponeteDeComunicacao(String url, String fileOutName){

        def doc = Jsoup.connect(url).get()

        // Componentes de Comunicação - Baixar: #parent-fieldname-text > div > table > tbody > tr:nth-child(5) > td:nth-child(3) > a
        def urlDownload = doc.select("#parent-fieldname-text > div > table > tbody > tr:nth-child(5) > td:nth-child(3) > a").attr("href")

        // Download
        def arquivo = OkHttpBuilder.configure {
            request.uri = urlDownload
        }.get {
            Download.toFile(delegate, new File("./Downloads/${fileOutName}.zip"))
        }
    }
}
