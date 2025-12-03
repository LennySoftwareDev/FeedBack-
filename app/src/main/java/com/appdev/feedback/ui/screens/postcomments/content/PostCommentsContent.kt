package com.appdev.feedback.ui.screens.postcomments.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.models.PostComment
import com.appdev.feedback.ui.screens.postcomments.viewmodel.PostCommentViewModel

@Composable
fun PostCommentsContent(
    navController: NavController,
    viewModel: PostCommentViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {

        FabDialog(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            viewModel = viewModel
        )
    }
}

@Composable
fun CommentItem(comment: PostComment, viewModel: PostCommentViewModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {
                Text(text = comment.postId.toString(), fontWeight = FontWeight.Bold)
                Text(text = comment.body)
            }
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, viewModel: PostCommentViewModel) {
    FloatingActionButton(onClick = {
        viewModel.onShowDialog()
    }, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}



