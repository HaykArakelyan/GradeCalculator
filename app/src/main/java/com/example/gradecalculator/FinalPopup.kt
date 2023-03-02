package com.example.gradecalculator

import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog

class FinalPopup (
    private var content: @Composable () -> Unit,
    private var isPopupShown: Boolean,
    private var onDismiss: () -> Unit
){
    
    @Composable
    fun ShowPopup(){
        if (this.isPopupShown){
            Dialog(
                onDismissRequest = {
                this.onDismiss()
            },
                content = this.content
            )
        }
    }
}