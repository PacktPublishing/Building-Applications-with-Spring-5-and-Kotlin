package com.journaler.api.repository

import com.journaler.api.data.Todo
import org.springframework.data.repository.CrudRepository

/**
 * String is the type for ID we use.
 */
interface TodoRepository : CrudRepository<Todo, String>