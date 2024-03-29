package com.example.blendings_backend.presentation.domain.auth

object AuthValidationValue {
    private const val VARIABLE_MAME_FOR_MESSAGE_PREFIX = " : 다음 변수에서 발생 - "

    const val MAIL_ADDRESS_REGEXP = "^[0-9a-zA-Z]*@[0-9a-zA-Z]*\\.[a-zA-Z]{2,3}$"
    const val MAIL_ADDRESS_MESSAGE = "메일 형태가 아닙니다."

    const val PASSWORD_REGEXP = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.[!@#\$%^&*]?)[a-zA-Z0-9!@#\$%^&*]{5,30}$"
    const val PASSWORD_MESSAGE = "비밀번호는 영문, 숫자 조합이어야 하고 특수문자가 포함될 수 있으며, 5자 이상 30자 이하여야합니다."

    const val NAME_REGEXP = "^[a-zA-Z가-힣]{1,20}$"
    const val NAME_MESSAGE = "이름은 영어 혹은 한글로 구성되어야 하며, 1자 이상 20자 이하여야합니다."

    const val COUPLE_NICKNAME_REGEXP = "^[\\w가-힣ㄱ-ㅎ]{1,20}$"
    const val COUPLE_NICKNAME_MESSAGE = "커플 별명은 영어, 숫자, 한글로 구성되어야 하며, 1자 이상 20자 이하여야합니다."

    const val AUTHENTICATION_CODE_REGEXP = "^[0-9A-Z]{6}$"
    const val AUTHENTICATION_CODE_MESSAGE = "인증 코드는 대문자 영문, 숫자 조합이어야 하고, 6자입니다."
}
