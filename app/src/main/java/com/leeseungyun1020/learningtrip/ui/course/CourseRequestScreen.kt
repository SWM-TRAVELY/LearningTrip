package com.leeseungyun1020.learningtrip.ui.course

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
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
            .setTitleText("")
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
    // 기간
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
    // 옵션
    val options by courseRequestViewModel.options.observeAsState()
    if (options == null) {
        courseRequestViewModel.loadOptions()
    }
    // 지역
    var locationExpanded by remember { mutableStateOf(false) }
    var locationOptionExpanded by remember { mutableStateOf(false) }
    // 학년
    var gradeExpanded by remember { mutableStateOf(false) }
    var gradeOptionExpanded by remember { mutableStateOf(false) }

    if (options == null)
        courseRequestViewModel.loadOptions()

    LearningTripScaffold(
        title = stringResource(id = R.string.title_request_course),
    ) {
        Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
            // 기간
            Text(
                text = stringResource(id = R.string.title_select_date),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 32.dp),
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
            Text(
                text = stringResource(id = R.string.title_select_location),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 16.dp),
            )

            Row {
                ExposedDropdownMenuBox(
                    expanded = locationExpanded,
                    onExpandedChange = { locationExpanded = !locationExpanded },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = courseRequestViewModel.location,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = locationExpanded) },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = locationExpanded,
                        onDismissRequest = { locationExpanded = false },
                    ) {
                        options?.locations?.map { it.name }?.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    courseRequestViewModel.onUpdateLocation(selectionOption)
                                    locationExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = locationOptionExpanded,
                    onExpandedChange = { locationOptionExpanded = !locationOptionExpanded },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = courseRequestViewModel.locationOption,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = locationOptionExpanded) },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = locationOptionExpanded,
                        onDismissRequest = { locationOptionExpanded = false },
                    ) {
                        options?.locations?.find { it.name == courseRequestViewModel.location }?.options?.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    courseRequestViewModel.onUpdateLocationOption(selectionOption)
                                    locationOptionExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }

            // 학년
            Text(
                text = stringResource(id = R.string.title_select_grade),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 16.dp),
            )
            Row {
                ExposedDropdownMenuBox(
                    expanded = gradeExpanded,
                    onExpandedChange = { gradeExpanded = !gradeExpanded },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = courseRequestViewModel.grade,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = gradeExpanded) },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = gradeExpanded,
                        onDismissRequest = { gradeExpanded = false },
                    ) {
                        options?.grades?.map { it.name }?.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    courseRequestViewModel.onUpdateGrade(selectionOption)
                                    gradeExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = gradeOptionExpanded,
                    onExpandedChange = { gradeOptionExpanded = !gradeOptionExpanded },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = courseRequestViewModel.gradeOption,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = gradeOptionExpanded) },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = gradeOptionExpanded,
                        onDismissRequest = { gradeOptionExpanded = false },
                    ) {
                        options?.grades?.find { it.name == courseRequestViewModel.grade }?.options?.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    courseRequestViewModel.onUpdateGradeOption(selectionOption)
                                    gradeOptionExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
            // 키워드
            Text(
                text = stringResource(id = R.string.title_select_keyword),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 16.dp),
            )
            FlowRow {
                courseRequestViewModel.keywordList.forEach { keyword ->
                    AssistChip(
                        onClick = { courseRequestViewModel.onKeywordDelete(keyword) },
                        label = { Text(keyword) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(id = R.string.action_delete),
                                modifier = Modifier.size(AssistChipDefaults.IconSize)
                            )
                        },
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }
            IconButton(onClick = {
                navController.navigate(Screen.KeywordSearchScreen.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.action_add)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                onClick = {
                    courseRequestViewModel.onRequestCourse {
                        navController.navigate(Screen.RecommendedCourse.route)
                    }
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = stringResource(id = R.string.action_recommend_course))
            }
        }
    }
}