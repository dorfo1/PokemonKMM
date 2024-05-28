package core.error

sealed interface CustomError {
    data object NetworkError: CustomError
    data object Generic: CustomError
}