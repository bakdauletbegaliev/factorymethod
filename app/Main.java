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

        if (args.length >= 2) {
            deliveryMode = args[0];
            platform = args[1];
        } else {
            Scanner scanner = new Scanner(System.in);
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

        DeliveryApplication app = new DeliveryApplication(guiFactory, logistics);
        app.run("laboratory equipment", "Aktau warehouse");
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