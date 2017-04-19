package rozetkautils;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Folder;
import javax.mail.MessagingException;


public class EmailUtilities {
    public static Message[] getEmailMessages(String host, String user, String pass) throws MessagingException, IOException {
        Auth auth=new Auth(user,pass);
        Message[] messages;
        Properties props = new Properties();
        props.put("mail.debug", "true");
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, auth);
        //IMAPStore emailStore = (IMAPStore) session.getStore("imaps");
        //emailStore.connect(user, pass);
        Store emailStore = session.getStore("imaps");
        emailStore.connect(host, user, pass);

        Folder inbox = emailStore.getFolder("inbox");
        inbox.open(Folder.READ_ONLY);

        messages = inbox.getMessages();

        //получаем последнее сообщение (самое старое будет под номером 1)
        Message m = inbox.getMessage(inbox.getMessageCount()-1);
        Multipart mp = (Multipart) m.getContent();
        BodyPart bp = mp.getBodyPart(0);

        //Выводим содержимое на экран
        System.out.println(bp.getContent());
        return messages;
    }
}
