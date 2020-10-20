package com.felipe.quasar.fire.apirest.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.quasar.fire.apirest.models.Position;
import com.felipe.quasar.fire.apirest.models.Satellite;
import com.felipe.quasar.fire.apirest.models.TopSecretRequest;
import com.felipe.quasar.fire.apirest.models.TopSecretSplitRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TopSecretControllerIntegrationTest {

	 private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

  
    @Test
    public void topSecretOk() throws Exception {
    
    	TopSecretRequest topSecretRequest= new TopSecretRequest();
    	
    	String[] message1 = {"este", "", "", "mensaje", ""};
        Satellite satellite1 = new Satellite();
    	satellite1.setName("kenobi");
    	satellite1.setDistance(100.0);
    	satellite1.setMessage(message1);
    	
    	String[] message2 = {"", "es", "", "", "secreto"};
        Satellite satellite2 = new Satellite();
    	satellite2.setName("skywalker");
    	satellite2.setDistance(115.5);
    	satellite2.setMessage(message2);
    	
    	String[] message3 = {"este", "", "un", "", ""};
        Satellite satellite3 = new Satellite();
    	satellite3.setName("sato");
    	satellite3.setDistance(142.7);
    	satellite3.setMessage(message3);
    	
    	Satellite[] satellites = {satellite1,satellite2,satellite3};
    	
    	topSecretRequest.setSatellites(satellites);
    	
        mockMvc.perform(post("/topsecret/")
        		.content(om.writeValueAsString(topSecretRequest))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x", is(-487.2859125)))
                .andExpect(jsonPath("$.position.y", is(1557.014225)))
                .andExpect(jsonPath("$.message", is("este es un mensaje secreto")));
    }
  
    @Test
    public void topSecretBadRequest() throws Exception {
    
    	TopSecretRequest topSecretRequest= new TopSecretRequest();
    	
    	String[] message1 = {"este", "", "", "mensaje", ""};
        Satellite satellite1 = new Satellite();
    	satellite1.setName("kenobi");
    	satellite1.setDistance(100.0);
    	satellite1.setMessage(message1);
    	
    	String[] message2 = {"", "es", "", "", "secreto"};
        Satellite satellite2 = new Satellite();
    	satellite2.setName("skywalker");
    	satellite2.setDistance(115.5);
    	satellite2.setMessage(message2);
    	
    	Satellite[] satellites = {satellite1,satellite2};
    	
    	topSecretRequest.setSatellites(satellites);
    	
        mockMvc.perform(post("/topsecret/")
        		.content(om.writeValueAsString(topSecretRequest))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void topSecretNotFound() throws Exception {
    
    	TopSecretRequest topSecretRequest= new TopSecretRequest();
    	
    	String[] message1 = {"este", "", "", "mensaje", ""};
        Satellite satellite1 = new Satellite();
    	satellite1.setName("kenobit");
    	satellite1.setDistance(100.0);
    	satellite1.setMessage(message1);
    	
    	String[] message2 = {"", "es", "", "", "secreto"};
        Satellite satellite2 = new Satellite();
    	satellite2.setName("skywalker");
    	satellite2.setDistance(115.5);
    	satellite2.setMessage(message2);
    	
    	String[] message3 = {"este", "", "un", "", ""};
        Satellite satellite3 = new Satellite();
    	satellite3.setName("sato");
    	satellite3.setDistance(142.7);
    	satellite3.setMessage(message3);
    	
    	Satellite[] satellites = {satellite1,satellite2,satellite3};
    	
    	topSecretRequest.setSatellites(satellites);
    	
        mockMvc.perform(post("/topsecret/")
        		.content(om.writeValueAsString(topSecretRequest))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void topSecretSplitBadRequest() throws Exception {
    	
        mockMvc.perform(post("/topsecret_split/sato")
        		.content(om.writeValueAsString("{{}"))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
                
    }

    @Test
    public void topSecretSplitOK() throws Exception {
    
    	TopSecretSplitRequest topSecretSplitRequest= new TopSecretSplitRequest();
    	String[] message1 = {"este", "", "", "mensaje", ""};
    	topSecretSplitRequest.setMessage(message1);
    	topSecretSplitRequest.setDistance(100.0);
    	
        mockMvc.perform(post("/topsecret_split/kenobi")
        		.content(om.writeValueAsString(topSecretSplitRequest))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("no hay suficiente información"));
        
        TopSecretSplitRequest topSecretSplitRequest2= new TopSecretSplitRequest();
    	String[] message2 = {"", "es", "", "", "secreto"};
    	topSecretSplitRequest2.setMessage(message2);
    	topSecretSplitRequest2.setDistance(115.5);
    	
        mockMvc.perform(post("/topsecret_split/skywalker")
        		.content(om.writeValueAsString(topSecretSplitRequest2))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())  
                .andExpect(content().string("no hay suficiente información"));
        
        TopSecretSplitRequest topSecretSplitRequest3= new TopSecretSplitRequest();
    	String[] message3 = {"este", "", "un", "", ""};
    	topSecretSplitRequest3.setMessage(message3);
    	topSecretSplitRequest3.setDistance(142.7);
    	
        mockMvc.perform(post("/topsecret_split/sato")
        		.content(om.writeValueAsString(topSecretSplitRequest3))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x", is(-487.2859125)))
                .andExpect(jsonPath("$.position.y", is(1557.014225)))
                .andExpect(jsonPath("$.message", is("este es un mensaje secreto")));
     
    }

}