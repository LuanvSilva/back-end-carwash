package com.carwash.carwash.domain.Service.usuario;

import java.util.List;
import java.util.stream.Collectors;

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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UserRepository userRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        validateNewUser(userDTO);
        Empresa empresa = findEmpresaById(userDTO.getEmpresa());
        Usuario user = createUserFromDTO(userDTO, empresa);
        return convertToDTO(userRepository.save(user));
    }

    public UserDTO getUserById(Long id) {
        return convertToDTO(findUserById(id));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Usuario user = findUserById(id);
        validateUpdateUser(user, userDTO);
        updateUserFromDTO(user, userDTO);
        return convertToDTO(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(ErrorMessages.USER_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    private void validateNewUser(UserDTO userDTO) {
        
        if (!userDTO.getEmail().matches(Constantes.EMAIL_VALIDATION_REGEX)) {
            throw new CustomException(ErrorMessages.INVALID_EMAIL + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }
        if (!userDTO.getPassword().matches(Constantes.PASSWORD_VALIDATION_REGEX)) {
            throw new CustomException(ErrorMessages.INVALID_PASSWORD, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorMessages.USER_ALREADY_REGISTERED + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }
    }

    private Empresa findEmpresaById(Long empresaId) {
        return empresaRepository.findById(empresaId)
                .orElseThrow(() -> new CustomException(ErrorMessages.COMPANY_NOT_FOUND + empresaId, HttpStatus.NOT_FOUND));
    }

    private Usuario findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.USER_NOT_FOUND + id, HttpStatus.NOT_FOUND));
    }

    private Usuario createUserFromDTO(UserDTO userDTO, Empresa empresa) {
        Usuario user = new Usuario();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmpresa(empresa);
        user.setAtivo(true);
        return user;
    }

    private void validateUpdateUser(Usuario user, UserDTO userDTO) {
        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorMessages.INVALID_EMAIL + userDTO.getEmail(), HttpStatus.BAD_REQUEST);
        }
    }

    private void updateUserFromDTO(Usuario user, UserDTO userDTO) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
    }

    private UserDTO convertToDTO(Usuario user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}