package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

//import com.example.accessingdatamysql.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Integer>{
    
}
