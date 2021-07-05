package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    ClinicService clinicSrvMock;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Init Mock
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        // Given
        // The Mocking is not necessary for this case, since we are returning an empty List
        // This occurs automatically exactly by not mocking!!!
        when(clinicSrvMock.findOwnerByLastName(anyString())).thenReturn(Collections.emptyList());

    }

    @Test
    void processFindForm_Owners_NotFound() throws Exception {
        mockMvc.perform(get("/owners").param("lastName","Mock"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrorCode("owner","lastName","notFound"))
                .andExpect(view().name("owners/findOwners"))
                .andDo(print());
    }
}