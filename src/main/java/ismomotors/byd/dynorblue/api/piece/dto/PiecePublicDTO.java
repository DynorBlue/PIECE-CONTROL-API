package ismomotors.byd.dynorblue.api.piece.dto;

import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para la consulta pública de una pieza mediante QR.
 * <p>
 * Contiene todos los campos de la entidad Piece para que la información
 * mostrada al escanear el QR refleje el estado actual en base de datos
 * sin necesidad de regenerar la imagen del código QR.
 * <p>
 * Seguridad:
 * - El endpoint de consulta es de solo lectura (GET).
 * - No expone el ID interno de base de datos (idPiece) como identificador
 *   de búsqueda; se usa el UUID específico para QR.
 * - La validación del UUID previene accesos a recursos inexistentes.
 */
@Getter
@Setter
public class PiecePublicDTO {

    private Integer idPiece;
    private UUID qrUuid;
    private String numberPart;
    private String vin;
    private LocalDateTime reportingDate;
    private String claimApplicationForm;
    private String vehiculo;
    private String description;
    private String operator;
    private LocalDateTime dateEntry;
    private Stock stock;
    private PieceStatus status;
    private boolean active;
}
