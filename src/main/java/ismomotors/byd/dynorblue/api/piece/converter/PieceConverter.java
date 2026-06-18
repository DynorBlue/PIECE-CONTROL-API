package ismomotors.byd.dynorblue.api.piece.converter;

import ismomotors.byd.dynorblue.api.piece.dto.PieceRequestDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceUpdateDTO;
import ismomotors.byd.dynorblue.api.piece.entity.Piece;
import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import org.springframework.stereotype.Component;

@Component
public class PieceConverter {

    public Piece toEntity(PieceRequestDTO dto) {
        Piece piece = new Piece();
        piece.setNumberPart(dto.getNumberPart());
        piece.setVin(dto.getVin());
        piece.setReportingDate(dto.getReportingDate());
        piece.setClaimApplicationForm(dto.getClaimApplicationForm());
        piece.setVehiculo(dto.getVehiculo());
        piece.setDescription(dto.getDescription());
        piece.setOperator(dto.getOperator());
        piece.setDateEntry(dto.getDateEntry());
        piece.setStock(dto.getStock());
        return piece;
    }

    public Piece toEntity(PieceUpdateDTO dto) {
        Piece piece = new Piece();
        piece.setIdPiece(dto.getIdPiece());
        piece.setNumberPart(dto.getNumberPart());
        piece.setVin(dto.getVin());
        piece.setReportingDate(dto.getReportingDate());
        piece.setClaimApplicationForm(dto.getClaimApplicationForm());
        piece.setVehiculo(dto.getVehiculo());
        piece.setDescription(dto.getDescription());
        piece.setOperator(dto.getOperator());
        piece.setDateEntry(dto.getDateEntry());
        piece.setStock(dto.getStock());
        piece.setStatus(dto.getStatus());
        return piece;
    }

    public PieceResponseDTO toResponseDTO(Piece piece) {
        PieceResponseDTO dto = new PieceResponseDTO();
        dto.setIdPiece(piece.getIdPiece());
        dto.setNumberPart(piece.getNumberPart());
        dto.setVin(piece.getVin());
        dto.setReportingDate(piece.getReportingDate());
        dto.setClaimApplicationForm(piece.getClaimApplicationForm());
        dto.setVehiculo(piece.getVehiculo());
        dto.setDescription(piece.getDescription());
        dto.setOperator(piece.getOperator());
        dto.setDateEntry(piece.getDateEntry());
        dto.setStock(piece.getStock());
        dto.setStatus(piece.getStatus());
        dto.setActive(piece.isActive());
        return dto;
    }

    public void updateEntityFromDTO(PieceUpdateDTO dto, Piece piece) {
        if (dto.getNumberPart() != null) {
            piece.setNumberPart(dto.getNumberPart());
        }
        if (dto.getVin() != null) {
            piece.setVin(dto.getVin());
        }
        if (dto.getReportingDate() != null) {
            piece.setReportingDate(dto.getReportingDate());
        }
        if (dto.getClaimApplicationForm() != null) {
            piece.setClaimApplicationForm(dto.getClaimApplicationForm());
        }
        if (dto.getVehiculo() != null) {
            piece.setVehiculo(dto.getVehiculo());
        }
        if (dto.getDescription() != null) {
            piece.setDescription(dto.getDescription());
        }
        if (dto.getOperator() != null) {
            piece.setOperator(dto.getOperator());
        }
        if (dto.getDateEntry() != null) {
            piece.setDateEntry(dto.getDateEntry());
        }
        if (dto.getStock() != null) {
            piece.setStock(dto.getStock());
        }
        if (dto.getStatus() != null) {
            piece.setStatus(dto.getStatus());
        }
    }
}
