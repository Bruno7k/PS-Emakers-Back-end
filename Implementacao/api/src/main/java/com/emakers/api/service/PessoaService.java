package com.emakers.api.service;

import com.emakers.api.data.dto.request.LoginRequestDto;
import com.emakers.api.data.dto.request.PessoaRequestDto;
import com.emakers.api.data.dto.response.LoginResponseDto;
import com.emakers.api.data.dto.response.PessoaResponseDto;
import com.emakers.api.data.entity.Pessoa;
import com.emakers.api.exception.authentication.CepNotExistsException;
import com.emakers.api.exception.authentication.EmailAlreadyExistsException;
import com.emakers.api.exception.general.EntityNotFoundException;
import com.emakers.api.exception.authentication.IncorrectPasswordException;
import com.emakers.api.exception.general.OperationNotAllowed;
import com.emakers.api.infra.config.CEPConfig;
import com.emakers.api.infra.config.CEPConsumerFeign;
import com.emakers.api.infra.security.TokenService;
import com.emakers.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.emakers.api.data.dto.response.EnderecoResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    private final CEPConfig cepConfig = new CEPConfig();

    @Autowired
    private  CEPConsumerFeign cepConsumerFeign;

    @Autowired
    private EmailService emailService;

    public List<PessoaResponseDto> getAllPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
    }

    public PessoaResponseDto getPessoaId(Long idPessoa){
        Pessoa pessoa = getPessoaByID(idPessoa);
        return new PessoaResponseDto(pessoa);
    }


    public LoginResponseDto updatePessoa(Long idPessoa, PessoaRequestDto pessoaRequestDto){
        Optional<Pessoa> aux = pessoaRepository.findByEmail(pessoaRequestDto.email());
        Pessoa pessoa = getPessoaByID(idPessoa);
        Long idAux = aux.get().getIdPessoa();
        if(idAux == pessoa.getIdPessoa()) {
            pessoa.setEmail(pessoaRequestDto.email());
            pessoa.setSenha(passwordEncoder.encode(pessoaRequestDto.senha()));
            pessoa.setNome(pessoaRequestDto.nome());
            pessoa.setCep(pessoaRequestDto.cep());
            this.pessoaRepository.save(pessoa);
            String token = tokenService.generateToken(pessoa);
            return new LoginResponseDto(pessoa.getEmail(), token);
        }
        throw new EmailAlreadyExistsException();
    }
    public String deletePessoa(Long idPessoa) {
        Pessoa pessoa = getPessoaByID(idPessoa);
        if (pessoa.getLivros().isEmpty()) {
            pessoaRepository.delete(pessoa);
            return "A pessoa com id: " + idPessoa + " foi removida com sucesso";
        }else{
            throw new OperationNotAllowed();
        }
    }

    //usando CEPConfig
//    public LoginResponseDto register(PessoaRequestDto pessoaRequestDto){
//        Optional<Pessoa> aux = pessoaRepository.findByEmail(pessoaRequestDto.email());
//        if(aux.isEmpty()) {
//            Pessoa pessoa = new Pessoa();
//            EnderecoResponseDto enderecoDto = cepConfig.getEndereco(pessoaRequestDto.cep());
//            pessoa.setEmail(pessoaRequestDto.email());
//            pessoa.setSenha(passwordEncoder.encode(pessoaRequestDto.senha()));
//            pessoa.setNome(pessoaRequestDto.nome());
//            pessoa.setCep(pessoaRequestDto.cep());
//            if (enderecoDto != null) {
//                pessoa.setLogradouro(enderecoDto.logradouro());
//                pessoa.setBairro(enderecoDto.bairro());
//                pessoa.setCidade(enderecoDto.localidade());
//                pessoa.setUf(enderecoDto.uf());
//            } else {
//                // Lidar com o caso onde o endereço não foi encontrado (CEP inválido ou erro na requisição)
//                throw new RuntimeException("Não foi possível encontrar o endereço para o CEP: " + pessoaRequestDto.cep());
//            }
//            this.pessoaRepository.save(pessoa);
//            String token = tokenService.generateToken(pessoa);
//            return new LoginResponseDto(pessoa.getEmail(), token);
//        }
//        throw new EmailAlreadyExistsException();
//    }
    public LoginResponseDto register(PessoaRequestDto pessoaRequestDto) {
        Optional<Pessoa> aux = pessoaRepository.findByEmail(pessoaRequestDto.email());
        if (aux.isEmpty()) {
            EnderecoResponseDto enderecoDto = cepConsumerFeign.getCEP(pessoaRequestDto.cep());
            if (enderecoDto == null || enderecoDto.logradouro() == null) {
                throw new CepNotExistsException(pessoaRequestDto.cep());
            }
            Pessoa pessoa = new Pessoa();
            pessoa.setEmail(pessoaRequestDto.email());
            pessoa.setSenha(passwordEncoder.encode(pessoaRequestDto.senha()));
            pessoa.setNome(pessoaRequestDto.nome());
            pessoa.setCep(pessoaRequestDto.cep());
            pessoa.setLogradouro(enderecoDto.logradouro());
            pessoa.setBairro(enderecoDto.bairro());
            pessoa.setCidade(enderecoDto.localidade());
            pessoa.setUf(enderecoDto.uf());
            this.pessoaRepository.save(pessoa);
            String token = tokenService.generateToken(pessoa);
            emailService.enviarEmailTexto(pessoaRequestDto.email(),
                    "Novo usuario cadastro com sucesso",
                    "Voce esta recebendo um email de cadastro na biblioteca do Bruno");
            return new LoginResponseDto(pessoa.getEmail(), token);
        }

        throw new EmailAlreadyExistsException();
    }


    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Pessoa pessoa = pessoaRepository.findByEmail(loginRequestDto.email()).orElseThrow(() -> new EntityNotFoundException(loginRequestDto.email()));
        if(passwordEncoder.matches(loginRequestDto.senha(), pessoa.getSenha())){
            String token = tokenService.generateToken(pessoa);
            return new LoginResponseDto(pessoa.getEmail(), token);
        }
        throw new IncorrectPasswordException();
    }

    private Pessoa getPessoaByID(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa));

    }

}
