package com.example.agoravideocall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.KeyEventDispatcher.Component
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agoravideocall.ui.theme.AgoraUIKitTheme

const val APP_ID = "f37b6566b8054a1ab9d3c0970ad70f8d"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AgoraUIKitTheme{
                Surface(color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(16.dp)
                    ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "room_screen"){
                        composable(route = "room_screen"){
                            RoomScreen(onNavigate = navController :: navigate)
                        }
                        composable(route ="video_screen/{roomName}",
                            arguments = listOf(
                                navArgument(name = "roomName"){
                                    type = NavType.StringType
                                }
                            ))
                        {
                            val roomName = it.arguments?.getString("roomName")?:  return@composable
                            VideoScreen(
                                    roomName = roomName,
                                    onNavigateUp = navController:: navigateUp
                            )
                        }
                    }
                }
            }
        }
    }
}


