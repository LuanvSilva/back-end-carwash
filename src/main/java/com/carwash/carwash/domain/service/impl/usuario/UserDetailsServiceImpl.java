package com.carwash.carwash.domain.Service.Impl.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Entities.usuario.Usuario;
import com.carwash.carwash.domain.Repositories.usuario.UserRepository;
import com.carwash.carwash.util.exceptions.ErrorMessages;

import java.util.Collections;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario == null) {
            throw new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND + email);
        }
        return new org.springframework.security.core.userdetails.User(
            usuario.getEmail(),
            usuario.getPassword(),
            Collections.emptyList() // Adicione uma coleção de authorities se necessário
        );
    }

    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}

