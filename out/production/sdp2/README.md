# Logistics & Cross-Platform UI Application

 Begaliev Bakdaulet  
**Group:** SE-2526  
**Repository:** https://github.com/bakdauletbegaliev/factorymethod

---

## 📌 Project Purpose

This project is a Java console application that demonstrates the simultaneous application of two creational design patterns:
1. **Factory Method:** Used for creating logistics transports (Road / Sea) and running a unified delivery plan workflow.
2. **Abstract Factory:** Used for generating cross-platform UI component families (Windows / macOS) containing buttons and checkboxes.

The application allows choosing the delivery mode and UI platform at runtime via command-line arguments or interactive console input without editing any existing code.

---

## 📁 Package Structure

```text
sdp2/
├── app/
│   ├── DeliveryApplication.java  # Client class working purely with abstractions
│   └── Main.java                 # Entry point, CLI argument parsing, and input validation
├── factory/
│   ├── GUIFactory.java           # Abstract Factory interface for UI components
│   ├── MacOSFactory.java         # Concrete factory for macOS UI family
│   └── WindowsFactory.java       # Concrete factory for Windows UI family
├── logistics/
│   ├── Logistics.java            # Creator declaring factory method and planDelivery()
│   ├── RoadLogistics.java        # Concrete creator for road transport
│   └── SeaLogistics.java         # Concrete creator for sea transport
├── transport/
│   ├── Transport.java            # Product interface for delivery vehicles
│   ├── Truck.java                # Concrete product for road delivery
│   └── Ship.java                 # Concrete product for sea delivery
└── ui/
    ├── Button.java               # Abstract product interface for buttons
    ├── Checkbox.java             # Abstract product interface for checkboxes
    ├── MacOSButton.java          # Concrete product (macOS button)
    ├── MacOSCheckbox.java        # Concrete product (macOS checkbox)
    ├── WindowsButton.java        # Concrete product (Windows button)
    └── WindowsCheckbox.java      # Concrete product (Windows checkbox)