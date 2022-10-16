package tissscraper.gerenciamento.emails

import jakarta.activation.DataHandler
import jakarta.activation.DataSource
import jakarta.activation.FileDataSource
import jakarta.mail.Authenticator
import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Multipart
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart

class EnviarEmail {

    static void enviarEmail(String nome, String email){

        final String from = "email@email.com"
        final String username = "username"
        final String password = "password"
        final String host = "smtp.mailtrap.io"
        final int port = 2525

        Properties props = new Properties()
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.host", host)
        props.put("mail.smtp.port", port)
        props.put("mail.debug", "true")

        Authenticator auth = new Authenticator() {
            protected def getPasswordAuthentication = { -> new PasswordAuthentication(username, password as char[]) }
        }

        def session = Session.getInstance(props, auth)
        def message = new MimeMessage(session)

        message.setFrom(new InternetAddress(from))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email))

        message.setSubject("Relatório Mensal de coleta de dados do TISS")

        BodyPart messageBodyPart1 = new MimeBodyPart()
        BodyPart messageBodyPart2 = new MimeBodyPart()
        Multipart multipart = new MimeMultipart()

        messageBodyPart1.setText("Cumprimentos, ${nome}.\n Segue em anexo os arquivos da coleta de dados mensal do TISS.\n Att.,\n João")

        String filename1 = "./Downloads/Componentes de comunicação Set2022.zip"
        String filename2 = "./Downloads/Informações sobre versões do Padrão TISS Jan2016-Set2022.csv"
        String filename3 = "./Downloads/Tabela Erros de envio para ANS.xlsx"
        DataSource source1 = new FileDataSource(filename1)
        DataSource source2 = new FileDataSource(filename2)
        DataSource source3 = new FileDataSource(filename3)

        messageBodyPart2.setDataHandler(new DataHandler(source1))
        messageBodyPart2.setDataHandler(new DataHandler(source2))
        messageBodyPart2.setDataHandler(new DataHandler(source3))
        messageBodyPart2.setFileName(filename1)
        messageBodyPart2.setFileName(filename2)
        messageBodyPart2.setFileName(filename3)

        multipart.addBodyPart(messageBodyPart1)
        multipart.addBodyPart(messageBodyPart2)

        message.setContent(multipart)

        Transport.send(message)

        println("E-mail enviado com sucesso!")
    }
}
