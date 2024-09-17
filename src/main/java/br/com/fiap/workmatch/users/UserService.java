package br.com.fiap.workmatch.users;

import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User create(User user){
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        user.setAvatar("https://avatar.iran.liara.run/username?username=" + user.getName());
        return repository.save(user);
    }

    public void uploadAvatar(String email, MultipartFile file) {
        if (file.isEmpty()){
            throw new RuntimeException("Empty file");
        }

        try (InputStream in = file.getInputStream()) {
            Path destinationDirectory = Path.of("src/main/resources/static/profile");
            Path destinationFile = destinationDirectory
                    .resolve(  System.currentTimeMillis() + file.getOriginalFilename() )
                    .normalize()
                    .toAbsolutePath();

            Files.copy(in, destinationFile);

            System.out.println("Arquivo copiado");

            var user = repository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found") );
            var baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            var avatarPath = baseUrl + "/users/avatar/" + destinationFile.getFileName();
            user.setAvatar(avatarPath);
            repository.save(user);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    public ResponseEntity<Resource> getAvatar(String filename) {
        Path path = Paths.get("src/main/resources/static/profile/" + filename);
        Resource file = UrlResource.from(path.toUri());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
}
