package com.carwash.carwash.domain.Service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Dtos.usuario.UserDTO;
import com.carwash.carwash.domain.Entities.empresa.Empresa;
import com.carwash.carwash.domain.Entities.usuario.Usuario;
import com.carwash.carwash.domain.Repositories.empresa.EmpresaRepository;
import com.carwash.carwash.domain.Repositories.usuario.UserRepository;
import com.carwash.carwash.util.constantes.Constantes;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

@Service
public class UsuarioService {

  
    private final UserRepository userRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UserRepository userRepository, EmpresaRepository empresaRepository) {
        this.userRepository = userRepository;
        this.empresaRepository = empresaRepository;
    }


    public UserDTO createUser(UserDTO userDTO) {

       
        if (!userDTO.getEmail().matches(Constantes.EMAIL_VALIDATION_REGEX)) {
            throw new CustomException(ErrorMessages.INVALID_EMAIL + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }

        if (!userDTO.getPassword().matches(Constantes.PASSWORD_VALIDATION_REGEX)) {
            throw new CustomException(ErrorMessages.INVALID_PASSWORD, HttpStatus.BAD_REQUEST);
        }

        if (this.userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorMessages.USER_ALREADY_REGISTERED + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }

        Empresa buscaEmpresa = this.empresaRepository.findById(userDTO.getEmpresa())
                .orElseThrow(() -> new CustomException(ErrorMessages.COMPANY_NOT_FOUND + userDTO.getEmpresa(), HttpStatus.NOT_FOUND));

        Usuario user = new Usuario();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmpresa(buscaEmpresa);
        user.setAtivo(true);

        Usuario savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO getUserById(Long id) {

        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.USER_NOT_FOUND + id, HttpStatus.NOT_FOUND));

        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).toList();
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {

        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.USER_NOT_FOUND + id, HttpStatus.NOT_FOUND));

        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorMessages.INVALID_EMAIL + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        Usuario updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new CustomException(ErrorMessages.USER_NOT_FOUND + id, HttpStatus.NOT_FOUND);
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
