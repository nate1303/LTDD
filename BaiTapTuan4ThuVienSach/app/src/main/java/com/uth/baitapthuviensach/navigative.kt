import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
@Composable
fun AppBottomNavigation(selectedIndex: Int, onSelectIndex: (Int) -> Unit) {
    val items = listOf(
        BottomNavItem("Quản lý", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("DS Sách", Icons.Filled.List, Icons.Outlined.List),
        BottomNavItem("Sinh viên", Icons.Filled.Person, Icons.Outlined.Person)
    )
    NavigationBar(containerColor = Color.White) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedIndex == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = selectedIndex == index,
                onClick = { onSelectIndex(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF0D47A1),
                    selectedTextColor = Color(0xFF0D47A1),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
