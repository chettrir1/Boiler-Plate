package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.ModuleResponse
import com.iions.entity.ModulesEntity

object ModulesMapper {
    fun mapToLocal(category: List<ModuleResponse>): List<ModulesEntity> {
        return category.map {
            ModulesEntity(
                modulesName = it.name,
                modulesIcon = it.image
            )
        }
    }
}