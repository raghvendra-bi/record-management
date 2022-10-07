package com.bi.recordmangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
public class RecordmangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordmangementApplication.class, args);
	}
	
//	public Module hibernate5Module() {
//        return new Hibernate5Module();
//    }
    
    @RequestMapping(value = "/api/health")
    public ResponseEntity<String> health(@RequestParam String token) {
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.name());
    }

}
