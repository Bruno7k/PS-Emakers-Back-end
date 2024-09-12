package com.emakers.api.controller;

import com.emakers.api.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/upload")
    public boolean uploadArquivo(@RequestParam("titulo") String titulo, @RequestParam("texto")String texto,@RequestParam("foto") MultipartFile foto) {
        try{
            arquivoService.salvarArquivo(titulo, texto, foto);
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

}
