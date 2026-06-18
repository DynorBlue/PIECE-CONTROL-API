package ismomotors.byd.dynorblue.api.piece.controller;

import ismomotors.byd.dynorblue.api.piece.dto.PieceFilterDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceRequestDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO;
import ismomotors.byd.dynorblue.api.piece.dto.PieceUpdateDTO;
import ismomotors.byd.dynorblue.api.piece.service.PieceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class PieceController {

    private final PieceService service;

    @PostMapping
    public ResponseEntity<PieceResponseDTO> create(@Valid @RequestBody PieceRequestDTO dto) {
        PieceResponseDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceResponseDTO> findById(@PathVariable Integer id) {
        PieceResponseDTO response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PieceResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody PieceUpdateDTO dto) {
        PieceResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PieceResponseDTO>> filter(@RequestBody PieceFilterDTO filter) {
        List<PieceResponseDTO> response = service.search(filter);
        return ResponseEntity.ok(response);
    }
}
