package com.appdev.feedback.ui.screens.postcomments.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.models.PostComment
import com.appdev.feedback.ui.screens.postcomments.viewmodel.PostCommentViewModel
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostCommentsContent(
    viewModel: PostCommentViewModel = hiltViewModel(),
    postId: String
) {

    Text(text = viewModel.postComments.collectAsState().value.toString())

    LaunchedEffect(postId) {
        viewModel.getPostCommentsById(postId.toInt())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AddCommentPostDialog(
            show = viewModel.showDialog.collectAsState().value,
            onDismiss = viewModel::onDialogClose,
            onCommentAdded = { commentDto -> viewModel.onCommentCreated(commentDto) }
        )
        CommentItem(viewModel)

        FabDialog(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            viewModel = viewModel
        )
    }
}

@Composable
fun CommentItem(viewModel: PostCommentViewModel) {

    val comments = viewModel.postComments.collectAsState().value

    LazyColumn {
        items(comments.size) {

            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(text = comments[it].postId.toString(), fontWeight = FontWeight.Bold)
                        Text(text = comments[it].body)
                    }
                }
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

@Composable
fun AddCommentPostDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onCommentAdded: (String) -> Unit,
) {
    var comment by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = "Deja tu comentario",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = comment,
                    onValueChange = { comment = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onCommentAdded(comment)
                    comment = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir comentario")
                }
            }
        }
    }

}



