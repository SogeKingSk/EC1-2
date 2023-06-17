package com.example.accessingdatamysql;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.PathVariable;

@Controller // This means that this class is a Controller
@RequestMapping(path="/mascotas") // This means URL's start with /demo (after Application path)
public class MainController {
    
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private MascotaRepository mascotaRepository;

  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private JdbcTemplate jdbcTemplate;
  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewMascota (@RequestParam String nombre
      , @RequestParam String raza, @RequestParam String propietario) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Mascota n = new Mascota();
    n.setNombre(nombre);
    n.setRaza(raza);
    n.setPropietario(propietario);
    mascotaRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Mascota> getAllMascotas() {
    // This returns a JSON or XML with the users
    return mascotaRepository.findAll();
  }

  @PutMapping(path="/edit") // Map ONLY POST Requests
  public @ResponseBody String editMascota (@RequestParam Integer id, @RequestParam String nombre
      , @RequestParam String raza, @RequestParam String propietario) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Mascota n = new Mascota();
    n.setId(id);
    n.setNombre(nombre);
    n.setRaza(raza);
    n.setPropietario(propietario);
    mascotaRepository.save(n);
    return "Edited";
  }

  @GetMapping(path="/ver/{id}")
  public @ResponseBody Mascota getMascota(@PathVariable Integer id){
    return mascotaRepository.findById(id).get();
  }

  @DeleteMapping(path="/del") // Map ONLY POST Requests
  public @ResponseBody String editMascota (@RequestParam Integer id) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Mascota n = new Mascota();
    n.setId(id);
    mascotaRepository.delete(n);
    return "Delete";
  }

  @GetMapping(path="/get/report")
  public @ResponseBody List<Map<String, Object>> getReport(){
    String sql = "SELECT CONCAT (nombre, '(nombre) ==> (raza))', raza) as MIS_MASCOTAS FROM mascota";
    List<Map<String, Object>> mascotas = jdbcTemplate.queryForList(sql);
    return mascotas;
  }
}
