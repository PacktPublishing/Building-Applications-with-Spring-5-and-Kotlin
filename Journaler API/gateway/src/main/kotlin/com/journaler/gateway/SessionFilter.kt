package com.journaler.gateway

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.stereotype.Component
import org.springframework.session.SessionRepository
import org.springframework.beans.factory.annotation.Autowired

@Component
class SessionFilter : ZuulFilter() {

    @Autowired
    private val repository: SessionRepository<*>? = null

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun run(): Any? {
        val context = RequestContext.getCurrentContext()
        val httpSession = context.request.session
        val session = repository?.getSession(httpSession.id)
        context.addZuulRequestHeader("Cookie", "SESSION=" + httpSession.id)
        return null
    }

    override fun filterType(): String {
        return "pre"
    }

    override fun filterOrder(): Int {
        return 0
    }

}