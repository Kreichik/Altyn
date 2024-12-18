import java.util.Scanner;

public class FitnessApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int age = getValidIntInput(scanner, "Enter your age: ");
        double weight = getValidDoubleInput(scanner, "Enter your weight: ");

        User user = new User(name, age, weight);

        while (true) {
            System.out.println("\nWelcome to FitnessApp!");
            System.out.println("1. Manage WorkoutRoutine");
            System.out.println("2. Manage User");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidIntInput(scanner, "");

            switch (choice) {
                case 1:
                    manageWorkoutRoutine(scanner, user);
                    break;
                case 2:
                    manageUser(scanner, user);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageWorkoutRoutine(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nWorkoutRoutine Menu:");
            System.out.println("1. Add Routine");
            System.out.println("2. Remove Routine");
            System.out.println("3. View All Routines");
            System.out.println("4. Compare Two Routines");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = getValidIntInput(scanner, "");

            switch (choice) {
                case 1:
                    System.out.print("Enter routine name: ");
                    String routineName = scanner.nextLine();
                    int duration = getValidIntInput(scanner, "Enter duration (minutes): ");
                    int calories = getValidIntInput(scanner, "Enter calories burned: ");

                    user.addRoutine(new WorkoutRoutine(routineName, duration, calories));
                    System.out.println("Routine added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the name of the routine to remove: ");
                    String nameToRemove = scanner.nextLine();
                    user.removeRoutine(nameToRemove);
                    System.out.println("Routine removed successfully.");
                    break;
                case 3:
                    System.out.println("\nAll Routines:");
                    if (user.getRoutines().isEmpty()) {
                        System.out.println("No routines found.");
                    } else {
                        user.getRoutines().forEach(System.out::println);
                    }
                    break;
                case 4:
                    compareTwoRoutines(scanner, user);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void compareTwoRoutines(Scanner scanner, User user) {
        if (user.getRoutines().size() < 2) {
            System.out.println("Not enough routines to compare. Please add more routines first.");
            return;
        }

        System.out.println("\nAvailable Routines:");
        for (int i = 0; i < user.getRoutines().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, user.getRoutines().get(i).getRoutineName());
        }

        int firstChoice = getValidIntInput(scanner, "Select the first routine (number): ") - 1;
        int secondChoice = getValidIntInput(scanner, "Select the second routine (number): ") - 1;

        if (firstChoice < 0 || firstChoice >= user.getRoutines().size() ||
                secondChoice < 0 || secondChoice >= user.getRoutines().size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        WorkoutRoutine routine1 = user.getRoutines().get(firstChoice);
        WorkoutRoutine routine2 = user.getRoutines().get(secondChoice);

        System.out.println("\nComparison:");
        System.out.printf("%s burned %.2f calories per minute.\n", routine1.getRoutineName(), routine1.getCaloriesPerMinute());
        System.out.printf("%s burned %.2f calories per minute.\n", routine2.getRoutineName(), routine2.getCaloriesPerMinute());

        if (routine1.getCaloriesBurned() > routine2.getCaloriesBurned()) {
            System.out.printf("%s burned more calories overall (%d kcal) compared to %s (%d kcal).\n",
                    routine1.getRoutineName(), routine1.getCaloriesBurned(),
                    routine2.getRoutineName(), routine2.getCaloriesBurned());
        } else if (routine1.getCaloriesBurned() < routine2.getCaloriesBurned()) {
            System.out.printf("%s burned more calories overall (%d kcal) compared to %s (%d kcal).\n",
                    routine2.getRoutineName(), routine2.getCaloriesBurned(),
                    routine1.getRoutineName(), routine1.getCaloriesBurned());
        } else {
            System.out.println("Both routines burned the same amount of calories.");
        }
    }

    private static void manageUser(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View User Info");
            System.out.println("2. Update User Info");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = getValidIntInput(scanner, "");

            switch (choice) {
                case 1:
                    System.out.println(user);
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    int newAge = getValidIntInput(scanner, "Enter new age: ");
                    double newWeight = getValidDoubleInput(scanner, "Enter new weight: ");

                    user.setName(newName);
                    user.setAge(newAge);
                    user.setWeight(newWeight);

                    System.out.println("User info updated successfully.");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getValidIntInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner, String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
