package ismomotors.byd.dynorblue.api.piece.service.impl;

import ismomotors.byd.dynorblue.api.exception.ResourceNotFoundException;
import ismomotors.byd.dynorblue.api.piece.converter.PieceConverter;
import ismomotors.byd.dynorblue.api.piece.dto.PieceFilterDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PiecePublicDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceRequestDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceUpdateDTO;
import ismomotors.byd.dynorblue.api.piece.entity.Piece;
import ismomotors.byd.dynorblue.api.piece.repository.PieceRepository;
import ismomotors.byd.dynorblue.api.piece.repository.PieceSpecification;
import ismomotors.byd.dynorblue.api.piece.service.PieceService;
import ismomotors.byd.dynorblue.api.piece.service.PieceStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PieceServiceImpl implements PieceService {

    private final PieceRepository repository;
    private final PieceConverter converter;
    private final PieceSpecification specification;
    private final PieceStatusService statusService;

    @Override
    @Transactional
    public PieceResponseDTO create(PieceRequestDTO dto) {
        Piece piece = converter.toEntity(dto);
        statusService.refreshStatus(piece);
        piece = repository.save(piece);
        return converter.toResponseDTO(piece);
    }

    @Override
    @Transactional
    public PieceResponseDTO update(Integer id, PieceUpdateDTO dto) {
        Piece piece = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Piece not found with id: " + id));
        converter.updateEntityFromDTO(dto, piece);
        statusService.refreshStatus(piece);
        piece = repository.save(piece);
        return converter.toResponseDTO(piece);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Piece not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PieceResponseDTO findById(Integer id) {
        Piece piece = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Piece not found with id: " + id));
        return converter.toResponseDTO(piece);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PieceResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(converter::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PieceResponseDTO> search(PieceFilterDTO filter) {
        return repository.findAll(specification.getSpecification(filter)).stream()
                .map(converter::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PiecePublicDTO findPublicByQrUuid(UUID qrUuid) {
        Piece piece = repository.findByQrUuid(qrUuid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Piece not found for QR: " + qrUuid));
        return converter.toPublicDTO(piece);
    }
}
