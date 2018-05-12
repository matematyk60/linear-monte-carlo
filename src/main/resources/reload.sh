#!/bin/bash

rm -rf parser
antlr4='java -jar /usr/local/lib/antlr-4.7.1-complete.jar'
${antlr4} Calculator.g4 -no-listener -visitor -o parser
rm -rf ../java/parser