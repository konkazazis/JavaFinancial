import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinancialToolsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create login layout
        VBox loginLayout = createLoginLayout(primaryStage);

        // Set initial scene to the login layout with dimensions 800x600
        Scene loginScene = new Scene(loginLayout, 800, 600);
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Create the login layout
    private VBox createLoginLayout(Stage primaryStage) {
        // Create a GridPane for better control over layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);  // Horizontal gap between columns
        gridPane.setVgap(10);  // Vertical gap between rows
        gridPane.setAlignment(Pos.CENTER);  // Center the entire grid

        // Username label and field
        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        userTextField.setPrefWidth(300);  // Set preferred width for username field

        // Password label and field
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        passField.setPrefWidth(300);  // Set preferred width for password field

        // Add username and password to the grid
        gridPane.add(userLabel, 0, 0);       // Add user label to column 0, row 0
        gridPane.add(userTextField, 1, 0);   // Add user text field to column 1, row 0
        gridPane.add(passLabel, 0, 1);       // Add pass label to column 0, row 1
        gridPane.add(passField, 1, 1);       // Add pass field to column 1, row 1

        // Login button and message
        Button loginButton = new Button("Login");
        Label loginMessage = new Label();
        gridPane.add(loginButton, 1, 2);     // Add login button to column 1, row 2 (below the password field)

        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passField.getText();

            // Simple credential check (you can replace this with your own logic)
            if (isValidCredentials(username, password)) {
                // If valid, switch to the main financial tools screen
                showFinancialToolsScreen(primaryStage);
            } else {
                loginMessage.setText("Invalid username or password. Please try again.");
            }
        });

        // Create VBox layout and set alignment, padding, and spacing
        VBox loginLayout = new VBox(10, gridPane, loginMessage);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setSpacing(10);  // Add spacing between elements
        loginLayout.setAlignment(Pos.CENTER);  // Center elements within VBox

        // Set preferred size for loginLayout
        loginLayout.setPrefSize(800, 600);

        return loginLayout;
    }

    // Dummy function to check if the credentials are valid (replace with real authentication logic)
    private boolean isValidCredentials(String username, String password) {
        // Example: hardcoded valid username and password
        return username.equals("user") && password.equals("password");
    }

    // Create the main financial tools screen with TabPane after successful login
    private void showFinancialToolsScreen(Stage primaryStage) {
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

        // Set the main scene with TabPane after login
        Scene mainScene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Financial Tools");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}