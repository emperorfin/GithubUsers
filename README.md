# GitHubUsers

GitHubUsers is a native Android app project that uses [GitHub REST API](https://api.github.com/) to
allow the app user search for GitHub users and view the details of each user and their repos. When 
the app user taps on a GitHub user's repo from the user details screen, the web page at the repo 
URL would open and display in a web browser.

When the app user views the details of a GitHub user, the app user could scroll through the list of 
previously visited GitHub users from the GitHub Users list screen if no internet connectivity. But 
an internet connectivity would be required in order to re-view the details of the previously viewed 
GitHub users.

## Project Tech-stack and Characteristics

* Android SDK
* Kotlin
* Jetpack Compose
* Material Design 3 Components
* ViewModel
* Kotlin Coroutine
* StateFlow
* MVVM Design Pattern
* Repository Pattern
* Navigation
* Offline Storage (via Room)
* Retrofit
* [GitHub REST API](https://api.github.com/)
* Dependency Injection (via Hilt)

## Getting Started

### Command-line

Clone the project via the command-line.

### Project Setup

Add github.apikey="<GITHUB_API_KEY>" in local.properties which is at the root of this project.
<GITHUB_API_KEY> is the GitHub REST API key. To get a GitHub API key, see [Authenticating with a personal access token](https://docs.github.com/en/rest/authentication/authenticating-to-the-rest-api?apiVersion=2022-11-28#authenticating-with-a-personal-access-token).