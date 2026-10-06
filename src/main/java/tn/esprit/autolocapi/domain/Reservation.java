package tn.esprit.autolocapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutReservation statut;

    // Reservation -> Vehicule : ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicule_id", referencedColumnName = "idVehicule", nullable = false)
    private Vehicule vehicule;

    // Reservation -> Client : ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "idClient", nullable = false)
    private Client client;

    // Reservation -> Contrat : OneToOne (côté inverse)
    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Contrat contrat;
}
