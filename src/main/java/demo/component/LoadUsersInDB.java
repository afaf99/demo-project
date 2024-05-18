package demo.component;

import demo.model.User;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("afafA",  UUID.randomUUID().toString(), "Afaf","Abdullah", 22, "Saudi Arabia");
        User user2 = new User("AsmaA", UUID.randomUUID().toString(),"Asma", "Ahmed", 33, "Saudi Arabia");
        User user3 = new User("NoorA", UUID.randomUUID().toString(),"Noor", "Anwar", 34, "Saudi Arabia");
        User user4 = new User("RoseS", UUID.randomUUID().toString(),"Rose", "Saad", 20, "Saudi Arabia");
        User user5 = new User("MahaH", UUID.randomUUID().toString(),"Maha", "Hatim", 23, "Saudi Arabia");
        User user6 = new User("AmalA", UUID.randomUUID().toString(),"Amal", "Abdulmotaleb", 40, "Saudi Arabia");
        User user7 = new User("MonaB", UUID.randomUUID().toString(),"Mona", "Bakr", 31, "Saudi Arabia");
        User user8 = new User("SallyN", UUID.randomUUID().toString(),"Sally", "Nader", 28, "Saudi Arabia");
        User user9 = new User("RubyO", UUID.randomUUID().toString(),"Ruby", "Omer", 29, "Saudi Arabia");
        User user10 = new User("ReemS", UUID.randomUUID().toString(),"Reem", "Sultan", 38, "Saudi Arabia");

        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

        userList = userList.stream().map(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return user;
        }).collect(Collectors.toList());
        userRepository.saveAll(userList);


    }
}
