package com.carwash.carwash.util.constantes;

public class Constantes {
                                              
    public static final String EMAIL_VALIDATION_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String SECRET_KEY_STRING = "secret_key";
    public static final int TIPO_ITEM_PRODUTO = 1;
    public static final int TIPO_ITEM_SERVICO = 2;
}
