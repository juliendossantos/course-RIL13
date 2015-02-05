#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import web
import xml.etree.ElementTree as ET

class MyApplication(web.application):
    def run(self, port=666, *middleware):
        func = self.wsgifunc(*middleware)
        return web.httpserver.runsimple(func, ('0.0.0.0', port))

urls = (
    '/stagiaires', 'Stagiaires',
    '/stagiaires\/(\d)*', 'Stagiaires'
)

app = MyApplication(urls, globals())

class Stagiaires:
    def GET(self, id=0):
        tree = ET.parse('Stagiaires.xml')
        root = tree.getroot()
        if id > 0:
            for user in root:
                if str(user.attrib['id']) == id:
                    return str(user.attrib)
            return web.notfound("Aucun stagiaires ne corresponde a l'identifiant que vous avait spécifié")
        else :
            txt = '['
            for user in root:
                txt = txt + str(user.attrib)
            txt = txt + ']'
            return txt

if __name__ == '__main__':
    app.run()