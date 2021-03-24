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
    public void unitTestGetAllSuperheroes(){        
        List<Superheroe> test = service.obtenerTodosSuperheroes();  
        assertNotNull(test);   
    }  
    
    @Test
    public void unitTestGetSuperheroesById() throws  ResourceNotFoundException {

        Superheroe heroe = new Superheroe("Wonder Woman", 40, 2L);        
        Superheroe test = service.obtenerSuperheroePorId(2L);
        assertThat(heroe, equalTo(test));        

        test = service.obtenerSuperheroePorId(5L);
        assertNotEquals(heroe, test);
        
        try {
            test = service.obtenerSuperheroePorId(9L);
            System.out.println(test);
        } catch (final ResourceNotFoundException e) {
            System.out.println(e.getMessage());             
         }  
    }
    
    @Test
    public void unitTestGetSuperheroeByCadenaInName() throws ResourceNotFoundException{              
        List<Superheroe> test = service.obtenerSuperheroesPorCadenaEnNombre("man");     
        assertThat(test.size(), is(5)); 
        try{
            test = service.obtenerSuperheroesPorCadenaEnNombre("eo");      
        }catch(ResourceNotFoundException e){
            assertNotNull(e.getMessage());
        }     
    }  
    
    @Test
    public void unitTestUpdateSuperheroes() throws ResourceNotFoundException {
        Long id = 1L;
        Superheroe updateHeroe = new Superheroe("Super Cris", 1000, 2L);
        Superheroe testing = new Superheroe("Super Cris", 1000, id);
        Superheroe heroe = service.actualizarSuperheroe(id,updateHeroe);
        System.out.println(heroe.getId());
        System.out.println(heroe.getNombre());
        System.out.println(heroe.getFuerza());
        assertThat(heroe, equalTo(testing));  
    }
    
    @Test
    public void unitTestDeleteSuperheroes() throws ResourceNotFoundException {
        Long id = 4L;
        Map<String, Boolean> respuesta = service.eliminarSuperheroe(id);
        assertThat(respuesta.containsValue(true),is(true)); 
    }
}
