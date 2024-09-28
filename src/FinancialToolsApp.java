import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class FinancialToolsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create TabPane to hold different financial tools
        TabPane tabPane = new TabPane();
        Tab expenseTrackerTab = new Tab("Expense Tracker", new ExpenseTracker().createExpenseTrackerLayout());
        expenseTrackerTab.setClosable(false);
        Tab savingsCalculatorTab = new Tab("Savings Calculator", new SavingsCalculator().createSavingsCalculatorLayout());
        savingsCalculatorTab.setClosable(false);
        Tab loanCalculatorTab = new Tab("Loan Calculator", new LoanCalculator().createLoanCalculatorLayout());
        loanCalculatorTab.setClosable(false);
        Tab expenseReportTab = new Tab("Expense Report", new ExpenseReport().createExpenseReportLayout());
        expenseReportTab.setClosable(false);
        tabPane.getTabs().addAll(expenseTrackerTab, savingsCalculatorTab, loanCalculatorTab, expenseReportTab);

        // Set the scene and show the stage
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Financial Tools");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
