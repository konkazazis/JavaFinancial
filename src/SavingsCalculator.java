import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SavingsCalculator {

    public VBox createSavingsCalculatorLayout() {
        TextField initialDepositField = new TextField();
        TextField monthlyDepositField = new TextField();
        TextField interestRateField = new TextField();
        TextField yearsField = new TextField();

        Button calculateButton = new Button("Calculate Savings");

        Label resultLabel = new Label("Total Savings: $0.00");

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Initial Deposit ($):"), 0, 0);
        form.add(initialDepositField, 1, 0);
        form.add(new Label("Monthly Deposit ($):"), 0, 1);
        form.add(monthlyDepositField, 1, 1);
        form.add(new Label("Annual Interest Rate (%):"), 0, 2);
        form.add(interestRateField, 1, 2);
        form.add(new Label("Number of Years:"), 0, 3);
        form.add(yearsField, 1, 3);
        form.add(calculateButton, 1, 4);

        calculateButton.setOnAction(e -> {
            try {
                double initialDeposit = Double.parseDouble(initialDepositField.getText());
                double monthlyDeposit = Double.parseDouble(monthlyDepositField.getText());
                double annualInterestRate = Double.parseDouble(interestRateField.getText()) / 100;
                int years = Integer.parseInt(yearsField.getText());

                double totalSavings = calculateSavings(initialDeposit, monthlyDeposit, annualInterestRate, years);
                resultLabel.setText("Total Savings: $" + String.format("%.2f", totalSavings));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(form, resultLabel);

        return layout;
    }

    private double calculateSavings(double initialDeposit, double monthlyDeposit, double annualInterestRate, int years) {
        int n = 12;
        int t = years;

        double futureValueInitialDeposit = initialDeposit * Math.pow(1 + annualInterestRate / n, n * t);
        double futureValueMonthlyDeposits = monthlyDeposit * (Math.pow(1 + annualInterestRate / n, n * t) - 1) / (annualInterestRate / n);

        return futureValueInitialDeposit + futureValueMonthlyDeposits;
    }
}
