import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Covid_k {
    public static double calculateDensity(int covidcases, int population){
        double newcases=(50000.00*covidcases)/population;
        return Math.round(newcases*100.00)/100.00;
    }
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Give name of the file: ");
        String filename = sc1.nextLine();
        int m = 0;
        QuickSort_using_Doubly_LinkedList queue= new QuickSort_using_Doubly_LinkedList();
        try {
            File myObj = new File(String.valueOf(filename));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                m += 1;
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
                        cases=Integer.parseInt(line.nextToken());
                    } else {
                        if (i == 1) {
                            name = line.nextToken();
                        } else {
                            name += (" " + line.nextToken());
                        }
                    }
                }
                QuickSort_using_Doubly_LinkedList.Node city = new QuickSort_using_Doubly_LinkedList.Node(new City(id, name, population, cases));
                queue.push(city.getData());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the name of the file.");
            System.exit(0);
        }
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Give number of cities you want to learn the Covid cases of: ");
        int number = sc2.nextInt();
        if (m < number) {
            System.out.println("Error, the file doesn't have so many cities!");
            System.exit(0);
        }
        System.out.println("The top "+ number +" cities are: ");
        queue.quickSort(queue.head);
        for(int i=1;i<=number; i++){
            String x=queue.tail.getData().name;
            System.out.println(x);
            queue.tail=queue.tail.prev;
        }
    }
}