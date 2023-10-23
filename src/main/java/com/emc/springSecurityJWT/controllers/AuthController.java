package com.emc.springSecurityJWT.controllers;

import com.emc.springSecurityJWT.configuration.jwt.JwtProvider;
import com.emc.springSecurityJWT.dto.CreateUserDto;
import com.emc.springSecurityJWT.dto.JwtDto;
import com.emc.springSecurityJWT.dto.LoginDto;
import com.emc.springSecurityJWT.entities.UserEntity;
import com.emc.springSecurityJWT.enums.E_UserAuthority;
import com.emc.springSecurityJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

//    @Autowired
//    UserService usuarioService;

    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult){
        System.out.println(loginDto.getLogin()+" / "+loginDto.getKey());
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos mal puestos", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getKey()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        System.out.println("token: "+jwt);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> nuevo(@Valid @RequestBody CreateUserDto newUser, BindingResult bindingResult){
//
//        System.out.println("CreateUserDto: " + newUser.toString());
//
//        if(bindingResult.hasErrors())
//            return new ResponseEntity("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);
//        if(usuarioService.existByUserLogin(newUser.getLogin()))
//            return new ResponseEntity("ese login ya existe", HttpStatus.BAD_REQUEST);
//        if(usuarioService.existByUserName(newUser.getName()))
//            return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
//        if(usuarioService.existByUserMail(newUser.getEmail()))
//            return new ResponseEntity("ese email ya existe", HttpStatus.BAD_REQUEST);
//        UserEntity usuario = new UserEntity(
//                newUser.getLogin(),
//                newUser.getName(),
//                newUser.getEmail(),
//                //se vuelve a cambiar el valor el constructor para hacer las pruebas
//                passwordEncoder.encode("tragsa"));
//
//        if(newUser.getType()== E_UserAuthority.ADMINISTRADOR)
//           usuario.setUsuTipo(0);
//        if(newUser.getType()== E_UserAuthority.GESTOR)
//           usuario.setUsuTipo(1);
//        if(newUser.getType()== E_UserAuthority.CONSULTOR)
//           usuario.setUsuTipo(2);
//
//        System.out.println("UserEntity: "+usuario);
//        usuarioService.saveUser(usuario);
//        return new ResponseEntity("usuario guardado", HttpStatus.CREATED);
//    }

//    @GetMapping("/users")
//    public Optional<List<UserEntity>> getUsers(){
//        usuarioService.getUsers().get().forEach(System.out::println);
//       return Optional.of(usuarioService.getUsers().get());
//    }
}
