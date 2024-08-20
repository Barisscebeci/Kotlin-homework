import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.barisscebeci.homework2008.ASayfasi
import com.barisscebeci.homework2008.BSayfasi
import com.barisscebeci.homework2008.XSayfasi
import com.barisscebeci.homework2008.YSayfasi

@Composable
fun NavLink(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = "Anasayfa",
        modifier = modifier
    ) {
        composable("Anasayfa") {
            Anasayfa(
                onClickA = { navigateToASayfasi(navHostController) },
                onClickX = { navigateToXSayfasi(navHostController) }
            )
        }
        composable("ASayfasi") {
            ASayfasi(
                onClickB = { navigateToBSayfasi(navHostController) }
            )
        }
        composable("XSayfasi") {
            XSayfasi(
                onClickY = { navigateToYSayfasi(navHostController) }
            )
        }
        composable("BSayfasi") {
            BSayfasi(
                onClickY = { navigateToYSayfasi(navHostController) }
            )
        }
        composable("YSayfasi") {
            YSayfasi(
                anaSayfayaDon = { navigateToAnasayfa(navHostController) }
            )
        }
    }
}

fun navigateToASayfasi(navHostController: NavHostController) {
    navHostController.navigate("ASayfasi")
}

fun navigateToXSayfasi(navHostController: NavHostController) {
    navHostController.navigate("XSayfasi")
}

fun navigateToBSayfasi(navHostController: NavHostController) {
    navHostController.navigate("BSayfasi")
}

fun navigateToYSayfasi(navHostController: NavHostController) {
    navHostController.navigate("YSayfasi")
}

fun navigateToAnasayfa(navHostController: NavHostController) {
    navHostController.navigate("Anasayfa")
}
