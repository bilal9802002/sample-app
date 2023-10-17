package com.interview.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.google.gson.Gson
import com.interview.sample.domain.entities.AssociatedDrugEntity
import com.interview.sample.ui.args.DrugDetailScreenArgs
import com.interview.sample.ui.screens.dashboard.DashboardRoute
import com.interview.sample.ui.screens.details.DrugDetailRoute
import com.interview.sample.ui.screens.login.LoginRoute
import com.interview.sample.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

object AppRoute {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val DRUG_DETAIL = "drugDetail"
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoute.LOGIN) {
        loginScreen { userId ->
            navController.navigate(
                AppRoute.DASHBOARD + "/$userId",
                navOptions {
                    launchSingleTop = true
                    popUpTo(0)
                })
        }
        dashboardScreen {
            navController.navigate(
                AppRoute.DRUG_DETAIL + "/${it}"
            )
        }
        drugDetailScreen {
            navController.popBackStack()
        }
    }
}

fun NavGraphBuilder.loginScreen(navigateCallback: (userId: String) -> Unit) {
    composable(AppRoute.LOGIN) {
        LoginRoute(navigateCallback = navigateCallback)
    }
}

fun NavGraphBuilder.dashboardScreen(action: (drug: AssociatedDrugEntity) -> Unit) {
    composable(
        AppRoute.DASHBOARD + "/{userId}",
        arguments = listOf(navArgument("userId") { type = NavType.StringType })
    ) {
        DashboardRoute(action = action)
    }
}

fun NavGraphBuilder.drugDetailScreen(backOnClick: () -> Unit) {
    composable(
        AppRoute.DRUG_DETAIL + "/{drug}", arguments = listOf(navArgument(
            "drug"
        ) {
            type = DrugDetailScreenArgs()
        })
    ) { entry ->

        DrugDetailRoute(
            Gson().fromJson(
                entry.arguments!!.getString("drug"),
                AssociatedDrugEntity::class.java
            ), backOnClick
        )
    }
}