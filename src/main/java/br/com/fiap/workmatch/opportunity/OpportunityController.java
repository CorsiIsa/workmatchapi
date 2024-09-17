package br.com.fiap.workmatch.opportunity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.workmatch.opportunity.dto.OpportunityResponse;
import br.com.fiap.workmatch.users.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    OpportunityService service;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{title}")
    public List<Opportunity> findByTitle(@RequestParam String name){
        return service.findByName(name);
    }
    @GetMapping
    public Page<Opportunity> findAll(@PageableDefault(size = 3) Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping
    public Opportunity create(@RequestBody OpportunityResponse postRequest){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        Opportunity post = postRequest.toModel(user);
        return service.create(post);
    }
}
