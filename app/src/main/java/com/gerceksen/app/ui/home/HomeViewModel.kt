package com.gerceksen.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gerceksen.app.AppContainer
import com.gerceksen.app.model.Category
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CategoryRow(
    val category: Category,
    val quizCount: Int,
)

data class HomeUiState(
    val categories: List<CategoryRow> = emptyList(),
    val popularQuizzes: List<Quiz> = emptyList(),
)

class HomeViewModel(
    private val repository: QuizRepository = AppContainer.quizRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        val rows = repository.getCategories().map { c ->
            CategoryRow(c, repository.getQuizzesForCategory(c.id).size)
        }
        _state.value = HomeUiState(
            categories = rows,
            popularQuizzes = repository.getPopularQuizzes(4),
        )
    }

    companion object {
        fun factory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                HomeViewModel() as T
        }
    }
}
