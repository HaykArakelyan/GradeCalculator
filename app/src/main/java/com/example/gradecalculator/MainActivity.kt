package com.example.gradecalculator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradecalculator.ui.theme.GradeCalculatorTheme
import kotlinx.coroutines.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController as LocalSoftwareKeyboardController1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    WholeUI()
                    Navigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WholeUI() {

    val keyboardController = LocalSoftwareKeyboardController1.current

    var isPopupShown: Boolean by remember {
        mutableStateOf(false)
    }

    var content: @Composable () -> Unit by remember {
        mutableStateOf({ DialogDesign()})
    }


    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val hw = HomeWorks()
        val md = Midterms()
        val prt = Participations()
        val prsnt = Presentations()
        val fin = FinalProj()
        val popup =
            FinalPopup(
                content = content,
                isPopupShown = isPopupShown,
                onDismiss = {
                    isPopupShown = false
                }
            )

        hw.ListOfGrades()
        md.Midterm(keyboardController)
        prt.Participation(keyboardController)
        prsnt.GroupPresentation(keyboardController)
        fin.FinalProject(keyboardController)
        popup.ShowPopup()



        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Final Grade",
                fontSize = 20.sp
            )
            Button(onClick = {
                val result = hw.getHomeworkMeanGrade() * 0.2 +
                        prt.getParticipationGrade() * 0.1 +
                        prsnt.getPresentationGrade() * 0.1 +
                        md.getMidtermMeanGrade().first * 0.1 +
                        md.getMidtermMeanGrade().second * 0.2 +
                        fin.getFinalProjectGrade() * 0.3
                content = {
                    DialogDesign(context = result)
                }
                isPopupShown = true
            }) {
                Text(text = "CALCULATE")
            }
        }
    }
}


@Composable
fun DialogDesign(context: Double = 0.0) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(200.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
            textAlign = TextAlign.Center,
            text = "You final grade for this course is $context",
        )
    }
}

