package app.web.drjackycv.core.testing.data

import app.web.drjackycv.core.domain.products.entity.Beer

object TestData {

    val testBeer = Beer(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        url = "https://rickandmortyapi.com/api/character/1",
    )

    val testBeer2 = Beer(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        url = "https://rickandmortyapi.com/api/character/2",
    )

    val testBeer3 = Beer(
        id = 3,
        name = "Summer Smith",
        status = "Alive",
        species = "Human",
        gender = "Female",
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        url = "https://rickandmortyapi.com/api/character/3",
    )

    @Suppress("LongParameterList")
    fun createBeer(
        id: Int = 1,
        name: String = "Test Character",
        status: String = "Alive",
        species: String = "Human",
        gender: String = "Male",
        image: String = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg",
        url: String = "https://rickandmortyapi.com/api/character/$id",
    ) = Beer(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        url = url,
    )
}
