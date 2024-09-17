package br.com.fiap.workmatch.opportunity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.fiap.workmatch.users.User;

@Entity
@Table(name = "opportunity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Opportunity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    String responsibilities;
    String benefits;
    String location;
    String contractType;
    String salary;
    String companyName;
    String industrySector;
    LocalDateTime createdAt;
    
    @ManyToOne
    User user;
}
