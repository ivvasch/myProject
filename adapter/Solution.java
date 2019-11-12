import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {
        PersonScanner personScanner = new PersonScannerAdapter(new Scanner(new File("C:\\Users\\User01\\.IdeaIC2018.3\\JavaRushTasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1904\\Data")));
        Person person = personScanner.read();
//        System.out.println(person);
        personScanner.close();
    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String line = fileScanner.nextLine();
            System.out.println(line);
            String[] parsingPerson = line.split(" ");
            String data = parsingPerson[3] + "/"+ parsingPerson[4] + "/" + parsingPerson[5];
                SimpleDateFormat dateFormat = (new SimpleDateFormat("d/MM/y", Locale.ENGLISH));
                Date date = dateFormat.parse(data);
              Person person1 = new Person(parsingPerson[1], parsingPerson[2], parsingPerson[0], date);
        return person1;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
