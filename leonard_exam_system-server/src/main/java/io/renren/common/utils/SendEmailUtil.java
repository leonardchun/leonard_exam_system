
package io.renren.common.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @Description 邮件发送工具类
 * @Author 王海春
 * @Date 2018/11/27 21:05
 */
public class SendEmailUtil {

    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "smtp.163.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private String SEND_USER = "chuncumt@163.com";
    private String SEND_UNAME = "chuncumt";
    private String SEND_PWD = "wang08113564*";
    // 建立会话
    private MimeMessage message;
    private Session session;

    /**
     * @Description
     * @Param smtpKey 设置服务器key
     * @Param smtpValue 设置服务器value
     * @Param propsKey 服务器验证
     * @Param sendName 发件人用户名
     * @Param sendPwd 发件人密码
     * @Return
     * @Author 王海春
     * @Date 2018/11/28 9:03
     */
    public SendEmailUtil(String smtpKey, String smtpValue, String propsKey, String sendName, String sendPwd) {
        Properties props = System.getProperties();
        props.setProperty(smtpKey, smtpValue);
        props.put(propsKey, "true");
        //props.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendName, sendPwd);
            }
        });
        session.setDebug(true);
        message = new MimeMessage(session);
    }

    /**
     * 发送邮件
     *
     * @param headName    邮件头文件名
     * @param sendHtml    邮件内容
     * @param receiveUser 收件人地址
     */
    public void doSendHtmlEmail(String headName, String sendHtml,
                                String receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SendEmailUtil se = new SendEmailUtil(KEY_SMTP, VALUE_SMTP, KEY_PROPS, "chuncumt", "wang08113564*");
        se.doSendHtmlEmail("邮件头文件名", "邮件内容", "1183770835@qq.com");
    }

}