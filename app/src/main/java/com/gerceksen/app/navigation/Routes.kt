package com.gerceksen.app.navigation

object Routes {
    const val HOME = "home"
    const val QUIZ_LIST = "quiz_list/{categoryId}"
    const val QUIZ_PLAY = "quiz_play/{quizId}"
    const val RESULT = "result/{quizId}/{resultId}"

    fun quizList(categoryId: String) = "quiz_list/$categoryId"
    fun quizPlay(quizId: String) = "quiz_play/$quizId"
    fun result(quizId: String, resultId: String) = "result/$quizId/$resultId"
}
