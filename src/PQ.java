import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PQ{
    public static City[] queue;
    private static int size;
    private static int number;

    public PQ(int number) {
        this.number=number;
        this.queue = new City[number *2];
        this.size = 0;
    }
    public static int getNumber() {
        return number;
    }

    public static boolean isEmpty(){
        return size==0;
    }

    public static double calculateDensity(int covidcases, int population){
        double newcases=(50000.00*covidcases)/population;
        return Math.round(newcases*100.00)/100.00;
    }

    public static int size(){
        return size;
    }
    public static City max(){
        // Ensure not empty
        if (isEmpty())
            return null;
        return queue[1];
    }
    public void insert(City x) {
        // Check available space
        if (size>=75/100*queue.length)
            resize();
        // Place item at the next available position
        queue[++size] = x;
        // Let the newly added item swim
        swim(size);
    }

    public static City getMax() {
        // Ensure not empty
        if (isEmpty())
            return null;

        // Keep a reference to the root item
        City root = queue[1];

        // Replace root item with the one at rightmost leaf
        queue[1] = queue[size];
        size--;

        // Dispose the rightmost leaf
        // Sink the new root element
        sink(1);

        // Return the int removed
        return root;
    }

    public static int TakeNumber(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Give number of cities you want to learn the Covid cases of: ");
        int number = sc2.nextInt();
        return number;
    }


    public static void swim(int i) {
        // if i is root (i==1) return
        if (i == 1)
            return;

        // find parent
        int parent = i / 2;

        // compare parent with child i
        while (i != 1 && compareTo2(queue[i], queue[parent]) > 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }


    private static void sink(int i) {
        // determine left, right child
        int left = 2 * i;
        int right = left + 1;

        // if 2*i > size, node i is a leaf return
        if (left > size)
            return;

        // while haven't reached the leafs
        while (left <= size) {
            // Determine the largest child of node i
            int max = left;
            if (right <= size) {
                if (compareTo2(queue[left], queue[right]) < 0)
                    max = right;
            }

            // If the queue condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (compareTo2(queue[i], queue[max]) >= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    public static City remove(int id){
        City city=null;
        if(!(isEmpty())){
            for(int i=1; i<=size; i++){
                if(id==queue[i].ID) {
                    city=queue[i];
                    swap(i,size);
                    queue[size]=null;
                    size--;
                    swim(i);
                }
            }
        }
        return city;
    }

    private static void swap(int i, int j) {
        City tmp = queue[i];
        queue[i] = queue[j];
        queue[j] = tmp;
    }

    private void resize() {
        City[] newHeap =new City[queue.length*2];
        System.arraycopy(queue, 1, newHeap, 1, size);
        queue = newHeap;
    }

    public static void main(String args[]){
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Give name of the file: ");
        String filename = sc1.nextLine();
        try {
            File myObj = new File(String.valueOf(filename));
            Scanner myReader = new Scanner(myObj);
            int number=PQ.TakeNumber();
            PQ list=new PQ(number);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer line = new StringTokenizer(data, " ");
                int id = 0;
                String name = "";
                int population = 0;
                int cases = 0;
                int count = line.countTokens();
                for (int i = 0; i < count; i++) {
                    if (i == 0) {
                        id = Integer.parseInt(line.nextToken());
                    } else if (i == (count - 2)) {
                        population = Integer.parseInt(line.nextToken());
                    } else if (i == (count - 1)) {
                        cases= Integer.parseInt(line.nextToken());
                    } else {
                        if (i == 1) {
                            name = line.nextToken();
                        } else {
                            name += (" " + line.nextToken());
                        }
                    }
                }
                City city=new City(id, name, population, cases);
                list.insert(city);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the name of the file.");
            System.exit(0);
        }
        if (size < number) {
            System.out.println("The file doesn't have so many cities!");
            System.exit(0);
        }
        if(number==1) {
            System.out.println("The top 1 city is: ");
        }else {
            System.out.println("The top " + number + " cities are: ");
        }
        for(int i=1;i<=number; i++){
            City city =getMax();
            System.out.println(city.name);
        }
    }

    public static int compareTo2(City obj1, City obj2) {
        double a=PQ.calculateDensity(obj1.CovidCases,obj1.population);
        double b=PQ.calculateDensity(obj2.CovidCases,obj2.population);
        if(a == b){
            if(obj1.name==obj2.name){
                if(obj1.ID<obj2.ID){
                    return -1;
                }else{return 1;}
            }else{return 1;}
        }else if(a>b){
            return 1;
        }else{
            return -1;
        }
    }
}