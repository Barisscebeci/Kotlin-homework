package com.barisscebeci.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barisscebeci.todoapplication.ui.theme.ToDoApplicationTheme
import com.barisscebeci.todoapplication.uix.viewmodel.HomeScreenViewModel
import com.barisscebeci.todoapplication.uix.viewmodel.NoteCreationViewModel
import com.barisscebeci.todoapplication.uix.viewmodel.NoteDetailsViewModel
import com.barisscebeci.todoapplication.uix.views.PageTransitions

class MainActivity : ComponentActivity() {

    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val noteCreationViewModel: NoteCreationViewModel by viewModels()
    private val noteDetailsViewModel: NoteDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoApplicationTheme {
                PageTransitions(
                    homeScreenViewModel = homeScreenViewModel,
                    noteCreationViewModel = noteCreationViewModel,
                    noteDetailsViewModel = noteDetailsViewModel
                )
            }
        }
    }
}
