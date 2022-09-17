package com.leeseungyun1020.learningtrip.ui.account

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.theme.Gray1
import com.leeseungyun1020.learningtrip.ui.theme.Gray3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes hintId: Int,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = stringResource(id = hintId),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Gray1,
            placeholderColor = Gray3
        ),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpTextFieldPrev() {
    SignUpTextField(
        "SOME",
        {},
        R.string.hint_email
    )
}