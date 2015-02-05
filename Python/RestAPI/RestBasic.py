#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import web

class MyApplication(web.application):
    def run(self, port=666, *middleware):
        func = self.wsgifunc(*middleware)
        return web.httpserver.runsimple(func, ('0.0.0.0', port))

urls = (
    '/(.*)', 'hello'
)

app = MyApplication(urls, globals())

class hello:
    def GET(self, name):
        if not name:
            name = "world"
        return 'Salut, ' + name + ' !'

if __name__ == '__main__':
    app.run()