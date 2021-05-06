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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "capital")
    private City capital;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private Set<City> citySet;

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


