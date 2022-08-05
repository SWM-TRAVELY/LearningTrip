package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SmallTextButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier
                .width(14.dp)
                .height(14.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SmallTextButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    onClick: () -> Unit
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier
                .width(14.dp)
                .height(14.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}