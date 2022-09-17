package app.web.drjackycv.presentation.base.navigation

interface MyNavigationDestination {
    /**
     * Defines a specific path to your composable.
     * You can think of it as an implicit deep link that leads to a specific destination.
     */
    val route: String

    /**
     * Defines a specific destination ID.
     * This is needed when using nested graphs via the navigation DLS, to differentiate a specific
     * destination's route from the route of the entire nested graph it belongs to.
     */
    val destination: String
}