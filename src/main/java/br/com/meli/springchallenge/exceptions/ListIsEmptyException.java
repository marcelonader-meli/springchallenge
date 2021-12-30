package br.com.meli.springchallenge.exceptions;

public class ListIsEmptyException extends Exception{

    public ListIsEmptyException(){
        super();
    }

    public ListIsEmptyException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Ocorreu a seguinte exceção: " + this.getClass().getName() + "\nMensagem: " + this.getMessage() + "\n";
    }
}
