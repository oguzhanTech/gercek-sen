package com.gerceksen.app.ui.quizplay

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerceksen.app.model.AnswerOption
import com.gerceksen.app.ui.components.ProgressHeader
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizPlayScreen(
    quizId: String,
    onBack: () -> Unit,
    onFinished: (resultId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuizPlayViewModel = viewModel(
        key = quizId,
        factory = QuizPlayViewModel.factory(quizId),
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val quiz = state.quiz

    LaunchedEffect(state.finishedResultId) {
        val id = state.finishedResultId ?: return@LaunchedEffect
        delay(180)
        onFinished(id)
        viewModel.clearFinishedEvent()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = quiz?.title ?: "Quiz",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (viewModel.currentQuestionIndex > 0) viewModel.goBackOneQuestion()
                            else onBack()
                        },
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                    }
                },
            )
        },
    ) { padding ->
        if (quiz == null) {
            Text("Quiz bulunamadı.", modifier = Modifier.padding(padding))
            return@Scaffold
        }

        val index = state.answers.size
        val total = quiz.questions.size

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                val step = if (index >= total) total else index + 1
                ProgressHeader(current = step, total = total)
            }
            item {
                if (index >= total) {
                    Text(
                        text = "Sonucun hazırlanıyor…",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                } else {
                    AnimatedContent(
                        targetState = index,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "question",
                    ) { i ->
                        val q = quiz.questions.getOrNull(i) ?: return@AnimatedContent
                        val showImageSlot = q.options.any { opt -> opt.imageResId != null }
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                text = q.text,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start,
                            )
                            Spacer(Modifier.height(20.dp))
                            q.options.forEachIndexed { optIdx, opt ->
                                FilledTonalButton(
                                    onClick = {
                                        viewModel.selectOption(optIdx)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 56.dp),
                                    shape = MaterialTheme.shapes.large,
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp),
                                ) {
                                    OptionButtonContent(
                                        option = opt,
                                        showImageSlot = showImageSlot,
                                    )
                                }
                                Spacer(Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OptionButtonContent(
    option: AnswerOption,
    showImageSlot: Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        if (showImageSlot) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
            ) {
                val resId = option.imageResId
                if (resId != null) {
                    Image(
                        painter = painterResource(resId),
                        contentDescription = option.text,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        Text(
            text = option.text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
