package br.com.meli.springchallenge.exceptions;

public class OutOfStockException extends Exception{
    public OutOfStockException(){
        super();
    }

    public OutOfStockException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Ocorreu a seguinte exceção: " + this.getClass().getName() + "\nMensagem: " + this.getMessage() + "\n";
    }
}
