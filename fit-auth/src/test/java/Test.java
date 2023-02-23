import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class Test {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        //passwordEncoder.encode("Tpyl63995");

        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        String  str =bCryptPasswordEncoder.encode("123456");
        log.info("--------------》：{}",str);
    }
}
