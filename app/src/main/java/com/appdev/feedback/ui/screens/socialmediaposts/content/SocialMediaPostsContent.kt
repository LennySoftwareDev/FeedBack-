package com.appdev.feedback.ui.screens.socialmediaposts.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.components.CardComponent
import com.appdev.feedback.ui.components.OutlinedTextFieldComponent
import com.appdev.feedback.ui.components.TextComponent
import com.appdev.feedback.ui.models.CardData
import com.appdev.feedback.ui.navigation.Screen
import com.appdev.feedback.ui.screens.socialmediaposts.event.UIEventSocialMediaPost
import com.appdev.feedback.ui.screens.socialmediaposts.viewmodel.ViewModelSocialMediaPosts

@Composable
fun SocialMediaPostsContent(
    navController: NavController,
    viewModel: ViewModelSocialMediaPosts = hiltViewModel()
) {
    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoadingUsers.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val eventFlow = viewModel.eventFlow.collectAsState().value


    val filteredList = remember(posts, searchQuery) {
        viewModel.filterDatabaseListUserPost(posts, searchQuery)
    }

    LaunchedEffect(Unit) {
        posts
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextFieldComponent(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = "Buscar por titulo o número de identificación",
            singleLine = true,
            leadingIcon = Icons.Default.Search
        )

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (eventFlow) {
                is UIEventSocialMediaPost.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UIEventSocialMediaPost.Error -> {
                    Text(
                        text = "No hay publicaciones",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                is UIEventSocialMediaPost.UserPostData -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredList) { post ->
                            CardComponent(
                                navController = navController,
                                data = CardData.UserPostItem(post),
                                onClick = {
                                    navController.navigate(
                                        Screen.PostCommentsScreen.route
                                            .plus("/${post.id}")
                                    )
                                },
                                content = {
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        TextComponent(
                                            text = post.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                            maxLines = 2,
                                            overflow = TextOverflow.Clip
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        TextComponent(
                                            text = post.body,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 4,
                                            overflow = TextOverflow.Clip
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        TextComponent(
                                            text = "User ID: ${post.userId}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.secondary,
                                            overflow = TextOverflow.Clip
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
