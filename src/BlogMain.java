import exception.PostNotFoundException;
import model.Post;
import model.User;
import storage.Commands;
import storage.PostStorageImpl;
import storage.UserStorageImpl;


import java.util.Scanner;

public class BlogMain implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static PostStorageImpl postStorage = new PostStorageImpl();
    private static UserStorageImpl userStorage = new UserStorageImpl();
    private static User usermain;

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            System.out.println("REGISTER" + REGISTER);
            System.out.println("LOGIN" + LOGIN);
            int commands;
            try {
                commands = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
                commands = -1;
            }
            boolean isRun2 = true;
            while (isRun2) {
                switch (commands) {
                    case REGISTER:
                        if (usermain == null) {
                            try {
                                addUser();
                            } catch (ArrayIndexOutOfBoundsException e) {
                                addUser();
                            }
                        }
                        prindCommands();
                        int commands2;
                        try {
                            commands2 = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            commands2 = -1;
                        }


                        switch (commands2) {
                            case ADD_POST:
                                addPost();
                                break;
                            case POSTS_BY_CATEGORY:
                                String category = scanner.nextLine();
                                postStorage.printPostsByCategory(category);
                                break;
                            case ALL_POSTS:
                                postStorage.printAllPosts();
                                break;
                            case SEARCH_BY_TITLE:
                                searchByTitle();
                                break;
                            case SEARCH_POST:
                                searchPost();
                                break;
                            case LOGOUT:
                                //  isRun2 = false;
                                usermain = null;
                                System.out.println(" the choice ");
                                break;
                            case EXIT:
                                isRun = false;
                                System.out.println(" Come again ");
                                break;
                            default:
                                System.out.println(" you write fault ");
                        }


                    case LOGIN:
                        try {
                            login();
                        } catch (ArrayIndexOutOfBoundsException a) {
                            login();
                        } catch (IndexOutOfBoundsException e) {
                            login();
                        }
                        prindCommands();
                        int commands3;
                        try {
                            commands3 = Integer.parseInt(scanner.nextLine());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            commands3 = -1;
                        }


                        switch (commands3) {
                            case ADD_POST:
                                addPost();
                                break;
                            case SEARCH_POST:
                                searchPost();
                                break;
                            case POSTS_BY_CATEGORY:
                                String category = scanner.nextLine();
                                postStorage.printPostsByCategory(category);
                                break;
                            case ALL_POSTS:
                                postStorage.printAllPosts();
                                break;
                            case SEARCH_BY_TITLE:
                                searchByTitle();
                                break;
                            case LOGOUT:
                                usermain = null;
                                System.out.println(" select ");
                                break;
                            case EXIT:
                                isRun = false;
                                System.out.println(" Come again ");
                                break;
                            default:
                                System.out.println(" you write fault ");
                        }
                    default:
                        System.out.println(" you write fault ");
                        isRun2 = false;
                }
            }
        }
    }


    private static void addUser() {
        System.out.println("Please input name ,surname, email, password ");
        String newUser = scanner.nextLine();
        String[] userData = newUser.split(",");
        User user = new User();
        user.setName(userData[0]);
        user.setSurname(userData[1]);
        user.setEmail(userData[2]);
        user.setPassword(userData[3]);
        usermain = user;
        userStorage.add(user);
        System.out.println("your added of user");
    }

    private static void login() {

        try {
            System.out.println("Please input email, passwords ");

            String emailPas = scanner.nextLine();
            String[] emailAndPasswords = emailPas.split(",");
            User user1 = userStorage.login(emailAndPasswords[0], emailAndPasswords[1]);
            if (user1 == null) {
                addUser();
            }
            usermain = user1;
        } catch (ArrayIndexOutOfBoundsException e) {
            login();
        }
    }

    private static void searchByTitle() {
        try {
            String title = scanner.nextLine();
            System.out.println(postStorage.getPostByTitle(title));
        } catch (PostNotFoundException e) {
            System.out.println("wrong name");
            searchByTitle();
        }
    }

    private static void searchPost() {
        System.out.println("write please");
        String Keyword = scanner.nextLine();
        postStorage.searchPostsByKeyword(Keyword);
    }

    private static void addPost() {
        try {
            System.out.println("title, text, category");
            String post = scanner.nextLine();
            String[] postMember = post.split(",");
            Post post1 = new Post();
            post1.setTitle(postMember[0]);
            post1.setText(postMember[1]);
            post1.setCategory(postMember[2]);
            post1.setUser(usermain);
            postStorage.add(post1);
            System.out.println(" your post has been added ");

        } catch (NumberFormatException e) {
            System.out.println(" or added a lot or a little ");
            addPost();
        }

    }

    private static void prindCommands() {
        System.out.println("for EXIT " + EXIT);
        System.out.println("for ADD POST " + ADD_POST);
        System.out.println("SEARCH POST " + SEARCH_POST);
        System.out.println("POSTS BY CATEGORY " + POSTS_BY_CATEGORY);
        System.out.println("ALL POSTS " + ALL_POSTS);
        System.out.println("SEARCH BY TITLE " + SEARCH_BY_TITLE);
    }
}
