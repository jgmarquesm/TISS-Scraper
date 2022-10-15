package tissscraper.gerenciamento.relatorio

import static tissscraper.DAO.EmailDAO.read
import static tissscraper.gerenciamento.emails.EnviarEmail.enviarEmail

class EnviarRelatorio {

    static void enviarRelatorio(){
        ArrayList<String> emails = read()

        for (String email : emails){
            enviarEmail(email)
        }
    }
}
