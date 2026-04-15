package com.github.app.model.consulta;



import java.time.LocalDateTime;

import com.github.app.model.medico.Medico;
import com.github.app.model.paciente.Paciente;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;


    @JoinColumn(name = "medicoId") // define o nome da coluna "elias" na tabela consulta. Ou seja, 
    // pega PK do médico transforma em FK em consulta, com o nome da coluna de medicoId.
    @ManyToOne
    private Medico medico;

    @JoinColumn(name = "pacienteId") // define o nome da coluna "elias" na tabela consulta. Ou seja, 
    // pega PK do paciente transforma em FK em consulta, com o nome da coluna de pacienteId.
    @ManyToOne
    private Paciente paciente;
    
    @Enumerated(EnumType.STRING)
    private Status status; 

    private String observacao;

    private LocalDateTime data;

    
}
