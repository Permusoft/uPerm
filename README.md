μPerm is a block-structured, dynamically typed, dynamically scoped, interpreted programming language for computation in the mathematical realm of the symmetric groups. In particular, μPerm provides as a primitive type the permutation, along with its related group-theoretic operations of composition and inversion.

uperm/
   /doc 
   /bin
   /src
   /samples

~/doc		Contains the Language Reference Manual. Start reading here.

~/bin		Contains Java .jars for ANTLR (v2.x), the compiler-compiler tool suite used to build
                the lexer and parser, and uperm.jar, the uPerm language interpreter. Requires Java
                5.x. Add these .jar files to your classpath.

~/src:		Contains uPerm source code, including the grammar, uperm.g, and all java source
		files used to build ~/uperm/bin/uperm.jar.

~/samples	Contains various sample uPerm programs for testing. The most complex is our
		implementation of Dimino's algorithm under ~/samples/dimino

