package br.com.carlospablo.todolist.user;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.carlospablo.todolist.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private IUserRepository userRepository;

    

@PostMapping("/")
public ResponseEntity<?> create(@RequestBody UserModel userModel) {

    System.out.println("Username recebido: " + userModel.getUsername());
    var user = this.userRepository.findByUsername(userModel.getUsername());

     System.out.println("Usuário encontrado: " + user);
     
    if (user != null) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("menssagem","Usuário já existe no banco de dados."));
    }

    var passwordHashed = BCrypt
            .withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(passwordHashed);

    var userCreated = this.userRepository.save(userModel);
    System.out.print("Chegamos ATE AQUI");

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userCreated);
            
}
@Autowired
private JwtService jwtService;
// Autenticação p/ login
@PostMapping("auth/login")
public ResponseEntity<?> login(@RequestBody UserModel userModel){
      
    // 1. buscar usuário pelo username
    var user = this.userRepository.findByUsername(userModel.getUsername());

    // 2. verificar senha com BCrypt
    if (user == null) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não encontrado");
    }
     var passwordVerify = BCrypt.verifyer().verify(
            userModel.getPassword()
            .toCharArray(),
            user.getPassword());

    if (!passwordVerify.verified) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha Inválida");
        }
    

    
    // 3. gerar token com jwtService
    var token = jwtService.generateToken(user.getId().toString() );
    

    // 4. retornar o token
   return ResponseEntity.ok(Map.of("token", token));

}

}

