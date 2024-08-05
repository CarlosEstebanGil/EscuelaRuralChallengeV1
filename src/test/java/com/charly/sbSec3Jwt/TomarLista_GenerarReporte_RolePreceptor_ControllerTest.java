/*package com.charly.sbSec3Jwt;

import com.charly.sbSec3Jwt.config.SecurityConfig;
import com.charly.sbSec3Jwt.config.AppConfig;
import com.charly.sbSec3Jwt.config.JwtService;
import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.controllers.RolePreceptorController;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TomarListaRequestDTO;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.motivo.Motivo;
import com.charly.sbSec3Jwt.escuelaRural.preceptor.PreceptorService;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.models.AuthResponse;
import com.charly.sbSec3Jwt.models.AuthenticationRequest;
import com.charly.sbSec3Jwt.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDateTime;

@WebMvcTest(controllers = RolePreceptorController.class)
@Import({SecurityConfig.class, AppConfig.class})
public class TomarLista_GenerarReporte_RolePreceptor_ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreceptorService preceptorService;

    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private JwtService jwtService;

    private String jwtToken;

    @BeforeEach
    public void setup() throws Exception {
        authenticatePreceptor();
    }

    private void authenticatePreceptor() throws Exception {
        AuthenticationRequest authRequest = new AuthenticationRequest("lionel@gmail.com", "1234");
        AuthResponse authResponse = new AuthResponse("fake-jwt-token");
        
        Mockito.when(jwtService.generateToken(Mockito.any())).thenReturn("fake-jwt-token");

        MvcResult result = mockMvc.perform(post("/api/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        jwtToken = authResponse.getToken();
    }

    @Test
    @WithMockUser(roles = "PRECEPTOR")
    public void testTomarLista() throws Exception {
        Long alumnoId = 1L;
        boolean presente = true;
        Fecha fecha = new Fecha(LocalDateTime.now(), false, false);
        Motivo motivo = new Motivo(null, "Enfermedad");
        Justificacion justificacion = new Justificacion(motivo, "Razón de justificación");

        Asistencia asistencia = Asistencia.builder()
                .alumno(new Alumno())
                .presente(presente)
                .fecha(fecha)
                .justificacion(justificacion)
                .build();

        TomarListaRequestDTO request = new TomarListaRequestDTO(alumnoId, presente, fecha, justificacion);

        Mockito.when(preceptorService.tomarLista(alumnoId, presente, fecha, justificacion)).thenReturn(asistencia);

        MockHttpServletRequestBuilder requestBuilder = post("/api/preceptor/tomar-lista")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alumno.id").value(alumnoId))
                .andExpect(jsonPath("$.presente").value(presente))
                .andExpect(jsonPath("$.fecha.fecha").value(fecha.getFecha().toString()))
                .andExpect(jsonPath("$.justificacion.motivo.descripcion").value(motivo.getDescripcion()))
                .andExpect(jsonPath("$.justificacion.descripcion").value(justificacion.getDescripcion()));

        Mockito.verify(preceptorService, Mockito.times(1)).tomarLista(alumnoId, presente, fecha, justificacion);
    }

    @Test
    @WithMockUser(roles = "PRECEPTOR")
    public void testGenerarReporte() throws Exception {
        Fecha fecha = new Fecha(LocalDateTime.now(), false, false);
        Reporte reporte = new Reporte();

        Mockito.when(preceptorService.generarReporte(fecha)).thenReturn(reporte);

        MockHttpServletRequestBuilder requestBuilder = post("/api/preceptor/reporte")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(fecha));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reporte.getId()));

        Mockito.verify(preceptorService, Mockito.times(1)).generarReporte(fecha);
    }
}
*/