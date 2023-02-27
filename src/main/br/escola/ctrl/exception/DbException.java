package main.br.escola.ctrl.exception;

public class DbException extends RuntimeException {

    public DbException(String msg) {
        super(msg);
    }
}
