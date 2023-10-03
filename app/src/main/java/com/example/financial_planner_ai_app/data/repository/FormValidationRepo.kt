package com.example.financial_planner_ai_app.data.repository

import android.util.Patterns
import com.example.financial_planner_ai_app.util.ValidationResult

class FormValidationRepo {

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "The email can't be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                message = "That's not a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun validateUsername(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "The username can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun validatePassword(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                message = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                message = "The password needs to contain at least one letter and digit"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult(
                successful = false,
                message = "The passwords don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}