package world.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {

    @Id
    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capital")
    private City capital;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<City> citySet;

    public Country(String code, String name, String continent, String region, int population) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
    }

    public Country() {

    }

    public Set<City> getCitySet() {
        return citySet;
    }

    public void setCitySet(Set<City> citySet) {
        this.citySet = citySet;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void removeCity(City city) {
        citySet.remove(city);
    }

    @Override
    public String toString() {
        return "\n<Country Information>" +
                "\nCountry Code: " + code +
                "\nName: " + name +
                "\nContinent: " + continent +
                "\nRegion: " + region +
                "\nPopulation: " + population +
                "\n<Capital City Information>" + capital;
    }
}


