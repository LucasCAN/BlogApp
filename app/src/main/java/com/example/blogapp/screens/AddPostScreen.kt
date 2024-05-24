package com.example.blogapp.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blogapp.R
import com.example.blogapp.components.ButtonAdd
import com.example.blogapp.viewmodel.BlogUIEvent
import com.example.blogapp.viewmodel.BlogViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(navController: NavController, blogViewModel: BlogViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    val date = LocalDateTime.now().format(formatter)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
                title = {
                    Text(
                        text = stringResource(R.string.blog_app),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        },
        content = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
//                        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp)),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())

                        Text(
                            text = stringResource(R.string.adicionar_post),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp, top = 8.dp),
                            textAlign = TextAlign.Start
                        )
                        Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = title,
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                            onValueChange = { title = it },
                            maxLines = 2,
                            label = { Text(stringResource(R.string.titulo)) }
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            value = description,
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                            onValueChange = { description = it },
                            label = { Text(stringResource(R.string.descricao)) }
                        )
                    }
                }
            }
        },
        bottomBar = {
            ButtonAdd(onButtonClicked = {
                validatePost(title, description, onInvalidate = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.valor_vazio, context.getString(it)),
                        Toast.LENGTH_LONG
                    ).show()
                }, onValidate = {
                    blogViewModel.onEvent(
                        BlogUIEvent.AddNewPost(
                            title.trim(),
                            date,
                            description.trim(),
                            onNavigate = {
                                navController.popBackStack()
                                Toast.makeText(
                                    context,
                                    "Post adicionado! -> $title",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            context
                        )
                    )
                })
            })
        }
    )
}

private fun validatePost(
    title: String,
    description: String,
    onInvalidate: (Int) -> Unit,
    onValidate: () -> Unit
) {
    if (title.isEmpty()) {
        onInvalidate(R.string.titulo_do_post)
        return
    }
    if (description.isEmpty()) {
        onInvalidate(R.string.descricao_do_post)
        return
    }
    onValidate()
}
