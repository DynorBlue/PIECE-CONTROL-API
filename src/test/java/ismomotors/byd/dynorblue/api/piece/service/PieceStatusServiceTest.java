package ismomotors.byd.dynorblue.api.piece.service;

import ismomotors.byd.dynorblue.api.piece.entity.Piece;
import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceStatusServiceTest {

    private PieceStatusService statusService;

    @BeforeEach
    void setUp() {
        statusService = new PieceStatusService();
    }

    @Test
    void shouldReturnCreatedWhenReportingDateIsNull() {
        Piece piece = new Piece();
        assertEquals(PieceStatus.CREATED, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnActiveWhenDaysLessThan30() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(15));
        assertEquals(PieceStatus.ACTIVE, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnActiveWhenExactly29Days() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(29));
        assertEquals(PieceStatus.ACTIVE, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnThirtyDaysWhenDaysBetween30And59() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(45));
        assertEquals(PieceStatus.THIRTY_DAYS, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnThirtyDaysWhenExactly30Days() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(30));
        assertEquals(PieceStatus.THIRTY_DAYS, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnSixtyDaysWhenDaysBetween60And89() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(75));
        assertEquals(PieceStatus.SIXTY_DAYS, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnSixtyDaysWhenExactly60Days() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(60));
        assertEquals(PieceStatus.SIXTY_DAYS, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnDestroyedWhen90OrMoreDays() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(90));
        assertEquals(PieceStatus.DESTROYED, statusService.calculateStatus(piece));
    }

    @Test
    void shouldReturnDestroyedWhenMoreThan90Days() {
        Piece piece = new Piece();
        piece.setReportingDate(LocalDateTime.now().minusDays(120));
        assertEquals(PieceStatus.DESTROYED, statusService.calculateStatus(piece));
    }

    @Test
    void shouldUpdateStatusOnRefresh() {
        Piece piece = new Piece();
        piece.setStatus(PieceStatus.CREATED);
        piece.setReportingDate(LocalDateTime.now().minusDays(15));
        statusService.refreshStatus(piece);
        assertEquals(PieceStatus.ACTIVE, piece.getStatus());
    }

    @Test
    void shouldRemainCreatedWhenNoReportingDate() {
        Piece piece = new Piece();
        piece.setStatus(PieceStatus.CREATED);
        statusService.refreshStatus(piece);
        assertEquals(PieceStatus.CREATED, piece.getStatus());
    }
}
