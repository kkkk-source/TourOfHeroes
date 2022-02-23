package co.udea.hero.api.controller;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    /**
     * Busca un heroe creado en la base de datos por su id.
     */
    @GetMapping("{id}")
    @ApiOperation(value = " Buscar un heroe por su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Heroe no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    /**
     * lista todos los heroes creados en la base de datos.
     */
    @GetMapping("")
    @ApiOperation(value = "Buscar todos los heroes",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Heroe no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request buscar heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    /**
     * Almacena un heroe recien creado en la base de datos.
     */
    @PostMapping
    @ApiOperation(value = "Crear heroe",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Heroe no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
        log.info("Rest request crear heroe");
        return ResponseEntity.ok(heroService.postHero(hero));
    }

    /**
     * Actualiza un heroe en la base de datos.
     */
    @PutMapping
    @ApiOperation(value = "Actualizar heroe",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Heroe no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero ){
        log.info("Rest request actualizar heroe ");
        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    /**
     * Elimina un heroe de la base de datos.
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "Eliminar heroe por id", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Heroe no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Void> deleteHero(@PathVariable Integer id){
        log.info("Rest request borrar heroe = {} ", id);
        heroService.deleteHero(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /**
     * Consulta un heroe en la base de datos por el nombre.
     */
    @GetMapping("/name")
    @ApiOperation(value = "Buscar heroe por nombre", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Heroe no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam String name) {
        log.info("Rest request buscar heroe por nombre: {}", name);
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }

}
