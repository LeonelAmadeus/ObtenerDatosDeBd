//PASO 1: ESTABLECER DEPENDENCIAS
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
POM.XML : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/*
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>init.product</groupId>
	<artifactId>product-api3</artifactId>
	<version>1</version>
	<name>product-api3</name>
	<description>Mi tercer api</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		
		<!-- DEPENDENCIAS SPRING BOOT -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		
		<!-- DEPENDENCIAS POSTGRESQL -->
		<dependency>
		    <groupId>org.postgresql</groupId>
		    <artifactId>postgresql</artifactId>
		    <scope>runtime</scope>
		</dependency>
		
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
*/
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
POM.XML : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//PASO 2: ESTABLECER COMUNICACION CON BASE DE DATOS
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
APPLICATION.PROPERTIES : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

server:
  port: 9876
	  
spring:
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/empleado?zeroDataTimeBehavior=convertToNull&serverTimezone=UTC
    username: postgres
    password: 123456

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
APPLICATION.PROPERTIES : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//PASO 3: ESTABLECER ESTRUCTURA DE PAQUETES Y CLASES
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
ESTRUCTURA DE PAQUETES : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

❖ COM.INIT.EMPLEADO
   © ProductApi3Application.java
   
❖ COM.INIT.EMPLEADO.CORS
   © CorsConfiguration.java
   
❖ COM.INIT.EMPLEADO.DAO
   © EmpleadoDAO.java

❖ COM.INIT.EMPLEADO.ENTITY
   © Empleado.java

❖ COM.INIT.EMPLEADO.REST
   © EmpleadoREST.java

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
ESTRUCTURA DE PAQUETES : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//PASO 4: CLASE PRINCIPAL SPRING BOOT QUE EJECUTA EL SERVIDOR TOMCAT
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO
   © ProductApi3Application.java   : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

package com.init.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.init.empleado.cors.CorsConfiguration;

@SpringBootApplication
@Import(CorsConfiguration.class)
public class ProductApi3Application {

	public static void main(String[] args) {
		SpringApplication.run(ProductApi3Application.class, args);
	}
}

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO
   © ProductApi3Application.java   : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//PASO 5:ESTO ES PARA QUE LOS DATOS DEL API REST SE PUEDAN VER EN UN HTML
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.CORS
   © CorsConfiguration.java        : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

package com.init.empleado.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
  }
}

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.CORS
   © CorsConfiguration.java        : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//PASO 6: SE CREA EL DAO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.DAO
   © EmpleadoDAO.java                  : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

package com.init.empleado.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.init.empleado.entitys.Empleado;

public interface EmpleadosDAO extends JpaRepository<Empleado, Long> {

	List<Empleado> findByNombre(String nombre);

	List<Empleado> findByPuesto(String puesto);
	
	List<Empleado> findByDepartamento(String Departamento);
}

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.DAO
   © EmpleadoDAO.java                  : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//PASO 7:SE CREA LA ENTIDAD EMPLEADO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.ENTITY
   © Empleado.java                : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

package com.init.empleado.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import java.util.Date;
import jakarta.persistence.Column;

@Entity
@Table(name="empleado")
public class Empleado {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre", nullable=false, length=40)
	private String nombre;
	
	@Column(name="departamento", nullable=false, length=40)
	private String departamento;
	
	@Column(name="puesto", nullable=false, length=40)
	private String puesto;
	
	@Column(name="correo", nullable=false, length=40)
	private String correo;
	
	@Column(name="telefono", nullable=false, length=40)
	private String telefono;
	
	@Column(name="extension", nullable=false, length=40)
	private String extension;
	
	@Column(name="sueldo", nullable=false, length=40)
	private Float sueldo;
	
	@Column(name="fecha_ingreso", nullable=false, length=40)
	private Date fecha_ingreso;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Float getSueldo() {
		return sueldo;
	}

	public void setSueldo(Float sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
		
}

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.ENTITY
   © Empleado.java                : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=






//PASO 8: CREO LOS METODOS GET, POST, PUT, DELETE DEL API REST
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.REST
   © EmpleadoREST.java                  : INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

package com.init.empleado.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.init.empleado.dao.EmpleadosDAO;
import com.init.empleado.entitys.Empleado;

@RestController
@RequestMapping("/api")   //localhost:9876/api
public class EmpleadoREST {
	
	@Autowired
	private EmpleadosDAO empleadoDAO;

	        //LISTAR
	
	        @GetMapping(value="/empleados/listar") //localhost:9876/api/empleados/listar
		@Transactional
		public ResponseEntity<List<Empleado>> getEmpleado() {
		    try {
		        List<Empleado> listarEmpleados = empleadoDAO.findAll();
		        if(!listarEmpleados.isEmpty()) {
		        	return ResponseEntity.ok(listarEmpleados);
		        }else {
		        	return ResponseEntity.noContent().build();
		        }
		    
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
		
	    
		//BUSCAR POR: ID
		
		@GetMapping(value="/empleado/{empleadoId}") //localhost:9876/api/empleado/4
		@Transactional
		public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("empleadoId") Long empleadoId) {
		    try {
		        Optional<Empleado> empleadoPorId = empleadoDAO.findById(empleadoId);
		        if (empleadoPorId.isPresent()) {
		            return ResponseEntity.ok(empleadoPorId.get());
		        } else {
		            return ResponseEntity.noContent().build();
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
		
	
		//BUSCAR POR: NOMBRE
		
		@GetMapping(value="/empleados/nombre/{nombre}")    	//localhost:9876/api/empleados/nombre/Juan Perez
		@Transactional
		public ResponseEntity<List<Empleado>> getEmpleadoByNombre(@PathVariable String nombre) {
			try {
				List<Empleado> empleadosPorNombre = empleadoDAO.findByNombre(nombre);
				if (!empleadosPorNombre.isEmpty()) {
				    return ResponseEntity.ok(empleadosPorNombre);
				} else {
				    return ResponseEntity.noContent().build();
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		
		//BUSCAR POR: PUESTO 
		
		@GetMapping(value="/empleados/puesto/{puesto}") //localhost:9876/api/empleados/puesto/Asistente
		@Transactional
		public ResponseEntity<List<Empleado>> getEmpleadoByPuesto(@PathVariable String puesto) {
		    try {
		        List<Empleado> empleadosPorPuesto = empleadoDAO.findByPuesto(puesto);
		        if (!empleadosPorPuesto.isEmpty()) {
		            return ResponseEntity.ok(empleadosPorPuesto);
		        } else {
		            return ResponseEntity.noContent().build();
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
		
		
		//BUSCAR POR: DEPARTAMENTO
		
		@GetMapping(value="/empleados/departamento/{departamento}") //localhost:9876/api/empleados/departamento/Ventas
		@Transactional
		public ResponseEntity<List<Empleado>> getEmpleadoByDepartamento(@PathVariable String departamento) {
		    try {
		        List<Empleado> empleadosPorDepartamento = empleadoDAO.findByDepartamento(departamento);
		        if (!empleadosPorDepartamento.isEmpty()) {
		            return ResponseEntity.ok(empleadosPorDepartamento);
		        } else {
		            return ResponseEntity.noContent().build();
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
		
		
		
		//INSERTAR
		
		@PostMapping(value="/empleado/insertar")   //localhost:9876/api/empleado/insertar
		@Transactional
		public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
		    try {
		    	
		    	if (//Si las siguientes propiedades no son nulas, continua con la validacion
		    	    empleado.getId() != null && 
		    	    empleado.getNombre() != null && 
		    	    empleado.getDepartamento() != null && 
		    	    empleado.getPuesto() != null && 
		    	    empleado.getCorreo() != null && 
		    	    empleado.getTelefono() != null && 
		    	    empleado.getExtension() != null && 
		    	    empleado.getSueldo() != null && 
		    	    empleado.getFecha_ingreso() != null &&
		    		
		    	    //Si las siguientes propiedades no estan vacias, continua con la validacion
		    	    !empleado.getNombre().isEmpty() &&
		    	    !empleado.getDepartamento().isEmpty() &&
		    	    !empleado.getPuesto().isEmpty() &&
		    	    !empleado.getCorreo().isEmpty() &&
		    	    !empleado.getTelefono().isEmpty() &&
		    	    !empleado.getExtension().isEmpty() &&
		    		
		    	    //Si las siguientes propiedades no sobrepasan el largo de la cadena de texto a 40 espacios, continua con la validacion
		    	    empleado.getNombre().length() <= 40 &&
		    	    empleado.getDepartamento().length() <= 40 &&
		    	    empleado.getPuesto().length() <= 40 &&
		    	    empleado.getCorreo().length() <= 40 &&
		    	    empleado.getTelefono().length() <= 40 &&
		    	    empleado.getExtension().length() <= 40 &&
		    	    // La propiedad "sueldo" es de tipo Float y su valor convertido a String no excede 10 caracteres, continuar con la validación
			    Float.toString(empleado.getSueldo()).length() <= 10 &&
		    		
		    	    //La propiedad "ID" es de tipo Long, continuar con la validación
		    	    empleado.getId().getClass() == Long.class &&
		    	    //Si las siguientes propiedades son de tipo String, continua con la validacion
		    	    empleado.getNombre() instanceof String &&
		    	    empleado.getDepartamento() instanceof String &&
		    	    empleado.getPuesto() instanceof String &&
		    	    empleado.getCorreo() instanceof String &&
		    	    empleado.getTelefono() instanceof String &&
		    	    empleado.getExtension() instanceof String &&
		    	    // La propiedad "sueldo" es de tipo Float, continuar con la validación
		    	    empleado.getSueldo().getClass() == Float.class &&
		    	    // La propiedad "fecha_ingreso" es de tipo Date, continuar con la validación
		    	    empleado.getFecha_ingreso() instanceof java.util.Date &&
		    		
		    	    /* La propiedad "correo" del objeto "empleado" contiene @gmail.com ó @hotmail.com ó @yahoo.com.mx ó 
		    	    @outlook.es ó @empresa.com, continuar con la validacion*/
		    	    empleado.getCorreo().matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com\\.mx|outlook\\.es|empresa\\.com)") &&
		    	    /*Las siguientes propiedades no contienen los siguientes simbolos: !, ", #, $, %, &, /, (, ), 
		    	    =, ?, ¡, ¿, *, -, +, ++, --, {, }, [, ], <, >, continua con el procedimiento */
		    	    empleado.getNombre().matches("^[a-zA-Z\\.]+[^!\"#$%&/()=?¡¿*-+{}\\[\\]<>]*$")) {
	                
				
	                Empleado newEmpleado = empleadoDAO.save(empleado);
	                return ResponseEntity.ok(newEmpleado);
	                
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	            }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}

		
		
		//ELIMINAR 
		
		@DeleteMapping(value="/empleado/eliminar/{empleadoId}") //localhost:9876/api/empleado/eliminar/6
		@Transactional
		public ResponseEntity<Void> deleteEmpleado(@PathVariable("empleadoId") Long empleadoId) {
		    try {
		        Optional<Empleado> empleadoid = empleadoDAO.findById(empleadoId);
	    		if (empleadoid.isPresent()) {
	    			empleadoDAO.deleteById(empleadoId);
	    			return ResponseEntity.ok(null);
			} else {
	    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			    
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
		
		
		
		//ACTUALIZAR
		
		@Transactional
		@PutMapping(value="/empleado/actualizar")   //localhost:9876/api/empleado/actualizar
		public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado) {
		    try {
		        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(empleado.getId());
		        if (optionalEmpleado.isPresent()) {
		        	
		            Empleado updateEmpleado = optionalEmpleado.get();
		            
		            if (//Si las siguientes propiedades no son nulas, continua con la validacion
				empleado.getId() != null && 
				empleado.getNombre() != null && 
				empleado.getDepartamento() != null && 
				empleado.getPuesto() != null && 
				empleado.getCorreo() != null && 
				empleado.getTelefono() != null && 
				empleado.getExtension() != null && 
				empleado.getSueldo() != null && 
				empleado.getFecha_ingreso() != null &&
				    		
				//Si las siguientes propiedades no estan vacias, continua con la validacion
				!empleado.getNombre().isEmpty() &&
				!empleado.getDepartamento().isEmpty() &&
				!empleado.getPuesto().isEmpty() &&
				!empleado.getCorreo().isEmpty() &&
				!empleado.getTelefono().isEmpty() &&
				!empleado.getExtension().isEmpty() &&
				    		
				/*Si las siguientes propiedades no sobrepasan el largo de la cadena de texto a 40 
				espacios, continua con la validacion*/
				empleado.getNombre().length() <= 40 &&
				empleado.getDepartamento().length() <= 40 &&
				empleado.getPuesto().length() <= 40 &&
				empleado.getCorreo().length() <= 40 &&
				empleado.getTelefono().length() <= 40 &&
				empleado.getExtension().length() <= 40 &&
				/* La propiedad "sueldo" es de tipo Float y su valor convertido a String no excede 
				10 caracteres, continuar con la validación*/
				Float.toString(empleado.getSueldo()).length() <= 10 &&
				  		
				// La propiedad "ID" es de tipo Long, continuar con la validación
				empleado.getId().getClass() == Long.class &&
				//Si las siguientes propiedades son de tipo String, continua con la validacion
				empleado.getNombre() instanceof String &&
				empleado.getDepartamento() instanceof String &&
				empleado.getPuesto() instanceof String &&
				empleado.getCorreo() instanceof String &&
				empleado.getTelefono() instanceof String &&
				empleado.getExtension() instanceof String &&
				// La propiedad "sueldo" es de tipo Float, continuar con la validación
				empleado.getSueldo().getClass() == Float.class &&
				// La propiedad "fecha_ingreso" es de tipo Date, continuar con la validación
				empleado.getFecha_ingreso() instanceof java.util.Date &&
				   		
				/* La propiedad "correo" del objeto "empleado" contiene @gmail.com ó @hotmail.com ó @yahoo.com.mx ó 
				@outlook.es ó @empresa.com, continuar con el procesamiento*/
				empleado.getCorreo().matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com\\.mx|outlook\\.es|empresa\\.com)") &&
				/*Las siguientes propiedades no quiero que contenga los siguientes simbolos: !, ", #, $, %, &, /, (, ), 
				=, ?, ¡, ¿, *, -, +, ++, --, {, }, [, ], <, > */
				empleado.getNombre().matches("^[a-zA-Z\\.]+[^!\"#$%&/()=?¡¿*-+{}\\[\\]<>]*$")) {
		            	
		            	
		                updateEmpleado.setNombre(empleado.getNombre());
		                updateEmpleado.setPuesto(empleado.getPuesto());
		                updateEmpleado.setDepartamento(empleado.getDepartamento());
		                updateEmpleado.setCorreo(empleado.getCorreo());
		                updateEmpleado.setTelefono(empleado.getTelefono());
		                updateEmpleado.setExtension(empleado.getExtension());
		                updateEmpleado.setFecha_ingreso(empleado.getFecha_ingreso());
		                
		                empleadoDAO.save(updateEmpleado);
		                return ResponseEntity.ok(updateEmpleado);
		                
		            } else {
		                return ResponseEntity.badRequest().build();
		            }
		        } else {
		            return ResponseEntity.notFound().build();
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
}

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
❖ COM.INIT.EMPLEADO.REST
   © EmpleadoREST.java                  : FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=




//PASO INTERMENDIO ANTES DE PRUEBAS
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
CREANDO TABLA Y LLENENDO INFORMACION EN BSE DE DATOS empleado: INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


/*
ANTES DE EJECUTAR LA APLICACION:
1-. IR A POSTGRESQL (PGADMIN4)
    -. Crear una base de datos: CREATE DATABASE empleado;
    
    Nota: Debe ser igual como viene en esta liga dentro del archivo properties.yaml
    
    	url: jdbc:postgresql://localhost:5432/empleado?zeroDataTimeBehavior=convertToNull&serverTimezone=UTC
	                                      ________
					      
					      
2-. EJECUTAR APLICACION.
    -. Regresar a PGADMIN4.
    -. Ejecutar script: SELECT * FROM empleado;
    
    Nota: Debe aparecer los mismos campos que establecimos en la entidad, dentro del paquete: 
    
    	com.init.empleado.entity > Empleado.java
3-. PARA PROBAR NECESITAMOS HACER VARIOS INSERTS A NUESTRA TABLA empleado.
	INSERT INTO empleado (nombre, departamento, puesto, correo, telefono, extension, sueldo, fecha_ingreso) VALUES
	('Juan Perez', 'Ventas', 'Gerente', 'juan.perez@empresa.com', '555-1234', '1234', 3000.00, '2020-01-01'),
	('Maria Garcia', 'Ventas', 'Vendedor', 'maria.garcia@empresa.com', '555-2345', '2345', 1500.00, '2020-01-02'),
	('Pedro Sanchez', 'Ventas', 'Vendedor', 'pedro.sanchez@empresa.com', '555-3456', '3456', 1500.00, '2020-01-02'),
	('Ana Rodriguez', 'Ventas', 'Asistente', 'ana.rodriguez@empresa.com', '555-4567', '4567', 1000.00, '2020-02-04'),
	('Sara Torres', 'Ventas', 'Asistente', 'sara.torres@empresa.com', '555-5678', '5678', 1000.00, '2020-01-07'),
	('Luis Jimenez', 'Compras', 'Gerente', 'luis.jimenez@empresa.com', '555-6789', '6789', 3000.00, '2020-02-03'),
	('Julia Gomez', 'Compras', 'Comprador', 'julia.gomez@empresa.com', '555-7890', '7890', 2000.00, '2020-01-08'),
	('Miguel Ramirez', 'Compras', 'Comprador', 'miguel.ramirez@empresa.com', '555-8901', '8901', 2000.00, '2020-07-05'),
	('Laura Hernandez', 'Compras', 'Asistente', 'laura.hernandez@empresa.com', '555-9012', '9012', 1000.00, '2020-01-01'),
	('Carla Castro', 'Compras', 'Asistente', 'carla.castro@empresa.com', '555-0123', '0123', 1000.00, '2020-01-01'),
	('Diego Lopez', 'Finanzas', 'Gerente', 'diego.lopez@empresa.com', '555-1111', '1111', 3500.00, '2020-01-01'),
	('Lucia Fernandez', 'Finanzas', 'Contador', 'lucia.fernandez@empresa.com', '555-2222', '2222', 2500.00, '2020-01-01'),
	('Roberto Rodriguez', 'Finanzas', 'Contador', 'roberto.rodriguez@empresa.com', '555-3333', '3333', 2500.00, '2020-01-01'),
	('Juan Perez', 'Ventas', 'Vendedor', 'juan.perez@empresa.com', '555-1234', '1234', 1500.00, '2021-01-01'),
	('Maria Rodriguez', 'Ventas', 'Supervisor', 'maria.rodriguez@empresa.com', '555-5678', '5678', 3000.00, '2020-06-01'),
	('Pedro Garcia', 'Ventas', 'Gerente', 'pedro.garcia@empresa.com', '555-9876', '9876', 5000.00, '2019-01-01'),
	('Luisa Martinez', 'Finanzas', 'Contador', 'luisa.martinez@empresa.com', '555-1111', '1111', 2000.00, '2020-09-01'),
	('Jose Hernandez', 'Finanzas', 'Analista', 'jose.hernandez@empresa.com', '555-2222', '2222', 2500.00, '2021-03-01'),
	('Ana Lopez', 'Finanzas', 'Gerente', 'ana.lopez@empresa.com', '555-3333', '3333', 5000.00, '2018-01-01'),
	('Mario Jimenez', 'Tecnologia', 'Desarrollador', 'mario.jimenez@empresa.com', '555-4444', '4444', 3000.00, '2020-06-01'),
	('Sofia Garcia', 'Tecnologia', 'Analista', 'sofia.garcia@empresa.com', '555-5555', '5555', 2500.00, '2019-03-01'),
	('Carlos Rodriguez', 'Tecnologia', 'Gerente', 'carlos.rodriguez@empresa.com', '555-6666', '6666', 5000.00, '2017-01-01'),
	('Fernanda Castro', 'Recursos Humanos', 'Analista', 'fernanda.castro@empresa.com', '555-7777', '7777', 2500.00, '2021-09-01'),
	('Javier Diaz', 'Recursos Humanos', 'Gerente', 'javier.diaz@empresa.com', '555-8888', '8888', 5000.00, '2016-01-01'),
	('Alejandra Chavez', 'Recursos Humanos', 'Asistente', 'alejandra.chavez@empresa.com', '555-9999', '9999', 2000.00, '2022-01-01'),
	('Jane Doe', 'Finanzas', 'Contador', 'janedoec@example.com', '555-5678', '456', 2500.00, '2022-02-01'),
	('Bob Smith', 'Marketing', 'Gerente de Marketing', 'bobsmith@example.com', '555-9876', '789', 3500.00, '2022-03-01'),
	('Alice Brown', 'Recursos Humanos', 'Gerente de Recursos Humanos', 'alicebrown@example.com', '555-4321', '321', 4000.00, '2022-04-01'),
	('Tom Jones', 'Ventas', 'Gerente de Ventas', 'tomjones@example.com', '555-5555', '555', 4500.00, '2022-05-01'),
	('Sara Johnson', 'Tecnología de la Información', 'Desarrollador', 'sarajohnson@example.com', '555-1111', '111', 3000.00, '2022-06-01'),
	('Michael Davis', 'Tecnología de la Información', 'Desarrollador', 'michaeldavis@example.com', '555-2222', '222', 3000.00, '2022-07-01'),
	('Emily Rodriguez', 'Finanzas', 'Contador', 'emilyrodriguez@example.com', '555-3333', '333', 2500.00, '2022-08-01'),
	('David Wilson', 'Marketing', 'Especialista en Marketing', 'davidwilson@example.com', '555-4444', '444', 3000.00, '2022-09-01'),
	('Karen Taylor', 'Recursos Humanos', 'Especialista en Recursos Humanos', 'karentaylor@example.com', '555-6666', '666', 3500.00, '2022-10-01'),
	('Adam Brown', 'Tecnología de la Información', 'Desarrollador', 'adambrown@example.com', '555-7777', '777', 3000.00, '2022-11-01'),
	('Laura Garcia', 'Tecnología de la Información', 'Gerente de Tecnología', 'lauragarcia@example.com', '555-8888', '888', 5000.00, '2022-12-01'),
	('Daniel Perez', 'Ventas', 'Vendedor', 'danielperez@example.com', '555-9999', '999', 2000.00, '2023-01-01'),
	('Juan Perez', 'Ventas', 'Vendedor', 'juan.perez@empresa.com', '555-1234', '1234', 1500.00, '2021-01-01'),
	('Maria Rodriguez', 'Ventas', 'Supervisor', 'maria.rodriguez@empresa.com', '555-5678', '5678', 3000.00, '2020-06-01'),
	('Pedro Garcia', 'Ventas', 'Gerente', 'pedro.garcia@empresa.com', '555-9876', '9876', 5000.00, '2019-01-01'),
	('Luisa Martinez', 'Finanzas', 'Contador', 'luisa.martinez@empresa.com', '555-1111', '1111', 2000.00, '2020-09-01'),
	('Jose Hernandez', 'Finanzas', 'Analista', 'jose.hernandez@empresa.com', '555-2222', '2222', 2500.00, '2021-03-01'),
	('Ana Lopez', 'Finanzas', 'Gerente', 'ana.lopez@empresa.com', '555-3333', '3333', 5000.00, '2018-01-01'),
	('Mario Jimenez', 'Tecnologia', 'Desarrollador', 'mario.jimenez@empresa.com', '555-4444', '4444', 3000.00, '2020-06-01'),
	('Sofia Garcia', 'Tecnologia', 'Analista', 'sofia.garcia@empresa.com', '555-5555', '5555', 2500.00, '2019-03-01'),
	('Carlos Rodriguez', 'Tecnologia', 'Gerente', 'carlos.rodriguez@empresa.com', '555-6666', '6666', 5000.00, '2017-01-01'),
	('Fernanda Castro', 'Recursos Humanos', 'Analista', 'fernanda.castro@empresa.com', '555-7777', '7777', 2500.00, '2021-09-01'),
	('Javier Diaz', 'Recursos Humanos', 'Gerente', 'javier.diaz@empresa.com', '555-8888', '8888', 5000.00, '2016-01-01'),
	('Alejandra Chavez', 'Recursos Humanos', 'Asistente', 'alejandra.chavez@empresa.com', '555-9999', '9999', 2000.00, '2022-01-01');
4-. Una vez hecho esto ya podemos jugar con los metodos GET, POST, DELETE Y PUT.
    -.Usaremos Postman.
    
*/
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
CREANDO TABLA Y LLENENDO INFORMACION EN BSE DE DATOS empleado: FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=











//REALIZAMOS PRUEBAS CON POSTMAN
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
PRUEBAS POSTMAN: INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/*Tomando en cuenta que nuestro API REST VA INICIAR DESDE LA URL: /api tal como lo muestra la notacion:
@RequestMapping("/api")   
	
	-. Nuestra url sera: localhost:9876/api
	-. Si nos conectamos al navegador y nos conectamos a esa URL, aparecera el siguiente texto
	   en pantalla.
             Whitelabel Error Page
	     This application has no explicit mapping for /error, so you are seeing this as a fallback.
	     Wed Apr 05 00:14:54 CST 2023
	     There was an unexpected error (type=Not Found, status=404).
	     
	-. Empezamos*/

	
	
	
//= INICIA PRUEBA CON PRIMER MÉTODO: LISTAR TODOS LOS EMPLEADOS ========================================
	
	
/*Con la notación @GetMapping(value="/empleados/listar") le indicamos que este método maneja una petición 
HTTP GET en el endpoint "/empleados/listar".*/
@GetMapping(value="/empleados/listar")

/*Con la notacion @Transactional le indico que es un método transaccional, es decir, se ejecuta dentro 
de una transacción de base de datos. Esto asegura que si algo sale mal durante la ejecución del método, 
se pueda hacer un rollback y deshacer todos los cambios realizados en la base de datos.*/
@Transactional
	
/*Tengo el metodo public ResponseEntity<List<Empleado>> getEmpleado() {} devuelve un objeto ResponseEntity 
que contiene una lista de objetos de tipo "Empleado".*/
public ResponseEntity<List<Empleado>> getEmpleado() {
    //En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {
	/*se llama al método findAll() del objeto empleadoDAO para obtener todos los registros de la 
	tabla de empleados de la base de datos. */
        List<Empleado> listarEmpleados = empleadoDAO.findAll();
	    
	//Si se obtienen registros,
        if(!listarEmpleados.isEmpty()) {
		/*con ResponseEntity.ok, crea un codigo ResponseEntity de estado HTTP 200, ok. Y con 
		(empleados) devuelve la lista de empleados*/
        	return ResponseEntity.ok(listarEmpleados);
        }
	//Por otro lado, si la lista de empleados está vacía,
	else {
		/*con ResponseEntity.noContent() se crea un codigo ResponseEntity de estado HTTP 204 
		(No Content). Con el método build() construimos un objeto con esta respuesta HTTP y 
		la devolvemos sin contenido.*/
        	return ResponseEntity.noContent().build();
        }
 
    } 
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

/*Para Listar, nuestra URL será: localhost:9876/api/empleados/listar
	-. Abrir Postman.
	-. Cambiar metodo a: GET
	-. Proporcionar URL: localhost:9876/api/empleados/listar
	-. Send.
Respuesta:*/

// 20230405012934
// http://localhost:9876/api/empleados/listar

[
  {
    "id": 1,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2020-01-01T06:00:00.000+00:00"
  },
  {
    "id": 2,
    "nombre": "Maria Garcia",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "maria.garcia@empresa.com",
    "telefono": "555-2345",
    "extension": "2345",
    "sueldo": 1500.0,
    "fecha_ingreso": "2020-01-02T06:00:00.000+00:00"
  },
  {
    "id": 3,
    "nombre": "Pedro Sanchez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "pedro.sanchez@empresa.com",
    "telefono": "555-3456",
    "extension": "3456",
    "sueldo": 1500.0,
    "fecha_ingreso": "2020-01-02T06:00:00.000+00:00"
  },
  {
  .
  .
  Representa el resto de la lista. ESTA MUY LARGA.
  . 
  .
  
  }
]
//= FINALIZA PRUEBA CON PRIMER MÉTODO: LISTAR TODOS LOS EMPLEADOS ========================================












//= INICIA PRUEBA CON SEGUNDO MÉTODO: BUSCAR UN EMPLEADO POR SU ID =======================================
		

/*Con la notación @GetMapping(value="/empleado/{empleadoId}") le indicamos que este método maneja una petición 
HTTP GET en el endpoint "/empleado/{empleadoId}".*/
@GetMapping(value="/empleado/{empleadoId}")

/*Con la notacion @Transactional le indico que es un método transaccional, es decir, se ejecuta dentro 
de una transacción de base de datos. Esto asegura que si algo sale mal durante la ejecución del método, 
se pueda hacer un rollback y deshacer todos los cambios realizados en la base de datos.*/
@Transactional


/*public ResponseEntity<Empleado> getEmpleadoById: Este es un método público llamado getEmpleadoById
que devuelve una respuesta HTTP ResponseEntity con un objeto Empleado. @PathVariable("empleadoId") 
Long empleadoId: Esta anotación indica que el parámetro empleadoId en la URL es una variable de ruta 
y su valor se asigna a la variable empleadoId.*/
public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("empleadoId") Long empleadoId) {
    //En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {
	
	/*Optional<Empleado> empleadoPorId = empleadoDAO.findById(empleadoId);: Se usa el método 
	findById del objeto empleadoDAO para obtener el objeto Empleado correspondiente al empleadoId 
	proporcionado. El objeto Optional es utilizado para evitar una excepción de puntero nulo 
	(null pointer exception), es decir, si no se encuentra ningún objeto Empleado con ese ID.*/   
        Optional<Empleado> empleadoPorId = empleadoDAO.findById(empleadoId);
	
	//Si se obtienen registros, si esta presente,
        if (empleadoPorId.isPresent()) {
	    /*con ResponseEntity.ok, crea un codigo ResponseEntity de estado HTTP 200, ok. Y con 
	    (empleadoPorId.get()) devuelve el empleado*/
            return ResponseEntity.ok(empleadoPorId.get());
        }
	//Por otro lado, si la lista de empleados está vacía,
	else {
            /*con ResponseEntity.noContent() se crea un codigo ResponseEntity de estado HTTP 204 
	    (No Content). Con el método build() construimos un objeto con esta respuesta HTTP y 
	    la devolvemos sin contenido.*/
            return ResponseEntity.noContent().build();
        }
	    
    }
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


/*Para Filtrar por id, nuestra URL será:  localhost:9876/api/empleado/#
	-. Abrir Postman.
	-. Cambiar metodo a: GET
	-. Proporcionar URL: localhost:9876/api/empleado/5
	-. Send.
Respuesta:*/

// 20230405013714
// http://localhost:9876/api/empleado/5

{
  "id": 5,
  "nombre": "Sara Torres",
  "departamento": "Ventas",
  "puesto": "Asistente",
  "correo": "sara.torres@empresa.com",
  "telefono": "555-5678",
  "extension": "5678",
  "sueldo": 1000.0,
  "fecha_ingreso": "2020-01-07T06:00:00.000+00:00"
}
//= FINALIZA PRUEBA CON SEGUNDO MÉTODO: BUSCAR UN EMPLEADO POR SU ID =====================================










//= INICIA PRUEBA CON TERCER MÉTODO: BUSCAR UN EMPLEADO POR SU NOMBRE ==================================


/*Para buscar por el campo nombre antes debemos añadir en la clase EmpleadoDAO.java la siguiente linea:
	
	List<Empleado> findByNombre(String nombre);
	
	-.Tal como se muestra acontinuacion*/

	package com.init.empleado.dao;

	import java.util.List;
	import org.springframework.data.jpa.repository.JpaRepository;
	import com.init.empleado.entitys.Empleado;

	public interface EmpleadosDAO extends JpaRepository<Empleado, Long> {
		List<Empleado> findByNombre(String nombre);
	}



/*Despues nos regresamos a la clase EmpleadoREST.java*/
			

/*Con la notación @GetMapping(value="/empleados/nombre/{nombre}") le indicamos que este método maneja una petición 
HTTP GET en el endpoint "/empleados/nombre/{nombre}".*/	
@GetMapping(value="/empleados/nombre/{nombre}")

/*Con la notacion @Transactional le indico que es un método transaccional, es decir, se ejecuta dentro 
de una transacción de base de datos. Esto asegura que si algo sale mal durante la ejecución del método, 
se pueda hacer un rollback y deshacer todos los cambios realizados en la base de datos.*/
@Transactional

/*Este método se encarga de manejar solicitudes GET para obtener una lista de Empleados por su nombre*/
public ResponseEntity<List<Empleado>> getEmpleadoByNombre(@PathVariable String nombre) {
	//En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
	try {
		/*Optional<Empleado> empleadosPorNombre = empleadoDAO.findByNombre(nombre);: Se utiliza 
		el método findByNombre del objeto empleadoDAO para buscar un objeto Empleado en la base 
		de datos con el nombre proporcionado. El objeto Optional se utiliza para evitar una 
		excepción de puntero nulo si no se encuentra ningún objeto Empleado con ese nombre.*/
		List<Empleado> empleadosPorNombre = empleadoDAO.findByNombre(nombre);
		
		//Si se obtienen registros, si esta presente,
		if (!empleadosPorNombre.isEmpty()) {
		    /*con ResponseEntity.ok, crea un codigo ResponseEntity de estado HTTP 200, ok. Y con 
	            (empleadosPorNombre.get()) devuelve el empleado*/
		    return ResponseEntity.ok(empleadosPorNombre);
		}
		//Por otro lado, si la lista de empleados está vacía,
		else {
		    /*con ResponseEntity.noContent() se crea un codigo ResponseEntity de estado HTTP 204 
	    	    (No Content). Con el método build() construimos un objeto con esta respuesta HTTP y 
	            la devolvemos sin contenido.*/	
		    return ResponseEntity.noContent().build();
		}
		
	}
	//Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
	catch (Exception e) {
		/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
		(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
		con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
		la ejecución del método.*/
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}


/*Para por nombre, nuestra URL será:  localhost:9876/api/empleados/nombre/Juan Perez
	-. Abrir Postman.
	-. Cambiar metodo a: GET
	-. Proporcionar URL: localhost:9876/api/empleados/nombre/Juan Perez
	-. Send.
Respuesta:*/

// 20230405235929
// http://localhost:9876/api/empleados/nombre/Juan%20Perez

[
  {
    "id": 1,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2020-01-01T06:00:00.000+00:00"
  },
  {
    "id": 14,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 1500.0,
    "fecha_ingreso": "2021-01-01T06:00:00.000+00:00"
  },
  {
    "id": 38,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 1500.0,
    "fecha_ingreso": "2021-01-01T06:00:00.000+00:00"
  }
]

//= FINALIZA PRUEBA CON TERCER MÉTODO: BUSCAR UN EMPLEADO POR SU NOMBRE ================================











//= INICIA PRUEBA CON CUARTO MÉTODO: BUSCAR UN EMPLEADO POR SU PUESTO =================================


/*Para buscar por el campo nombre antes debemos añadir en la clase EmpleadoDAO.java la siguiente linea:
	
	List<Empleado> findByPuesto(String puesto);
	
	-.Tal como se muestra acontinuacion*/

	package com.init.empleado.dao;
	import java.util.List;
	import org.springframework.data.jpa.repository.JpaRepository;
	import com.init.empleado.entitys.Empleado;

	public interface EmpleadosDAO extends JpaRepository<Empleado, Long> {

		List<Empleado> findByNombre(String nombre);
		List<Empleado> findByPuesto(String puesto);

	}

/*Despues nos regresamos a la clase EmpleadoREST.java*/



/*Con la notación @GetMapping(value="/empleados/puesto/{puesto}") le indicamos que este método maneja una petición 
HTTP GET en el endpoint "/empleados/puesto/{puesto}".*/		
@GetMapping(value="/empleados/puesto/{puesto}")

/*Con la notacion @Transactional le indico que es un método transaccional, es decir, se ejecuta dentro 
de una transacción de base de datos. Esto asegura que si algo sale mal durante la ejecución del método, 
se pueda hacer un rollback y deshacer todos los cambios realizados en la base de datos.*/
@Transactional

/*Este método se encarga de manejar solicitudes GET para obtener una lista de objetos Empleado que 
cumplan con ciertos criterios especificados por el valor del parámetro puesto*/
public ResponseEntity<List<Empleado>> getEmpleadoByPuesto(@PathVariable String puesto) {
    //En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {
	/*findByPuesto(puesto): este es un método definido en la clase empleadoDAO que busca los 
	registros en la base de datos que tienen un cierto valor en la columna "puesto".*/
        List<Empleado> empleadosPorPuesto = empleadoDAO.findByPuesto(puesto);
	    
	//Si se obtienen registros, si existen datos,
        if (!empleadosPorPuesto.isEmpty()) {
	    /*con ResponseEntity.ok, crea un codigo ResponseEntity de estado HTTP 200, ok. Y con 
	    (empleadosPorPuesto) devuelve la lista de empleados que coincidan con el puesto*/
            return ResponseEntity.ok(empleadosPorPuesto);
        }
	//Por otro lado, si la lista de empleados está vacía,
	else {
	    /*con ResponseEntity.noContent() se crea un codigo ResponseEntity de estado HTTP 204 
	    (No Content). Con el método build() construimos un objeto con esta respuesta HTTP y 
	    la devolvemos sin contenido.*/
            return ResponseEntity.noContent().build();
        }
    }
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/    
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


/*Para Filtrar por Puesto, nuestra URL será:  localhost:9876/api/empleados/puesto/Asistente
	-. Abrir Postman.
	-. Cambiar metodo a: GET
	-. Proporcionar URL: localhost:9876/api/empleados/puesto/Asistente
	-. Send.
Respuesta:*/

// 20230405225238
// http://localhost:9876/api/empleados/puesto/Asistente

[
  {
    "id": 4,
    "nombre": "Ana Rodriguez",
    "departamento": "Ventas",
    "puesto": "Asistente",
    "correo": "ana.rodriguez@empresa.com",
    "telefono": "555-4567",
    "extension": "4567",
    "sueldo": 1000.0,
    "fecha_ingreso": "2020-02-04T06:00:00.000+00:00"
  },
  {
    "id": 5,
    "nombre": "Sara Torres",
    "departamento": "Ventas",
    "puesto": "Asistente",
    "correo": "sara.torres@empresa.com",
    "telefono": "555-5678",
    "extension": "5678",
    "sueldo": 1000.0,
    "fecha_ingreso": "2020-01-07T06:00:00.000+00:00"
  },
  {
    "id": 9,
    "nombre": "Laura Hernandez",
    "departamento": "Compras",
    "puesto": "Asistente",
    "correo": "laura.hernandez@empresa.com",
    "telefono": "555-9012",
    "extension": "9012",
    "sueldo": 1000.0,
    "fecha_ingreso": "2020-01-01T06:00:00.000+00:00"
  },
  {
    "id": 10,
    "nombre": "Carla Castro",
    "departamento": "Compras",
    "puesto": "Asistente",
    "correo": "carla.castro@empresa.com",
    "telefono": "555-0123",
    "extension": "0123",
    "sueldo": 1000.0,
    "fecha_ingreso": "2020-01-01T06:00:00.000+00:00"
  },
  {
    "id": 25,
    "nombre": "Alejandra Chavez",
    "departamento": "Recursos Humanos",
    "puesto": "Asistente",
    "correo": "alejandra.chavez@empresa.com",
    "telefono": "555-9999",
    "extension": "9999",
    "sueldo": 2000.0,
    "fecha_ingreso": "2022-01-01T06:00:00.000+00:00"
  },
  {
    "id": 49,
    "nombre": "Alejandra Chavez",
    "departamento": "Recursos Humanos",
    "puesto": "Asistente",
    "correo": "alejandra.chavez@empresa.com",
    "telefono": "555-9999",
    "extension": "9999",
    "sueldo": 2000.0,
    "fecha_ingreso": "2022-01-01T06:00:00.000+00:00"
  }
]

//= Finaliza PRUEBA CON CUARTO MÉTODO: BUSCAR UN EMPLEADO POR SU PUESTO ===============================












//= INICIA PRUEBA CON QUINTO MÉTODO: BUSCAR UN EMPLEADO POR SU DEPARTAMENTO ===========================


/*Para buscar por el campo nombre antes debemos añadir en la clase EmpleadoDAO.java la siguiente linea:
	
	List<Empleado> findByDepartamento(String Departamento);
	
	-.Tal como se muestra acontinuacion*/

	package com.init.empleado.dao;
	import java.util.List;
	import org.springframework.data.jpa.repository.JpaRepository;
	import com.init.empleado.entitys.Empleado;

	public interface EmpleadosDAO extends JpaRepository<Empleado, Long> {

		List<Empleado> findByNombre(String nombre);
		List<Empleado> findByPuesto(String puesto);
		List<Empleado> findByDepartamento(String Departamento);

	}

/*Despues nos regresamos a la clase EmpleadoREST.java*/




/*Con la notación @GetMapping(value="/empleados/departamento/{departamento}") le indicamos que este 
método maneja una petición HTTP GET en el endpoint "/empleados/departamento/{departamento}".*/	
@GetMapping(value="/empleados/departamento/{departamento}")

/*Con la notacion @Transactional le indico que es un método transaccional, es decir, se ejecuta dentro 
de una transacción de base de datos. Esto asegura que si algo sale mal durante la ejecución del método, 
se pueda hacer un rollback y deshacer todos los cambios realizados en la base de datos.*/
@Transactional

/*Este método se encarga de manejar solicitudes GET para obtener una lista de objetos Empleado que 
cumplan con ciertos criterios especificados por el valor del parámetro departamento*/
public ResponseEntity<List<Empleado>> getEmpleadoByDepartamento(@PathVariable String departamento) {
    //En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {
	/*findByDepartamento(puesto): este es un método definido en la clase empleadoDAO que busca los 
	registros en la base de datos que tienen un cierto valor en la columna "departamento".*/
        List<Empleado> empleadosPorDepartamento = empleadoDAO.findByDepartamento(departamento);
	    
	//Si se obtienen registros, si existen datos,
        if (!empleadosPorDepartamento.isEmpty()) {
	    /*con ResponseEntity.ok, crea un codigo ResponseEntity de estado HTTP 200, ok. Y con 
	    (empleadosPorDepartamento) devuelve la lista de empleados que coincidan con el 
	    departamento*/
            return ResponseEntity.ok(empleadosPorDepartamento);
        }
	//Por otro lado, si la lista de empleados está vacía,    
	else {
	    /*con ResponseEntity.noContent() se crea un codigo ResponseEntity de estado HTTP 204 
	    (No Content). Con el método build() construimos un objeto con esta respuesta HTTP y 
	    la devolvemos sin contenido.*/
            return ResponseEntity.noContent().build();
        }
    }
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion	
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


/*Para Filtrar por Departamento, nuestra URL será:  localhost:9876/api/empleados/departamento/Ventas
	-. Abrir Postman.
	-. Cambiar metodo a: GET
	-. Proporcionar URL: localhost:9876/api/empleados/departamento/Ventas
	-. Send.
Respuesta:*/

// 20230406002007
// http://localhost:9876/api/empleados/departamento/Ventas

[
  {
    "id": 1,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2020-01-01T06:00:00.000+00:00"
  },
  {
    "id": 2,
    "nombre": "Maria Garcia",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "maria.garcia@empresa.com",
    "telefono": "555-2345",
    "extension": "2345",
    "sueldo": 1500.0,
    "fecha_ingreso": "2020-01-02T06:00:00.000+00:00"
  },
  {
    "id": 3,
    "nombre": "Pedro Sanchez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "pedro.sanchez@empresa.com",
    "telefono": "555-3456",
    "extension": "3456",
    "sueldo": 1500.0,
    "fecha_ingreso": "2020-01-02T06:00:00.000+00:00"
  },
  {
    "id": 4,
    "nombre": "Ana Rodriguez",
    "departamento": "Ventas",
    "puesto": "Asistente",
    "correo": "ana.rodriguez@empresa.com",
    "telefono": "555-4567",
    "extension": "4567",
    "sueldo": 1000.0,
    "fecha_ingreso": "2020-02-04T06:00:00.000+00:00"
  },
  {
    "id": 5,
    "nombre": "Sara Torres",
    "departamento": "Ventas",
    "puesto": "Asistente",
    "correo": "sara.torres@empresa.com",
    "telefono": "555-5678",
    "extension": "5678",
    "sueldo": 1000.0,
    "fecha_ingreso": "2020-01-07T06:00:00.000+00:00"
  },
  {
    "id": 14,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 1500.0,
    "fecha_ingreso": "2021-01-01T06:00:00.000+00:00"
  },
  {
    "id": 15,
    "nombre": "Maria Rodriguez",
    "departamento": "Ventas",
    "puesto": "Supervisor",
    "correo": "maria.rodriguez@empresa.com",
    "telefono": "555-5678",
    "extension": "5678",
    "sueldo": 3000.0,
    "fecha_ingreso": "2020-06-01T05:00:00.000+00:00"
  },
  {
    "id": 16,
    "nombre": "Pedro Garcia",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "pedro.garcia@empresa.com",
    "telefono": "555-9876",
    "extension": "9876",
    "sueldo": 5000.0,
    "fecha_ingreso": "2019-01-01T06:00:00.000+00:00"
  },
  {
    "id": 29,
    "nombre": "Tom Jones",
    "departamento": "Ventas",
    "puesto": "Gerente de Ventas",
    "correo": "tomjones@example.com",
    "telefono": "555-5555",
    "extension": "555",
    "sueldo": 4500.0,
    "fecha_ingreso": "2022-05-01T05:00:00.000+00:00"
  },
  {
    "id": 37,
    "nombre": "Daniel Perez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "danielperez@example.com",
    "telefono": "555-9999",
    "extension": "999",
    "sueldo": 2000.0,
    "fecha_ingreso": "2023-01-01T06:00:00.000+00:00"
  },
  {
    "id": 38,
    "nombre": "Juan Perez",
    "departamento": "Ventas",
    "puesto": "Vendedor",
    "correo": "juan.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 1500.0,
    "fecha_ingreso": "2021-01-01T06:00:00.000+00:00"
  },
  {
    "id": 39,
    "nombre": "Maria Rodriguez",
    "departamento": "Ventas",
    "puesto": "Supervisor",
    "correo": "maria.rodriguez@empresa.com",
    "telefono": "555-5678",
    "extension": "5678",
    "sueldo": 3000.0,
    "fecha_ingreso": "2020-06-01T05:00:00.000+00:00"
  },
  {
    "id": 40,
    "nombre": "Pedro Garcia",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "pedro.garcia@empresa.com",
    "telefono": "555-9876",
    "extension": "9876",
    "sueldo": 5000.0,
    "fecha_ingreso": "2019-01-01T06:00:00.000+00:00"
  }
]

//= FINALIZA PRUEBA CON QUINTO MÉTODO: BUSCAR UN EMPLEADO POR SU DEPARTAMENTO ==========================















//= INICIA PRUEBA CON SEXTO MÉTODO: INSERTAR EMPLEADO NUEVO ============================================


/*@PostMapping(value="/empleado/insertar") : esta es una anotación que indica que este método se 
llamará cuando se reciba una solicitud HTTP POST en el endpoint /empleado/insertar.*/	
@PostMapping(value="/empleado/insertar")

/*@Transactional : esta es una anotación que indica que la operación de guardado del empleado se 
realizará dentro de una transacción. Esto significa que si algo sale mal durante el proceso de 
guardado, se revertirá la operación y se desharán los cambios realizados en la base de datos.*/
@Transactional

/*public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) : este es el 
método que se ejecutará cuando se reciba una solicitud POST en el endpoint mencionado anteriormente. 
Espera un objeto Empleado en formato JSON en el cuerpo de la solicitud (request body).*/
public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
    //En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {  	
	    
	 //Antes de insertar los campos, todos los campos deben ser válidos, si esto se cumple:
	    
	 if (//Si las siguientes propiedades no son nulas, continua con la validacion
	     empleado.getId() != null && 
	     empleado.getNombre() != null && 
	     empleado.getDepartamento() != null && 
	     empleado.getPuesto() != null && 
	     empleado.getCorreo() != null && 
	     empleado.getTelefono() != null && 
	     empleado.getExtension() != null && 
	     empleado.getSueldo() != null && 
	     empleado.getFecha_ingreso() != null &&
		    		
	     //Si las siguientes propiedades no estan vacias, continua con la validacion
	     !empleado.getNombre().isEmpty() &&
	     !empleado.getDepartamento().isEmpty() &&
	     !empleado.getPuesto().isEmpty() &&
	     !empleado.getCorreo().isEmpty() &&
 	     !empleado.getTelefono().isEmpty() &&
	     !empleado.getExtension().isEmpty() &&
		    		
	     /*Si las siguientes propiedades no sobrepasan el largo de la cadena de texto a 40 espacios, 
	     continua con la validacion*/
	     empleado.getNombre().length() <= 40 &&
	     empleado.getDepartamento().length() <= 40 &&
	     empleado.getPuesto().length() <= 40 &&
	     empleado.getCorreo().length() <= 40 &&
	     empleado.getTelefono().length() <= 40 &&
	     empleado.getExtension().length() <= 40 &&
	     /*La propiedad "sueldo" es de tipo Float y su valor convertido a String no excede 10 caracteres, 
	     continuar con la validación*/
	     Float.toString(empleado.getSueldo()).length() <= 10 &&
		    		
	     //La propiedad "ID" es de tipo Long, continuar con la validación
	     empleado.getId().getClass() == Long.class &&
	     //Si las siguientes propiedades son de tipo String, continua con la validacion
	     empleado.getNombre() instanceof String &&
	     empleado.getDepartamento() instanceof String &&
	     empleado.getPuesto() instanceof String &&
	     empleado.getCorreo() instanceof String &&
	     empleado.getTelefono() instanceof String &&
	     empleado.getExtension() instanceof String &&
	     // La propiedad "sueldo" es de tipo Float, continuar con la validación
	     empleado.getSueldo().getClass() == Float.class &&
	     // La propiedad "fecha_ingreso" es de tipo Date, continuar con la validación
	     empleado.getFecha_ingreso() instanceof java.util.Date &&
		    		
	     /* La propiedad "correo" del objeto "empleado" contiene @gmail.com ó @hotmail.com ó @yahoo.com.mx ó 
	     @outlook.es ó @empresa.com, continuar con la validacion*/
	     empleado.getCorreo().matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com\\.mx|outlook\\.es|empresa\\.com)") &&
	     /*Las siguientes propiedades no quiero que contenga los siguientes simbolos: !, ", #, $, %, &, /, (, 
	     ), =, ?, ¡, ¿, *, -, +, ++, --, {, }, [, ], <, >, continua con el procedimiento */
	     empleado.getNombre().matches("^[a-zA-Z\\.]+[^!\"#$%&/()=?¡¿*-+{}\\[\\]<>]*$")) {
		    		
		 
		 
	         //se guarda el registro en la base de datos       
	         Empleado newEmpleado = empleadoDAO.save(empleado);
		 //Y nos regresara lo que se insertó
	         return ResponseEntity.ok(newEmpleado);
	                
	 } 
	 //Si algun campo no es valido, 
	 else {
	         /*se devuelve una respuesta de error 400 (Bad Request) Y no se añadira nada en la 
		 base de datos*/
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
     }
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/    
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}



/*Para Insertar, nuestra URL será:  localhost:9876/api/empleado/insertar
	-. Abrir Postman.
	-. Cambiar metodo a: POST
	-. Proporcionar URL: localhost:9876/api/empleado/insertar
	-. Proporsionar JSON de datos a insertar.
	-. Vista: raw.
	-. Formato: JSON
	-. Ejemplo campos completos:
	
		{
		  "id": 50,
		  "nombre": "Rosalva Cantu",
		  "departamento": "Desarrollo de Nueva Tecnologia",
		  "puesto": "Arquitecto de Software",
		  "correo": "rosalva.cantu@empresa.com",
		  "telefono": "555-5678",
		  "extension": "8564",
		  "sueldo": 5000.0,
		  "fecha_ingreso": "2020-01-07T06:00:00.000+00:00"
		}
		
	-. Send.
	-. Respuesta 200. OK.
Respuesta:*/

{
    "id": 50,
    "nombre": "Rosalva Cantu",
    "departamento": "Desarrollo de Nueva Tecnologia",
    "puesto": "Arquitecto de Software",
    "correo": "rosalva.cantu@empresa.com",
    "telefono": "555-5678",
    "extension": "8564",
    "sueldo": 5000.0,
    "fecha_ingreso": "2020-01-07T06:00:00.000+00:00"
}

/*Validar en base de datos que se alla añadido el registro con el id 50.
	-. Usar comando: select * from empleado;
	-. Validar en lista*/


/*      -. Ejemplo campos incompletos: (no pondre el campo extension)
	
		{
		  "id": 52,
		  "nombre": "Prueba",
		  "departamento": "Prueba",
		  "puesto": "Prueba",
		  "correo": "prueba.prueba@empresa.com",
		  "telefono": "555-5678",
		  "sueldo": 1000.0,
		  "fecha_ingreso": "2020-01-07T06:00:00.000+00:00"
		}
		
	-. Send.
	-. Respuesta 400. BAD REQUEST.
Respuesta:
NO DEVUELVE NADA.
	-. Si no cumple con alguna de las validaciones dentro del if, marcara respuesta: 400 BAD REQUEST.*/


//= FINALIZA PRUEBA CON SEXTO MÉTODO: INSERTAR EMPLEADO NUEVO ==========================================














//= INICIA PRUEBA CON SEPTIMO MÉTODO: ELIMINAR UN EMPLEADO =============================================


/*Esta línea es una anotación de Spring que indica que este método maneja solicitudes HTTP DELETE. 
En este caso, maneja solicitudes DELETE a la ruta /empleado/eliminar/{empleadoId}, donde {empleadoId} 
es una variable de ruta que puede tomar cualquier valor numérico.*/	
@DeleteMapping(value="/empleado/eliminar/{empleadoId}")

/*Esta línea es una anotación de Spring que indica que este método se ejecutará dentro de una 
transacción de base de datos. Si ocurre alguna excepción dentro de esta transacción, la 
transacción se revertirá y se desharán todos los cambios realizados dentro de ella.*/
@Transactional

public ResponseEntity<Void> deleteEmpleado(@PathVariable("empleadoId") Long empleadoId) {
    //En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {
	/*Esta línea utiliza el método findById() de empleadoDAO para buscar un registro de la base 
	de datos con el empleadoId proporcionado y lo almacena en un objeto Optional<Empleado>. Un 
	Optional es un contenedor que puede contener un objeto Empleado, o puede estar vacío si no 
	se encuentra ningún registro con el empleadoId proporcionado.*/
        Optional<Empleado> empleadoid = empleadoDAO.findById(empleadoId);
	    
	//Si se encuentra el registro id
	if (empleadoid.isPresent()) {
		
	    /*Esta línea elimina el registro de la base de datos con el empleadoId proporcionado 
	    utilizando el método deleteById() de empleadoDAO.*/
	    empleadoDAO.deleteById(empleadoId);
	    /*Esta línea devuelve una respuesta HTTP con el estado 200 OK y sin cuerpo (ya que se 
	    elimina el registro).*/
	    return ResponseEntity.ok(null);
		
	}
	//Si no se encontro ningun registro entonces,
	else {
	    /*Esta línea devuelve una respuesta HTTP con el estado 404 Not Found y sin cuerpo (ya que 
	    no se encuentra el registro).*/
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
    }
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/     
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


/*Para Eliminar, nuestra URL será:  localhost:9876/api/empleado/eliminar/52
	-. Abrir Postman.
	-. Cambiar metodo a: DELETE
	-. Proporcionar URL: localhost:9876/api/empleado/eliminar/52		
	-. Send.
	-. Respuesta 200. OK.
Respuesta:
No devuelve valor nulo.
	-. Si en vez de valor numerico es una letra o palabra o simbolo, marcara error 400 BAD REQUEST*/


//= FINALIZA PRUEBA CON SEPTIMO MÉTODO: ELIMINAR UN EMPLEADO ===========================================













//= INICIA PRUEBA CON OCTAVO MÉTODO: ACTUALIZAR REGISTRO DE UN EMPLEADO ================================


/*@Transactional: Esta anotación indica que el método debe ser ejecutado en una transacción. Las 
transacciones son una forma de garantizar la integridad de la base de datos, ya que garantizan que 
todas las operaciones dentro de una transacción se completen con éxito o se reviertan si algo falla.*/
@Transactional

/*@PutMapping(value="/empleado/actualizar"): Esta anotación indica que este método manejará una 
solicitud PUT en la URL /empleado/actualizar (que sería accesible a través de la ruta 
http://localhost:9876/api/empleado/actualizar). Es decir, cuando se reciba una solicitud PUT a esta 
URL, Spring Boot invocará este método.*/
@PutMapping(value="/empleado/actualizar")

/*Este es el método que manejará la solicitud PUT. Toma un objeto de tipo Empleado en el cuerpo de la 
solicitud (a través de la anotación @RequestBody) y devuelve un objeto ResponseEntity que contendrá la 
respuesta a la solicitud. El tipo de retorno del método es ResponseEntity<Empleado>, lo que significa 
que la respuesta contendrá un objeto Empleado.*/
public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado) {
	
    ////En éste try, si no ocurre un error interno en el servidor, se ejecuta este bloque, en donde
    try {
	/*Aquí se busca en la base de datos un objeto de tipo Empleado cuyo ID coincida con el ID 
	proporcionado en el objeto empleado recibido en la solicitud.*/
        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(empleado.getId());
	    
	/*Se verifica si el objeto Empleado buscado existe en la base de datos. Si existe, se continua 
	con la actualización de los datos del empleado.*/
        if (optionalEmpleado.isPresent()) {
        	
	    /*Si el objeto Empleado existe en la base de datos, se obtiene su instancia mediante el 
	    método get() de Optional y se guarda en una variable llamada updateEmpleado.*/
            Empleado updateEmpleado = optionalEmpleado.get();
            
            if (//Si las siguientes propiedades no son nulas, continua con la validacion
		empleado.getId() != null && 
		empleado.getNombre() != null && 
		empleado.getDepartamento() != null && 
		empleado.getPuesto() != null && 
		empleado.getCorreo() != null && 
		empleado.getTelefono() != null && 
		empleado.getExtension() != null && 
		empleado.getSueldo() != null && 
		empleado.getFecha_ingreso() != null &&
				   		
		//Si las siguientes propiedades no estan vacias, continua con la validacion
		!empleado.getNombre().isEmpty() &&
		!empleado.getDepartamento().isEmpty() &&
		!empleado.getPuesto().isEmpty() &&
		!empleado.getCorreo().isEmpty() &&
		!empleado.getTelefono().isEmpty() &&
		!empleado.getExtension().isEmpty() &&
		    		
		/*Si las siguientes propiedades no sobrepasan el largo de la cadena de texto a 40
		espacios, continua con la validacion*/
		empleado.getNombre().length() <= 40 &&
		empleado.getDepartamento().length() <= 40 &&
		empleado.getPuesto().length() <= 40 &&
		empleado.getCorreo().length() <= 40 &&
		empleado.getTelefono().length() <= 40 &&
		empleado.getExtension().length() <= 40 &&
		/* La propiedad "sueldo" es de tipo Float y su valor convertido a String no excede
		10 caracteres, continuar con la validación*/
		Float.toString(empleado.getSueldo()).length() <= 10 &&
		    		
		// La propiedad "ID" es de tipo Long, continuar con la validación
		empleado.getId().getClass() == Long.class &&
		//Si las siguientes propiedades son de tipo String, continua con la validacion
		empleado.getNombre() instanceof String &&
		empleado.getDepartamento() instanceof String &&
		empleado.getPuesto() instanceof String &&
		empleado.getCorreo() instanceof String &&
		empleado.getTelefono() instanceof String &&
		empleado.getExtension() instanceof String &&
		// La propiedad "sueldo" es de tipo Float, continuar con la validación
		empleado.getSueldo().getClass() == Float.class &&
		// La propiedad "fecha_ingreso" es de tipo Date, continuar con la validación
		empleado.getFecha_ingreso() instanceof java.util.Date &&
			   		
		/* La propiedad "correo" del objeto "empleado" contiene @gmail.com ó @hotmail.com ó @yahoo.com.mx ó 
		@outlook.es ó @empresa.com, continuar con el procesamiento*/
		empleado.getCorreo().matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com\\.mx|outlook\\.es|empresa\\.com)") &&
		/*Las siguientes propiedades no quiero que contenga los siguientes simbolos: !, ", #, $, %, &, /, (, ), 
		=, ?, ¡, ¿, *, -, +, ++, --, {, }, [, ], <, > */
		empleado.getNombre().matches("^[a-zA-Z\\.]+[^!\"#$%&/()=?¡¿*-+{}\\[\\]<>]*$")) {
		            	
		       /*Si las validaciones son correctas, se actualizan los datos*/
	               updateEmpleado.setNombre(empleado.getNombre());
	               updateEmpleado.setPuesto(empleado.getPuesto());
	               updateEmpleado.setDepartamento(empleado.getDepartamento());
	               updateEmpleado.setCorreo(empleado.getCorreo());
	               updateEmpleado.setTelefono(empleado.getTelefono());
	               updateEmpleado.setExtension(empleado.getExtension());
	               updateEmpleado.setFecha_ingreso(empleado.getFecha_ingreso());
		    
	               /*Después de actualizar el objeto Empleado con los nuevos datos, se guarda en la base de datos 
		       utilizando el objeto empleadoDAO que es una instancia de la interfaz EmpleadoDAO que se encarga 
		       de interactuar con la base de datos.*/
	               empleadoDAO.save(updateEmpleado);
		       /*Si la actualización se realiza con éxito, se devuelve una respuesta 200 OK que contiene el 
		       objeto Empleado actualizado en el cuerpo de la respuesta.*/
	               return ResponseEntity.ok(updateEmpleado);
	                
	        }
		//Si no se aprueban las validaciones de los datos que se van a ingresar para actualizar el empleado
		else {
		        //Lanza un mensaje 400 BAD REQUES
                	return ResponseEntity.badRequest().build();
                }
		
        }
	//Si el objeto Empleado no existe en la base de datos,   
	else {
	    //se devuelve una respuesta 404 Not Found indicando que no se encontró el recurso buscado.
            return ResponseEntity.notFound().build();
        }
	    
    }
    //Si ocurre un error interno del servidor, no se ejecuta el bloque try y se imprime la excepcion
    catch (Exception e) {
	/*Donde ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) crea un código de estado 500 
	(Internal Server Error). Luego con el método build() se construye y devolve una respuesta HTTP 
	con uel código 500 (Internal Server Error) cuando se produce la excepción (Exception e) durante 
	la ejecución del método.*/     
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}



/*Para Actualizar un registro de empleado, nuestra URL será:  localhost:9876/api/empleado/actualizar
	-. Abrir Postman.
	-. Cambiar metodo a: PUT
	-. Proporcionar URL: localhost:9876/api/empleado/actualizar	
	-. Proporciono los datos que se van actualizar en el regristro.
	-. Agregar el id del usuario que se va actualizar:
	
	    {
		"id": 51,
		"nombre": "Juanita Perez",
		"departamento": "Ventas",
		"puesto": "Gerente",
		"correo": "juanita.perez@empresa.com",
		"telefono": "555-1234",
		"extension": "1234",
		"sueldo": 3000.0,
		"fecha_ingreso": "2019-01-01T06:00:00.000+00:01"
	    }
	    
	-. Send.
	-. Respuesta 200. OK.
Respuesta:*/

{
    "id": 51,
    "nombre": "Juanita Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juanita.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2019-01-01T05:59:00.000+00:00"
}


/*	-.Si se pone un id que no existe marcara error 404. Not Found.
	-. El id 100 no existe en base de datos.*/
{
    "id": 100,                                <---- Se coloco un id que no existe en base de datos
    "nombre": "Juanita Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juanita.perez@empresa.com",
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2019-01-01T05:59:00.000+00:00"
}


/*	-.Si se omite el id en los campos, marcara error 500. Internal Server Error.*/
{
     <---- Se omite el campo id                                   
    "nombre": "Juanita Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juanita.perez@empresa.com.es",  
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2019-01-01T06:00:00.000+00:01"
}


/*Si al escribir en los campos a actualizar no se cumple con las validaciones. Marca error 400 BAD REQUEST*/
{
    "id": 51,
    "nombre": "Juanita Perez",
    "departamento": "Ventas",
    "puesto": "Gerente",
    "correo": "juanita.perez@empresa.com.es",  <---- Si coloco .es y no esta permitido
    "telefono": "555-1234",
    "extension": "1234",
    "sueldo": 3000.0,
    "fecha_ingreso": "2019-01-01T06:00:00.000+00:01"
}

//= FINALIZA PRUEBA CON OCTAVO MÉTODO: ACTUALIZAR REGISTRO DE UN EMPLEADO ==============================
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
PRUEBAS POSTMAN: FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=














-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
PAGINA WEB QUE BUSCA Y FILTRA POR NOMBRE, PUESTO O DEPARTAMENTO: INICIO
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Buscar empleados</title>
    <!-- Importamos los estilos de Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

    
  </head>
  <body>
    <div class="container">
      <h1>Buscar empleados</h1>
      <form id="search-form">
        <div class="form-group">
          <label for="search-type">Buscar por:</label>
          <select class="form-control" id="search-type">
            <option value="name">Nombre</option>
            <option value="position">Puesto</option>
            <option value="department">Departamento</option>
          </select>
        </div>
        <div class="form-group">
          <label for="search-term">Término de búsqueda:</label>
          <input type="text" class="form-control" id="search-term" />
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
      </form>
      <br>
      <div id="search-results"></div>
    </div>

    <!-- Importamos el script de jQuery y el de Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNSzN9W" crossorigin="anonymous"></script> -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	

    <script>
	$(document).ready(function () {
	  $("#search-form").submit(function (event) {
	    // Prevenimos el comportamiento por defecto del formulario
	    event.preventDefault();

	    // Obtenemos el tipo de búsqueda seleccionado y el término de búsqueda
	    const searchType = $("#search-type").val();
	    const searchTerm = $("#search-term").val();

	    // Construimos la URL del endpoint correspondiente
	    let url;
	    if (searchType === "name") {
	      url = "http://localhost:9876/api/empleados/nombre/" + searchTerm;
	    } else if (searchType === "position") {
	      url = "http://localhost:9876/api/empleados/puesto/" + searchTerm;
	    } else if (searchType === "department") {
	      url = "http://localhost:9876/api/empleados/departamento/" + searchTerm;
	    }

	    // Hacemos la petición GET al endpoint correspondiente
	    fetch(url)
	      .then(response => response.json())
	      .then(data => {
		// Limpiamos los resultados anteriores
		$("#search-results").empty();

		// Si la respuesta es un array vacío, mostramos un mensaje
		if (data.length === 0) {
		  $("#search-results").append(
		    '<div class="alert alert-warning" role="alert">No se encontraron resultados para la búsqueda realizada.</div>'
		  );
		} else {
		  // Si la respuesta tiene datos, creamos una tabla con los resultados
		  const table = $("<table class='table'></table>");
		  const tableHeader = $("<thead><tr><th>Id</th><th>Nombre</th><th>Puesto</th><th>Departamento</th><th>Correo</th><th>Telefono</th><th>Extension</th><th>Sueldo</th><th>Fecha_ingreso</th></tr></thead>");
		  const tableBody = $("<tbody></tbody>");

		  // Recorremos los resultados y agregamos una fila por cada empleado
		  data.forEach(function (employee) {
		    const row = $("<tr></tr>");
		    const idCell = $("<td></td>").text(employee.id);
		    const nameCell = $("<td></td>").text(employee.nombre);
		    const positionCell = $("<td></td>").text(employee.puesto);
		    const departmentCell = $("<td></td>").text(employee.departamento);
		    const emailCell = $("<td></td>").text(employee.correo);
		    const phoneCell = $("<td></td>").text(employee.telefono);
		    const extentionCell = $("<td></td>").text(employee.extension);
		    const salaryCell = $("<td></td>").text(employee.sueldo);
		    const ingresoCell = $("<td></td>").text(employee.fecha_ingreso);

		    row.append(idCell);
		    row.append(nameCell);
		    row.append(positionCell);
		    row.append(departmentCell);
		    row.append(emailCell);
		    row.append(phoneCell);
		    row.append(extentionCell);
		    row.append(salaryCell);
		    row.append(ingresoCell);

		    tableBody.append(row);
		  });

		  table.append(tableHeader);
		  table.append(tableBody);

		  $("#search-results").append(table);
		}
	      })
	      .catch(error => {
		console.error(error);
		// Mostramos un mensaje de error
		$("#search-results").empty().append(
		  '<div class="alert alert-danger" role="alert">Ha ocurrido un error al realizar la búsqueda.</div>'
		);
	      });
	  });
	});
    </script>
  </body>
</html>

-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
PAGINA WEB QUE BUSCA Y FILTRA POR NOMBRE, PUESTO O DEPARTAMENTO: FINAL
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=





//SI QUISIRA MODIFICAR LOS RESULTADOS PERO EN VEZ QUE SE IMPRIMA EN UNA TABLA QUE SEA EN TARJETAS DE PRESENTACION.

DROP TABLE empleado;

INSERT INTO empleado (nombre, departamento, puesto, correo, telefono, extension, sueldo, fecha_ingreso, imagen) VALUES
	('Juan Perez', 'Ventas', 'Gerente', 'juan.perez@empresa.com', '555-1234', '1234', 3000.00, '2020-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\1.gif'),
	('Maria Garcia', 'Ventas', 'Vendedor', 'maria.garcia@empresa.com', '555-2345', '2345', 1500.00, '2020-01-02','C:\Users\amade\OneDrive\Desktop\Empleados\img\2.jpeg'),
	('Pedro Sanchez', 'Ventas', 'Vendedor', 'pedro.sanchez@empresa.com', '555-3456', '3456', 1500.00, '2020-01-02','C:\Users\amade\OneDrive\Desktop\Empleados\img\3.jpg'),
	('Ana Rodriguez', 'Ventas', 'Asistente', 'ana.rodriguez@empresa.com', '555-4567', '4567', 1000.00, '2020-02-04','C:\Users\amade\OneDrive\Desktop\Empleados\img\4.jpg'),
	('Sara Torres', 'Ventas', 'Asistente', 'sara.torres@empresa.com', '555-5678', '5678', 1000.00, '2020-01-07','C:\Users\amade\OneDrive\Desktop\Empleados\img\5.jpg'),
	('Luis Jimenez', 'Compras', 'Gerente', 'luis.jimenez@empresa.com', '555-6789', '6789', 3000.00, '2020-02-03','C:\Users\amade\OneDrive\Desktop\Empleados\img\6.jpg'),
	('Julia Gomez', 'Compras', 'Comprador', 'julia.gomez@empresa.com', '555-7890', '7890', 2000.00, '2020-01-08','C:\Users\amade\OneDrive\Desktop\Empleados\img\7.jpg'),
	('Miguel Ramirez', 'Compras', 'Comprador', 'miguel.ramirez@empresa.com', '555-8901', '8901', 2000.00, '2020-07-05','C:\Users\amade\OneDrive\Desktop\Empleados\img\8.jpg'),
	('Laura Hernandez', 'Compras', 'Asistente', 'laura.hernandez@empresa.com', '555-9012', '9012', 1000.00, '2020-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\9.jpg'),
	('Carla Castro', 'Compras', 'Asistente', 'carla.castro@empresa.com', '555-0123', '0123', 1000.00, '2020-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\10.jpg'),
	('Diego Lopez', 'Finanzas', 'Gerente', 'diego.lopez@empresa.com', '555-1111', '1111', 3500.00, '2020-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\11.jpg'),
	('Lucia Fernandez', 'Finanzas', 'Contador', 'lucia.fernandez@empresa.com', '555-2222', '2222', 2500.00, '2020-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\12.jpg'),
	('Roberto Rodriguez', 'Finanzas', 'Contador', 'roberto.rodriguez@empresa.com', '555-3333', '3333', 2500.00, '2020-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\13.jpg'),
	('Juan Perez', 'Ventas', 'Vendedor', 'juan.perez@empresa.com', '555-1234', '1234', 1500.00, '2021-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\14.jpg'),
	('Maria Rodriguez', 'Ventas', 'Supervisor', 'maria.rodriguez@empresa.com', '555-5678', '5678', 3000.00, '2020-06-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\15.jpg'),
	('Pedro Garcia', 'Ventas', 'Gerente', 'pedro.garcia@empresa.com', '555-9876', '9876', 5000.00, '2019-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\16.jpg'),
	('Luisa Martinez', 'Finanzas', 'Contador', 'luisa.martinez@empresa.com', '555-1111', '1111', 2000.00, '2020-09-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\17.jpg'),
	('Jose Hernandez', 'Finanzas', 'Analista', 'jose.hernandez@empresa.com', '555-2222', '2222', 2500.00, '2021-03-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\18.jpg'),
	('Ana Lopez', 'Finanzas', 'Gerente', 'ana.lopez@empresa.com', '555-3333', '3333', 5000.00, '2018-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\19.jpg'),
	('Mario Jimenez', 'Tecnologia', 'Desarrollador', 'mario.jimenez@empresa.com', '555-4444', '4444', 3000.00, '2020-06-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\20.jpg'),
	('Sofia Garcia', 'Tecnologia', 'Analista', 'sofia.garcia@empresa.com', '555-5555', '5555', 2500.00, '2019-03-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\21.jpg'),
	('Carlos Rodriguez', 'Tecnologia', 'Gerente', 'carlos.rodriguez@empresa.com', '555-6666', '6666', 5000.00, '2017-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\22.jpg'),
	('Fernanda Castro', 'Recursos Humanos', 'Analista', 'fernanda.castro@empresa.com', '555-7777', '7777', 2500.00, '2021-09-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\23.jpg'),
	('Javier Diaz', 'Recursos Humanos', 'Gerente', 'javier.diaz@empresa.com', '555-8888', '8888', 5000.00, '2016-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\24.jpg'),
	('Alejandra Chavez', 'Recursos Humanos', 'Asistente', 'alejandra.chavez@empresa.com', '555-9999', '9999', 2000.00, '2022-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\25.jpg'),
	('Jane Doe', 'Finanzas', 'Contador', 'janedoec@example.com', '555-5678', '456', 2500.00, '2022-02-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\26.jpg'),
	('Bob Smith', 'Marketing', 'Gerente de Marketing', 'bobsmith@example.com', '555-9876', '789', 3500.00, '2022-03-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\27.jpg'),
	('Alice Brown', 'Recursos Humanos', 'Gerente de Recursos Humanos', 'alicebrown@example.com', '555-4321', '321', 4000.00, '2022-04-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\28.jpg'),
	('Tom Jones', 'Ventas', 'Gerente de Ventas', 'tomjones@example.com', '555-5555', '555', 4500.00, '2022-05-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\29.jpg'),
	('Sara Johnson', 'Tecnología de la Información', 'Desarrollador', 'sarajohnson@example.com', '555-1111', '111', 3000.00, '2022-06-01''C:\Users\amade\OneDrive\Desktop\Empleados\img\30.jpg'),
	('Michael Davis', 'Tecnología de la Información', 'Desarrollador', 'michaeldavis@example.com', '555-2222', '222', 3000.00, '2022-07-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\31.jpg'),
	('Emily Rodriguez', 'Finanzas', 'Contador', 'emilyrodriguez@example.com', '555-3333', '333', 2500.00, '2022-08-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\32.jpg'),
	('David Wilson', 'Marketing', 'Especialista en Marketing', 'davidwilson@example.com', '555-4444', '444', 3000.00, '2022-09-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\33.jpg'),
	('Karen Taylor', 'Recursos Humanos', 'Especialista en Recursos Humanos', 'karentaylor@example.com', '555-6666', '666', 3500.00, '2022-10-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\34.jpg'),
	('Adam Brown', 'Tecnología de la Información', 'Desarrollador', 'adambrown@example.com', '555-7777', '777', 3000.00, '2022-11-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\35.jpg'),
	('Laura Garcia', 'Tecnología de la Información', 'Gerente de Tecnología', 'lauragarcia@example.com', '555-8888', '888', 5000.00, '2022-12-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\36.jpg'),
	('Daniel Perez', 'Ventas', 'Vendedor', 'danielperez@example.com', '555-9999', '999', 2000.00, '2023-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\37.jpg'),
	('Juan Perez', 'Ventas', 'Vendedor', 'juan.perez@empresa.com', '555-1234', '1234', 1500.00, '2021-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\38.jpg'),
	('Maria Rodriguez', 'Ventas', 'Supervisor', 'maria.rodriguez@empresa.com', '555-5678', '5678', 3000.00, '2020-06-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\39.jpg'),
	('Pedro Garcia', 'Ventas', 'Gerente', 'pedro.garcia@empresa.com', '555-9876', '9876', 5000.00, '2019-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\40.jpg'),
	('Luisa Martinez', 'Finanzas', 'Contador', 'luisa.martinez@empresa.com', '555-1111', '1111', 2000.00, '2020-09-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\41.jpg'),
	('Jose Hernandez', 'Finanzas', 'Analista', 'jose.hernandez@empresa.com', '555-2222', '2222', 2500.00, '2021-03-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\42.jpg'),
	('Ana Lopez', 'Finanzas', 'Gerente', 'ana.lopez@empresa.com', '555-3333', '3333', 5000.00, '2018-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\43.jpg'),
	('Mario Jimenez', 'Tecnologia', 'Desarrollador', 'mario.jimenez@empresa.com', '555-4444', '4444', 3000.00, '2020-06-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\44.jpg'),
	('Sofia Garcia', 'Tecnologia', 'Analista', 'sofia.garcia@empresa.com', '555-5555', '5555', 2500.00, '2019-03-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\45.jpg'),
	('Carlos Rodriguez', 'Tecnologia', 'Gerente', 'carlos.rodriguez@empresa.com', '555-6666', '6666', 5000.00, '2017-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\46.jpg'),
	('Fernanda Castro', 'Recursos Humanos', 'Analista', 'fernanda.castro@empresa.com', '555-7777', '7777', 2500.00, '2021-09-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\47.jpg'),
	('Javier Diaz', 'Recursos Humanos', 'Gerente', 'javier.diaz@empresa.com', '555-8888', '8888', 5000.00, '2016-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\48.jpg'),
	('Alejandra Chavez', 'Recursos Humanos', 'Asistente', 'alejandra.chavez@empresa.com', '555-9999', '9999', 2000.00, '2022-01-01','C:\Users\amade\OneDrive\Desktop\Empleados\img\49.jpg');
	
SELECT * FROM empleado;





//CAMBIARIA HTML EN JQUERY

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Buscar empleados</title>
    <!-- Importamos los estilos de Bootstrap -->
	  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <style>

        #imgn{
	      	width: 209px;
	      	height: 350px;
		float: left;
		object-fit: cover;
		transition: .2s;
		border-radius: 5px 5px;
        	padding-right: 15px;
          	padding-bottom: 20px;
				}

    </style>
    
  </head>
  <body>
    <div class="container">
      <h1>Buscar empleados</h1>
      <form id="search-form">
        <div class="form-group">
          <label for="search-type">Buscar por:</label>
          <select class="form-control" id="search-type">
            <option value="name">Nombre</option>
            <option value="position">Puesto</option>
            <option value="department">Departamento</option>
          </select>
        </div>
        <div class="form-group">
          <label for="search-term">Término de búsqueda:</label>
          <input type="text" class="form-control" id="search-term" />
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
      </form>
      <br>
      <div id="search-results"></div>
    </div>

    <!-- Importamos el script de jQuery y el de Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNSzN9W" crossorigin="anonymous"></script> -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <script>
        $(document).ready(function () {
          $("#search-form").submit(function (event) {
            // Prevenimos el comportamiento por defecto del formulario
            event.preventDefault();

            // Obtenemos el tipo de búsqueda seleccionado y el término de búsqueda
            const searchType = $("#search-type").val();
            const searchTerm = $("#search-term").val();

            // Construimos la URL del endpoint correspondiente
            let url;
            if (searchType === "name") {
              url = "http://localhost:9876/api/empleados/nombre/" + searchTerm;
            } else if (searchType === "position") {
              url = "http://localhost:9876/api/empleados/puesto/" + searchTerm;
            } else if (searchType === "department") {
              url = "http://localhost:9876/api/empleados/departamento/" + searchTerm;
            }

            // Hacemos la petición GET al endpoint correspondiente
            fetch(url)
              .then(response => response.json())
              .then(data => {

                // Limpiamos los resultados anteriores
                $("#search-results").empty();

                // Si la respuesta es un array vacío, mostramos un mensaje
                if (data.length === 0) {
                  $("#search-results").append(
                    '<div class="alert alert-warning" role="alert">No se encontraron resultados para la búsqueda realizada.</div>'
                  );
                } else {
                  // Si la respuesta tiene datos, creamos una tabla con los resultados
                  
                  data.forEach(function (employee) {
                    const card = $("<div class='col-md-6' style='float:left;'><div class='card'></div></div>");
                    const image = $("<img id='imgn' class='card-img-top' src='" + employee.imagen + "' alt='Foto de " + employee.nombre + "'>");
                    const cardBody = $("<div class='card-body'></div>");
                    const name = $("<h5 class='card-title'>" + employee.nombre + "</h5>");
                    const position = $("<p class='card-text'>Puesto: " + employee.puesto + "</p>");
                    const department = $("<p class='card-text'>Departamento: " + employee.departamento + "</p>");
                    const email = $("<p class='card-text'>Correo: " + employee.correo + "</p>");
                    const phone = $("<p class='card-text'>Teléfono: " + employee.telefono + "</p>");
                    const extension = $("<p class='card-text'>Extensión: " + employee.extension + "</p>");
                    const salary = $("<p class='card-text'>Sueldo: " + employee.sueldo + "</p>");

                    // Agregamos los elementos creados al contenedor cardBody
                    cardBody.append(name, position, department, email, phone, extension, salary);

                    // Agregamos la imagen y el contenedor cardBody al contenedor card
                    card.append(image, cardBody);

                    // Agregamos la tarjeta al contenedor search-results
                    $("#search-results").append(card);
                  });

                }
              })
              .catch(error => {
                console.error(error);
                // Mostramos un mensaje de error
                $("#search-results").empty().append(
                  '<div class="alert alert-danger" role="alert">Ha ocurrido un error al realizar la búsqueda.</div>'
                );
              });
          });
        });
        

    </script>
  </body>
</html>


