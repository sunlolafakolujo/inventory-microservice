package com.inventory.appuserservice.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.appuserservice.AbstractContainerBaseTest;
import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.address.service.AddressService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ObjectMapper objectMapper;

    Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
    }

    @Test
    @Order(1)
    void testThatWhenYouCallAddAddressMethod_thenAddressIsAdded() throws Exception {
        address.setFullAddress("1 Ona-Olapo Street Alajomeji");
        address.setCity("Yaba");
        address.setLandmark("Osanyin Street");
        address.setState("Lagos");
        address.setCountry("Nigeria");

        this.mockMvc.perform(post("/api/address/addAddress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(address))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.city", is("Yaba")))
                .andReturn();
    }

    @Test
    @Order(2)
    void testThatWhenYouCallGetAddressByIdMethod_thenAddressIsReturned() throws Exception {
        String id = "";
        this.mockMvc.perform(get("/api/address/addressesId?id=" + id)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.state", is("Lagos")))
                .andReturn();
    }

    @Test
    @Order(3)
    void testThatWhenYouCallGetAllAddressesMethod_thenAddressesAreReturned() throws Exception {
        this.mockMvc.perform(get("/api/address/addresses?pageNumber=0&pageSize=9")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[1].landmark", is("")))
                .andReturn();
    }

    @Test
    @Order(4)
    void updateAddress() throws Exception {
        String id = "";
        address = addressService.fetchAddressById(id);
        address.setFullAddress("");
        address.setCity("");
        address.setLandmark("");
        address.setState("");
        address.setCountry("");
        addressService.editAddress(address, id);

        this.mockMvc.perform(put("/api/address/editAddress?id=" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .content(objectMapper.writeValueAsString(address))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(address.getId()))
                .andExpect(jsonPath("$.fullAddress", is("")))
                .andReturn();
    }

    @Test
    @Order(5)
    void testThatWhenYouCallDeleteAddressMethod_thenAddressIsDeleted() throws Exception {
        this.mockMvc.perform(delete("/api/address")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}