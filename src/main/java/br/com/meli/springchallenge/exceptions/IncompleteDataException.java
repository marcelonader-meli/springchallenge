package br.com.meli.springchallenge.exceptions;

public class IncompleteDataException extends Exception{

    public IncompleteDataException(){
        super();
    }

    public IncompleteDataException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Ocorreu a seguinte exceção: " + this.getClass().getName() + "\nMensagem: " + this.getMessage() + "\n";
    }
}
