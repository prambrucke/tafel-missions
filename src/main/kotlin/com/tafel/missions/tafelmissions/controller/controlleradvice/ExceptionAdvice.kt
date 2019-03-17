package com.tafel.missions.tafelmissions.controller.controlleradvice

import com.tafel.missions.tafelmissions.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(NotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(notFoundException: NotFoundException): Any {
        return Problem(errorCode = HttpStatus.NOT_FOUND, message = notFoundException.localizedMessage)
    }

}

private data class Problem(
        val message:String,
        val errorCode:HttpStatus
)