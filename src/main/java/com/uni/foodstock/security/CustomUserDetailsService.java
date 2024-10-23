
package com.uni.foodstock.security;

import com.uni.foodstock.entidade.Usuario;
import com.uni.foodstock.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
//@Profile("secure")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = this.usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        return new User(user.getEmail(),user.getSenha(), new ArrayList<>());
    }
}
