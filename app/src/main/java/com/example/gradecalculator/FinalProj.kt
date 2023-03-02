package com.example.gradecalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class FinalProj {
    private var finalGrade = 100

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun FinalProject(keyboardController: SoftwareKeyboardController?) {
        var finalProjectGrade by remember {
            mutableStateOf("")
        }

        var errorMessage by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Final Project(30%)")

            OutlinedTextField(
                modifier = Modifier.height(45.dp),
                value = finalProjectGrade,
                onValueChange = {
                    finalProjectGrade = it
                    errorMessage = ""
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    val (errorMsg, isGradeValid) = isGradeValid(finalProjectGrade)

                    if (isGradeValid) {
                        keyboardController?.hide()
                        finalGrade = Integer.parseInt(finalProjectGrade)
                    } else {
                        errorMessage = errorMsg
                        finalProjectGrade = ""
                    }
                }),
                textStyle = TextStyle(
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                ),
                placeholder = {
                    Text(
                        text = errorMessage,
                        fontSize = 11.sp,
                        color = Color.Red,
                    )
                }
            )
        }
    }


    fun getFinalProjectGrade(): Int{
        return finalGrade
    }
}