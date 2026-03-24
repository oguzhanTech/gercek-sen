package com.gerceksen.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gerceksen.app.model.Quiz

@Composable
fun QuizCoverBox(
    quiz: Quiz,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 18.dp,
    showEmojiFallback: Boolean = true,
) {
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        val resId = quiz.coverImageResId
        if (resId != null) {
            Image(
                painter = painterResource(resId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = quizListPlaceholderBrush(quiz.id)),
            )
            if (showEmojiFallback) {
                Text(
                    text = "❓",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }
    }
}
