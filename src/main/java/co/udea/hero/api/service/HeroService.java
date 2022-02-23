package co.udea.hero.api.service;

import co.udea.hero.api.controller.HeroController;
import co.udea.hero.api.model.Hero;
import co.udea.hero.api.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            log.error("Error en el servicio de getHero() ");
        }
        return optionalHero.get();
    }

    public List<Hero> getHeroes(){
        List<Hero> heroesList = heroRepository.findAll();
        return heroesList;
    }
    public Hero postHero(Hero hero){
        return heroRepository.save(hero);
    }

    public Hero updateHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public void deleteHero(Integer id) {
        heroRepository.deleteById(id);
    }

    public List<Hero> searchHeroes(String name) {
        List<Hero> heroesList = heroRepository.findByName(name);
        return heroesList;
    }
}
