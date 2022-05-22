package com.example.shopproject.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopproject.MainActivity
import com.example.shopproject.db.viewmodels.BasketEntityViewModel
import com.example.shopproject.model.customer.UserVm
import com.example.shopproject.model.invoice.InvoiceItem
import com.example.shopproject.model.invoice.PaymentTransaction
import com.example.shopproject.viewmodels.invoice.TransactionViewModel

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserPaymentScreen(
    navController: NavController,
    mainActivity: MainActivity,
    basketEntityViewModel: BasketEntityViewModel,
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    var firstName by remember { mutableStateOf(TextFieldValue()) }
    var firstNameError by rememberSaveable { mutableStateOf(false) }

    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var lastNameError by rememberSaveable { mutableStateOf(false) }

    var phone by remember { mutableStateOf(TextFieldValue()) }
    var phoneError by rememberSaveable { mutableStateOf(false) }

    var postalCode by remember { mutableStateOf(TextFieldValue()) }
    var postalCodeError by rememberSaveable { mutableStateOf(false) }

    var address by remember { mutableStateOf(TextFieldValue()) }
    var addressError by rememberSaveable { mutableStateOf(false) }

    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userNameError by rememberSaveable { mutableStateOf(false) }

    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    var stateScroll = rememberScrollState()

    Column() {
        Row(
            Modifier
                .height(54.dp)

        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }

            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(), text = "Complete Your Information",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }

        Column(

            Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .verticalScroll(enabled = true, state = stateScroll),
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            OutlinedTextField(value = firstName,
                onValueChange = {
                    firstName = it
                    firstNameError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "FirstName") },
                isError = firstNameError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    if (firstNameError) {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
                    }
                }
            )
            if (firstNameError) {
                Text(
                    text = "firstName cannot be Empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }
            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameError = false
                },
                label = { Text(text = "LastName") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                isError = lastNameError,
                trailingIcon = {
                    if (lastNameError) {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (lastNameError) {
                Text(
                    text = "lastName cannot be Empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }
            OutlinedTextField(
                value = phone,
                isError = phoneError,
                onValueChange = {
                    phone = it
                    phoneError = false
                },
                trailingIcon = {
                    if (phoneError) {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = "Phone") },
                modifier = Modifier.fillMaxWidth()
            )
            if (phoneError) {
                Text(
                    text = "phone cannot be Empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }
            OutlinedTextField(
                value = userName,
                onValueChange = {
                    userName = it
                    userNameError = false
                },
                isError = userNameError,
                trailingIcon = {
                    if (userNameError) {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = "UserName") },
                modifier = Modifier.fillMaxWidth()

            )
            if (userNameError) {
                Text(
                    text = "userName cannot be Empty",
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
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
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
            OutlinedTextField(
                value = postalCode,
                onValueChange = {
                    postalCode = it
                    postalCodeError = false
                },
                isError = postalCodeError,
                trailingIcon = {
                    if (postalCodeError) {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = "PostalCode") },
                modifier = Modifier.fillMaxWidth()
            )
            if (postalCodeError) {
                Text(
                    text = "postalCode cannot be Empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }
            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    addressError = false
                },
                isError = addressError,
                trailingIcon = {
                    if (addressError)
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Error")
                },
                label = { Text(text = "Address") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (addressError) {
                Text(
                    text = "address cannot be Empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            Button(
                onClick = {
                    if (firstName.text.isEmpty()) {
                        firstNameError = true
                    }
                    if (lastName.text.isEmpty()) {
                        lastNameError = true
                    }
                    if (phone.text.isEmpty()) {
                        phoneError = true
                    }
                    if (userName.text.isEmpty()) {
                        userNameError = true
                    }
                    if (password.text.isEmpty()) {
                        passwordError = true
                    }
                    if (postalCode.text.isEmpty()) {
                        postalCodeError = true
                    }
                    if (address.text.isEmpty()) {
                        addressError = true
                    }
                    val items = ArrayList<InvoiceItem>()
                    basketEntityViewModel.dataList.value.forEach {
                        items.add(InvoiceItem.convertFromBasket(basketEntity = it))
                    }
                    val userInfo = UserVm(
                        address = address.text,
                        firstName = firstName.text,
                        lastName = lastName.text,
                        password = password.text,
                        phone = phone.text,
                        username = userName.text,
                        postalCode = postalCode.text
                    )
                    val data = PaymentTransaction(items = items, user = userInfo)
                    transactionViewModel.goToPayment(data) { response ->
                        if (response.status == "OK") {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(response.data!![0]))
                            context.startActivity(intent)
                            mainActivity.finish()
                        }
                    }
                }, elevation = ButtonDefaults.elevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 32.dp, end = 32.dp, bottom = 32.dp, top = 32.dp)
            ) {
                Text(text = "$ Pay")
            }
        }
    }
}