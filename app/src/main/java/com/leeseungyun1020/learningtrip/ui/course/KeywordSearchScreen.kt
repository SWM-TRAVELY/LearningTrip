package com.leeseungyun1020.learningtrip.ui.course

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.home.TextListView
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.viewmodel.CourseRequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeywordSearchScreen(
    navController: NavController,
    courseRequestViewModel: CourseRequestViewModel = viewModel(
        viewModelStoreOwner = navController.previousBackStackEntry
            ?: LocalViewModelStoreOwner.current!!
    ),
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keywordList by courseRequestViewModel.searchedKeywordList.observeAsState()

    LearningTripScaffold(
        title = stringResource(id = R.string.app_name),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = {
            courseRequestViewModel.onKeywordClear()
            navController.popBackStack()
        },
        topBarExtraContent = {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    courseRequestViewModel.onKeywordChange(it)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .padding(
                        top = 8.dp
                    )
                    .fillMaxWidth(),
                placeholder = {
                    Row {
                        Icon(Icons.Filled.Search, stringResource(id = R.string.action_search))
                        Spacer(modifier = Modifier.padding(start = 4.dp))
                        Text(
                            text = stringResource(id = R.string.hint_search),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                },
                singleLine = true,
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                Icons.Filled.Clear,
                                stringResource(id = R.string.action_delete)
                            )
                        }
                    }
                },

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.background,
                    placeholderColor = Gray3
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }, bodyContent = {
            if (searchText.isNotEmpty()) {
                BackHandler {
                    searchText = ""
                    courseRequestViewModel.onKeywordClear()
                }
            }

            TextListView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textList = keywordList ?: listOf(),
                onTextClicked = { keyword ->
                    courseRequestViewModel.onKeywordAdd(keyword)
                    courseRequestViewModel.onKeywordClear()
                    navController.popBackStack()
                }
            )
        })
}