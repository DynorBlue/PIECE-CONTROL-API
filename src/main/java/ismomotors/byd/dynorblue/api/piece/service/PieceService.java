package ismomotors.byd.dynorblue.api.piece.service;

import ismomotors.byd.dynorblue.api.piece.dto.PieceFilterDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceRequestDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceUpdateDTO;

import java.util.List;

public interface PieceService {

    PieceResponseDTO create(PieceRequestDTO dto);

    PieceResponseDTO update(Integer id, PieceUpdateDTO dto);

    void delete(Integer id);

    PieceResponseDTO findById(Integer id);

    List<PieceResponseDTO> findAll();

    List<PieceResponseDTO> search(PieceFilterDTO filter);
}
