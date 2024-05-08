package com.emakers.api.infra.security;

import com.emakers.api.data.entity.Pessoa;
import com.emakers.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa pessoa = this.pessoaRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Pessoa nao encontrada"));
        return new org.springframework.security.core.userdetails.User(pessoa.getEmail(), pessoa.getSenha(), new ArrayList<>());
    }
}
