import com.mjc.school.controller.implementation.AuthorController;
import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan("com.mjc.school")
public class MainApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NewsController newsController = new NewsController();
        AuthorController authorController = new AuthorController();
        boolean status = true;
        while (status) {
            System.out.println("click for performing \n" +
                    "1 -> Create News\n" +
                    "2 -> Create Author\n" +
                    "3 -> All News\n" +
                    "4 -> All Authors\n" +
                    "5 -> Get News by id\n" +
                    "6 -> Get Author by id\n" +
                    "7 -> Update news \n" +
                    "8 -> Update author \n" +
                    "9 -> Delete news by id\n" +
                    "10 -> Delete author by id\n" +
                    "11 -> Exit");
            int numOfFunction = sc.nextInt();
            switch (numOfFunction) {
                case 1 -> {
                    sc.nextLine();
                    System.out.println("Enter news title");
                    String title = sc.nextLine();
                    System.out.println("Enter news content");
                    String content = sc.nextLine();
                    System.out.println("Enter news authorId");
                    Long authorId = sc.nextLong();
                    NewsDtoRequest newsDto = new NewsDtoRequest(null , title, content, authorId);
                    newsController.create(newsDto);
                }
                case 2 -> {
                    sc.nextLine();
                    System.out.println("Enter author name");
                    String name = sc.nextLine();
                    AuthorDtoRequest authorDto = new AuthorDtoRequest(null, name);
                    authorController.create(authorDto);
                }
                case 3 -> {
                    newsController.readAll().forEach(System.out::println);

                }
                case 4 -> {
                    authorController.readAll().forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Enter news id : ");
                    Long id = sc.nextLong();
                    System.out.println(newsController.readById(id));
                }
                case 6 -> {
                    System.out.println("Enter author id : ");
                    Long id = sc.nextLong();
                    System.out.println(authorController.readById(id));
                }
                case 7 -> {
                    sc.nextLine();
                    System.out.print("Enter updated title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter updated content: ");
                    String content = sc.nextLine();
                    System.out.print("Enter updated author ID: ");
                    Long authorId = sc.nextLong();
                    NewsDtoRequest newsDto = new NewsDtoRequest(null, title, content, authorId);
                    System.out.println(newsController.update(newsDto));
                }
                case 8 -> {
                    sc.nextLine();
                    System.out.print("Enter author name: ");
                    String name = sc.nextLine();
                    AuthorDtoRequest authorDto = new AuthorDtoRequest(null, name);
                    System.out.println(authorController.update(authorDto));
                }
                case 9 -> {
                    System.out.println("Enter news id : ");
                    Long id = sc.nextLong();
                    System.out.println(newsController.deleteById(id));
                }
                case 10 -> {
                    System.out.println("Enter author id : ");
                    Long id = sc.nextLong();
                    System.out.println(authorController.deleteById(id));
                }
                case 11 -> {
                    status = false;
                }
                default ->
                        System.out.println("have done .... :):");
            }
        }
        sc.close();
    }
}
