package com.homecare.measurements.patient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.homecare.measurements.access.CurrentUser;
import com.homecare.measurements.access.PatientAccessService;

@WebMvcTest(PatientController.class)
@Import(PatientAccessService.class)
class PatientControllerTest {

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
    }

    @Test
    void returnsThePatientToACareTeamMember() throws Exception {
        when(currentUser.userId()).thenReturn("nurse-1");

        mvc.perform(get("/patients/p-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("p-1"));
    }

    @Test
    void refusesAUserOutsideTheCareTeam() throws Exception {
        when(currentUser.userId()).thenReturn("nurse-2");

        mvc.perform(get("/patients/p-1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void returnsNotFoundForAnUnknownPatient() throws Exception {
        when(currentUser.userId()).thenReturn("nurse-1");

        mvc.perform(get("/patients/p-404"))
                .andExpect(status().isNotFound());
    }
}
