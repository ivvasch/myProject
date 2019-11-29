package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        try {
            File yourFile = new File("C:\\Users\\User01\\.IdeaIC2018.3\\JavaRushTasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2001\\File1");
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            User user = new User();
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setCountry(User.Country.UKRAINE);
            user.setMale(true);
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse("25-11-1981");
            user.setBirthDate(date);
            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            if (javaRush.equals(loadedObject)){
                System.out.println(true);
            }
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }
    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            if (!users.isEmpty()) {
                for (User us : users) {
                    if (us.getFirstName() != null) writer.println(us.getFirstName());
                    if (us.getLastName() != null) writer.println(us.getLastName());
                    writer.println(us.isMale());
                    if (us.getBirthDate() != null) writer.println(us.getBirthDate().getTime());
                    if (us.getCountry() != null) writer.println(us.getCountry());
                }
                writer.flush();
            }
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                User user2 = new User();
                String firstName = reader.readLine();
                String lastName = reader.readLine();
                boolean male = Boolean.parseBoolean(reader.readLine());
                long dateTime = Long.parseLong(reader.readLine());
                String country = reader.readLine();
                user2.setFirstName(firstName);
                user2.setLastName(lastName);
                user2.setMale(male);
                user2.setBirthDate(new Date(dateTime));
                if (country.equals("UKRAINE")) user2.setCountry(User.Country.UKRAINE);
                if (country.equals("RUSSIA")) user2.setCountry(User.Country.RUSSIA);
                if (country.equals("OTHER")) user2.setCountry(User.Country.OTHER);
                int countUser = 0;
                for (User newUser : users) {
                    if (user2.equals(newUser)) {
                        countUser = countUser++;
                    }
                }
                if (countUser == 0) {
                    this.users.add(user2);
                }
            }
            reader.close();

            for (User usRead: users) {
                System.out.println(usRead.getFirstName() + " + "
                        + usRead.getLastName() + " + "
                + usRead.isMale() + " + "
                + usRead.getBirthDate() + " + "
                + usRead.getCountry());
            }
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;
        }
        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
