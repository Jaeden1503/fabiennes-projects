package com.example.safehome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.safehome.data.Biometric
import com.example.safehome.ui.theme.SafeHomeTheme

class LoginActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            SafeHomeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Image(
                            painter = painterResource(id = R.drawable.logo_without_text),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(top = 40.dp))

                        Spacer(modifier = Modifier.height(40.dp))

                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = (Icons.Default.AccountCircle).toString(),
                            tint = colorResource(id = R.color.dark_blue),
                            modifier = Modifier.size(200.dp, 200.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        //Login button, navigates to the home page
                        Button(onClick = { Biometric.authenticate(
                            activity = this@LoginActivity,
                            title = "Biometric Authentication",
                            subtitle = "Authenticate to proceed",
                            description = "Authentication is must",
                            negativeText = "Cancel",
                            onSuccess = { context.startActivity(Intent(context, MainActivity::class.java)) },
                            onError = { errorCode, errorString->

                                Toast.makeText(
                                    context,
                                    "Authentication error: $errorCode, $errorString",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        ) {
                            Toast.makeText(
                                context,
                                "Authentication failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                            border = BorderStroke(1.dp, colorResource(id = R.color.dark_blue))
                        ) {
                            Text(text = "Log In", color = colorResource(R.color.dark_blue),
                                fontFamily = FontFamily(Font(R.font.montserrat_bold))
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        //Sign up button
                        Button(onClick = { context.startActivity(Intent(context, MainActivity::class.java)) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                            border = BorderStroke(1.dp, colorResource(id = R.color.dark_blue))
                        ) {
                            Text(text = "Sign up", color = colorResource(R.color.dark_blue),
                                fontFamily = FontFamily(Font(R.font.montserrat_bold))
                            )
                        }

                        Text(text = "use as a guest", modifier = Modifier
                            .padding(top = 15.dp)
                            .clickable { context.startActivity(Intent(context, MainActivity::class.java)) },
                            color = Color.DarkGray, fontFamily = FontFamily(
                                Font(R.font.montserrat_bold)
                            )
                        )
                    }
                }
            }
        }
    }
}