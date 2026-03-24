package com.gerceksen.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gerceksen.app.ui.home.HomeScreen
import com.gerceksen.app.ui.quizlist.QuizListScreen
import com.gerceksen.app.ui.quizplay.QuizPlayScreen
import com.gerceksen.app.ui.result.ResultScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier,
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onCategoryClick = { navController.navigate(Routes.quizList(it)) },
                onQuizClick = { navController.navigate(Routes.quizPlay(it)) },
            )
        }
        composable(
            route = Routes.QUIZ_LIST,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.StringType },
            ),
        ) { entry ->
            val categoryId = entry.arguments?.getString("categoryId") ?: return@composable
            QuizListScreen(
                categoryId = categoryId,
                onBack = { navController.popBackStack() },
                onQuizClick = { navController.navigate(Routes.quizPlay(it)) },
            )
        }
        composable(
            route = Routes.QUIZ_PLAY,
            arguments = listOf(
                navArgument("quizId") { type = NavType.StringType },
            ),
        ) { entry ->
            val quizId = entry.arguments?.getString("quizId") ?: return@composable
            QuizPlayScreen(
                quizId = quizId,
                onBack = { navController.popBackStack() },
                onFinished = { resultId ->
                    navController.navigate(Routes.result(quizId, resultId)) {
                        popUpTo(Routes.HOME) { inclusive = false }
                    }
                },
            )
        }
        composable(
            route = Routes.RESULT,
            arguments = listOf(
                navArgument("quizId") { type = NavType.StringType },
                navArgument("resultId") { type = NavType.StringType },
            ),
        ) { entry ->
            val quizId = entry.arguments?.getString("quizId") ?: return@composable
            val resultId = entry.arguments?.getString("resultId") ?: return@composable
            ResultScreen(
                quizId = quizId,
                resultId = resultId,
                onBack = { navController.popBackStack() },
                onRedoQuiz = {
                    navController.navigate(Routes.quizPlay(quizId)) {
                        popUpTo(Routes.HOME) { inclusive = false }
                    }
                },
                onOpenQuiz = { newQuizId ->
                    navController.navigate(Routes.quizPlay(newQuizId)) {
                        popUpTo(Routes.HOME) { inclusive = false }
                    }
                },
            )
        }
    }
}
