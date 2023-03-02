package com.example.gradecalculator

import androidx.compose.foundation.layout.*
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

class Midterms {
    private var midterm1Grade = 100
    private var midterm2Grade = 100


    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Midterm(keyboardController: SoftwareKeyboardController?) {
        var midterm1 by remember {
            mutableStateOf("")
        }

        var midterm2 by remember {
            mutableStateOf("")
        }

        var errorMessage by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Midterm 1(10%)")
                OutlinedTextField(
                    value = midterm1,
                    onValueChange = {
                        midterm1 = it
                        errorMessage = ""
                    },
                    modifier = Modifier
                        .width(280.dp)
                        .height(45.dp),
                    textStyle = TextStyle(fontSize = 11.sp, textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        val (errorMsg, isGradeValid) = isGradeValid(midterm1)

                        if (isGradeValid) {
                            keyboardController?.hide()
                            midterm1Grade = Integer.parseInt(midterm1)
                        } else {
                            errorMessage = errorMsg
                            midterm1 = ""
                        }
                    }),
                    placeholder = {
                        Text(
                            text = errorMessage,
                            fontSize = 11.sp,
                            color = Color.Red,
                        )
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Midterm 2(20%)")
                OutlinedTextField(
                    value = midterm2,
                    onValueChange = {
                        midterm2 = it
                        errorMessage = ""
                    },
                    modifier = Modifier
                        .width(280.dp)
                        .height(45.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 11.sp, textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        val (errorMsg, isGradeValid) = isGradeValid(midterm2)

                        if (isGradeValid) {
                            keyboardController?.hide()
                            midterm2Grade = Integer.parseInt(midterm2)
                        } else {
                            errorMessage = errorMsg
                            midterm2 = ""
                        }
                    }),
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
    }


    fun getMidtermMeanGrade(): Pair<Int, Int>{
        return Pair(midterm1Grade, midterm2Grade)
    }

}