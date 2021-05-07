package world;

import world.domain.City;
import world.domain.Country;

import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DAO {

    Scanner input = new Scanner(System.in);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();


    public void findCity(String name) {

        Query query = em.createQuery("SELECT c FROM City c WHERE c.name =:name");
        query.setParameter("name", name);

        @SuppressWarnings("unchecked")
        List<City> cityList = (List<City>) query.getResultList();

        System.out.printf("Found %d matches for %s\n", cityList.size(), name);

        for (City c : cityList) {
            System.out.println("\nName: " + c.getName() + "\nPopulation: " + c.getPopulation() + "\nLocated in: " + c.getCountry().getName());
        }
    }

    public void findCountry(String name) {

        Query query = em.createQuery("SELECT c FROM Country  c WHERE c.name=:name");
        query.setParameter("name", name);

        @SuppressWarnings("unchecked")
        List<Country> countryList = (List<Country>) query.getResultList();

        System.out.printf("Found %d matches for %s\n", countryList.size(), name);

        for (Country c : countryList) {
            System.out.println("\nName: " + c.getName() + "\nContinent: " + c.getContinent()
                    + "\nPopulation: " + c.getPopulation() + "\nCapital City: " + c.getCapital().getName());
        }

        System.out.println("=========================");
        System.out.println("Display ALL cities in " + name + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("=========================");
        System.out.print("Input: ");

        int choice = scanInt();

        if (choice == 1) {

            try {

                Country c = (Country) query.getSingleResult();
                for (City city : c.getCitySet()) {

                    System.out.println(city.getName());

                }

                System.out.println("<End Of List>");

            } catch (Exception error) {
                System.out.println("<Invalid Country!>");
            }

        } else if (choice == 2) {

            System.out.println("<Returning To main Menu>");

        } else {
            System.out.println("<Invalid Input!>");
        }

    }

    public void createCountry() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Enter Name Of New Country: ");
        String name = input.nextLine();

        System.out.print("Enter Country Code: ");
        String code = input.nextLine();

        System.out.print("Enter Pop: ");
        int pop = scanInt();

        System.out.print("Enter Region: ");
        String region = input.nextLine();

        String continent = "null";

        boolean loop = true;
        while (loop) {

            System.out.println("<Please Choose Continent>");
            System.out.println("1. Europe");
            System.out.println("2. Asia");
            System.out.println("3. North America");
            System.out.println("4. Africa");
            System.out.println("5. Antarctica");
            System.out.println("6. Oceania");
            System.out.println("7. South America");


            int choice = scanInt();

            switch (choice) {

                case 1:
                    continent = ("Europe");
                    break;
                case 2:
                    continent = ("Asia");
                    break;
                case 3:
                    continent = ("North America");
                    break;
                case 4:
                    continent = ("Africa");
                    break;
                case 5:
                    continent = ("Antarctica");
                    break;
                case 6:
                    continent = ("Oceania");
                    break;
                case 7:
                    continent = ("South America");
                    break;
                default: {
                    System.out.println("<Invalid Input!>");
                    break;
                }
            }

            loop = false;


        }

        Country newCountry = new Country(code, name, continent, region, pop);

        Country countryCheck = em.find(Country.class, name);

        if (countryCheck != null) {

            em.getTransaction().begin();
            em.merge(newCountry);
            em.getTransaction().commit();

        } else {
            try {
                em.getTransaction().begin();
                em.persist(newCountry);
                em.getTransaction().commit();

            } catch (Exception error) {
                em.getTransaction().rollback();
                System.out.println("Could not add Country to Database");
            }
        }


    }

    public void updateCountry() {

        EntityManager em = emf.createEntityManager();

        System.out.print("CODE Of Country To Update: ");
        String countryCode = input.nextLine();

        Country updatedCountry = em.find(Country.class, countryCode);

        System.out.println("<Edit Menu>");
        System.out.println("=========================");
        System.out.println("1. Edit Name");
        System.out.println("2. Edit Continent");
        System.out.println("3. Edit Region");
        System.out.println("4. Edit Population");
        System.out.println("5. Add Capital");
        System.out.println("0. Exit");
        System.out.println("=========================");
        System.out.print("Input: ");

        int choice = scanInt();

        switch (choice) {
            case 1: {
                System.out.print("Enter New Name: ");
                updatedCountry.setName(input.nextLine());
            }
            break;

            case 2: {
                System.out.print("Enter New Continent: ");

                boolean loop = true;
                while (loop) {

                    System.out.println("<Please Choose Continent>");
                    System.out.println("1. Europe");
                    System.out.println("2. Asia");
                    System.out.println("3. North America");
                    System.out.println("4. Africa");
                    System.out.println("5. Antarctica");
                    System.out.println("6. Oceania");
                    System.out.println("7. South America");


                    choice = scanInt();

                    switch (choice) {

                        case 1:
                            updatedCountry.setContinent("Europe");
                            break;
                        case 2:
                            updatedCountry.setContinent("Asia");
                            break;
                        case 3:
                            updatedCountry.setContinent("North America");
                            break;
                        case 4:
                            updatedCountry.setContinent("Africa");
                            break;
                        case 5:
                            updatedCountry.setContinent("Antarctica");
                            break;
                        case 6:
                            updatedCountry.setContinent("Oceania");
                            break;
                        case 7:
                            updatedCountry.setContinent("South America");
                            break;
                        default: {
                            System.out.println("<Invalid Input!>");
                            break;
                        }
                    }

                    loop = false;

                }

            }
            break;


            case 3: {
                System.out.print("Enter New Region: ");
                updatedCountry.setRegion(input.nextLine());

            }
            break;

            case 4: {
                System.out.print("Enter New Population: ");
                updatedCountry.setPopulation(scanInt());
            }
            break;

            case 5: {

                System.out.println("Enter Name Of New Capital: ");
                String name = input.nextLine();

                System.out.println("Enter Population: ");
                int pop = scanInt();

                System.out.println("Enter District Name: ");
                String district = input.nextLine();

                City newCapital = new City();
                newCapital.setDistrict(district);
                newCapital.setPopulation(pop);
                newCapital.setName(name);
                newCapital.setCountry(updatedCountry);

                updatedCountry.setCapital(newCapital);
            }
            break;

            case 0:
                System.exit(0);

            default:
                System.out.println("<Invalid Input!>");
        }


        em.getTransaction().begin();
        em.merge(updatedCountry);
        em.getTransaction().commit();
        em.close();

    }

    public void deleteCountry() {

        System.out.println("<You may ONLY Delete CUSTOM Cities>");
        System.out.print("Input CODE Of Country to NUKE: ");
        System.out.println("<WARNING: THIS WILL NUKE ALL CITIES IN THIS COUNTRY>");
        String code = input.nextLine();

        em.getTransaction().begin();
        Country c = em.find(Country.class, code);

        em.remove(c);
        em.getTransaction().commit();

    }

    public int scanInt() {

        int input;

        while (true) {
            try {
                input = this.input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please input numerical data");
                this.input.nextLine();
            }
        }
        this.input.nextLine();
        return input;
    }

}
