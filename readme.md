# µPerm
*μPerm* is a block-structured, dynamically typed, dynamically scoped, interpreted programming language for computation in the mathematical realm of the symmetric groups. In particular, *μPerm* provides as a primitive type the permutation, along with its related group-theoretic operations of composition and inversion.

`~/doc`
Contains the Language Reference Manual.

`~/bin`
Contains Java .jars for [ANTLR](http://www.antlr.org) (µPerm uses [v2.7.7](http://www.antlr2.org)) and `uperm.jar`, the µPerm interpreter. Requires Java 5.x.

`~/src`
Contains *µPerm* source code, including the grammar, `uperm.g`, and all java source files used to build `~/bin/uperm.jar`.
</p>

`~/samples`
Contains various sample *µPerm* programs. The most complex is our implementation of [Dimino's algorithm](http://link.springer.com/chapter/10.1007%2F3-540-54955-2_22) under `~/samples/dimino`.
