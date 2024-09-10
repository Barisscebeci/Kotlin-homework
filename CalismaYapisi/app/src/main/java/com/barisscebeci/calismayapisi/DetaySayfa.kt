package com.barisscebeci.calismayapisi

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.barisscebeci.calismayapisi.ui.theme.CalismaYapisiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetaySayfa(gelenAd: String, gelenYas: Int) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Detay Sayfa") })
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            Text(text = " $gelenAd - $gelenYas")

        }

        BackHandler (false){
            //True durumunda geri dönemez false durumunda ise normal çalışmaya devam edecek
            Log.e("Detay Sayfa", "Geri tuşuna basıldı")
        }
    }
}



