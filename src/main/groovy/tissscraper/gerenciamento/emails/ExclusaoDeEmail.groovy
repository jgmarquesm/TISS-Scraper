package tissscraper.gerenciamento.emails

import static tissscraper.DAO.EmailDAO.delete

class ExclusaoDeEmail {

    static void excluir(){

        Scanner entrada = new Scanner(System.in)

        println "Digite o seu e-mail: "
        String email = entrada.next()

        delete(email)
    }
}
