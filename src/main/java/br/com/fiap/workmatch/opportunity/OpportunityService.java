package br.com.fiap.workmatch.opportunity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class OpportunityService {
    
    @Autowired
    OpportunityRepository repository;

    public Page<Opportunity> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Opportunity create(Opportunity post) {
        return repository.save(post);
    }

    public List<Opportunity> findByName(String title){
        return repository.findByTitleContainingIgnoreCase(title);
    }
}
