package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Heritage
import com.leeseungyun1020.learningtrip.ui.theme.Gray2

@Composable
fun HeritageDescriptionPage(
    modifier: Modifier = Modifier,
    heritage: Heritage,
    isOpen: Boolean = false,
    onOpenClicked: () -> Unit = {}
) {
    Column(modifier = modifier) {
        // Simple Description
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = stringResource(id = R.string.title_simple_desc),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = heritage.description, style = MaterialTheme.typography.bodySmall,
            color = Gray2,
            maxLines = if (!isOpen) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis
        )

        if (isOpen) {
            // Type
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(id = R.string.title_type),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = heritage.type, style = MaterialTheme.typography.bodySmall,
                color = Gray2,
            )

            // Category
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(id = R.string.title_category),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = heritage.category, style = MaterialTheme.typography.bodySmall,
                color = Gray2,
            )
        }

        IconButton(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            onClick = onOpenClicked
        ) {
            if (isOpen)
                Icon(
                    imageVector = Icons.Default.ExpandLess,
                    contentDescription = stringResource(id = R.string.action_expand_less)
                )
            else
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = stringResource(id = R.string.action_expand_more)
                )
        }
    }
}