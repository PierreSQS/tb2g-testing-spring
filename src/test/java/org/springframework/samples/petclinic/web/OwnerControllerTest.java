package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(get("/owners/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("owner"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testProcessCreationFormGoodPath() throws Exception {
        Owner ownerMock = new Owner();
        ownerMock.setId(4);
        ownerMock.setCity("Douala");
        ownerMock.setFirstName("Owner");
        ownerMock.setLastName("Mock");
        ownerMock.setTelephone("123434434");
        ownerMock.setAddress("Rue des Manguiers");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id","4");
        multiValueMap.add("firstName", ownerMock.getFirstName());
        multiValueMap.add("lastName", ownerMock.getLastName());
        multiValueMap.add("city", ownerMock.getCity());
        multiValueMap.add("telephone", ownerMock.getTelephone());
        multiValueMap.add("address", ownerMock.getAddress());

        mockMvc.perform(post("/owners/new").params(multiValueMap))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors())
                // Not Possible since we can not Mock clinicService.saveOwner(owner) is void-method!!
                //.andExpect(view().name("redirect:/owners/"+ownerMock.getId()))
                .andDo(print());
    }
}