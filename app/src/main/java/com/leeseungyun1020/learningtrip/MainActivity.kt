package com.leeseungyun1020.learningtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.ui.theme.Primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningTripTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var searchText by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(
                    color = Primary,
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
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
                            Text(text = stringResource(id = R.string.hint_search))
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
                        containerColor = Color.White,
                        placeholderColor = Gray3
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                // Keyword List
                LazyRow(
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        start = 16.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Box(
                            modifier = Modifier
                                .width(136.dp)
                                .height(136.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(color = Color.Black),
                        ) {
                            // Keyword Image
                            Text(
                                text = list[it],
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.BottomEnd),
                                color = Color.White,
                            )
                        }

                    }
                }

                // Introduce(Banner) Image
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(104.dp)
                        .background(color = Gray3),
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                // Place List
                Text(
                    text = "주변", modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                    fontSize = 16.sp, color = Gray2
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(listOf<String>("a", "b", "c", "d", "e")) { char ->
                        Box(
                            modifier = Modifier
                                .height(128.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(color = Color.Black),
                        ) {
                            Text(
                                text = char,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.BottomEnd),
                                color = Color.White,
                            )
                        }

                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningTripTheme {
        HomeScreen()
    }
}