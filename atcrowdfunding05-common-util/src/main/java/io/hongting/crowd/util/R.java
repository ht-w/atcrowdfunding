package io.hongting.crowd.util;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 09 27 12:54 AM
 */
public class R<T>  {


    public static final String SUCCESS = "Success";

    public static final String FAIL = "Fail";

    private String result;

    private String message;

    private T data;



    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public R() {
    }

    public static <Type> R <Type> success(){
        return new R<Type>(SUCCESS, null, null);
    }


    public static <Type> R <Type> success(Type data){
        return new R<Type>(SUCCESS, null, data);
    }

    public static <Type> R <Type> fail(String message){
        return new R<Type>(FAIL, message, null);
    }


}
