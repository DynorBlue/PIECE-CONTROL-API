package ismomotors.byd.dynorblue.api.piece.service;

/**
 * Servicio genérico y desacoplado para generar imágenes de códigos QR.
 * <p>
 * No depende de ninguna entidad ni lógica de negocio. Toma un texto
 * arbitrario y genera una imagen PNG del código QR correspondiente.
 * <p>
 * Esto permite reutilizarlo para cualquier propósito sin acoplar
 * la generación del QR a la lógica de piezas.
 */
public interface QrCodeService {

    /**
     * Genera una imagen PNG de código QR a partir del texto proporcionado.
     *
     * @param text   contenido que codificará el QR (ej: una URL)
     * @param width  ancho de la imagen en píxeles
     * @param height alto de la imagen en píxeles
     * @return arreglo de bytes con la imagen PNG
     */
    byte[] generateQrCodeImage(String text, int width, int height);
}
