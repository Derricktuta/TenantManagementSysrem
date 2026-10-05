package com.example.tenantmanagementsysrem

class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) {
    fun summary(): String {
        return "Name: $name\nPhone: $phone\nRent: $rent"
    }
}