package com.emakers.api.service;

import jakarta.persistence.EntityManager;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ArquivoService {

    @Autowired
    private EmailService emailService;

    private static final String diretorioPasta = "C:\\Users\\Bruno\\Documents\\PS-Emakers-Back-end\\Implementacao\\api\\src\\main\\resources\\static\\images";

    public void salvarArquivo(String titulo, String texto, MultipartFile arquivoSalvo) throws IOException {
        if(arquivoSalvo == null){
            throw new NullPointerException(" O arquivo e nullo");
        }
        var arquivoAlvo = new File(diretorioPasta+ File.separator +arquivoSalvo.getOriginalFilename());
        if(!Objects.equals(arquivoAlvo.getParent(), diretorioPasta)){
            throw new ServiceException("O nome do arquivo nao e suportado");
        }
        Files.copy(arquivoSalvo.getInputStream(), arquivoAlvo.toPath(), StandardCopyOption.REPLACE_EXISTING);

        emailService.nomeFoto(titulo, texto, arquivoAlvo.getName());
    }
}