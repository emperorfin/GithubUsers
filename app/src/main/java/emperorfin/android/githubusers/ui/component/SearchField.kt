package emperorfin.android.githubusers.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.ui.theme.GithubUsersTheme


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchInput: String?,
    onSearchInputChanged: (searchInput: String) -> Unit,
    onSearchClicked: (searchInput: String) -> Unit,
) {

    val searchInputOrEmptyString: String = searchInput ?: ""

    val trailingIconImageVector: ImageVector = Icons.Default.Search
    val trailingIconContentDescription: String = stringResource(
        id = R.string.content_description_search_leading_icon
    )

    val singleLine = true

    val keyboardOptionsKeyboardType: KeyboardType = KeyboardType.Text
    val keyboardOptionsImeAction: ImeAction = ImeAction.Search

    val label: @Composable () -> Unit = {
        Text(
            text = stringResource(id = R.string.label_search_users)
        )
    }

    val trailingIcon: @Composable () -> Unit = {
        Icon(
            imageVector = trailingIconImageVector,
            contentDescription = trailingIconContentDescription,
            modifier = Modifier.clickable {
                onSearchClicked(searchInputOrEmptyString)
            }
        )
    }

    val keyboardOptions = KeyboardOptions(
        keyboardType = keyboardOptionsKeyboardType,
        imeAction = keyboardOptionsImeAction,
    )

    val keyboardActions = KeyboardActions(
        onSearch = {
            onSearchClicked(searchInputOrEmptyString)
        }
    )

    TextField(
        modifier = modifier,
        value = searchInputOrEmptyString,
        onValueChange = { changedSearchInput ->
            onSearchInputChanged(changedSearchInput)
        },
        label = label,
        singleLine = singleLine,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )

}

@Composable
@Preview(showBackground = true)
private fun SearchFieldPreview() {
    GithubUsersTheme {
        SearchField(
            searchInput = "search",
            onSearchInputChanged = {},
            onSearchClicked = {}
        )
    }
}