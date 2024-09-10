package com.barisscebeci.calismayapisi

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barisscebeci.calismayapisi.ui.theme.CalismaYapisiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    val sayac = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = true) {
        Log.e("Anasayfa","LaunchedEffect çalıştı")
        //Sayfa her görüldüğünde çalışır
        //Sayfaya geri dönüldüğünde çalışır
    }
    DisposableEffect(Unit){
        onDispose { Log.e("Anasayfa","LaunchedEffect çalıştı") }

        //Sayfa her görüldüğünde çalışır
    }


    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Anasayfa") })
        }
    )
    {
        paddingValues ->
        Log.e("Anasayfa", "Yenilendi")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            
        ) {
            Text(text = sayac.value.toString(), fontSize = 50.sp)
            Button(onClick = {
                    sayac.value = sayac.value +1

            }) {
                Text(text = "Tıkla")
            }
            Button(onClick = {

                navController.navigate("detaySayfa/Barış/23")

            }) {
                Text(text = "Detay")
            }
        }
    }
}