package emperorfin.android.githubusers.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@Composable
fun ContentLoader(
    loading: Boolean,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loadingIndicator: @Composable () -> Unit,
    content: @Composable () -> Unit = {}
) {
    if (empty) {
        emptyContent()
    } else if (loading) {
        loadingIndicator()
    } else {

        Box {

            content()

        }
    }

}