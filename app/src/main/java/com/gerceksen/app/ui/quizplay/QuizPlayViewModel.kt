package com.gerceksen.app.ui.quizplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gerceksen.app.AppContainer
import com.gerceksen.app.data.resolveQuizResult
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuizPlayUiState(
    val quiz: Quiz? = null,
    /** Indices of selected options per question answered so far. */
    val answers: List<Int> = emptyList(),
    /** Set when last answer submitted; triggers one-shot navigation. */
    val finishedResultId: String? = null,
)

class QuizPlayViewModel(
    private val quizId: String,
    private val repository: QuizRepository = AppContainer.quizRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(QuizPlayUiState())
    val state: StateFlow<QuizPlayUiState> = _state.asStateFlow()

    init {
        val q = repository.getQuizById(quizId)
        _state.value = QuizPlayUiState(quiz = q, answers = emptyList(), finishedResultId = null)
    }

    val currentQuestionIndex: Int
        get() = _state.value.answers.size

    fun selectOption(optionIndex: Int) {
        _state.update { s ->
            val quiz = s.quiz ?: return@update s
            if (s.answers.size >= quiz.questions.size || s.finishedResultId != null) return@update s
            val nextAnswers = s.answers + optionIndex
            if (nextAnswers.size == quiz.questions.size) {
                val resultId = resolveQuizResult(quiz, nextAnswers)
                s.copy(answers = nextAnswers, finishedResultId = resultId)
            } else {
                s.copy(answers = nextAnswers)
            }
        }
    }

    fun clearFinishedEvent() {
        _state.update { it.copy(finishedResultId = null) }
    }

    fun goBackOneQuestion() {
        _state.update { s ->
            if (s.answers.isEmpty() || s.finishedResultId != null) s
            else s.copy(answers = s.answers.dropLast(1))
        }
    }

    companion object {
        fun factory(quizId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    QuizPlayViewModel(quizId) as T
            }
    }
}
