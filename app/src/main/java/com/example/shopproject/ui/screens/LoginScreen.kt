package com.example.shopproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopproject.db.viewmodels.UserEntityViewModel
import com.example.shopproject.model.customer.UserVm
import com.example.shopproject.viewmodels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController,userEntityViewModel: UserEntityViewModel ,userViewModel: UserViewModel = hiltViewModel()) {
    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userNameError by rememberSaveable { mutableStateOf(false) }

    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    var contenxt = LocalContext.current

    val isFormValid by derivedStateOf {
        userName.text.isNotBlank() && password.text.length >= 3
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primaryVariant) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    color = Color.White
                )
            }

            Card(
                Modifier
                    .weight(2f)
                    .padding(8.dp), shape = RoundedCornerShape(32.dp)
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Wellcome Back", fontSize = 22.sp)
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(value = userName,
                            onValueChange = {
                                userName = it
                                userNameError = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "UserName") },
                            isError = userNameError,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            trailingIcon = {
                                if (userName.text.isNotBlank()) {
                                    IconButton(onClick = {

                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "clear"
                                        )
                                    }
                                }
                                if (userNameError) {
                                    Icon(
                                        imageVector = Icons.Filled.Warning,
                                        contentDescription = "Error"
                                    )
                                }
                            }
                        )
                        if (userNameError) {
                            Text(
                                text = "firstName cannot be Empty",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                            )
                        }
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError = false
                            },
                            isError = passwordError,
                            label = { Text(text = "Password") },
                            singleLine = true,
                            visualTransformation = if (passwordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            trailingIcon = {
                                val image = if (passwordVisibility)
                                    Icons.Filled.KeyboardArrowUp
                                else Icons.Filled.KeyboardArrowDown
                                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                    Icon(imageVector = image, contentDescription = "")
                                }
                                if (passwordError) {
                                    Icon(
                                        imageVector = Icons.Filled.Warning,
                                        contentDescription = "Error"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()

                        )
                        if (passwordError) {
                            Text(
                                text = "password cannot be Empty",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                userViewModel.login(
                                    UserVm(
                                        username = userName.text,
                                        password = password.text
                                    )
                                ) { response ->
                                    if (response.status == "OK") {
                                        Toast.makeText(
                                            contenxt,
                                            "${response.data!![0].firstName}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val user = response.data!![0]
                                        CoroutineScope(Dispatchers.IO).launch {
                                            userEntityViewModel.addUser(user.convertToEntity())
                                        }
                                        navController.popBackStack()
                                    }
                                }
                            },
                            shape = RoundedCornerShape(16.dp),
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Log in", color = Color.White)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "Sing up")
                            }
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "Forgot Password")
                            }
                        }
                    }

                }

            }
        }
    }

}