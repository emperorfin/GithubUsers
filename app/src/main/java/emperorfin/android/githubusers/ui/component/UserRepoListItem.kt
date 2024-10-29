package emperorfin.android.githubusers.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import emperorfin.android.githubusers.R
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import emperorfin.android.githubusers.ui.model.repo.RepoUiModel
import emperorfin.android.githubusers.ui.util.ColoredLanguage


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 29th October, 2024.
 */




@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserRepoListItem(
    modifier: Modifier,
    repo: RepoUiModel,
    onClicked: (repo: RepoUiModel) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClicked(repo) }
    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Image(
//                modifier = Modifier
//                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
//                    .size(24.dp),
//                painter = rememberImagePainter(
//                    data = repo.owner.avatarUrl,
//                    builder = {
//                        transformations(RoundedCornersTransformation(16f))
//                    }
//                ),
//                contentDescription = null,
//            )
//            Text(
//                modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
//                text = repo.owner.login,
//                style = MaterialTheme.typography.titleSmall,
//            )
//        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = repo.name,
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
        )
        if (repo.description.isNotBlank()) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp),
                text = repo.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                ),
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val language = ColoredLanguage.of(repo.language)

            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(vertical = 8.dp)
                    .size(12.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .padding(vertical = 8.dp),
                text = repo.stargazersCount.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            )

            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(vertical = 12.dp)
                    .size(8.dp),
                painter = painterResource(language.icon),
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .padding(vertical = 8.dp),
                text = language.title,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            )
        }
    }
}
