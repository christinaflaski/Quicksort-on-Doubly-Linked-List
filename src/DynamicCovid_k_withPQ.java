import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DynamicCovid_k_withPQ {
    private static int size;
    public static int number;

    public static int getNumber() {
        return number;
    }

    //Return the index of the min
    public static int getMin(int number) {
        int min=1;
        for(int i=1; i<number; i++){
            if(PQ.compareTo2(PQ.queue[min],PQ.queue[i])>0){
                min=i;
            }
        }
        return min;
    }


    public static void main(String args[]) {
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Give name of the file: ");
        String filename = sc1.nextLine();
        int m = 0; //counts the lines in the file
        int k = 0;
        try {
            File myObj = new File(String.valueOf(filename));
            Scanner myReader = new Scanner(myObj);
            int number = PQ.TakeNumber();
            PQ queue = new PQ(number);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer line = new StringTokenizer(data, " ");
                m += 1;
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
                        cases = Integer.parseInt(line.nextToken());
                    } else {
                        if (i == 1) {
                            name = line.nextToken();
                        } else {
                            name += (" " + line.nextToken());
                        }
                    }
                }
                City city = new City(id, name, population, cases);
                if (m <= number) {
                    queue.insert(city);
                } else {
                    int min=getMin(number);
                    if (PQ.compareTo2(city,PQ.queue[min]) > 0) {
                        int id2=PQ.queue[min].ID;
                        PQ.remove(id2);
                        queue.insert(city);
                    }
                }
            }
            myReader.close();
            k = number;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the name of the file.");
            System.exit(0);
        }
        if (size < number) {
            System.out.println("The file doesn't have so many cities!");
            System.exit(0);
        }
        System.out.println("The top " + k + " cities are: ");
        for (int j = 1; j <= k; j++) {
            City city = PQ.getMax();
            System.out.println(city.name);
        }
    }
}