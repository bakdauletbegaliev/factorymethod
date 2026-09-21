package app;

import factory.GUIFactory;
import factory.MacOSFactory;
import factory.WindowsFactory;
import logistics.Logistics;
import logistics.RoadLogistics;
import logistics.SeaLogistics;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String deliveryMode;
        String platform;
        Scanner scanner = new Scanner(System.in);

        if (args.length >= 2) {
            deliveryMode = args[0];
            platform = args[1];
        } else {
            System.out.print("Enter delivery mode (ROAD / SEA): ");
            deliveryMode = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

            System.out.print("Enter UI platform (WINDOWS / MACOS): ");
            platform = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        }

        if (deliveryMode.isEmpty() || platform.isEmpty()) {
            System.out.println("Error: Input mode and platform cannot be empty.");
            return;
        }

        Logistics logistics = createLogistics(deliveryMode);
        GUIFactory guiFactory = createGUIFactory(platform);

        if (logistics == null) {
            System.out.println("Error: Unsupported delivery mode '" + deliveryMode + "'. Use ROAD or SEA.");
            return;
        }

        if (guiFactory == null) {
            System.out.println("Error: Unsupported UI platform '" + platform + "'. Use WINDOWS or MACOS.");
            return;
        }

        System.out.print("Enter cargo (e.g. electronics): ");
        String cargo = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

        System.out.print("Enter destination (e.g. Barcelona): ");
        String destination = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

        if (cargo.isEmpty()) {
            cargo = "electronics";
        }
        if (destination.isEmpty()) {
            destination = "Barcelona";
        }

        DeliveryApplication app = new DeliveryApplication(guiFactory, logistics);
        app.run(cargo, destination);
    }

    private static Logistics createLogistics(String mode) {
        return switch (mode.toUpperCase()) {
            case "ROAD" -> new RoadLogistics();
            case "SEA" -> new SeaLogistics();
            default -> null;
        };
    }

    private static GUIFactory createGUIFactory(String platform) {
        return switch (platform.toUpperCase()) {
            case "WINDOWS" -> new WindowsFactory();
            case "MACOS" -> new MacOSFactory();
            default -> null;
        };
    }
}