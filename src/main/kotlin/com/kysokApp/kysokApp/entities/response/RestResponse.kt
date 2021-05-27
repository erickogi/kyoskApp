package com.kysokApp.kysokApp.entities.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class RestResponse(private var body: Any?, status: HttpStatus) :
    ResponseEntity<Any>(body, status) {
    override fun getBody(): Any? {
        return body
    }
}