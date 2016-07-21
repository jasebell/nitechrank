# NiTechrank

The NiTechrank index was published on Twitter (@nitechrank) during May-July 2016 as a way to see fluctuations in the software development sector in Northern Ireland.

[The original NiTechrank blogpost](https://dataissexy.wordpress.com//?s=nitechrank&search=Go)

Originally the rank was culled from a really grubby shell script.

[Data Science for the confused. NI software jobs.](https://dataissexy.wordpress.com/2015/04/06/datascience-for-the-confused-ni-software-jobs-unix-excel/)

There'll be no further development done on this repo, it's here for anyone who wants to have a look at quick, dirty and embarassing code that was slung together over a cup of tea at six in the morning.

If you are interested in the data from NiTechrank then have a look in the `data` directory, there is a csv file of all the readings from day one.

## Installation

This index pull system is writtenin Clojure and you will need [Leiningen](http://leiningen.org/) installed to build it. Pull the repo and run `lein uberjar` to build.

## Usage

Running the jar file will then scrape the data from www.nijobs.com and then write the updated csv file.

    $ java -jar nitechrank-0.1.0-standalone.jar 


## License

Copyright © 2016 Jason Bell

Distributed under the DWYWWI License (Do What You Want With It) either version 1.0 or (at
your option) any later version.
