import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.barisscebeci.homework2008.ui.theme.AnasayfaRenk
import com.barisscebeci.homework2008.ui.theme.YaziRenk2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(
    onClickA: () -> Unit,
    onClickX: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Anasayfa")
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AnasayfaRenk,
                    titleContentColor = YaziRenk2
                )
            )
        },
        containerColor = AnasayfaRenk
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

        ) {
            Button(onClick = onClickA) {
                Text(text = "A Sayfası")
            }
            Button(onClick = onClickX) {
                Text(text = "X Sayfası")
            }
        }
    }
}
