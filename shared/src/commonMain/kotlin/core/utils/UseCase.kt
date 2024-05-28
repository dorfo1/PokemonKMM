package core.utils

import kotlinx.coroutines.flow.Flow

interface UseCase<Params,Data,Error> : (Params) -> Flow<DataResult<Data, Error>>