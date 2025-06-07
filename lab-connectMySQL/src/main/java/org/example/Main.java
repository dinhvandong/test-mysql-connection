package org.example;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.daos.UserDAO;
import org.example.daos.UserDAOImpl;
import org.example.models.User;
import org.example.services.UserService;
import org.example.services.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_management_db", "root", "123456");
        UserDAO userDAO = new UserDAOImpl(conn);
        UserService userService = new UserServiceImpl(userDAO);


        User user = new User();

        System.out.print("Full name: ");
        user.setFullName(scanner.nextLine());

        System.out.print("Email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Password (min 8 chars): ");
        user.setPassword(scanner.nextLine());

        System.out.print("Phone (e.g. +1234567890): ");
        user.setPhone(scanner.nextLine());

        System.out.print("Address: ");
        user.setAddress(scanner.nextLine());

        System.out.print("Birthday (yyyy-MM-dd): ");
        try {
            String birth = scanner.nextLine();
            if (!birth.isBlank())
                user.setBirthDay(LocalDate.parse(birth));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
        }

        System.out.print("Role: ");
        user.setRole(scanner.nextLine());

        // === Validate using Jakarta Validator ===
       boolean check =  validateUser(user);

       if(check) {
         boolean checkRegister =   userService.signUp(user);
         if(checkRegister){
             System.out.println("Register successful");
         }else {
             System.out.println("Register fail");

         }
       }

        System.out.println("Login with email and password");
        System.out.print("Email: ");
        String email = (scanner.nextLine());

        System.out.print("Password (min 8 chars): ");
        String  password = (scanner.nextLine());
        boolean checkLogin =   userService.signIn(email, password);
        if(checkLogin){
            System.out.println("Login successful");
        }else {
            System.out.println("Login fail");

        }

    }

    private static boolean validateUser(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.isEmpty()) {
            System.out.println("\n✅ User input is valid!");
            System.out.println("User created: " + user.getFullName() + ", Email: " + user.getEmail());
            return true;
        } else {
            System.out.println("\n❌ Validation errors:");
            for (ConstraintViolation<User> violation : violations) {
                System.out.println("- " + violation.getPropertyPath() + ": " + violation.getMessage());
            }
            return false;
        }
    }
}