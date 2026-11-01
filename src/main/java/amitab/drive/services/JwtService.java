package amitab.drive.services;


import amitab.drive.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    public String issueAccessToken(User user) {
        return "";
    }
}
