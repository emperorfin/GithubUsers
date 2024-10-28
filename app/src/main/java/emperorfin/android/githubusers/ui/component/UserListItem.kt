package emperorfin.android.githubusers.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.palette.BitmapPalette
import emperorfin.android.githubusers.ui.model.user.UserUiModel


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@Composable
fun UserListItem(
    user: UserUiModel,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .clickable(
                onClick = {
                    onClick(user.id)
                }
            ),
        color = MaterialTheme.colorScheme.onBackground
    ) {

        ConstraintLayout {
            val (image, box, title) = createRefs()

            var palette by remember { mutableStateOf<Palette?>(null) }
            NetworkImage(
                networkUrl = user.avatarUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    },
                bitmapPalette = BitmapPalette {
                    palette = it
                }
            )

            Crossfade(
                targetState = palette,
                modifier = Modifier
                    .height(50.dp)
                    .constrainAs(box) {
                        top.linkTo(image.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            ) {

                Box(
                    modifier = Modifier
                        .background(Color(it?.darkVibrantSwatch?.rgb ?: 0))
                        .alpha(0.7f)
                        .fillMaxSize()
                )
            }

            Text(
                text = user.handle,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.85f)
                    .padding(horizontal = 8.dp)
                    .constrainAs(title) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.bottom)
                    }
            )
        }
    }
}