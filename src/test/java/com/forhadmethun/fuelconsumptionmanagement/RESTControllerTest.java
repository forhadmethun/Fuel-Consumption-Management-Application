package com.forhadmethun.fuelconsumptionmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forhadmethun.fuelconsumptionmanagement.registration.RegistrationDTO;
import com.forhadmethun.fuelconsumptionmanagement.registration.RegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@WebMvcTest(RESTController.class)
public class RESTControllerTest {
    @MockBean
    private RegistrationService registrationService;
    @Autowired
    private MockMvc mvc;

    @Test
    public void createFuelRegistrationCheck() throws Exception {
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        registrationDTOS.add(new RegistrationDTO(1L, "D", 10.0, 4.0, "12345", "01.21.2018"));
        registrationDTOS.add(new RegistrationDTO(2L, "D", 10.0, 4.0, "12345", "01.21.2018"));

        mvc.perform(
                post("/create-fuel-registration").
                        content(asJsonString(registrationDTOS))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
