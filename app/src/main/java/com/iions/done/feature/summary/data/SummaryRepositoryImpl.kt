package com.iions.done.feature.summary.data

import com.iions.done.utils.SchedulersFactory
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: SummaryRepository.Local,
    private val remoteRepository: SummaryRepository.Remote
) : SummaryRepository {
}
