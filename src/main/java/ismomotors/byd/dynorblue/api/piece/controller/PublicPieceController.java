package ismomotors.byd.dynorblue.api.piece.controller;

import ismomotors.byd.dynorblue.api.piece.dto.PiecePublicDTO;
import ismomotors.byd.dynorblue.api.piece.service.PieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Endpoint público para consulta de piezas mediante identificador QR.
 * <p>
 * Consideraciones de seguridad:
 * - Es un endpoint de solo lectura (GET), no permite escritura.
 * - No requiere autenticación, accesible por cualquier persona que escanee el QR.
 * - El identificador es un UUID, no secuencial ni predecible.
 * - No expone el ID interno de base de datos como parámetro de búsqueda.
 * - La información devuelta se obtiene en tiempo real desde la BD,
 *   reflejando cualquier actualización futura sin regenerar el QR.
 * - Se valida que el UUID exista; si no, se retorna 404.
 */
@RestController
@RequestMapping("/api/public/qr")
@RequiredArgsConstructor
public class PublicPieceController {

    private final PieceService pieceService;

    @GetMapping("/{qrUuid}")
    public ResponseEntity<PiecePublicDTO> getPieceByQr(@PathVariable UUID qrUuid) {
        PiecePublicDTO dto = pieceService.findPublicByQrUuid(qrUuid);
        return ResponseEntity.ok(dto);
    }
}
