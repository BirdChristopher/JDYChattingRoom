package exception;

public class MyException extends Throwable{
    public int code = 200;
    public MyException(int code){
        this.code=code;
    }

    public String toString(){
        return Integer.toString(code);
    }
}
