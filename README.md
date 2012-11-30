<h1><i>µPerm</i></h1>
<p><i>μPerm</i> is a block-structured, dynamically typed, dynamically scoped, interpreted programming language for computation in the mathematical realm of the symmetric groups. In particular, <i>μPerm</i> provides as a primitive type the permutation, along with its related group-theoretic operations of composition and inversion.</p>

<p><code>~/doc</code><br />
Contains the Language Reference Manual. Start reading here.
</p>

<p><code>~/bin</code><br />
Contains Java .jars for <a href="http://www.antlr.org">ANTLR</a> (µPerm requires <a href="http://www.antlr2.org">v2.7.7</a>), the compiler-compiler tool suite used to build the lexer and parser, and <code>uperm.jar</code>, the <i>µPerm</i> language interpreter. Requires Java 5.x. Add these .jar files to your classpath.
</p>

<p><code>~/src</code><br />
Contains <i>µPerm</i> source code, including the grammar, <code>uperm.g</code>, and all java source files used to build <code>~/bin/uperm.jar</code>.
</p>

<p><code>~/samples</code><br />
Contains various sample <i>µPerm</i> programs for testing. The most complex is our implementation of <a href="http://link.springer.com/chapter/10.1007%2F3-540-54955-2_22">Dimino's algorithm</a> under <code>~/samples/dimino</code>.
</p>
