/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zeppelin.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * Zeppelin security rest api endpoint.
 */
public class SendMailService {
  /**
   * Get ticket Returns username & ticket for anonymous access, username is
   * always anonymous. After getting this ticket, access through websockets
   * become safe
   *
   * @return 200 response
  */
  public void sendMail(byte[] pdf) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("teste@gmail.com", "senha");
      }
    });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("teste@gmail.com"));
      message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("teste@gmail.com"));
      message.setSubject("Testing Subject");
      MimeBodyPart attachment = new MimeBodyPart();
      ByteArrayDataSource src = new ByteArrayDataSource(pdf, "application/pdf");

      Multipart mp1 = new MimeMultipart();
      attachment.setDataHandler(new DataHandler(src));
      attachment.setFileName("sample.pdf");
      mp1.addBodyPart(attachment);
      message.setContent(mp1);

      Transport.send(message);
      System.out.println("Done");
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
