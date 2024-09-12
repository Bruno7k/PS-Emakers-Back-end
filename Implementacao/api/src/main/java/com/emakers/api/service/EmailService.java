package com.emakers.api.service;

import jakarta.mail.internet.MimeMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;



@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    //    public String enviarEmailTexto(String destinatario, String assunto, String mensagem) {
    //        try{
    //            SimpleMailMessage message = new SimpleMailMessage();
    //            message.setFrom(remetente);
    //            message.setTo(destinatario);
    //            message.setSubject(assunto);
    //            message.setText(mensagem);
    //            mailSender.send(message);
    //            return "Email enviado com sucesso!";
    //        }catch (Exception e){
    //            return "Erro ao enviar email!";
    //        }
    //    }

    private String imagem6, titulo, texto;
    public void nomeFoto(String titulo, String texto, String caminho) {
        imagem6 = caminho;
        this.titulo = titulo;
        this.texto = texto;
    }

    public String enviarEmailTexto()throws IOException {
        try{
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(remetente);
            helper.setSubject("Resultado do processo seletivo");
            helper.setTo("brunoastronauta18@gmail.com");

            String template = carregarTemplateEmail();
            template = template.replace("#{nome}", "Bruno");
            template = template.replace("#{titulo}", titulo);
            template = template.replace("#{texto}", texto);
            helper.setText(template, true);
            helper.addInline("image1", new File("C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images\\image-1.png"));
            helper.addInline("image2", new File("C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images\\image-2.png"));
            helper.addInline("image3", new File("C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images\\image-3.png"));
            helper.addInline("image4", new File("C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images\\image-4.png"));
            helper.addInline("image5", new File("C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images\\image-5.png"));
            helper.addInline("image6", new File("C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images\\" + imagem6));
            mailSender.send(message);
            return "Email enviado com sucesso!";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String carregarTemplateEmail() throws IOException{
        ClassPathResource classPathResource = new ClassPathResource("/template/trainee.html");
        return new String(classPathResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }


}
