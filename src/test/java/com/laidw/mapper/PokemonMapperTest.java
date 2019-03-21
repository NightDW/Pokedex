package com.laidw.mapper;

import com.laidw.entity.Ability;
import com.laidw.entity.Pokemon;
import com.laidw.entity.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokemonMapperTest {
    @Autowired
    private PokemonMapper mapper;

    //一些实体类对象
    private Pokemon pokemon = new Pokemon();
    private List<Type> types0, types1, types2;
    private List<Ability> abilities0, abilities1, abilities2, abilities3;
    private Type t1, t2, t3, t4;
    private Ability a1, a2, a3, a4, a5, a6;

    @Test
    public void testSavePokemon(){
        pokemon.setTypes(null);
        pokemon.setName("pokemon01"); pokemon.setAbilities(null); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon02"); pokemon.setAbilities(abilities0); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon03"); pokemon.setAbilities(abilities1); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon04"); pokemon.setAbilities(abilities2); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon05"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);

        pokemon.setTypes(types0);
        pokemon.setName("pokemon06"); pokemon.setAbilities(null); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon07"); pokemon.setAbilities(abilities0); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon08"); pokemon.setAbilities(abilities1); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon09"); pokemon.setAbilities(abilities2); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon10"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);

        pokemon.setTypes(types1);
        pokemon.setName("pokemon11"); pokemon.setAbilities(null); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon12"); pokemon.setAbilities(abilities0); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon13"); pokemon.setAbilities(abilities1); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon14"); pokemon.setAbilities(abilities2); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon15"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);

        pokemon.setTypes(types2);
        pokemon.setName("pokemon16"); pokemon.setAbilities(null); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon17"); pokemon.setAbilities(abilities0); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon18"); pokemon.setAbilities(abilities1); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon19"); pokemon.setAbilities(abilities2); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon20"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon21"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon22"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon23"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon24"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);
        pokemon.setName("pokemon25"); pokemon.setAbilities(abilities3); mapper.savePokemon(pokemon);
    }

    @Test
    public void testSelectPokemon(){
        System.out.println(mapper.selectPokemonById(1));
        System.out.println(mapper.selectPokemonById(8));
        System.out.println("-----------------------------------------");

        System.out.println(mapper.selectPokemonByName("pokemon03"));
        System.out.println(mapper.selectPokemonByName("pokemon20"));
        System.out.println("-----------------------------------------");

        List<Pokemon> list = mapper.selectAllPokemons();
        for(Pokemon pk : list)
            System.out.println(pk);
    }

    @Test
    public void testUpdatePokemon(){
        pokemon = new Pokemon();
        pokemon.setName("pokemon21"); pokemon.setAbilities(abilities2);
        mapper.updatePokemonByName(pokemon);

        pokemon = new Pokemon();
        pokemon.setName("pokemon22"); pokemon.setTypes(types1);
        mapper.updatePokemonByNameSelectively(pokemon);

        pokemon = new Pokemon();
        pokemon.setId(23); pokemon.setAbilities(abilities1);
        mapper.updatePokemonById(pokemon);

        pokemon = new Pokemon();
        pokemon.setId(24); pokemon.setTypes(types0);
        mapper.updatePokemonByIdSelectively(pokemon);
    }

    @Test
    public void testDeletePokemon() throws InterruptedException {
        mapper.deletePokemonByName("pokemon08");
        mapper.deletePokemonByName("pokemon16");
        Thread.sleep(8000);

        mapper.deletePokemonById(5);
        mapper.deletePokemonById(1);
        Thread.sleep(8000);

        mapper.deleteAllPokemons();
    }

    @Test
    public void testSelectPokemonNew(){
        System.out.println(mapper.selectPokemonById(1));
        System.out.println(mapper.selectPokemonByName("Pidgey"));
        System.out.println(mapper.selectPokemonByName("Gardevoir"));
    }

    @Before
    public void initTypes(){
        t1 = new Type(); t1.setIconUrl("t1-url"); t1.setName("t1");
        t2 = new Type(); t2.setIconUrl("t2-url"); t2.setName("t2");
        t3 = new Type(); t3.setIconUrl("t3-url"); t3.setName("t3");
        t4 = new Type(); t4.setIconUrl("t4-url"); t4.setName("t4");

        types0 = new ArrayList<>();
        types1 = new ArrayList<>(); types1.add(t1);
        types2 = new ArrayList<>(); types2.add(t1); types2.add(t2);
    }

    @Before
    public void initAbilities(){
        a1 = new Ability(); a1.setName("a1"); a1.setDescription("desc1");
        a2 = new Ability(); a2.setName("a2"); a2.setDescription("desc2");
        a3 = new Ability(); a3.setName("a3"); a3.setDescription("desc3");
        a4 = new Ability(); a4.setName("a4"); a4.setDescription("desc4");
        a5 = new Ability(); a5.setName("a5"); a5.setDescription("desc5");
        a6 = new Ability(); a6.setName("a6"); a6.setDescription("desc6");

        abilities0 = new ArrayList<>();
        abilities1 = new ArrayList<>(); abilities1.add(a1);
        abilities2 = new ArrayList<>(); abilities2.add(a1); abilities2.add(a2);
        abilities3 = new ArrayList<>(); abilities3.add(a1); abilities3.add(a2); abilities3.add(a3);
    }

    @Before
    public void initPokemon(){
        //pokemon.setIconUrl("http://233/pokemon.png");
        pokemon.setOther(1, 2, 3, 4, 5, 6);
    }
}
