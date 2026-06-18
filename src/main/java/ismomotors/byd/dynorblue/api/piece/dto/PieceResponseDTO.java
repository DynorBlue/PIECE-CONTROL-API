package ismomotors.byd.dynorblue.api.piece.dto;

import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PieceResponseDTO {

    private Integer idPiece;
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
