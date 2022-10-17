package tissscraper.gerenciamento.menus

import tissscraper.App
import tissscraper.scrapers.ScrapeAll
import static tissscraper.scrapers.HistoricoDeVersoes.scraperHistoricoDeVersoes
import static tissscraper.scrapers.PadraoDoMes.scraperPadraoDoMes
import static tissscraper.scrapers.TabelasRelacionadas.scraperTabelasRelacionadas

class MenuColetaDeDados {

    static void coletaDeDados(){

        String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss"

        Scanner entrada = new Scanner(System.in)
        println """>>>>>>>>>>>>>>>>> Selecione a opção que deseja coletar <<<<<<<<<<<<<<<<<
> 1 - Padrão TISS - Mês/Ano: Componentes de Comunicação
> 2 - Padrão TISS - Histórico das versões dos Componentes do Padrão TISS
> 3 - Padrão TISS - Tabelas Relacionadas: Erros no envio para a ANS
> 4 - Todas as anteriores
> 5 - Voltar
> 6 - Sair
"""
        int opcao = entrada.nextInt()
        switch (opcao){
            case 1:
                scraperPadraoDoMes(url)
                App.main()
                break
            case 2:
                scraperHistoricoDeVersoes(url)
                App.main()
                break
            case 3:
                scraperTabelasRelacionadas(url)
                App.main()
                break
            case 4:
                ScrapeAll.scrape(url)
                App.main()
                break
            case 5:
                App.main()
                break
            case 6:
                break
        }
    }
}
