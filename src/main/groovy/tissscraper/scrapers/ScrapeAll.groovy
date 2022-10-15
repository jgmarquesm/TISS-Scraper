package tissscraper.scrapers


import static HistoricoDeVersoes.scraperHistoricoDeVersoes
import static PadraoDoMes.scraperPadraoDoMes
import static TabelasRelacionadas.scraperTabelasRelacionadas

class ScrapeAll {
    static void scrape(String url) {

        scraperPadraoDoMes(url)
        scraperHistoricoDeVersoes(url)
        scraperTabelasRelacionadas(url)
    }
}
