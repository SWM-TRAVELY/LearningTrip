package com.leeseungyun1020.learningtrip.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel = viewModel()
) {
    val category by categoryViewModel.category.observeAsState()
    if (category == null)
        categoryViewModel.loadCategory()

    LearningTripScaffold(
        title = stringResource(id = R.string.nav_category),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            val categoryList = category ?: emptyList()
            items(count = categoryList.size) {
                var isOpen by remember { mutableStateOf(false) }
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isOpen = !isOpen }
                    ) {
                        Text(
                            text = categoryList[it].name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.Center)
                        )
                        if (isOpen) {
                            Icon(
                                imageVector = Icons.Default.ExpandLess,
                                contentDescription = stringResource(id = R.string.action_expand_less),
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.ExpandMore,
                                contentDescription = stringResource(id = R.string.action_expand_more),
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    if (isOpen) {
                        for (option in categoryList[it].options) {
                            Box(modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondary)
                                .fillMaxWidth()
                                .clickable { navController.navigate("${Screen.Search.root}/${categoryList[it].name} $option") }) {
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.Center),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}