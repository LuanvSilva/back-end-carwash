package com.carwash.carwash.domain.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.dtos.usuario.UserDTO;
import com.carwash.carwash.domain.entities.usuario.Usuario;
import com.carwash.carwash.domain.repositories.usuario.UserRepository;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

@Service
public class UsuarioService {

  
    private final UserRepository userRepository;

    @Autowired
    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorMessages.EMAIL_INVALIDO + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }

        Usuario user = new Usuario();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setEmpresa(userDTO.getEmpresa());
        user.setAtivo(true);

        Usuario savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO getUserById(Long id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.USUARIO_NAO_ENCONTRADO + id, HttpStatus.NOT_FOUND));

        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).toList();
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.USUARIO_NAO_ENCONTRADO + id, HttpStatus.NOT_FOUND));

        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorMessages.EMAIL_INVALIDO + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        Usuario updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(ErrorMessages.USUARIO_NAO_ENCONTRADO + id, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(Usuario user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
