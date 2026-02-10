package com.rtllabs.tddtictactoe.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rtllabs.tddtictactoe.domain.entity.Player

@Composable
fun Cell(player: Player?, onCellClick: () -> Unit) {

    val cellValue = player
    val backgroundColor = when (cellValue) {
        Player.X -> Color(0xFFBBDEFB) // light blue
        Player.O -> Color(0xFFFFCDD2) // light red
        else -> Color.White
    }
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor)
            .border(2.dp, Color.Black)
            .clickable(enabled = player == null) {
                onCellClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cellValue?.name ?: "",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}