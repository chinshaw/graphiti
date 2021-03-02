package core

import graphql.schema.GraphQLScalarType

interface IColumnInfo {
    fun getColumnName(): String?
    fun getColumnType(): String?
    fun getGraphQLType(): GraphQLScalarType
}
