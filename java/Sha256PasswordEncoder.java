package;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component("passwordEncoder")
public class Sha256PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(rawPassword.toString().getBytes());
            byte[] digestBuffer = digest.digest();
            for (byte b : digestBuffer) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}