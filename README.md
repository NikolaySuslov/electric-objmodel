# Extensible object model and messaging semantics for Electric Clojure

Electric-objmodel is a tiny prototype-based object model that adds messaging to the Electric Clojure. Three object types and five methods are sufficient to bootstrap an extensible object model and messaging semantics, that are described entirely in terms of those same objects and messages.

## WIP  
This repository contains a partial implementations of Piumarta and Warth's [Open, extensible object models](https://www.piumarta.com/software/id-objmodel/) paper for [Electric Clojure](https://github.com/hyperfiddle/electric).

* [id-objmodel Racket's port](https://github.com/NikolaySuslov/electric-objmodel/blob/main/objapp.cljc) 
* [MetaObject](https://github.com/NikolaySuslov/electric-objmodel/blob/main/metaobject.cljc) implementation by Dustin Getz (core developer of [Electric Clojure](https://github.com/hyperfiddle/electric) and founder of [hyperfiddle](https://github.com/hyperfiddle)) under the [unlicense](https://github.com/NikolaySuslov/electric-objmodel/blob/main/z_license.txt). [Gist](https://gist.github.com/dustingetz/27db12af17acf8dba88c365c023f31b4).

### Original implementation

- [id-objmodel — simple dynamic dispatch for C](https://www.piumarta.com/software/id-objmodel/)

### Existed experimental implementations for other programming languages

- [Racket](https://gist.github.com/tonyg/8661807)
- [JavaScript](https://gist.github.com/9diov/56be6e545ee59051bdfd64407f9f57cc)
- [Ruby](https://gist.github.com/davidbalbert/c588b72b7fd2d60ea1f4)

### Contributing
Code is published under the MIT license and partly unlicense

