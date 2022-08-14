public class City implements CityInterface, Comparable<City> {

    public int ID;
    public String name;
    public int population;
    public int CovidCases;

    public City(int ID, String name, int population, int CovidCases) {
        setID(ID);
        setName(name);
        setPopulation(population);
        setCovidCases(CovidCases);
    }


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPopulation() {
        return population;
    }

    @Override
    public int getCovidCases() {
        return CovidCases;
    }

    @Override
    public void setID(int ID) {
        if (ID>0 && ID<1000) {
            this.ID = ID;
        }else{
            System.out.println("Error!");
            System.exit(0);
        }
    }

    @Override
    public void setName(String name) {
        if (name.length()<51) {
            this.name = name;
        }else{
            System.out.println("Error!");
            System.exit(0);
        }
    }

    @Override
    public void setPopulation(int population) {
        if (population>=0 && population<=10000000) {
            this.population = population;
        }else{
            System.out.println("Error!");
            System.exit(0);
        }
    }

    @Override
    public void setCovidCases(int CovidCases) {
        if (CovidCases<population) {
            this.CovidCases = CovidCases;
        }else{
            System.out.println("Error!");
            System.exit(0);
        }
    }

    @Override
    public int compareTo(City City) {
        double a=Covid_k.calculateDensity(CovidCases,population);
        double b=Covid_k.calculateDensity(City.CovidCases,City.population);
        if(a == b){
            return 0;
        }else if(a>b){
            return 1;
        }else{
            return -1;
        }
    }

}
