package Hogwarts.ServiceImpl;


import Hogwarts.Domain.People;
import Hogwarts.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendRegistrationNotification(People people) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(people.getEmail());
        simpleMailMessage.setFrom("hogwartssocialnetwork@gmail.com");
        simpleMailMessage.setSubject("Hogwarts social network registration");
        simpleMailMessage.setText("Здравствуйте "+people.getName()+","+'\n'+"Вы были зарегистрированы в социальной сети школы Хогвартс. Однако ,к превеликому сожелению, вы не сможете ею пользоваться т.к. у нас нету денег на хостинг. Но не печальтесь, всё в ваших руках! Поддержите наш проект отправив некоторую сумму денег на вебмоней."+'\n'+" Номер кошелька R906368703202."+'\n'+"Спасибо за вашу поддержку!!");
        System.out.println("Отправка сообщения юзеру..");
        javaMailSender.send(simpleMailMessage);
        System.out.println("Сообщение юзера отправлено");
    }

    @Override
    public void sendRegInfoNotification(People user, List<People> admins) {
        for (People admin: admins) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(admin.getEmail());
            simpleMailMessage.setFrom("hogwartssocialnetwork@gmail.com");
            simpleMailMessage.setSubject("Hogwarts social network registration");
            simpleMailMessage.setText("Новый пользователь - " +user.getLogin()+" оставил заявку на регистрацию, проверьте список заявок.");
            System.out.println("Отправка сообщения..");
            javaMailSender.send(simpleMailMessage);
            System.out.println("Сообщение отправлено");
        }
    }

    @Override
    public void sendApproveInfo(People user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("hogwartssocialnetwork@gmail.com");
        simpleMailMessage.setSubject("Hogwarts social network registration approved");
        simpleMailMessage.setText("Здравствуйте "+user.getName()+","+'\n'+"Ваша заявка на регестрацию была принята. Посетите ваш личный кабинет чтобы узнать больше.");
        System.out.println("Отправка сообщения юзеру..");
        javaMailSender.send(simpleMailMessage);
        System.out.println("Сообщение юзера отправлено");
    }
}
