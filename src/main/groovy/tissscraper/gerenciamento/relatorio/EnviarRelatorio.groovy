package tissscraper.gerenciamento.relatorio

import static tissscraper.DAO.EmailDAO.read
import static tissscraper.gerenciamento.emails.EnviarEmail.enviarEmail

class EnviarRelatorio {

    static void enviarRelatorio(){
        ArrayList<String> pessoas = read()

        for (String pessoa : pessoas){
            enviarEmail(pessoa[0], pessoa[1])
        }
    }
}
