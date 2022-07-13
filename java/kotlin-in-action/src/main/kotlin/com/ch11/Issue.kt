package com.ch11

data class Issue(
    val id: String,
    val project: String,
    val type: String,
    val priority: String,
    val description: String
)

class ImportantIssuePredicate(private val project: String): (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.isImportant()
    }

    private fun Issue.isImportant(): Boolean {
        return type == "Bug" && (priority == "Major" || priority == "Critical")
    }
}
