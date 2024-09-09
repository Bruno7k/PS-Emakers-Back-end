package com.emakers.api.infra.config;

import com.emakers.api.data.dto.response.EnderecoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CEP-consumer", url = "http://viacep.com.br/ws/")
public interface CEPConsumerFeign {

    @GetMapping(value = "{CEP}//json/")
    EnderecoResponseDto getCEP(@PathVariable("CEP") String cep);
}
