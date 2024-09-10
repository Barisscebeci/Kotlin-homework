package com.barisscebeci.todoapplication.uix.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.ui.theme.CardBackgroundColor
import com.barisscebeci.todoapplication.ui.theme.TextColor2


@Composable
fun TodoItem(
    todos: ToDo,
    onTodoClick: () -> Unit,
    onToDoCompChange: (Boolean) -> Unit,
    onTodoDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onTodoClick() },
        colors = CardDefaults.cardColors(
            containerColor = CardBackgroundColor,
            contentColor = TextColor2
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = todos.title,
                    style = TextStyle(

                        fontSize = 20.sp
                    ),

                    )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = todos.description,
                    style = TextStyle(

                        fontSize = 16.sp
                    )
                )
            }


            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                IconButton(onClick = onTodoDelete) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete Task", )
                }

            }
        }
    }
}
