package ismomotors.byd.dynorblue.api.piece.dto;

import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PieceRequestDTO {

    @NotBlank(message = "numberPart is required")
    private String numberPart;

    @NotBlank(message = "vin is required")
    private String vin;

    private LocalDateTime reportingDate;

    @NotBlank(message = "claimApplicationForm is required")
    private String claimApplicationForm;

    @NotBlank(message = "vehiculo is required")
    private String vehiculo;

    private String description;

    @NotBlank(message = "operator is required")
    private String operator;

    @NotNull(message = "dateEntry is required")
    private LocalDateTime dateEntry;

    @NotNull(message = "stock is required")
    private Stock stock;
}
