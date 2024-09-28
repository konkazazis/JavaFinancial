import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;

public class LoanCalculator {
    public VBox createLoanCalculatorLayout() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().add(new Label("Loan Calculator: Under Construction"));
        return layout;
    }
}
