package com.example.gradecalculator

fun isGradeValid(grade: String): Pair<String, Boolean> {

    var errorMessage = ""

    if ('.' in grade || '-' in grade || grade == "") {
        errorMessage = "Invalid Grade!"
        return Pair(errorMessage, false)
    }

    val gradeInt = Integer.parseInt(grade)

    if (gradeInt in 0..100) {
        return Pair(errorMessage, true)
    }
    errorMessage = "Invalid Grade!"
    return Pair(errorMessage, false)
}