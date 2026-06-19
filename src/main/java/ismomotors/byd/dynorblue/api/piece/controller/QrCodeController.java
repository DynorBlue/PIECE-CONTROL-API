package ismomotors.byd.dynorblue.api.piece.controller;

import ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO;
import ismomotors.byd.dynorblue.api.piece.service.PieceService;
import ismomotors.byd.dynorblue.api.piece.service.QrCodeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador protegido (requiere autenticación) para generar imágenes QR
 * de piezas bajo demanda.
 * <p>
 * El QR generado apunta a la URL pública de consulta ({@code /api/public/qr/{uuid}}).
 * Como el QR contiene únicamente una referencia (UUID), la información mostrada
 * al escanear se obtiene en tiempo real desde la base de datos, por lo que
 * cualquier actualización de la pieza se refleja sin necesidad de regenerar
 * la imagen del código QR.
 * <p>
 * La imagen no se almacena en base de datos; se genera en memoria bajo demanda.
 */
@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class QrCodeController {

    private final PieceService pieceService;
    private final QrCodeService qrCodeService;

    @Value("${app.qr.base-url}")
    private String baseUrl;

    @GetMapping("/{pieceId}/qr-code")
    public ResponseEntity<byte[]> getQrCode(
            @PathVariable Integer pieceId,
            @RequestParam(defaultValue = "300") int width,
            @RequestParam(defaultValue = "300") int height,
            HttpServletRequest request) {

        PieceResponseDTO piece = pieceService.findById(pieceId);
        String qrContent = baseUrl + "/api/public/qr/" + piece.getQrUuid();
        byte[] qrImage = qrCodeService.generateQrCodeImage(qrContent, width, height);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }
}
