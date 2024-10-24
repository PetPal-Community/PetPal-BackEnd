package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.integration.email.dto.Mail;
import com.ingsw.petpal.integration.email.service.EmailService;
import com.ingsw.petpal.model.entity.PasswordResetToken;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.UserGeneral;
import com.ingsw.petpal.repository.PasswordResetTokenRepository;
import com.ingsw.petpal.repository.UserGeneralRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserGeneralRepository userGeneralRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    @Override
    public void createAndSendPasswordResetToken (String email) throws Exception {
        UserGeneral userGeneral = userGeneralRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado con email: "+ email));
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setUser(userGeneral);
        passwordResetToken.setExpiration(10);
        passwordResetTokenRepository.save(passwordResetToken);

        Map<String, Object> model = new HashMap<>();
        String resetUrl = "http://localhost:4200/#/forgot/" + passwordResetToken.getToken();
        model.put("user", userGeneral.getEmail());
        model.put("resetUrl", resetUrl);

        Mail mail = emailService.createMail(
                userGeneral.getEmail(),
                "Restablecer Contraseña",
                model,
                mailFrom
        );

        emailService.sendEmail(mail, "email/password-reset-template");
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .orElseThrow(()-> new ResourceNotFoundException("Token de reestablecimiento de contraseña no encontrado"));
    }

    @Override
    public void removeResetToken (PasswordResetToken passwordResetToken){
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public boolean isValidToken(String token){
        return passwordResetTokenRepository.findByToken(token).filter(t->!t.isExpired()).isPresent();
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .orElseThrow(()->new ResourceNotFoundException("Token invalido o expirado"));

        UserGeneral user = resetToken.getUser();
        user.setContrasena(passwordEncoder.encode(newPassword));
        userGeneralRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);
    }

}
