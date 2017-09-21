package ua.nure.tur.services;

public class ServiceResult<T> {

    private ServiceResultStatus status;

    private String Message;

    private T data;

    public ServiceResult(ServiceResultStatus status) {
        this.status = status;
    }

    public ServiceResult(ServiceResultStatus status, String message) {
        this.status = status;
        Message = message;
        this.data = data;
    }

    public ServiceResult(ServiceResultStatus status, String message, T data) {
        this.status = status;
        Message = message;
        this.data = data;
    }

    public ServiceResultStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceResultStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
