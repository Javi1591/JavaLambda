# COP2251 – Java Lambda & JavaFX Project (nazariolambda)

## Summary
This JavaFX project demonstrates the use of lambda-enabled functional interfaces (`Predicate<T>`), custom domain models, and a simple GUI for filtering and displaying mutual funds. It integrates object modeling, collection management, declarative filtering via predicates, and JavaFX layout/controls, with a small stylesheet and application icon.

## Program Behavior
- MutualFund  
  - Domain model representing a fund/ETF with fields such as ticker, average holding size (in millions), minimum investment requirement, value measure, and a `MARKET` enum (e.g., DOMESTIC, GLOBAL, INTERNATIONAL).  
  - Provides standard constructors, accessors, and display-friendly formatting (via `toString()`).

- FundsDef  
  - Supplies a predefined dataset `INITIAL_FUNDS` of `MutualFund` instances, covering varied tickers, markets, and numeric attributes for demonstration and testing.

- Portfolio  
  - A simple collection wrapper that initializes an in-memory portfolio with `FundsDef.INITIAL_FUNDS`.  
  - Supports refreshing the in-memory list back to its initial state.

- FundScreenFX (JavaFX GUI)  
  - A desktop UI that displays the portfolio in a `TextArea` with controls to filter and reset data.  
  - Typical control set includes text inputs and a `ChoiceBox` to build `Predicate<MutualFund>` filters (for example, by market or numeric thresholds).  
  - The user applies filters to narrow the list; clicking a reset/refresh button reloads the original dataset.  
  - Loads `application.css` for basic styling and sets an application icon (`SP.png`).  
  - Launches as a standard JavaFX `Application`, builds a scene with layout containers, and wires button actions to filtering logic expressed with lambdas.

## Key Concepts Demonstrated
- Functional Interfaces and Lambdas: Building and composing `Predicate<MutualFund>` filters for declarative, readable logic.  
- JavaFX UI Construction: Scene graph creation, layouts (e.g., `BorderPane`, spacing, padding), controls (`TextField`, `ChoiceBox`, `Button`, `TextArea`), and event handling.  
- Data Modeling: Clean separation of concerns with `MutualFund` (model), `FundsDef` (seed data), and `Portfolio` (collection wrapper).  
- Styling and Assets: External CSS (`application.css`) and image resources (`SP.png`) integrated into the GUI.  
- Modules: `module-info.java` declares required JavaFX modules and opens the package for JavaFX.

## How to Compile and Run
Requirements:
- Java Development Kit (JDK) 17 or later  
- JavaFX SDK (matching your JDK)  
- Command line terminal or IDE (IntelliJ IDEA, Eclipse, or VS Code) configured with a JavaFX module path

Commands (from the `nazariolambda/src` directory):

Compile:
javac --module-path "<path_to_javafx_lib>" --add-modules javafx.controls,javafx.graphics -d ../out module-info.java lambda_Lab_Starter/MutualFund.java lambda_Lab_Starter/FundsDef.java lambda_Lab_Starter/Portfolio.java lambda_Lab_Starter/FundScreenFX.java

Run:
java --module-path "<path_to_javafx_lib>" --add-modules javafx.controls,javafx.graphics -cp ../out lambda_Lab_Starter.FundScreenFX

(Replace `<path_to_javafx_lib>` with the absolute path to your JavaFX SDK `lib` directory, for example on Windows: `C:\javafx-sdk-22\lib`, or on macOS/Linux: `/opt/javafx-sdk-22/lib`.)
