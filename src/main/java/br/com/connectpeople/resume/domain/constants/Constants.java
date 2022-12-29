package br.com.connectpeople.resume.domain.constants;

public class Constants {

    private Constants() {
    }

    public static class ErrorMessage {

        public static final String ERROR_MSG_FIELD_CANNOT_BE_EMPTY = "O campo não pode estar vazio";
        public static final String ERROR_MSG_SELECT_OPTION = "Por favor selecione uma das opções.";
        public static final String ERROR_MSG_NAME_INVALID = "O nome deve conter um nome e pelo menos um sobrenome";
        public static final String ERROR_MSG_BIRTH_DATE_BEFORE_AFTER = "Data de nascimento deve estar entre 15 e 100 anos";
        public static final String ERROR_MSG_BIRTH_DATE_FORMAT = "Data deve estar no formato yyyy/MM/dd";
        public static final String ERROR_MSG_POSTALCODE_INVALID = "Insira um CEP válido";
        public static final String ERROR_MSG_PHONENUMBER_INVALID = "Insira um número de telefone válido";
        public static final String ERROR_MSG_EMAIL_INVALID = "Insira um e-mail válido";
        public static final String ERROR_MSG_EMAIL_ALREADY_REGISTER = "O e-mail informado já está registrado.";
        public static final String ERROR_MSG_MONTH_INVALID = "Selecione um mês válido";
        public static final String ERROR_MSG_YEAR_INVALID = "Selecione um ano válido";
        public static final String ERROR_MSG_MONTH_END_BEFORE_START = "A data de saída não pode ser menor que a data de entrada";
        public static final String ERROR_MSG_YEAR_END_BEFORE_START = "O ano de entrada não pode ser maior que o ano de saída";

        private ErrorMessage() {
        }
    }

    public static class StateProcess {
        public static final String NEW = "NEW";
        public static final String PROCESSING = "PROCESSING";
        public static final String PROCESSED = "PROCESSED";
        public static final String SUCCESS = "SUCCESS";
        public static final String FAILURE = "FAILURE";
    }
}
