package ismomotors.byd.dynorblue.api.piece.controller;

import ismomotors.byd.dynorblue.api.exception.ResourceNotFoundException;
import ismomotors.byd.dynorblue.api.piece.enums.PieceStatus;
import ismomotors.byd.dynorblue.api.piece.enums.Stock;
import ismomotors.byd.dynorblue.api.piece.service.PieceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PieceController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser
class PieceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PieceService service;

    private final LocalDateTime now = LocalDateTime.now();
    private final String requestJson = """
            {
                "numberPart": "NP-001",
                "vin": "VIN-001",
                "claimApplicationForm": "CAF-001",
                "vehiculo": "Sedan",
                "operator": "Operator1",
                "dateEntry": "%s",
                "stock": "BIG"
            }
            """.formatted(now.toString());
    private final String updateJson = """
            {
                "idPiece": 1,
                "numberPart": "NP-001-Updated"
            }
            """;
    private final String invalidJson = "{}";
    private final String filterJson = """
            {
                "stock": "BIG"
            }
            """;
    private final String emptyFilterJson = "{}";
    private final String responseJson = """
            {
                "idPiece": 1,
                "numberPart": "NP-001",
                "vin": "VIN-001",
                "claimApplicationForm": "CAF-001",
                "vehiculo": "Sedan",
                "description": null,
                "operator": "Operator1",
                "dateEntry": "%s",
                "stock": "BIG",
                "status": "ACTIVE",
                "active": true,
                "reportingDate": null
            }
            """.formatted(now.toString());

    @Test
    void create_ShouldReturn201() throws Exception {
        when(service.create(any())).thenAnswer(invocation -> {
            ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO dto = new ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO();
            dto.setIdPiece(1);
            dto.setNumberPart("NP-001");
            dto.setVin("VIN-001");
            dto.setClaimApplicationForm("CAF-001");
            dto.setVehiculo("Sedan");
            dto.setOperator("Operator1");
            dto.setDateEntry(now);
            dto.setStock(Stock.BIG);
            dto.setStatus(PieceStatus.ACTIVE);
            dto.setActive(true);
            return dto;
        });

        mockMvc.perform(post("/api/pieces")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPiece").value(1))
                .andExpect(jsonPath("$.numberPart").value("NP-001"));
    }

    @Test
    void create_ShouldReturn400WhenInvalid() throws Exception {
        mockMvc.perform(post("/api/pieces")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById_ShouldReturn200() throws Exception {
        when(service.findById(1)).thenAnswer(invocation -> {
            ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO dto = new ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO();
            dto.setIdPiece(1);
            dto.setNumberPart("NP-001");
            dto.setVin("VIN-001");
            dto.setClaimApplicationForm("CAF-001");
            dto.setVehiculo("Sedan");
            dto.setOperator("Operator1");
            dto.setDateEntry(now);
            dto.setStock(Stock.BIG);
            dto.setStatus(PieceStatus.ACTIVE);
            dto.setActive(true);
            return dto;
        });

        mockMvc.perform(get("/api/pieces/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPiece").value(1));
    }

    @Test
    void findById_ShouldReturn404() throws Exception {
        when(service.findById(99)).thenThrow(new ResourceNotFoundException("Piece not found with id: 99"));

        mockMvc.perform(get("/api/pieces/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_ShouldReturn200() throws Exception {
        when(service.update(eq(1), any())).thenAnswer(invocation -> {
            ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO dto = new ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO();
            dto.setIdPiece(1);
            dto.setNumberPart("NP-001-Updated");
            dto.setVin("VIN-001");
            dto.setClaimApplicationForm("CAF-001");
            dto.setVehiculo("Sedan");
            dto.setOperator("Operator1");
            dto.setDateEntry(now);
            dto.setStock(Stock.BIG);
            dto.setStatus(PieceStatus.ACTIVE);
            dto.setActive(true);
            return dto;
        });

        mockMvc.perform(put("/api/pieces/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPiece").value(1));
    }

    @Test
    void update_ShouldReturn404() throws Exception {
        when(service.update(eq(99), any()))
                .thenThrow(new ResourceNotFoundException("Piece not found with id: 99"));

        mockMvc.perform(put("/api/pieces/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idPiece\":99}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_ShouldReturn204() throws Exception {
        doNothing().when(service).delete(1);

        mockMvc.perform(delete("/api/pieces/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_ShouldReturn404() throws Exception {
        doThrow(new ResourceNotFoundException("Piece not found with id: 99"))
                .when(service).delete(99);

        mockMvc.perform(delete("/api/pieces/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void filter_ShouldReturn200() throws Exception {
        when(service.search(any())).thenAnswer(invocation -> {
            ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO dto = new ismomotors.byd.dynorblue.api.piece.dto.PieceResponseDTO();
            dto.setIdPiece(1);
            dto.setNumberPart("NP-001");
            dto.setVin("VIN-001");
            dto.setClaimApplicationForm("CAF-001");
            dto.setVehiculo("Sedan");
            dto.setOperator("Operator1");
            dto.setDateEntry(now);
            dto.setStock(Stock.BIG);
            dto.setStatus(PieceStatus.ACTIVE);
            dto.setActive(true);
            return List.of(dto);
        });

        mockMvc.perform(post("/api/pieces/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(filterJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPiece").value(1));
    }

    @Test
    void filter_ShouldReturnEmptyList() throws Exception {
        when(service.search(any())).thenReturn(List.of());

        mockMvc.perform(post("/api/pieces/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyFilterJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
