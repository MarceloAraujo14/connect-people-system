package br.com.connectpeople.commons.constants;

public class Constants {

    private Constants() {
    }

    public static class ErrorMessage {

        //Resume Errors
        public static final String ERROR_MSG_FIELD_CANNOT_BE_EMPTY = "Dado obrigatório.";
        public static final String ERROR_MSG_SELECT_OPTION = "Selecione uma opção.";
        public static final String ERROR_MSG_VALID_OPTION = "Opção inválida.";
        public static final String ERROR_MSG_NAME_INVALID = "Nome inválido.";
        public static final String ERROR_MSG_BIRTH_DATE_BEFORE_AFTER = "Data inválida 15 e 100 anos";
        public static final String ERROR_MSG_BIRTH_DATE_FORMAT = "Formato válido yyyy/MM/dd";
        public static final String ERROR_MSG_POSTALCODE_INVALID = "CEP inválido";
        public static final String ERROR_MSG_PHONENUMBER_INVALID = "Telefone inválido";
        public static final String ERROR_MSG_EMAIL_INVALID = "e-mail inválido";
        public static final String ERROR_MSG_EMAIL_ALREADY_REGISTER = "O e-mail já registrado.";
        public static final String ERROR_MSG_MONTH_INVALID = "Mês inválido";
        public static final String ERROR_MSG_YEAR_INVALID = "Ano inválido";
        public static final String ERROR_MSG_MONTH_END_BEFORE_START = "A data de saída inválida";
        public static final String ERROR_MSG_YEAR_END_BEFORE_START = "O ano de saída inválido";

        //User Errors
        public static final String ERROR_MSG_USER_NOT_FOUND = "Email ou senha inválidos.";
        public static final String ERROR_MSG_WEAK_PASSWORD = "Senha deve conter ao menos 6 caracteres, 1 letra maiuscula, 1 letra minuscula, 1 numero e 1 caracter especial.";


        private ErrorMessage() {
        }
    }

    public static class StateProcess {
        private StateProcess(){}
        public static final String NEW = "NEW";
        public static final String PROCESSING = "PROCESSING";
        public static final String PROCESSED = "PROCESSED";
        public static final String SUCCESS = "SUCCESS";
        public static final String FAILURE = "FAILURE";
    }
}
