package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.model.SimplePlaceReview
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily

@Composable
fun MyReviewScreen(navController: NavController) {
    val editableReviewPlaces = listOf(
        SimplePlace(1, "관광지명", 14, "서울시 강남구", "imageURL"),
        SimplePlace(2, "관광지명", 14, "서울시 강남구", "imageURL"),
    )
    val writtenReviews = listOf(
        SimplePlaceReview(3, "imageURL", "placeName", 4.1, "imageURL", "reviewContent"),
    )
    var openReviewDeleteDialog by remember { mutableStateOf(false) }
    if (openReviewDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                openReviewDeleteDialog = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.action_delete_review),
                    fontFamily = notoSansKRFamily
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.desc_delete_review),
                    fontFamily = notoSansKRFamily
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openReviewDeleteDialog = false
                        /*TODO: Delete review*/
                    }
                ) {
                    Text(
                        stringResource(id = R.string.action_confirm),
                        fontFamily = notoSansKRFamily
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openReviewDeleteDialog = false
                    }
                ) {
                    Text(
                        stringResource(id = R.string.action_cancel),
                        fontFamily = notoSansKRFamily
                    )
                }
            }
        )
    }
    LearningTripScaffold(
        title = stringResource(id = R.string.title_my_review),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() }
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.title_editable_review),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(start = 20.dp, top = 14.dp)
            )
            LazyColumn {
                items(editableReviewPlaces) { place ->
                    EditableReviewTab(
                        simplePlace = place,
                        onReviewButtonClick = { navController.navigate("${Screen.AddReview.root}/${place.id}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("${Screen.Place.root}/${place.id}") }
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                    )
                    Divider(thickness = 6.dp)
                }
            }


            Text(
                text = stringResource(id = R.string.title_written_review),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(start = 20.dp, top = 14.dp)
            )
            LazyColumn {
                items(writtenReviews) { review ->
                    WrittenReviewTab(
                        simplePlaceReview = review,
                        onDeleteReviewButtonClick = {
                            openReviewDeleteDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("${Screen.Place.root}/${review.placeId}") }
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                    )
                    Divider(thickness = 6.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyReviewScreenPrev() {
    MyReviewScreen(rememberNavController())
}