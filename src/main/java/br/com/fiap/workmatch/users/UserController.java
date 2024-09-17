package br.com.fiap.workmatch.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.workmatch.users.dto.UserRequest;
import br.com.fiap.workmatch.users.dto.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder){
        var user = service.create(userRequest.toModel());

        var uri = uriBuilder
                    .path("/users/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();

        return ResponseEntity
                .created(uri)
                .body(UserResponse.from(user));
    }

    @PostMapping("avatar")
    public void uploadAvatar(@RequestBody MultipartFile file){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        service.uploadAvatar(email, file);
    }

    @GetMapping("avatar/{filename}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename){
        return service.getAvatar(filename);
    }
}
