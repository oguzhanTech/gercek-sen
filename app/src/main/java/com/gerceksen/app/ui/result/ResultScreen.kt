package com.gerceksen.app.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerceksen.app.data.buildShareText
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.ui.components.accentStyle
import com.gerceksen.app.ui.components.QuizCoverBox
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    quizId: String,
    resultId: String,
    onBack: () -> Unit,
    onRedoQuiz: () -> Unit,
    onOpenQuiz: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ResultViewModel = viewModel(
        key = "$quizId-$resultId",
        factory = ResultViewModel.factory(quizId, resultId),
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val quiz = state.quiz
    val result = state.result

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Sonucun", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                    }
                },
            )
        },
    ) { padding ->
        if (quiz == null || result == null) {
            Text(
                "Sonuç yüklenemedi.",
                modifier = Modifier.padding(padding).padding(20.dp),
            )
            return@Scaffold
        }

        val style = accentStyle(result.accent)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(style.container)
                    .padding(24.dp),
            ) {
                Text(
                    text = result.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = style.primary,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = result.subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = result.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Button(
                    onClick = {
                        val text = buildShareText(quiz.title, result)
                        ShareCompat.IntentBuilder(context)
                            .setType("text/plain")
                            .setText(text)
                            .setChooserTitle("Sonucunu paylaş")
                            .startChooser()
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(Icons.Filled.Share, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Paylaş")
                }
                FilledTonalButton(
                    onClick = onRedoQuiz,
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Yeniden")
                }
            }

            Spacer(Modifier.height(24.dp))
            Text(
                text = "Bu sonuç sana uygun mu?",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Geri bildirimin için teşekkürler!")
                        }
                    },
                ) {
                    Icon(Icons.Filled.ThumbUp, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Uyuyor")
                }
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Not aldık — içerikleri geliştireceğiz.")
                        }
                    },
                ) {
                    Text("👎", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.width(6.dp))
                    Text("Az")
                }
            }

            Spacer(Modifier.height(28.dp))
            Text(
                text = "Bunları da deneyebilirsin",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(end = 8.dp),
            ) {
                items(state.recommendations, key = { it.id }) { q ->
                    RecommendationMiniCard(quiz = q, onClick = { onOpenQuiz(q.id) })
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun RecommendationMiniCard(
    quiz: Quiz,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .size(width = 160.dp, height = 148.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .clickable(onClick = onClick)
            .padding(12.dp),
    ) {
        QuizCoverBox(
            quiz = quiz,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            cornerRadius = 14.dp,
            showEmojiFallback = false,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = quiz.title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
