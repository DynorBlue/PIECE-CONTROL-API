package ismomotors.byd.dynorblue.api.piece.service;

import ismomotors.byd.dynorblue.api.piece.entity.Piece;
import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PieceStatusService {

    public PieceStatus calculateStatus(Piece piece) {

        if (piece.getReportingDate() == null) {
            return PieceStatus.CREATED;
        }

        long days = ChronoUnit.DAYS.between(
                piece.getReportingDate(),
                LocalDateTime.now()
        );

        if (days < 30) return PieceStatus.ACTIVE;
        if (days < 60) return PieceStatus.THIRTY_DAYS;
        if (days < 90) return PieceStatus.SIXTY_DAYS;

        return PieceStatus.DESTROYED;
    }

    public void refreshStatus(Piece piece) {
        piece.setStatus(calculateStatus(piece));
    }
}
