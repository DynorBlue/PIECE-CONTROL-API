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
import java.util.UUID;

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

    /**
     * Identificador único e inmutable para consulta pública vía QR.
     * Se usa UUID en lugar del ID interno (idPiece) porque:
     * - Los UUID no son secuenciales ni predecibles, evitando enumeración de recursos.
     * - El ID interno (autoincremental) expone información sobre el volumen de datos.
     * - El UUID es seguro para exponer en URLs públicas.
     *
     * El QR contiene únicamente este UUID como referencia, sin datos de negocio.
     * Al escanear el QR, la información se obtiene en tiempo real desde la BD,
     * reflejando siempre el estado actual sin necesidad de regenerar la imagen.
     */
    @Column(name = "qr_uuid", unique = true, updatable = false)
    private UUID qrUuid;

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
        if (this.qrUuid == null) {
            this.qrUuid = UUID.randomUUID();
        }
        if (this.status == null) {
            this.status = PieceStatus.CREATED;
        }
    }
}