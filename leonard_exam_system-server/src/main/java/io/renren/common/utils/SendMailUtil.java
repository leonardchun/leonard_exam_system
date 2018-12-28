package io.renren.common.utils;

import javax.mail.Session;

/**
 * @Description 邮件发送工具类
 * @Author 王海春
 * @Date 2018/11/28 9:09
 */
public class SendMailUtil {

    /**
     * 邮件发送的方法
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param smtp     协议
     * @param host     发送服务器服务器
     * @param sendName 邮件发送人
     * @param sendPort 邮件发送人端口
     * @param userName 邮件发送人名
     * @param userPwd  邮件发送人密码
     * @return 成功或失败
     */
    public static boolean send(String to, String subject, String content, String smtp, String host,
                               String sendName, String sendPort, String userName, String userPwd) {


        return true;
    }
}
