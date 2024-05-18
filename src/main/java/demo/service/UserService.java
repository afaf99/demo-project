package demo.service;

import demo.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public Page<User> findAll(Pageable pageable);

    public List<User> findByCriteria(String criteria, String searchItem);

    public Optional<User> findById(Long id);

    public void add(User user);

    public Optional<User> update(User user);

    public Optional<User> delete(Long id);
}
