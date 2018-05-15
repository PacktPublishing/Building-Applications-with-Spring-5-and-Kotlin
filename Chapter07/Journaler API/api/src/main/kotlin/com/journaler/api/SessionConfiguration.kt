package com.journaler.api

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer

@EnableRedisHttpSession
class SessionConfiguration : AbstractHttpSessionApplicationInitializer()