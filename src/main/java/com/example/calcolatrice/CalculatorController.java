package com.example.calcolatrice;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;



public class CalculatorController implements EventHandler<ActionEvent> {

    private static CalculatorController controller ;
    private static final char EMPTY_CHAR = '\u0000' ;

    public static final String IMPOSSIBLE_STRING = "CAN NOT DIVIDE FOR ZERO" ;
    public static final char PLUS_SYMBOL = '+' ;
    public static final char MINUS_SYMBOL = '-' ;
    public static final char PER_SYMBOL = 'x' ;
    public static final char DIVIDE_SYMBOL = '/' ;

    private char operationSymbol;
    private String firstOperand;
    private String secondOperand;
    private String resultString;
    private String previousResult ;


    private Scene calculatorScene ;

    private CalculatorController() {
        initialize() ;
    }

    private void initialize() {
        operationSymbol = EMPTY_CHAR ;
        firstOperand = "" ;
        secondOperand = "" ;
        resultString = "" ;
        previousResult = "" ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource() ;
        if (source instanceof KeyboardButton clickedButton) {
            switch (clickedButton.getId()) {
                case "buttonOne", "buttonTwo", "buttonThree", "buttonFour", "buttonFive", "buttonSix", "buttonSeven",
                        "buttonEight", "buttonNine", "buttonZero" -> {
                    if (resultString.length() != 0) {
                        nextOperation();
                        firstOperand = "" + clickedButton.getText().toCharArray()[0] ;
                    }
                    else {
                        if (operationSymbol == EMPTY_CHAR) {
                            firstOperand += clickedButton.getText().toCharArray()[0] ;
                        }
                        else {
                            secondOperand += clickedButton.getText() ;
                        }
                    }
                }

                case "buttonPlus", "buttonMinus", "buttonPer", "buttonDiv" -> {
                    if (firstOperand.length() > 0) {
                        if (resultString.length() > 0) {
                            nextOperation();
                        }
                        operationSymbol = clickedButton.getText().toCharArray()[0];

                    }
                }
                case "buttonEqual" -> operationHandler();
                case "buttonDel" -> {
                    if (operationSymbol == EMPTY_CHAR) {
                        if (firstOperand.length() != 0) firstOperand = firstOperand.substring(0, firstOperand.length() - 1) ;
                    }
                    else {
                        if (secondOperand.length() != 0) secondOperand = secondOperand.substring(0, secondOperand.length() - 1) ;
                    }

                }
                case "buttonClear" -> initialize();

                case "buttonAns" -> {
                    if (resultString.length() != 0) {
                        nextOperation();
                    }
                    else {
                        if (operationSymbol == EMPTY_CHAR) firstOperand = previousResult ;
                        else secondOperand = previousResult ;
                    }
                }

                case "buttonDot" -> {
                    if (operationSymbol == EMPTY_CHAR) {
                        if (!firstOperand.contains(".")) firstOperand = firstOperand + "." ;
                    }
                    else {
                        if (!secondOperand.contains(".")) secondOperand = secondOperand + "." ;
                    }
                }

                case "buttonNegate" -> {
                    if (operationSymbol == EMPTY_CHAR) {
                        if (firstOperand.length() > 0) {
                            firstOperand = (firstOperand.startsWith("-")) ? firstOperand.substring(1) : "-" + firstOperand ;
                        }
                    }
                    else {
                        if (secondOperand.length() > 0) {
                            secondOperand = (secondOperand.startsWith("-")) ? secondOperand.substring(1) : "-" + secondOperand ;
                        }
                    }
                }
            }
            updateLabels();
        }
    }

    private void nextOperation() {
        firstOperand = /*(resultString.length() == 0) ? firstOperand : */resultString ;
        resultString = "" ;
        secondOperand = "" ;
        operationSymbol = EMPTY_CHAR ;
    }

    private void updateLabels() {
        MyLabel firstOperandLabel = (MyLabel) calculatorScene.lookup("#" + Calculator.FIRST_OPERAND_LABEL_ID) ;
        MyLabel secondOperandLabel = (MyLabel) calculatorScene.lookup("#" + Calculator.SECOND_OPERAND_LABEL_ID) ;
        MyLabel operationLabel = (MyLabel) calculatorScene.lookup("#" + Calculator.OPERATION_LABEL_ID) ;
        MyLabel resultLabel = (MyLabel) calculatorScene.lookup("#" + Calculator.RESULT_LABEL_ID) ;

        if (firstOperandLabel != null) firstOperandLabel.setText(firstOperand) ;
        if (secondOperandLabel != null) secondOperandLabel.setText(secondOperand);
        if (operationLabel != null) operationLabel.setText(String.valueOf(operationSymbol));
        if (resultLabel != null) resultLabel.setText(resultString);
    }

    private void operationHandler() {
        if (operationSymbol != EMPTY_CHAR) {
            if (firstOperand.length() > 0 && secondOperand.length() > 0) {
                Double first = Double.parseDouble(this.firstOperand);
                Double second = Double.parseDouble(this.secondOperand);
                resultString = switch (operationSymbol) {
                    case PLUS_SYMBOL -> Double.toString(first + second);
                    case MINUS_SYMBOL -> Double.toString(first - second);
                    case PER_SYMBOL -> Double.toString(first * second);
                    case DIVIDE_SYMBOL -> {
                        if (second != 0.0) yield Double.toString(first / second);
                        else {
                            initialize();
                            showAlert() ;
                            yield "";
                        }
                    }
                    default -> Double.toString(0.0);
                };
                previousResult = (resultString.compareTo("") == 0) ? previousResult : resultString ;
            }
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR) ;
        alert.setHeaderText(IMPOSSIBLE_STRING);
        alert.showAndWait() ;
    }

    public void setScene(Scene scene) {
        calculatorScene = scene ;
    }

    public static CalculatorController getController() {
        if (controller == null) {
            controller = new CalculatorController() ;
        }
        return controller ;
    }
}
