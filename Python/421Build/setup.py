#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
from cx_Freeze import setup, Executable

setup(
    name = '421',
    version = '1',
    description = 'mon 421',
    executables = [Executable("421.py")]
)