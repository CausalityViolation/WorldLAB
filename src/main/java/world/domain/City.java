package world.domain;

import javax.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String district;
    private int population;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "countrycode")
    private Country country;

    public City() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "\n<City Information>" +
                "\nCity ID: " + id +
                "\nName: " + name +
                "\nDistrict: " + district +
                "\nPopulation: " + population +
                "\n<Country with the City>" + country;
    }
}

