import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Transport;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * Created by cxlyc007 on 3/11/15.
 */
public class Mail {
    public boolean SendMail(Set<String> toWhom, String eTitle, String eBody) {
        String host = UserMail.getSmtpSender();

        try {

            Properties props = new Properties();
            props.setProperty("mail.stmp.host", host);
            props.setProperty("mail.stmp.auth", "true");

            Session session = Session.getInstance(props);
            session.setDebug(false);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(UserMail.getUser()));

            for (String recipient : toWhom) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }

            message.setSubject(eTitle);
            message.setSentDate(new Date());

            Multipart mul = new MimeMultipart();

            BodyPart mdp = new MimeBodyPart();

            mdp.setContent(eBody, "text/html;charset=utf-8");

            mul.addBodyPart(mdp);

            message.setContent(mul);

            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(host, UserMail.getUser(), UserMail.getPassword());

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
