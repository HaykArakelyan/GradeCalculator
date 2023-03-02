package com.example.gradecalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

class Participations {
    private var participation = 100


    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Participation(keyboardController: SoftwareKeyboardController?) {

        var participationGrade by remember {
            mutableStateOf("")
        }

        var errorMessage by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Participation(10%)")

            OutlinedTextField(
                value = participationGrade,
                onValueChange = {
                    participationGrade = it
                    errorMessage = ""
                },
                modifier = Modifier
                    .width(280.dp)
                    .height(45.dp),
                textStyle = TextStyle(
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        val (errorMsg, isGradeValid) = isGradeValid(participationGrade)

                        if (isGradeValid) {
                            keyboardController?.hide()
                            participation = Integer.parseInt(participationGrade)
                        } else {
                            errorMessage = errorMsg
                            participationGrade = ""
                        }
                    },
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
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

    fun getParticipationGrade(): Int{
        return participation
    }
}