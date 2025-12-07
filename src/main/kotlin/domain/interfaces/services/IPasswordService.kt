package com.example.domain.interfaces.services

interface IPasswordService {
    fun hashPassword(password: String): String
    fun checkPassword(password: String, hashed: String): Boolean
}