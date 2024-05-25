package com.game.clicker.data.utils

import java.util.regex.Pattern

class ValidationUser {

    companion object {
        fun isEmailCorrect(email: String?): Boolean {
            val pattern =
                Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
            if (email == null) return false
            return pattern.matcher(email).matches()
        }

        fun isPasswordCorrect(password: String?): Boolean {
            val pattern =
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
            if (password == null) return false
            return pattern.matcher(password).matches()
        }
    }
}