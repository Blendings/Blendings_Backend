package com.example.blendings_backend.domain.auth.presentation

object AuthValidationValue {
    private const val VARIABLE_MAME_FOR_MESSAGE_PREFIX = " : 다음 변수에서 발생 - "

    const val MAIL_REGEXP = "^[0-9a-zA-Z]*@[0-9a-zA-Z]*\\.[a-zA-Z]{2,3}$"
    const val MAIL_MESSAGE = "메일 형태가 아닙니다."
    const val MALE_MAIL_MESSAGE = MAIL_MESSAGE + VARIABLE_MAME_FOR_MESSAGE_PREFIX + "남자의 메일"
    const val FEMALE_MAIL_MESSAGE = MAIL_MESSAGE + VARIABLE_MAME_FOR_MESSAGE_PREFIX + "여자의 메일"

    const val PASSWORD_REGEXP = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.[!@#\$%^&*]?)[a-zA-Z0-9!@#\$%^&*]{5,20}$"
    private const val PASSWORD_MESSAGE = "비밀번호는 영문, 숫자 조합이어야하고 특수문자가 포함될 수 있으며, 5자 이상 20자 이하여야합니다."
    const val MALE_PASSWORD_MESSAGE = PASSWORD_MESSAGE + VARIABLE_MAME_FOR_MESSAGE_PREFIX + "남자의 비밀번호"
    const val FEMALE_PASSWORD_MESSAGE = PASSWORD_MESSAGE + VARIABLE_MAME_FOR_MESSAGE_PREFIX + "여자의 비밀번호"
}
