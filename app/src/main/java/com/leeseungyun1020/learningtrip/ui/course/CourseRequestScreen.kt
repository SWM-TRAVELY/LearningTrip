package com.leeseungyun1020.learningtrip.ui.course

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.viewmodel.CourseRequestViewModel
import java.util.*

@Composable
fun rememberFragmentManager(): FragmentManager {
    val context = LocalContext.current
    return remember(context) {
        (context as FragmentActivity).supportFragmentManager
    }
}

@Composable
fun rememberDatePickerDialog(
    startInMilliseconds: Long,
    endInMilliseconds: Long,
    onDateSelected: (Long, Long) -> Unit = { start, end -> },
): MaterialDatePicker<androidx.core.util.Pair<Long, Long>> {
    val dateRangePicker = remember {
        MaterialDatePicker.Builder.dateRangePicker()
            .setSelection(
                androidx.core.util.Pair(
                    startInMilliseconds,
                    endInMilliseconds
                )
            )
            .build()
    }

    DisposableEffect(dateRangePicker) {
        val listener =
            MaterialPickerOnPositiveButtonClickListener<androidx.core.util.Pair<Long, Long>> {
                if (it != null) onDateSelected(it.first, it.second)
            }
        dateRangePicker.addOnPositiveButtonClickListener(listener)
        onDispose {
            dateRangePicker.removeOnPositiveButtonClickListener(listener)
        }
    }

    return dateRangePicker
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseRequestScreen(
    navController: NavController,
    courseRequestViewModel: CourseRequestViewModel = viewModel()
) {
    // 날짜
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    val start = courseRequestViewModel.start
    val end = courseRequestViewModel.end
    val fragmentManager = rememberFragmentManager()

    val datePicker = rememberDatePickerDialog(
        startInMilliseconds = start,
        endInMilliseconds = end,
    ) { start, end ->
        courseRequestViewModel.onUpdateRange(start, end)
    }

    Text(
        text = stringResource(id = R.string.title_select_date),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
    )
    OutlinedTextField(
        value = "${dateFormat.format(start)} ~ ${dateFormat.format(end)}",
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        readOnly = true,
        trailingIcon = {
            IconButton({ datePicker.show(fragmentManager, "DatePicker") }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }
        },
    )
    // 지역

    // 학년

    // 키워드

}