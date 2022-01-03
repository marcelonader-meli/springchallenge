package br.com.meli.springchallenge.exceptions;

public class ExistingClientException extends Exception{

    public ExistingClientException(){
        super();
    }

    public ExistingClientException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Ocorreu a seguinte exceção: " + this.getClass().getName() + "\nMensagem: " + this.getMessage() + "\n";
    }
}
