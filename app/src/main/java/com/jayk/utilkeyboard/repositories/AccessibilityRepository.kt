package com.jayk.utilkeyboard.repositories

interface AccessibilityRepository {
    fun getLatestMessages() : List<String>
}