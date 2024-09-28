import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {

    private List<Expense> expenses = new ArrayList<>();

    // UI elements for Expense Tracker
    private TextField descriptionField;
    private TextField amountField;
    private DatePicker datePicker;
    private ListView<String> expenseListView;
    private Label totalExpenseLabel;

    public VBox createExpenseTrackerLayout() {
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

    private void addExpense() {
        double amount = 0.0;
        try {
            String description = descriptionField.getText();
            LocalDate date = datePicker.getValue();
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

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }

    private void updateExpenseList() {
        expenseListView.getItems().clear();
        for (Expense expense : expenses) {
            expenseListView.getItems().add(expense.toString());
        }
    }

    private void updateTotalExpense() {
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        totalExpenseLabel.setText("Total Expense: $" + String.format("%.2f", total));
    }
}
