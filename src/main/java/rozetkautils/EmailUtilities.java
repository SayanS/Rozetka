package rozetkautils;

import org.dom4j.Node;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import org.jsoup.nodes.Document;
import org.xml.sax.SAXException;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.*;

import org.dom4j.*;
import us.codecraft.xsoup.Xsoup;



public class EmailUtilities {
    private static Message[] messages;

    public static Message[] getEmailMessages(String host, String user, String pass) throws MessagingException, IOException {
        Auth auth = new Auth(user, pass);
        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getInstance(props);

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
        for (Message msg : messages) {
            if (msg.getFrom()[0].toString().contains(fromAddress) && (msg.getSubject().equals(subject))) {
                return msg;
            }
        }
        return null;
    }

    public static String getConfirmRegistrationLinkFromEmail(Message msg) throws IOException, SAXException, MessagingException, JDOMException, DocumentException {
        String s;
        s = msg.getContent().toString();
        Document document = Jsoup.parse(s);
        return Xsoup.compile("//tbody/tr[4]/td/span/a").evaluate(document).getElements().get(0).attr("href");
    }
}
