package org.i4di.green.api;

import org.i4di.green.domain.User;
import org.i4di.green.dto.UserDTO;
import org.i4di.green.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        Optional<UserDTO> byId = userService.show(id);

        return byId.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO) {
//        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        boolean result = userService.delete(id);

        return result ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) {
        Optional<UserDTO> updated = userService.update(id, userDTO);

        return updated
                .map(updatedUserDTO -> new ResponseEntity<>(updatedUserDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


//    *****************SA JWT TUTORIJALA

    //radice ovo nakon svakog build-a aplikacije, tj. dodace usere i uloge
//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }

    @GetMapping({"/get-users"})
    public List<User> getUsers() throws Exception {
        return userService.getUsers();
    }

//    @PostMapping({"/registerNewUser"})
//    public User registerNewUser(@RequestBody User user) {
//        return userService.registerNewUser(user);
//    }


    @PostMapping({"/registerNewUser"})
    public ResponseEntity<?>  registerNewUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userService.registerNewUser(userDTO), HttpStatus.CREATED) ;
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
