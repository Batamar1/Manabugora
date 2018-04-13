package ru.bell.manabu.View;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResponseData <T> {
    @NotNull
    private ResponseData.ResponseDataStatusEnum status = ResponseDataStatusEnum.SUCCESS;//0 - ok, 1 - errors

    private T data;

    @NotNull
    private List<String> errors;

    public static ResponseData ok;

    static {
        ok = new ResponseData(ResponseDataStatusEnum.SUCCESS, null, new ArrayList());
    }

    public static ResponseData errors(List<String> errors) {
        return new ResponseData(ResponseDataStatusEnum.FAIL, null, errors);
    }

    public ResponseData(T data){
        this.data = data;
    }

    public ResponseData(ResponseDataStatusEnum status, T data, List<String> errors){
        this.status = status;
        this.data = data;
        this.errors = errors;
    }

    public ResponseData(){
    }
    public void addError(String error){
        if(errors == null){
            errors = new ArrayList<>();
        }
        status = ResponseDataStatusEnum.FAIL;
        errors.add(error);
    }


    public enum ResponseDataStatusEnum {
        SUCCESS, FAIL
    }
}
