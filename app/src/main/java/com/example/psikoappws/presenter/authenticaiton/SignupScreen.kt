package com.example.psikoappws.presenter.authenticaiton

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psikoappws.R
import com.example.psikoappws.domain.util.Resource
import com.example.psikoappws.presenter.bottombarNav.BottomBarNavigation
import com.example.psikoappws.presenter.graph.AuthScreen
import com.example.psikoappws.presenter.graph.Graph
import com.example.psikoappws.ui.theme.Shapes


@Composable
fun SignUpScreen(
    navController: NavController,
    //onClick: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val passwordFocusRequester = FocusRequester()
    val focusManager : FocusManager = LocalFocusManager.current
    val ctx = LocalContext.current

    var email: String by remember{ mutableStateOf("") }
    var name: String by remember{ mutableStateOf("") }
    var password: String by remember{ mutableStateOf("") }

    var confirmPassword: String by remember{ mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordVisibilityForConfirm by remember { mutableStateOf(false) }

    val authResource = viewModel?.signupFlow?.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffFBFCF8)),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier

                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.psikologo),
                contentDescription = "login",
                modifier = Modifier
                    .size(150.dp)
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
                    .shadow(5.dp)
            )

            //Name
            TextField(
                value = name,
                onValueChange = {name = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, CircleShape)
                    .focusRequester(focusRequester = FocusRequester()),
                label = { Text(text = InputTypeSignUp.Name.label) },
                shape = Shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                singleLine = true,
                keyboardOptions = InputTypeSignUp.Name.keyboardOptions,
                visualTransformation = InputTypeSignUp.Name.visualTransformation,
                keyboardActions = KeyboardActions(
                    onNext = {passwordFocusRequester.requestFocus()})
            )

            //Email
            TextField(
                value = email,
                onValueChange = {email = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, CircleShape)
                    .focusRequester(focusRequester = FocusRequester()),
                label = { Text(text = InputTypeSignUp.Email.label) },
                shape = Shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                singleLine = true,
                keyboardOptions = InputTypeSignUp.Email.keyboardOptions,
                visualTransformation = InputTypeSignUp.Email.visualTransformation,
                keyboardActions = KeyboardActions(
                    onNext = {passwordFocusRequester.requestFocus()})

            )

            //Password

            TextField(
                value = password,
                onValueChange = {password = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, CircleShape)
                    .focusRequester(focusRequester = passwordFocusRequester),
                label = { Text(text = InputTypeSignUp.Password.label) },
                shape = Shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                singleLine = true,
                keyboardOptions = InputTypeSignUp.Password.keyboardOptions,
                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),

                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(painter = painterResource(R.drawable.password_eye),
                            contentDescription = null,
                            tint = if(passwordVisibility) Color.DarkGray else Color.Gray)
                    }
                }
            )
            //ConfirmPAssword
            TextField(
                value = confirmPassword,
                onValueChange = {confirmPassword = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, CircleShape)
                    .focusRequester(focusRequester = passwordFocusRequester),
                label = { Text(text = InputTypeSignUp.ConfirmPassword.label) },
                shape = Shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                singleLine = true,
                keyboardOptions = InputTypeSignUp.ConfirmPassword.keyboardOptions,
                visualTransformation = if(passwordVisibilityForConfirm) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibilityForConfirm = !passwordVisibilityForConfirm }) {
                        Icon(painter = painterResource(R.drawable.password_eye),
                            contentDescription = null,
                            tint = if(passwordVisibilityForConfirm) Color.DarkGray else Color.Gray)
                    }
                }
            )


            //Button
            Button(
                onClick = {
                    if(password.equals(confirmPassword)){
                        viewModel.signUpUser(name, email, password)
                        navController.navigate(AuthScreen.Login.route)
                        Toast.makeText(ctx,"The registration is successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(ctx,"Confirmation Password is not same as the Password you entered\n\nPlease check again", Toast.LENGTH_SHORT).show()
                    }

                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, CircleShape),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF4E4F50),
                    contentColor = Color(0xFFE2DED0)
                )
            ) {
                Text(text = "SIGN UP", modifier = Modifier.padding(vertical = 8.dp))
            }

        }
        authResource?.value?.let{
            when(it){
                is Resource.Error -> {
                    Log.e("error" , "error in login")
                }
                is Resource.Loading -> {
                    //CircularProgressIndicator( modifier = Modifier.)
                    Log.e("loading", "login is loading")
                }
                is Resource.Success -> {
                    LaunchedEffect("",Unit){
                        navController.navigate(Graph.HOME){
                            popUpTo("signup_screen"){inclusive = true}
                        }
                    }
                }
            }
        }

    }

    authResource?.value?.let{
        when(it){
            is Resource.Error -> {
                Toast.makeText(ctx,"Email or password might be wrong or cannot be empty", Toast.LENGTH_SHORT).show()
            }
            is
            Resource.Loading -> {

            }
            is Resource.Success -> {
                LaunchedEffect(Unit){
                    navController.navigate(AuthScreen.Login.route){
                        popUpTo(AuthScreen.SignUp.route){inclusive=true}
                    }
                }
            }
        }

    }


}

    sealed class InputTypeSignUp(
        val label : String,
        val keyboardOptions: KeyboardOptions,
        val visualTransformation: VisualTransformation
    ){
        object Email : InputTypeSignUp(
            label = "Email",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
            visualTransformation = VisualTransformation.None
        )
        object Password : InputTypeSignUp(
            label = "Password",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        object ConfirmPassword : InputTypeSignUp(
            label = "Confirm Password",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        object Name : InputTypeSignUp(
            label = "Name",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
            visualTransformation = VisualTransformation.None
        )

    }

