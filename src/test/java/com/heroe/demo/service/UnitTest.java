package com.heroe.demo.service;

import com.heroe.demo.service.SuperheroeService;
import com.heroe.demo.cache.ResourceNotFoundException;
import com.heroe.demo.model.Superheroe;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 *
 * @author w45776304
 */
@SpringBootTest
public class UnitTest {
    
    @Autowired
    private SuperheroeService service;

    @Test
    public void unitTestGetAllSuperhero(){        
        List<Superheroe> test = service.getAll();  
        assertNotNull(test);   
    }  
    
    @Test
    public void unitTestGetSuperheroById() throws  ResourceNotFoundException {

        Superheroe heroe = new Superheroe("Wonder Woman", 40, 2L);        
        Superheroe test = service.getById(2L);
        assertThat(heroe, equalTo(test));        

        test = service.getById(5L);
        assertNotEquals(heroe, test);
        
        try {
            test = service.getById(9L);
            System.out.println(test);
        } catch (final ResourceNotFoundException e) {
            System.out.println(e.getMessage());             
         }  
    }
    
    @Test
    public void unitTestGetSuperheroByName() throws ResourceNotFoundException{              
        List<Superheroe> test = service.getByName("man");     
        assertThat(test.size(), is(5)); 
        try{
            test = service.getByName("eo");      
        }catch(ResourceNotFoundException e){
            assertNotNull(e.getMessage());
        }     
    }  
    
    @Test
    public void unitTestUpdateSuperhero() throws ResourceNotFoundException {
        Long id = 1L;
        Superheroe updateHeroe = new Superheroe("Super Cris", 1000, 2L);
        Superheroe testing = new Superheroe("Super Cris", 1000, id);
        Superheroe heroe = service.updateSuperhero(id,updateHeroe);
        System.out.println(heroe.getId());
        System.out.println(heroe.getNombre());
        System.out.println(heroe.getFuerza());
        assertThat(heroe, equalTo(testing));  
    }
    
    @Test
    public void unitTestDeleteSuperhero() throws ResourceNotFoundException {
        Long id = 5L;
        Map<String, Boolean> respuesta = service.deleteSuperheroe(id);
        assertThat(respuesta.containsValue(true),is(true)); 
    }
}
