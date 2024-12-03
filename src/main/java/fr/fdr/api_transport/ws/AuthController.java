package fr.fdr.api_transport.ws;

import fr.fdr.api_transport.security.jwt.JwtUtils;
import fr.fdr.api_transport.security.models.ERole;
import fr.fdr.api_transport.security.models.Role;
import fr.fdr.api_transport.security.models.User;
import fr.fdr.api_transport.security.repository.RoleRepository;
import fr.fdr.api_transport.security.repository.UserRepository;
import fr.fdr.api_transport.security.request.LoginRequest;
import fr.fdr.api_transport.security.request.SignupRequest;
import fr.fdr.api_transport.security.response.JwtResponse;
import fr.fdr.api_transport.security.response.MessageResponse;
import fr.fdr.api_transport.security.service.UserDetailsImpl;
import fr.fdr.api_transport.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));

    }

    @PutMapping("/changePassword")
    public void changePassword (@Valid @RequestBody SignupRequest signupRequest) {
        this.authService.changePassword(signupRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body( new MessageResponse("Username is already taken!"));
        }

        //Creation du nx user
        User user = new User(signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.EMPLOYE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        }else{
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.EMPLOYE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
