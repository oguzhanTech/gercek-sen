package com.gerceksen.app.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gerceksen.app.AppContainer
import com.gerceksen.app.data.ResultReflectionCatalog
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.model.QuizResultDefinition
import com.gerceksen.app.model.ResultReflectionContent
import com.gerceksen.app.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ResultUiState(
    val quiz: Quiz? = null,
    val result: QuizResultDefinition? = null,
    val reflection: ResultReflectionContent? = null,
    val recommendations: List<Quiz> = emptyList(),
)

class ResultViewModel(
    private val quizId: String,
    private val resultId: String,
    private val repository: QuizRepository = AppContainer.quizRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ResultUiState())
    val state: StateFlow<ResultUiState> = _state.asStateFlow()

    init {
        val quiz = repository.getQuizById(quizId)
        val def = quiz?.resultDefinitions?.find { it.id == resultId }
        _state.value = ResultUiState(
            quiz = quiz,
            result = def,
            reflection = ResultReflectionCatalog.get(resultId),
            recommendations = repository.getRecommendations(quizId, 3),
        )
    }

    companion object {
        fun factory(quizId: String, resultId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    ResultViewModel(quizId, resultId) as T
            }
    }
}
