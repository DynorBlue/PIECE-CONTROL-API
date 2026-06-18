package ismomotors.byd.dynorblue.api.piece.dto;

import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PieceFilterDTO {

    private String numberPart;
    private String vin;
    private Stock stock;
    private PieceStatus status;
    private String operator;
    private LocalDateTime dateEntryFrom;
    private LocalDateTime dateEntryTo;
    private LocalDateTime reportingDateFrom;
    private LocalDateTime reportingDateTo;
}
