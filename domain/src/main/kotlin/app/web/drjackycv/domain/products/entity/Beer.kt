package app.web.drjackycv.domain.products.entity

data class Beer(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val url: String
)