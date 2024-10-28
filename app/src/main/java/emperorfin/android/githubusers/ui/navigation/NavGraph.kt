package emperorfin.android.githubusers.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import emperorfin.android.githubusers.ui.navigation.Destinations.ROUTE_USERS
import emperorfin.android.githubusers.ui.navigation.Destinations.ROUTE_USER_DETAILS
import emperorfin.android.githubusers.ui.navigation.ScreenArgs.SCREEN_USER_DETAILS_ID
import emperorfin.android.githubusers.ui.screen.users.UsersScreen


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_USERS,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
) {

    val userDetailsRouteWithArgs = "${ROUTE_USER_DETAILS}/{$SCREEN_USER_DETAILS_ID}"

    ProvideWindowInsets {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination,
        ) {

            composable(ROUTE_USERS) {
                UsersScreen(navigationActions = navActions)
            }

            composable(
                userDetailsRouteWithArgs,
                arguments = listOf(
                    navArgument(SCREEN_USER_DETAILS_ID) { type = NavType.StringType },
                )
            ) { backStackEntry ->

                val userId: String = backStackEntry.arguments?.getString(SCREEN_USER_DETAILS_ID)!!

//                UserDetailsScreen(
//                    navigationActions = navActions,
//                    userId = userId,
//                )
            }
        }
    }

}