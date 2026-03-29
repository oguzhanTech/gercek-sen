package com.gerceksen.app.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.ui.components.CategoryVisual
import com.gerceksen.app.ui.components.GercekSenCard
import com.gerceksen.app.ui.components.QuizTagChip
import com.gerceksen.app.ui.components.QuizCoverBox
import com.gerceksen.app.ui.theme.AccentViolet
import com.gerceksen.app.ui.theme.CoralPrimary

private val HorizontalScreenPadding = 20.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onCategoryClick: (String) -> Unit,
    onQuizClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.factory()),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val outlineSoft = MaterialTheme.colorScheme.outline.copy(alpha = 0.22f)

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            HomeHero()
        }

        if (state.popularQuizzes.isNotEmpty()) {
            item {
                SectionLabel(text = "Popüler quizler")
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = HorizontalScreenPadding),
                ) {
                    items(state.popularQuizzes, key = { it.id }) { quiz ->
                        PopularQuizMiniCard(quiz = quiz, onClick = { onQuizClick(quiz.id) })
                    }
                }
            }
        }

        item {
            SectionLabel(text = "Kategori seç")
        }

        items(state.categories, key = { it.category.id }) { row ->
            GercekSenCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = HorizontalScreenPadding),
                onClick = { onCategoryClick(row.category.id) },
                border = BorderStroke(1.dp, outlineSoft),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    CategoryVisual(icon = row.category.iconGlyph, size = 64.dp)
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = row.category.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = row.category.shortDescription,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "${row.quizCount} quiz",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeHero() {
    val surface = MaterialTheme.colorScheme.background
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CoralPrimary.copy(alpha = 0.14f),
                        AccentViolet.copy(alpha = 0.08f),
                        surface,
                    ),
                ),
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalScreenPadding)
                .padding(top = 28.dp, bottom = 16.dp),
        ) {
            Text(
                text = "Gerçek Sen",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Kısa quizler, net sonuçlar.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalScreenPadding)
                .padding(bottom = 6.dp)
                .height(2.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            CoralPrimary.copy(alpha = 0.0f),
                            CoralPrimary.copy(alpha = 0.55f),
                            AccentViolet.copy(alpha = 0.45f),
                            CoralPrimary.copy(alpha = 0.0f),
                        ),
                    ),
                    shape = RoundedCornerShape(1.dp),
                ),
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(horizontal = HorizontalScreenPadding),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PopularQuizMiniCard(
    quiz: Quiz,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(220.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        QuizCoverBox(
            quiz = quiz,
            modifier = Modifier.size(112.dp),
            cornerRadius = 20.dp,
            showEmojiFallback = false,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = quiz.title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.height(6.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            quiz.tags.take(2).forEach { QuizTagChip(tag = it) }
        }
    }
}
