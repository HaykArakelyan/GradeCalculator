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

class Presentations {
    private var presentation = 100


    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun GroupPresentation(keyboardController: SoftwareKeyboardController?) {

        var presentationGrade by remember {
            mutableStateOf("")
        }

        var errorMessage by remember {
            mutableStateOf("")
        }

        Column(
            Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Group Presentation(10%)",
            )
            OutlinedTextField(
                modifier = Modifier.height(45.dp),
                value = presentationGrade,
                onValueChange = {
                    presentationGrade = it
                    errorMessage = ""
                },
                keyboardActions = KeyboardActions(onDone = {
                    val (errorMsg, isGradeValid) = isGradeValid(presentationGrade)

                    if (isGradeValid) {
                        keyboardController?.hide()
                        presentation = Integer.parseInt(presentationGrade)
                    } else {
                        errorMessage = errorMsg
                        presentationGrade = ""
                    }
                }),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
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


    fun getPresentationGrade(): Int{
        return presentation
    }
}