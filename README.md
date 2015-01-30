# ear_training

A clojure library using overtone intended to train one's musical ear for intervals and chords. Currently only intervals are implemented.

## Usage

Start a REPL with `lein repl` and then load the library with `(use 'ear-training.core)`. Then, use the `tri` function, which stands for "Test Random Interval". This will play a random interval, and then wait for you to type it in and identify it. Use notation like `M2` for major second, `P1` for perfect unison etc. (to see all the intervals available look at the INTERVALS variable). You can restrict testing to a subset of intervals by invoking `(tri :from n)` where n is either a list of intervals or a number coresponding to a predefined list of intervals defined in the levels variable.

## Contributing

If you want to send me a pull request, be my guest. Document what you change, please!

## License

Copyright © 2015 Owen Lynch

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
