package ismomotors.byd.dynorblue.api.piece.repository;

import ismomotors.byd.dynorblue.api.piece.entity.Piece;
import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Integer>, JpaSpecificationExecutor<Piece> {

    List<Piece> findByNumberPart(String numberPart);

    List<Piece> findByVin(String vin);

    List<Piece> findByReportingDate(LocalDateTime reportingDate);

    List<Piece> findByStock(Stock stock);

    List<Piece> findByStatus(PieceStatus status);

    List<Piece> findByOperator(String operator);

    List<Piece> findByDateEntry(LocalDateTime dateEntry);

    Optional<Piece> findByQrUuid(UUID qrUuid);
}
