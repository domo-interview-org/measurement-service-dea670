package com.homecare.measurements.measurement;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.homecare.measurements.access.CurrentUser;
import com.homecare.measurements.access.PatientAccessService;
import com.homecare.measurements.patient.Patient;
import com.homecare.measurements.patient.PatientRepository;

/**
 * Written from docs/requirements/measurements.md before the endpoint exists.
 */
@Disabled("Enable when POST /patients/{patientId}/measurements is implemented")
@WebMvcTest
@Import(PatientAccessService.class)
class MeasurementControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PatientRepository patientRepository;

    @MockBean
    CurrentUser currentUser;

    @BeforeEach
    void setUp() {
        when(patientRepository.findById("p-1"))
                .thenReturn(Optional.of(new Patient("p-1", "Anna Muster", List.of("nurse-1"))));
        when(currentUser.userId()).thenReturn("nurse-1");
    }

    @Test
    void recordsATemperature() throws Exception {
        mvc.perform(post("/patients/p-1/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"type": "TEMPERATURE", "value": 37.2, "unit": "°C"}
                                """))
                .andExpect(status().isCreated());
    }

    // REQ-MEAS-2
    @Test
    void rejectsATemperatureOutsideThePlausibleRange() throws Exception {
        mvc.perform(post("/patients/p-1/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"type": "TEMPERATURE", "value": 370, "unit": "°C"}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void rejectsAMeasurementWithoutAType() throws Exception {
        mvc.perform(post("/patients/p-1/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"value": 37.2, "unit": "°C"}
                                """))
                .andExpect(status().isBadRequest());
    }
}
