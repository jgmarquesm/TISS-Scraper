package tissscraper

class App {
    static void main(String[] args) {

        String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss"
        TissScraper.scrape(url)
    }
}

