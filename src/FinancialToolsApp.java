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

public class FinancialToolsApp extends Application {

    // List to store expenses
    private List<Expense> expenses = new ArrayList<>();

    // UI elements for Expense Tracker
    private TextField descriptionField;
    private TextField amountField;
    private DatePicker datePicker;
    private ListView<String> expenseListView;
    private Label totalExpenseLabel;

    @Override
    public void start(Stage primaryStage) {
        // Create TabPane to hold different financial tools
        TabPane tabPane = new TabPane();

        // Expense Tracker Tab
        Tab expenseTrackerTab = new Tab("Expense Tracker", createExpenseTrackerLayout());
        expenseTrackerTab.setClosable(false); // Prevent the tab from being closed

        // Savings Calculator Tab (placeholder, we can add logic here later)
        Tab savingsCalculatorTab = new Tab("Savings Calculator", createSavingsCalculatorLayout());
        savingsCalculatorTab.setClosable(false);

        // Loan Calculator Tab (placeholder, we can add logic here later)
        Tab loanCalculatorTab = new Tab("Loan Calculator", createLoanCalculatorLayout());
        loanCalculatorTab.setClosable(false);

        // Add tabs to TabPane
        tabPane.getTabs().addAll(expenseTrackerTab, savingsCalculatorTab, loanCalculatorTab);

        // Set the scene and show the stage
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Financial Tools");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create the Expense Tracker layout
    private VBox createExpenseTrackerLayout() {
        // Create UI components for the expense tracker
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

        // Main layout for expense tracker (VBox to stack form, expense list, and total)
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(form, new Label("Expenses:"), expenseListView, totalExpenseLabel);

        return layout;
    }

    // Placeholder method to create Savings Calculator layout
    private VBox createSavingsCalculatorLayout() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().add(new Label("Savings Calculator: Under Construction"));
        // Add your savings calculator components here
        return layout;
    }

    // Placeholder method to create Loan Calculator layout
    private VBox createLoanCalculatorLayout() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().add(new Label("Loan Calculator: Under Construction"));
        // Add your loan calculator components here
        return layout;
    }

    // Method to add an expense
    private void addExpense() {
        double amount = 0.0;

        try {
            String description = descriptionField.getText();
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty.");
            }

            LocalDate date = datePicker.getValue();
            if (date == null) {
                throw new IllegalArgumentException("Please select a valid date.");
            }

            if (amountField.getText() == null || amountField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Amount is empty. Please enter a valid number.");
            }
            amount = Double.parseDouble(amountField.getText());

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
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
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


