package demo.controller;

import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import demo.service.UserService;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){

        return new ResponseEntity<Page<User>>(userService.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            return new ResponseEntity<User>(userOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByCriteria(
            @RequestParam(name = "criteria", required = true)String criteria,
            @RequestParam(name = "searchItem", required = true) String searchItem){
        return new ResponseEntity<List<User>>(userService.findByCriteria(criteria, searchItem), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){
        userService.add(user);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        Optional<User> optionalUser = userService.update(user);

        if (optionalUser.isPresent()){
            return new ResponseEntity<User>(optionalUser.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOptional = userService.delete(id);

        if (userOptional.isPresent()){
            return new ResponseEntity<User>(userOptional.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
