package experis.lessons.jdbc;

import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Select action:
                    1. print table
                    2. search in table by country name
                    0. exit
                    """);
            switch (sc.nextLine()) {
                case "1": {
                    DB.searchCountries(null);
                    break;
                }
                case "2": {
                    System.out.println("Write search string: ");
                    String searchString = sc.nextLine();

                    DB.searchCountries(searchString);
                    break;
                }
                case "0": {
                    return;
                }
            }
        }
    }
}
