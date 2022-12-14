package br.com.connectpeople.resume.domain.constants;

public class Constants {

    private Constants(){}

    public static class ErrorMessage {

        private ErrorMessage() {}

        public static final String ERROR_MSG_FIELD_CANNOT_BE_EMPTY = "Field cannot be empty";
        public static final String ERROR_MSG_FIELD_CANNOT_BE_NULL = "Field cannot be null";
        public static final String ERROR_MSG_NAME_INVALID = "Field name must contain a name and at least one surname";
        public static final String ERROR_MSG_BIRTH_DATE_BEFORE_AFTER = "Date of birth must be between 1 and 100 years ago";
        public static final String ERROR_MSG_BIRTH_DATE_FORMAT = "Field Birth Date should be format as dd/MM/yyyy";
        public static final String ERROR_MSG_CPF_INVALID = "Inform a valid CPF";
        public static final String ERROR_MSG_CPF_ALREADY_REGISTER = "CPF already register in our base.";
        public static final String ERROR_MSG_POSTALCODE_INVALID = "Insert a valid postalCode";
        public static final String ERROR_MSG_PHONENUMBER_INVALID = "Insert a valid phoneNumber";
        public static final String ERROR_MSG_EMAIL_INVALID = "Insert a valid email";
        public static final String ERROR_MSG_EMAIL_ALREADY_REGISTER = "E-mail already register in our base.";
    }

    public static class StateProcess {
        public static final String NEW = "NEW";
        public static final String PROCESSING = "PROCESSING";
        public static final String PROCESSED = "PROCESSED";
        public static final String SUCCESS = "SUCCESS";
        public static final String FAILURE = "FAILURE";
    }
}
