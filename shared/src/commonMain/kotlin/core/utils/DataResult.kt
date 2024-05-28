package core.utils

sealed interface DataResult<Data,Error> {
    data class Success<Data,Error>(val data : Data) : DataResult<Data,Error>
    data class Error<Data,Error>(val error: Error) : DataResult<Data,Error>
}