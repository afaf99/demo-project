package demo.service.impl;

import demo.model.User;
import demo.repository.UserRepository;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isPresent()){
            User existingUser = userOptional.get();

            if (user.getFirstName() != null){
                existingUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null){
                existingUser.setLastName(user.getLastName());
            }

            if (user.getAge() != null){
                existingUser.setAge(user.getAge());
            }

            if (user.getCountry() != null){
                existingUser.setCountry(user.getCountry());
            }

            userRepository.save(existingUser);

            return Optional.of(existingUser);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            userRepository.delete(userOptional.get());
            return userOptional;
        }
        return Optional.empty();
    }
}
