package br.com.meli.springchallenge.exceptions;

public class ListProductIsEmptyException extends Exception{

    public ListProductIsEmptyException(){
        super();
    }

    public ListProductIsEmptyException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Ocorreu a seguinte exceção: " + this.getClass().getName() + "\nMensagem: " + this.getMessage() + "\n";
    }
}
