package rozetkautils;

import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.mail.*;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Folder;
import javax.mail.MessagingException;


public class EmailUtilities {
    private static Message[] messages;
    public static Message[] getEmailMessages(String host, String user, String pass) throws MessagingException, IOException {
        Auth auth=new Auth(user,pass);
        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(props);

        Store store = emailSession.getStore("pop3s");
        store.connect(host, user, pass);

        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);
        messages = inbox.getMessages();
/*
        //получаем последнее сообщение (самое старое будет под номером 1)
        Message m = inbox.getMessage(inbox.getMessageCount()-1);
        Multipart mp = (Multipart) m.getContent();
        BodyPart bp = mp.getBodyPart(0);*/
        return messages;
    }

    public static Message getEmailMessage(String email, String password, String fromAddress, String subject) throws IOException, MessagingException {
        messages = getEmailMessages("pop.mail.ru", email, password);
        for(Message msg:messages){
            if(msg.getFrom()[0].toString().contains(fromAddress)&&(msg.getSubject().equals(subject))){
                return msg;
            }
        }
        return null;
    }

    public static void clickOnConfirmRegistration(Message msg) throws IOException, SAXException {
        DOMParser parser = new DOMParser();
        parser.parse(String.valueOf(msg));
        org.w3c.dom.Document doc = parser.getDocument();

        DOMReader reader = new DOMReader();
        Document document = reader.read(doc);

        @SuppressWarnings("unchecked")
        List<Node> nodes = document.selectNodes("//span[contains(text(),'перейдите по ссылке')]/ancestor::a");
        nodes.get(0).getStringValue()

    }
}
