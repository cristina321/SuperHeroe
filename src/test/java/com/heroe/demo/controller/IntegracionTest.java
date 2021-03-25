package com.heroe.demo.controller;

import com.heroe.demo.DemoApplication;
import com.heroe.demo.model.Superheroe;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author w45776304
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegracionTest {
    
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
     public void contextLoads() {

     }

     @Test
     public void testGetAllSuperhero() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/superheroes",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }
     
    @Test
    public void testGetSuperheroById() {
        int id = 1;
        Superheroe superheroe = restTemplate.getForObject(getRootUrl() + "/api/superheroes/id/"+id,Superheroe.class);
        System.out.println(superheroe.getNombre());
        assertNotNull(superheroe);
    }
    
    @Test
    public void testGetSuperheroByName() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/superheroes/name/"+"man",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }
    
    @Test
    public void testUpdateSuperhero() {
        int id = 1;
        Superheroe heroe = restTemplate.getForObject(getRootUrl() + "/api/superheroes/id/" + id, Superheroe.class);       
        heroe.setNombre("Super Cris");
        heroe.setFuerza(1000);
        restTemplate.put(getRootUrl() + "/api/superheroes/id/" + id, heroe);
        Superheroe updatedHeroe = restTemplate.getForObject(getRootUrl() + "/api/superheroes/id/" + id, Superheroe.class);
        assertNotNull(updatedHeroe);
    }
    
    @Test
    public void testDeleteSuperhero() {
         int id = 2;
         Superheroe heroe = restTemplate.getForObject(getRootUrl() + "/api/superheroes/id/" + id, Superheroe.class);
         assertNotNull(heroe);
         restTemplate.delete(getRootUrl() + "/api/superheroes/id/" + id);
         try {
              heroe = restTemplate.getForObject(getRootUrl() + "/api/superheroes/id/" + id, Superheroe.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
 
}
