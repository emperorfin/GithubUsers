package emperorfin.android.githubusers.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsHeight
import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.ui.theme.Purple40


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppBar() {
    TopAppBar(
        modifier = Modifier
            .height(70.dp),
        title = {
            Box {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopCenter),
                    text = stringResource(R.string.app_name),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40
        )
    )
}