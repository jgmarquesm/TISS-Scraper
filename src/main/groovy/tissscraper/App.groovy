package tissscraper

import tissscraper.scrapers.ScrapeAll

import static tissscraper.gerenciamento.emails.AtualizacaoDeEmail.atualizar
import static tissscraper.gerenciamento.emails.CadastroDeEmail.cadastrar
import static tissscraper.gerenciamento.emails.ExclusaoDeEmail.excluir
import static tissscraper.gerenciamento.relatorio.EnviarRelatorio.enviarRelatorio
import static tissscraper.scrapers.HistoricoDeVersoes.scraperHistoricoDeVersoes
import static tissscraper.scrapers.PadraoDoMes.scraperPadraoDoMes
import static tissscraper.scrapers.TabelasRelacionadas.scraperTabelasRelacionadas

class App {
    static void main(String[] args) {

        Scanner entrada = new Scanner(System.in)
        println """>>>>>>>>>> Menu <<<<<<<<<<
> 1 - Coleta de dados
> 2 - Opções da lista envios por e-mail
> 3 - Enviar relatório da coleta de dados por e-mail
"""
        int opcao = entrada.nextInt()

        switch (opcao) {
            case 1:
                menuColetaDeDados()
                break
            case 2:
                crudEmail()
                break
            case 3: enviarRelatorio()
        }
    }

    static void menuColetaDeDados(){

        String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss"

        Scanner entrada = new Scanner(System.in)
        println """>>>>>>>>>> Selecione a opção que deseja coletar <<<<<<<<<<
> 1 - Padrão TISS - Mês/Ano: Componentes de Comunicação
> 2 - Padrão TISS - Histórico das versões dos Componentes do Padrão TISS
> 3 - Padrão TISS - Tabelas Relacionadas: Erros no envio para a ANS
> 4 - Todas as anteriores
"""
        int opcao = entrada.nextInt()
        switch (opcao){
            case 1:
                scraperPadraoDoMes(url)
                break
            case 2:
                scraperHistoricoDeVersoes(url)
                break
            case 3:
                scraperTabelasRelacionadas(url)
                break
            case 4:
                ScrapeAll.scrape(url)
        }
    }

    static void crudEmail(){
        Scanner entrada = new Scanner(System.in)
        println """>>>>>>>>>> Selecione uma opção <<<<<<<<<<
> 1 - Cadastrar e-mail 
> 2 - Atualizar e-mail
> 3 - Excluir e-mail
"""
        int opcao = entrada.nextInt()
        switch (opcao){
            case 1:
                cadastrar()
                break
            case 2:
                atualizar()
                break
            case 3:
                excluir()
        }
    }
}
