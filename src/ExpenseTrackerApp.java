import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp extends Application {

    // List to store expenses
    private List<Expense> expenses = new ArrayList<>();

    // UI elements
    private TextField descriptionField;
    private TextField amountField;
    private DatePicker datePicker;
    private ListView<String> expenseListView;
    private Label totalExpenseLabel;

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        descriptionField = new TextField();
        amountField = new TextField();
        datePicker = new DatePicker();
        Button addButton = new Button("Add Expense");

        expenseListView = new ListView<>();
        totalExpenseLabel = new Label("Total Expense: $0.00");

        // Add event handler for the "Add Expense" button
        addButton.setOnAction(e -> addExpense());

        // Layout for the form
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Description:"), 0, 0);
        form.add(descriptionField, 1, 0);
        form.add(new Label("Amount:"), 0, 1);
        form.add(amountField, 1, 1);
        form.add(new Label("Date:"), 0, 2);
        form.add(datePicker, 1, 2);
        form.add(addButton, 1, 3);

        // Main layout (VBox to stack form, expense list, and total)
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(15));
        mainLayout.getChildren().addAll(form, new Label("Expenses:"), expenseListView, totalExpenseLabel);

        // Set the scene and show the stage
        Scene scene = new Scene(mainLayout, 400, 400);
        primaryStage.setTitle("Expense Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add an expense
    // Method to add an expense
    private void addExpense() {
        double amount = 0.0; // Declare amount variable

        try {
            // Get description
            String description = descriptionField.getText();
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty.");
            }

            // Get date
            LocalDate date = datePicker.getValue();
            if (date == null) {
                throw new IllegalArgumentException("Please select a valid date.");
            }

            // Get and parse the amount
            if (amountField.getText() == null || amountField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Amount is empty. Please enter a valid number.");
            }
            amount = Double.parseDouble(amountField.getText());

            // Create an expense and add it to the list
            Expense expense = new Expense(description, amount, date);
            expenses.add(expense);

            // Update UI
            updateExpenseList();
            updateTotalExpense();

            // Clear input fields
            descriptionField.clear();
            amountField.clear();
            datePicker.setValue(null);

            System.out.println("Added expense: " + amount);
        } catch (NumberFormatException e) {
            // Handle invalid number input
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            // Handle custom validation errors (like empty description or date)
            System.out.println(e.getMessage());
        }
    }



    // Method to update the expense list in the UI
    private void updateExpenseList() {
        expenseListView.getItems().clear();
        for (Expense expense : expenses) {
            expenseListView.getItems().add(expense.toString());
        }
    }

    // Method to update the total expense
    private void updateTotalExpense() {
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        totalExpenseLabel.setText("Total Expense: $" + String.format("%.2f", total));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
