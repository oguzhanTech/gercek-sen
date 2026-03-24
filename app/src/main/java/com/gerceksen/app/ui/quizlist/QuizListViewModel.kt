package com.gerceksen.app.ui.quizlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gerceksen.app.AppContainer
import com.gerceksen.app.model.Category
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizListUiState(
    val category: Category? = null,
    val quizzes: List<Quiz> = emptyList(),
)

class QuizListViewModel(
    private val categoryId: String,
    private val repository: QuizRepository = AppContainer.quizRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(QuizListUiState())
    val state: StateFlow<QuizListUiState> = _state.asStateFlow()

    init {
        val cat = repository.getCategories().find { it.id == categoryId }
        val list = repository.getQuizzesForCategory(categoryId)
        _state.value = QuizListUiState(category = cat, quizzes = list)
    }

    companion object {
        fun factory(categoryId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    QuizListViewModel(categoryId) as T
            }
    }
}
