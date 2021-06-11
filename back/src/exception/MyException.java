package exception;

/**
 * 自定义报错异常
 * <p>用于自定义报错信息，反映业务过程中的一些问题，以错误码的形式抛出</p>
 * @author 季晓东
 */
public class MyException extends Throwable{
    public int code = 200;
    public MyException(int code){
        this.code=code;
    }

    public String toString(){
        return Integer.toString(code);
    }
}
