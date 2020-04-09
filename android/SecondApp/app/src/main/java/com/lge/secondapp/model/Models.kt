package com.lge.secondapp.model

import com.google.gson.annotations.SerializedName
import java.util.*

// JSON => DTO
//     Gson(Google + JSON) - KVC
data class User(
    val login: String,
    val id: Int,
    val name: String?,
    val location: String?,
    val company: String?,
    val blog: String?,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date
)


/*
https://api.github.com/users/JakeWharton
{
  "login": "JakeWharton",
  "id": 66577,
  "avatar_url": "https://avatars0.githubusercontent.com/u/66577?v=4",
  "name": "Jake Wharton",
  "company": "Google, Inc.",
  "blog": "http://jakewharton.com",
  "location": "Pittsburgh, PA, USA",
  "created_at": "2009-03-24T16:09:53Z",
  "updated_at": "2020-04-07T13:27:20Z"
}
*/

data class Repo(
    val id: Int,
    val name: String,
    @field:SerializedName("full_name") val fullName: String,
    val private: Boolean,
    val owner: User,
    val description: String,
    @field:SerializedName("stargazers_count") val stargazersCount: Int,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date
)

data class RepoSearchResponse(
    @field:SerializedName("total_count") val totalCount: Int,
    @field:SerializedName("incomplete_results") val incompleteResults: Boolean,
    val items: List<Repo>
)

/*
{
  "total_count": 88738,
  "incomplete_results": false,
  "items": [
    {
      "id": 3432266,
      "name": "kotlin",
      "full_name": "JetBrains/kotlin",
      "private": false,
      "owner": {
        "login": "JetBrains",
        "id": 878437,
        "type": "Organization",
      },
      "html_url": "https://github.com/JetBrains/kotlin",
      "description": "The Kotlin Programming Language",
      "size": 684036,
      "stargazers_count": 31280,
      "watchers_count": 31280,
      "language": "Kotlin",
      "has_issues": false,
      "has_projects": false,
      "has_downloads": true,
      "has_wiki": false,
      "has_pages": true,
      "forks_count": 3808,
      "mirror_url": null,
      "archived": false,
      "disabled": false,
      "open_issues_count": 211,
      "license": null,
      "forks": 3808,
      "open_issues": 211,
      "watchers": 31280,
      "default_branch": "master",
      "score": 1.0
    }
  ]
}
*/









