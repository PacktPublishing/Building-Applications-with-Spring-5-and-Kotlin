package com.journaler.gateway

import org.springframework.session.data.redis.RedisFlushMode
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer

@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
class SessionConfiguration : AbstractHttpSessionApplicationInitializer()