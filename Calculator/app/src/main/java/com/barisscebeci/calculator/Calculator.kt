package com.barisscebeci.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    var displayValue by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0) }
    var pendingOperation by remember { mutableStateOf<String?>(null) }

    val onClick: (String) -> Unit = { label ->
        when (label) {
            "C" -> {
                displayValue = ""
                result = 0
                pendingOperation = null
            }

            "+/-" -> {
                displayValue = if (displayValue.startsWith("-")) {
                    displayValue.drop(1)
                } else {
                    "-$displayValue"
                }
            }

            "%" -> {
                if (displayValue.isNotEmpty()) {
                    val value = displayValue.toDouble()
                    displayValue = (value / 100).toString()
                }
            }

            "+", "-", "x", "÷" -> {
                if (displayValue.isNotEmpty()) {
                    result = displayValue.toInt()
                    displayValue = ""
                }
                pendingOperation = label
            }

            "=" -> {
                if (pendingOperation != null && displayValue.isNotEmpty()) {
                    val currentValue = displayValue.toInt()
                    result = when (pendingOperation) {
                        "+" -> result + currentValue
                        "-" -> result - currentValue
                        "x" -> result * currentValue
                        "÷" -> if (currentValue != 0) result / currentValue else result
                        else -> result
                    }
                    displayValue = result.toString()
                    pendingOperation = null
                }
            }

            else -> {
                displayValue += label
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = displayValue.ifEmpty { result.toString() },
            fontSize = 60.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CalculatorButtonRow(listOf("C", "+/-", "%", "÷"), onClick)
        CalculatorButtonRow(listOf("7", "8", "9", "x"), onClick)
        CalculatorButtonRow(listOf("4", "5", "6", "-"), onClick)
        CalculatorButtonRow(listOf("1", "2", "3", "+"), onClick)
        CalculatorButtonRow(listOf("0", ".", "="), onClick)
    }
}

@Composable
fun CalculatorButtonRow(buttons: List<String>, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        buttons.forEach { label ->
            if (label == "0") {
                CalculatorButton(
                    label,
                    onClick,
                    modifier = Modifier
                        .weight(2f)
                        .padding(4.dp)
                        .height(80.dp)
                        .fillMaxWidth(fraction = 0.5f)
                )
            } else {
                CalculatorButton(label, onClick, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val isGrayButton = label in listOf("C", "+/-", "%")
    Button(
        onClick = { onClick(label) },
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = when {
                isGrayButton -> Color(0xFF757575)
                label in listOf("+", "-", "x", "÷", "=") -> Color(0xFFFF9800)
                else -> Color(0xFF212121)
            },
            contentColor = Color.White
        ),
        shape = CircleShape
    ) {
        Text(text = label, fontSize = 24.sp)
    }
}
