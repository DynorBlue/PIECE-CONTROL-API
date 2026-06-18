package ismomotors.byd.dynorblue.api.piece.entity;

import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "piece")
@SQLDelete(sql = "UPDATE piece SET active = false WHERE id_piece = ?")
@SQLRestriction("active = true")
@Getter
@Setter
@NoArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_piece")
    private Integer idPiece;

    @Column(name = "number_part")
    private String numberPart;

    @Column(name = "vin")
    private String vin;

    @Column(name = "reporting_date")
    private LocalDateTime reportingDate;

    @Column(name = "claim_application_form")
    private String claimApplicationForm;

    @Column(name = "vehicle")
    private String vehiculo;

    @Column(name = "description")
    private String description;

    @Column(name = "operator")
    private String operator;

    @Column(name = "date_entry")
    private LocalDateTime dateEntry;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock", nullable = false)
    private Stock stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PieceStatus status;

    @Column(name = "active", nullable = false)
    private boolean active;

    @PrePersist
    public void prePersist() {
        this.active = true;
        if (this.status == null) {
            this.status = PieceStatus.CREATED;
        }
    }
}