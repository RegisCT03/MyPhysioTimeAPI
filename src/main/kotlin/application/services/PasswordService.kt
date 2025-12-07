package com.example.application.services

import com.example.domain.interfaces.services.IPasswordService
import org.mindrot.jbcrypt.BCrypt

class PasswordService : IPasswordService {
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }

    override fun checkPassword(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }
}