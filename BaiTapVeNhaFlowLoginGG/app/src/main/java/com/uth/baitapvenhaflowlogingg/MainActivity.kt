package com.uth.baitapvenhaflowlogingg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var navController: androidx.navigation.NavHostController

    companion object { const val RC_SIGN_IN = 9001 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            SmartTasksApp(auth, googleSignInClient) { navController = it }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential).addOnCompleteListener { t ->
                    if (t.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            navController.navigate(
                                "home/${urlEncode(user.displayName)}/${urlEncode(user.email)}/${urlEncode(user.photoUrl.toString())}"
                            ) { popUpTo("login") { inclusive = true } }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

@Composable
fun SmartTasksApp(
    auth: FirebaseAuth,
    googleSignInClient: GoogleSignInClient,
    onNavControllerReady: (androidx.navigation.NavHostController) -> Unit
) {
    val navController = rememberNavController()
    onNavControllerReady(navController)
    val context = LocalContext.current as ComponentActivity

    // Nếu đã login trước đó → tự động navigate
    val lastAccount = GoogleSignIn.getLastSignedInAccount(context)
    if (lastAccount != null && auth.currentUser == null) {
        val credential = GoogleAuthProvider.getCredential(lastAccount.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate(
                        "home/${urlEncode(lastAccount.displayName)}/${urlEncode(lastAccount.email)}/${urlEncode(lastAccount.photoUrl.toString())}"
                    ) { popUpTo("login") { inclusive = true } }
                }
            }
    }

    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(googleSignInClient)
        }
        composable("home/{name}/{email}/{photoUrl}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")?.let { urlDecode(it) }
            val email = backStackEntry.arguments?.getString("email")?.let { urlDecode(it) }
            val photoUrl = backStackEntry.arguments?.getString("photoUrl")?.let { urlDecode(it) }
            ProfileScreen(googleSignInClient, navController, name, email, photoUrl)
        }
    }
}

fun urlEncode(value: String?): String {
    return URLEncoder.encode(value ?: "", StandardCharsets.UTF_8.toString()).replace("+", "%20")
}

fun urlDecode(value: String?): String {
    return Uri.decode(value ?: "")
}
