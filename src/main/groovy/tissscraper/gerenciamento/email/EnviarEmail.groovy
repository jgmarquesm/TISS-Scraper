package tissscraper.gerenciamento.email

import com.sun.mail.smtp.SMTPTransport
import jakarta.activation.CommandMap
import jakarta.activation.DataHandler
import jakarta.activation.DataSource
import jakarta.activation.FileDataSource
import jakarta.activation.MailcapCommandMap
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Authenticator
import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Multipart
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.Provider
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import java.util.concurrent.Executors

import static jakarta.mail.Provider.Type.TRANSPORT
import static java.util.concurrent.CompletableFuture.runAsync

class EnviarEmail {

    static void enviarEmail(String nome, String email){

        final String USERNAME = "teste.envio.email.jgmarques@gmail.com"
        final String PASSWORD = "eeysowkeixykpwgw"
        final String FROM = "tissScarper@relatorio.mensal"
        final String HOST = "smtp.gmail.com"
        final int PORT = 587

        Properties props = new Properties()
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.host", HOST)
        props.put("mail.smtp.port", PORT)
        props.put("mail.debug", "true")

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD)
            }
        }

        Session session = Session.getInstance(props, auth)
        def message = new MimeMessage(session)

        message.setFrom(new InternetAddress(FROM))
        message.setSentDate(new Date())
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email))

        message.setSubject("Relatório Mensal de coleta de dados do TISS")

        BodyPart messageBodyPart1 = new MimeBodyPart()
        BodyPart messageBodyPart2 = new MimeBodyPart()
        BodyPart messageBodyPart3 = new MimeBodyPart()
        BodyPart messageBodyPart4 = new MimeBodyPart()
        Multipart multipart = new MimeMultipart()

        messageBodyPart1.setText("Saudações, ${nome}.\n Segue em anexo os arquivos da coleta de dados mensal do TISS.\n Att.,\n João")

        String filename1 = "./Downloads/Componentes de comunicação Set2022.zip"
        String filename2 = "./Downloads/Informações sobre versões do Padrão TISS Jan2016-Set2022.csv"
        String filename3 = "./Downloads/Tabela Erros de envio para ANS.xlsx"
        DataSource source1 = new FileDataSource(filename1)
        DataSource source2 = new FileDataSource(filename2)
        DataSource source3 = new FileDataSource(filename3)

        messageBodyPart2.setDataHandler(new DataHandler(source1))
        messageBodyPart3.setDataHandler(new DataHandler(source2))
        messageBodyPart4.setDataHandler(new DataHandler(source3))
        messageBodyPart2.setFileName(filename1)
        messageBodyPart3.setFileName(filename2)
        messageBodyPart4.setFileName(filename3)

        multipart.addBodyPart(messageBodyPart1)
        multipart.addBodyPart(messageBodyPart2)
        multipart.addBodyPart(messageBodyPart3)
        multipart.addBodyPart(messageBodyPart4)

        message.setContent(multipart)
        message.saveChanges()

        runAsync(() -> {

            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap()
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml")
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain")
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed")
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822")
            CommandMap.setDefaultCommandMap(mc)

            Thread.currentThread().setContextClassLoader(getClass().getClassLoader())
            try {
                Provider smtp = Provider.Provider(TRANSPORT, "smtp", "com.sun.mail.smtp.SMTPTransport", "Oracle", "2.0.1")
                Transport tr = session.getTransport(smtp)
                tr.connect(HOST, USERNAME, PASSWORD)
                tr.sendMessage(message, message.getAllRecipients())
                tr.close()
            } catch (final Exception e) {
                throw new RuntimeException(e)
            }
        }, Executors.newSingleThreadExecutor())

        println "E-mail enviado com sucesso!"
    }
}
