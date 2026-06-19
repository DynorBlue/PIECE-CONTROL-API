package ismomotors.byd.dynorblue.api.piece.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import ismomotors.byd.dynorblue.api.piece.service.QrCodeService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Implementación de QrCodeService usando ZXing.
 * <p>
 * Genera códigos QR en formato PNG bajo demanda, sin almacenar
 * la imagen en base de datos ni en el sistema de archivos.
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public byte[] generateQrCodeImage(String text, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating QR code image", e);
        }
    }
}
