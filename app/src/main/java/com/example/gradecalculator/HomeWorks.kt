package com.example.gradecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomeWorks {
    private var homeWorkGrades = mutableListOf<Int>()


    @Composable
    fun ListOfGrades() {
        var grade by remember {
            mutableStateOf("")
        }

        val homeworkGrades: MutableList<String> = remember {
            mutableStateListOf()
        }

        var errorMessage by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Button(
                onClick = {
                    homeworkGrades.clear()
                    homeWorkGrades.clear()
                },
                enabled = homeworkGrades.size > 0
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Reset Homeworks")
                    Text(
                        text = "(Currently ${homeworkGrades.size} added)",
                        fontSize = 11.sp
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    val (errorMsg, isGradeValid) = isGradeValid(grade)

                    if (isGradeValid) {
                        homeworkGrades.add(grade)

                    } else {
                        errorMessage = errorMsg
                    }
                    grade = ""
                }) {
                    Text(text = "Add")
                }

                Text(text = "Homeworks(20%)")

                OutlinedTextField(
                    value = grade,
                    onValueChange = {
                        grade = it
                        errorMessage = ""
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    maxLines = 1,
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        val (errorMsg, isGradeValid) = isGradeValid(grade)

                        if (isGradeValid) {
                            homeworkGrades.add(grade)
                            homeWorkGrades.add(Integer.parseInt(grade))
                        } else {
                            errorMessage = errorMsg
                        }
                        grade = ""
                    }),
                    placeholder = {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            fontSize = 13.sp,
                        )
                    },
                    textStyle = TextStyle(textAlign = TextAlign.Center)
                )

                Column(
                    modifier = Modifier
                        .padding(
                            start = 15.dp,
                            end = 15.dp,
                            top = 20.dp,
                            bottom = 20.dp
                        )
                        .height(95.dp)
                        .width(280.dp)
                        .verticalScroll(rememberScrollState())
                        .background(Color.Gray)
                ) {
                    homeworkGrades.forEachIndexed{ index, it ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround

                        ) {
                            Text(
                                text = "Homework ${index + 1}: $it",
                                color = Color.Cyan,
                                textAlign = TextAlign.Center,
                            )
                            IconButton(onClick = {
                                homeworkGrades.removeAt(index)
                            }) {
                                Icon(
                                    Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    modifier = Modifier
                                        .size(15.dp),
                                    tint = Color.Cyan
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun getHomeworkMeanGrade(): Int {
        if (homeWorkGrades.size == 0) {
            return 100
        }
        return (homeWorkGrades.sum() / homeWorkGrades.size)
    }
}