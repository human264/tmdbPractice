package fast.campus.netplix3.controller;

import fast.campus.netplix3.exception.ErrorCode;

public record NetplixApiResponse<T>(
        boolean success,
        String code,
        String message,
        T data
) {
    private static final String CODE_SUCCESS = "SUCCESS";

    public static <T> NetplixApiResponse<T> ok(T data) {
        return new NetplixApiResponse<>(true,CODE_SUCCESS, null, data);
    }

    public static <T> NetplixApiResponse<T> fail(ErrorCode errorCode, String message) {
        return new NetplixApiResponse<>(false, errorCode.getCode(), message, null);
    }

}
